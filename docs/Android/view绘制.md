#view绘制
###view绘制的主要三个方法：
 - measure：为测量宽高过程，如果是ViewGroup还要在onMeasure中对所有子View进行measure操作。
- layout：用于摆放View在ViewGroup中的位置，如果是ViewGroup要在onLayout方法中对所有子View进行layout操作。
- draw：往View上绘制图像。


###specMode三种模式

- UNSPECIFIED ：父布局不会对子View做任何限制，要多大给多大例，如我们常用的ScrollView就是这种测量模式。


- EXACTLY ：精确数值，比如使用了match_parent或者xxxdp，表示父布局已经决定了子View的大小，通常在这种情况下View的尺寸就是SpacSize


- AT_MOST ：自适应，对应wrap_content子View可以根据内容设置自己的大小，但前提是不能超出父ViewGroup的宽高。