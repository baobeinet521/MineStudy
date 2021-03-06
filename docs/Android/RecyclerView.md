#RecycleView



##1.RecyclerView的多级缓存机制,每一级缓存具体作用是什么,分别在什么场景下会用到哪些缓存

- 一级缓存：mAttachedScrap 和 mChangedScrap
- 二级缓存：mCachedViews
- 三级缓存：ViewCacheExtension
- 四级缓存：RecycledViewPool

先从 1 级找，然后 2 级...然后4 级，找不到 create ViewHolder

 
##  详细说明
[深入理解 RecyclerView 的缓存机制](https://juejin.cn/post/6844904146684870669)



在创建 ViewHolder 之前，RecyclerView 会先从缓存中尝试获取是否有符合要求的 ViewHolder，详见 Recycler#tryGetViewHolderForPositionByDeadline 方法



- 第一次，尝试从 mChangedScrap 中获取。

    只有在 mState.isPreLayout() 为 true 时，也就是预布局阶段，才会做这次尝试。
   「预布局」的概念会在介绍。




- 第二次，getScrapOrHiddenOrCachedHolderForPosition() 获得 ViewHolder。

  尝试从 1. mAttachedScrap 2.mHiddenViews 3.mCachedViews 中查找 ViewHolder

  其中 mAttachedScrap 和 mCachedViews 都是 Recycler 的成员变量
  如果成功获得 ViewHolder 则检验其有效性，

  若检验失败则将其回收到 RecyclerViewPool 中
  检验成功可以直接使用



- 第三次，如果给 Adapter 设置了 stableId，调用 getScrapOrCachedViewForId 尝试获取 ViewHolder。

  跟第二次的区别在于，之前是根据 position 查找，现在是根据 id 查找




- 第四次，mViewCacheExtension 不为空的话，则调用 ViewCacheExtension#getViewForPositionAndType 方法尝试获取 View



> 注：ViewCacheExtension 是由开发者设置的，默认情况下为空，一般我们也不会设置。这层缓存大部分情况下可以忽略。




- 第五次。尝试从 RecyclerViewPool 中获取，相比较于 mCachedViews，从 mRecyclerPool 中成功获取 
   ViewHolder 对象后并没有做合法性和 item 位置校验，只检验 viewType 是否一致。

  从 RecyclerViewPool 中取出来的 ViewHolder 需要重新执行 bind 才能使用。


如果上面五次尝试都失败了，调用 RecyclerView.Adapter#createViewHolder 创建一个新的 ViewHolder
最后根据 ViewHolder 的状态，确定是否需要调用 bindViewHolder 进行数据绑定。


![RcyclerView缓存执行说明图](../img/recyclerview/cacheview.png)


####先来温习一下RecyclerView的滚动和回收机制：
RecyclerView之所以能滚动，就是因为它在监听到手指滑动之后，不断地更新Item的位置，也就是反复layout子View了，这部分工作由LayoutManager负责。

LayoutManager在layout子View之前，会先把RecyclerView的每个子View所对应的ViewHolder都放到mAttachedScrap中，然后根据当前滑动距离，筛选出哪些Item需要layout。获取子View对象，会通过getViewForPosition方法来获取。这个方法就是题目中说的那样：先从mAttachedScrap中找，再......

Item布局完成之后，会对刚刚没有再次布局的Item进行缓存（回收），这个缓存分两种：

- 取出（即将重用）时无须重新绑定数据（即不用执行onBindViewHolder方法）。这种缓存只适用于特定position的Item（名花有主）；
- 取出后会回调onBindViewHolder方法，好让对应Item的内容能正确显示。这种缓存适用所有同类型的Item（云英未嫁）；

   第一种缓存，由Recycler.mCachedViews来保管，第二种放在RecycledViewPool中。

如果回收的Item它的状态（包括：INVALID、REMOVED、UPDATE、POSITION_UNKNOWN）没有变更，就会放到mCachedViews中，否则扔RecycledViewPool里。

除了滚动过程中，会对Item进行回收和重新布局，还有一种就是，当Adapter数据有更新时：

- Inserted：如果刚好插入在屏幕可见范围内，会从RecycledViewPool中找一个相同类型的ViewHolder（找不到就create）来重新绑定数据并layout；

- Removed：会把对应ViewHolder扔到mAttachedScrap中并播放动画，动画播放完毕后移到RecycledViewPool里；

- Changed：这种情况并不是大家所认为的：直接将这个ViewHolder传到Adapter的onBindViewHolder中重新绑定数据。而是先把旧的ViewHolder扔mChangedScrap中，然后像Inserted那样从RecycledViewPool中找一个相同类型的ViewHolder来重新绑定数据。旧ViewHolder对象用来播放动画，动画播完，同样会移到RecycledViewPool里；

**注意**：如果是使用notifyDataSetChanged方法来通知更新的话，那么所有Item都会直接扔RecycledViewPool中，然后逐个重新绑定数据的。

当然了，上面说的这几种情况，不包括瞎写自定义的LayoutManager，因为在自定义的LayoutManger中，怎么去管理缓存，完全出于个人喜好。

####这几个存放缓存的集合，各自的作用以及使用场景？

- mAttachedScrap：LayoutManager每次layout子View之前，那些已经添加到RecyclerView中的Item以及被删除的Item的临时存放地。使用场景就是RecyclerView滚动时、还有在可见范围内删除Item后用notifyItemRemoved方法通知更新时；

- mChangedScrap：作用：存放可见范围内有更新的Item。使用场景：可见范围内的Item有更新，并且使用notifyItemChanged方法通知更新时；就是临时缓存局部更新，用于播放动画，动画播放完viewholder还是会给 recyclerpool

- mCachedViews：作用：存放滚动过程中没有被重新使用且状态无变化的那些旧Item。场景：滚动，prefetch；

    **prefetch**机制就是RecyclerView在滚动和惯性滚动的时候，借助Handler来事先从RecycledViewPool中取出即将要显示的Item，随即扔到mCachedViews中，这样的话，当layout到这个Item时，就能直接拿来用而不用绑定数据了。

- RecycledViewPool：作用：缓存Item的最终站，用于保存那些Removed、Changed、以及mCachedViews满了之后更旧的Item。场景：Item被移除、Item有更新、滚动过程



##2、RecyclerView的滑动回收复用机制



