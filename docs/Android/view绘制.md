#view绘制
##view绘制的主要三个方法：
 - measure：为测量宽高过程，如果是ViewGroup还要在onMeasure中对所有子View进行measure操作。
- layout：用于摆放View在ViewGroup中的位置，如果是ViewGroup要在onLayout方法中对所有子View进行layout操作。
- draw：往View上绘制图像。
## 2.MeasureSpec是什么
Measure是View绘制三个过程中的第一步，提到Measure就不得不提MeasureSpac它是一个32位int类型数值，高两
###内部也包含三种测量模式 specMode三种模式 ：

- UNSPECIFIED ：父布局不会对子View做任何限制，要多大给多大例，如我们常用的ScrollView就是这种测量模式。


- EXACTLY ：精确数值，比如使用了match_parent或者xxxdp，表示父布局已经决定了子View的大小，通常在这种情况下View的尺寸就是SpacSize


- AT_MOST ：自适应，对应wrap_content子View可以根据内容设置自己的大小，但前提是不能超出父ViewGroup的宽高。
注意点：
在我们自定义View的过程中都会在onMeasure中进行宽高的测量，这个方法会从父布局中接收两个参数widthMeasureSpac和heightMeasureSpac，所以子布局的宽高大小需要受限于父布局。

LayoutParams
在刚接触Android的时候经常有一个疑问，为什么View设置自己的宽高，还要创建一个xxx.LayoutParams？前面也提到了，子View的宽高是要受限于父布局的，所以不能通过setWidth或者setHeight直接设置宽高的，另外 LayoutParams的作用不仅如此，比如一个View的父布局是RelativeLayout，可以通过设置RelativeLayout.LayoutParams的above，below等属性来调整在父布局中的位置。位SpacMode代表测量模式，低30位SpacSize代表测量尺寸，是View的内部类


##3.子View创建MeasureSpec创建规则是什么
![子View创建MeasureSpec创建规则](..\img\view\child_view_creat_measurespec_rule1.png)

