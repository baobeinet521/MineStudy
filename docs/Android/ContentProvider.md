##1.什么是ContentProvider及其使用

ContentProvider是Android4大组件之一，其底层通过Binder进行数据共享。如果我们要对第三方应用提供数据，可以考虑使用ContentProvider实现。

android中ContenProvider借助ContentResolver通过Uri与其他的ContentProvider进行匹配通信

##2.ContentProvider,ContentResolver,ContentObserver之间的关系

-  ContentProvider：管理数据，提供数据的增删改查操作，数据源可以是数据库、文件、XML、网络等，ContentProvider为这些数据的访问提供了统一的接口，可以用来做进程间数据共享。
-   ContentResolver：ContentResolver可以不同URI操作不同的ContentProvider中的数据，外部进程可以通过ContentResolver与ContentProvider进行交互。
- ContentObserver：观察ContentProvider中的数据变化，并将变化通知给外界。

##3.ContentProvider的实现原理

需要注意的是除了onCreate()其他的方法都运行在binder线程池

当ContentProvider所在的进程启动的时候，ContentProvider会同时启动并被发布到AMS中，需要注意的是ContentProvider的onCreate方法会先于Application的OnCreate调用。




##4.ContentProvider的优点

优点：透明地提供内容
使用 ContentProvider 允许应用透明地将数据开放给其它应用，无论底层数据采用何种实现方式（网络、内存、文件或数据库），外界对于数据的访问方式都是统一的 & 固定的


##5.Uri 是什么

  URI为系统中的每一个资源赋予一个名字，比方说通话记录。每一个ContentProvider都拥有一个公共的URI，用于表示ContentProvider所提供的数据。 Android所提供的ContentProvider都位于android.provider包中， 可以将URI分为A、B、C、D 4个部分来理解。

如对于content://com.wang.provider.myprovider/tablename/id：

- a、标准前缀——content://，用来说明一个Content Provider控制这些数据；
- b、URI的标识——com.wang.provider.myprovider，用于唯一标识这个ContentProvider，外部调用者可以根据这个标识来找到它。对于第三方应用程序，为了保证URI标识的唯一性，它必须是一个完整的、小写的类名。这个标识在元素的authorities属性中说明，一般是定义该ContentProvider的包.类的名称；
- c、路径——tablename，通俗的讲就是你要操作的数据库中表的名字，或者你也可以自己定义，记得在使用的时候保持一致就可以了；
- d、记录ID——id，如果URI中包含表示需要获取的记录的ID，则返回该id对应的数据，如果没有ID，就表示返回全部；
  

对于第三部分路径（path）做进一步的解释，用来表示要操作的数据，构建时应根据实际项目需求而定。如：

- a、操作tablename表中id为11的记录，构建路径：/tablename/11；
- b、操作tablename表中id为11的记录的name字段：tablename/11/name；
- c、操作tablename表中的所有记录：/tablename；
- d、操作来自文件、xml或网络等其他存储方式的数据，如要操作xml文件中tablename节点下name字段：/ tablename/name；
- e、若需要将一个字符串转换成Uri，可以使用Uri类中的parse()方法，如：
Uri uri = Uri.parse("content://com.wang.provider.myprovider/tablename")；


#总结:


- 如何通过ContentProvider查询数据
通过ContentResolver 进行uri匹配


- 如何实现自己的ContentProvider
继承ContentProvider,实现对应的方法。在manifest中声明


- ContentResolver如何返回Cursor对象
在跨进程的情况下返回的是CursorToBulkCursorAdaptor对象，其实质是借助Binder的跨进程传输能力，在ContentProvider进程中序列化，在调用程序中反序列化