##4.自定义View wrap_content不起作用的原因
[自定义View wrap_content不起作用的原因](https://blog.csdn.net/qq_37136511/article/details/78740862)

` 

    public static int getDefaultSize(int size, int measureSpec) {  

      //参数说明：
      // 第一个参数size：提供的默认大小
      // 第二个参数：宽/高的测量规格（含模式 & 测量大小）

       //设置默认大小
      int result = size; 

      //获取宽/高测量规格的模式 & 测量大小
      int specMode = MeasureSpec.getMode(measureSpec);  
      int specSize = MeasureSpec.getSize(measureSpec);  

      switch (specMode) {  
        // 模式为UNSPECIFIED时，使用提供的默认大小
        // 即第一个参数：size 
        case MeasureSpec.UNSPECIFIED:  
            result = size;  
            break;  
        // 模式为AT_MOST,EXACTLY时，使用View测量后的宽/高值
        // 即measureSpec中的specSize
        case MeasureSpec.AT_MOST:  
        case MeasureSpec.EXACTLY:  
            result = specSize;  
            break;  
       }  

      //返回View的宽/高值
      return result;  
    }
`

从上面发现：

在getDefaultSize（）的默认实现中，当View的测量模式是AT_MOST或EXACTLY时，View的大小都会被设置成子View MeasureSpec的specSize。

因为AT_MOST对应wrap _ content；EXACTLY对应match _ parent，所以，默认情况下，wrap _ content和match _ parent是具有相同的效果的。


由于在getDefaultSize（）的默认实现中，当View被设置成wrap _ content和match _ parent时，View的大小都会被设置成子View MeasureSpec的specSize。
所以，这个问题的关键在于**子View MeasureSpec的specSize的值是多少**


我们知道，子View的MeasureSpec值是根据子View的布局参数（LayoutParams）和父容器的MeasureSpec值计算得来，具体计算逻辑封装在getChildMeasureSpec()里。

接下来，我们看**生成子View MeasureSpec的方法:getChildMeasureSpec()的源码**分析：

//作用：
/ 根据父视图的MeasureSpec & 布局参数LayoutParams，计算单个子View的MeasureSpec
//即子view的确切大小由两方面共同决定：父view的MeasureSpec 和 子view的LayoutParams属性 


    public static int getChildMeasureSpec(int spec, int padding, int childDimension) {  

    //参数说明
    * @param spec 父view的详细测量值(MeasureSpec) 
    * @param padding view当前尺寸的的内边距和外边距(padding,margin) 
    * @param childDimension 子视图的布局参数（宽/高）

    //父view的测量模式
    int specMode = MeasureSpec.getMode(spec);     

    //父view的大小
    int specSize = MeasureSpec.getSize(spec);     

    //通过父view计算出的子view = 父大小-边距（父要求的大小，但子view不一定用这个值）   
    int size = Math.max(0, specSize - padding);  

    //子view想要的实际大小和模式（需要计算）  
    int resultSize = 0;  
    int resultMode = 0;  

    //通过父view的MeasureSpec和子view的LayoutParams确定子view的大小  


    // 当父view的模式为EXACITY时，父view强加给子view确切的值
    //一般是父view设置为match_parent或者固定值的ViewGroup 
    switch (specMode) {  
    case MeasureSpec.EXACTLY:  
        // 当子view的LayoutParams>0，即有确切的值  
        if (childDimension >= 0) {  
            //子view大小为子自身所赋的值，模式大小为EXACTLY  
            resultSize = childDimension;  
            resultMode = MeasureSpec.EXACTLY;  

        // 当子view的LayoutParams为MATCH_PARENT时(-1)  
        } else if (childDimension == LayoutParams.MATCH_PARENT) {  
            //子view大小为父view大小，模式为EXACTLY  
            resultSize = size;  
            resultMode = MeasureSpec.EXACTLY;  

        // 当子view的LayoutParams为WRAP_CONTENT时(-2)      
        } else if (childDimension == LayoutParams.WRAP_CONTENT) {  
            //子view决定自己的大小，但最大不能超过父view，模式为AT_MOST  
            resultSize = size;  
            resultMode = MeasureSpec.AT_MOST;  
        }  
        break;  

    // 当父view的模式为AT_MOST时，父view强加给子view一个最大的值。（一般是父view设置为wrap_content）  
    case MeasureSpec.AT_MOST:  
        // 道理同上  
        if (childDimension >= 0) {  
            resultSize = childDimension;  
            resultMode = MeasureSpec.EXACTLY;  
        } else if (childDimension == LayoutParams.MATCH_PARENT) {  
            resultSize = size;  
            resultMode = MeasureSpec.AT_MOST;  
        } else if (childDimension == LayoutParams.WRAP_CONTENT) {  
            resultSize = size;  
            resultMode = MeasureSpec.AT_MOST;  
        }  
        break;  

    // 当父view的模式为UNSPECIFIED时，父容器不对view有任何限制，要多大给多大
    // 多见于ListView、GridView  
    case MeasureSpec.UNSPECIFIED:  
        if (childDimension >= 0) {  
            // 子view大小为子自身所赋的值  
            resultSize = childDimension;  
            resultMode = MeasureSpec.EXACTLY;  
        } else if (childDimension == LayoutParams.MATCH_PARENT) {  
            // 因为父view为UNSPECIFIED，所以MATCH_PARENT的话子类大小为0  
            resultSize = 0;  
            resultMode = MeasureSpec.UNSPECIFIED;  
        } else if (childDimension == LayoutParams.WRAP_CONTENT) {  
            // 因为父view为UNSPECIFIED，所以WRAP_CONTENT的话子类大小为0  
            resultSize = 0;  
            resultMode = MeasureSpec.UNSPECIFIED;  
        }  
        break;  
    }  
    return MeasureSpec.makeMeasureSpec(resultSize, resultMode);  


 ###问题总结

在onMeasure()中的getDefaultSize（）的默认实现中，当View的测量模式是AT_MOST或EXACTLY时，View的大小都会被设置成子View MeasureSpec的specSize。


因为AT_MOST对应wrap_content；EXACTLY对应match_parent，所以，默认情况下，wrap_content和match_parent是具有相同的效果的。


因为在计算子View MeasureSpec的getChildMeasureSpec()中，子View MeasureSpec在属性被设置为wrap_content或match_parent情况下，子View MeasureSpec的specSize被设置成parenSize = 父容器当前剩余空间大小


所以：wrap_content起到了和match_parent相同的作用：等于父容器当前剩余空间大小



