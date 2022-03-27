package com.zd.study.java.JvmTest;

public class TestStr {
    public static void main(String[] args) {
        strTest6();
    }

    /**
     * intern方法 1.6
     * 调用字符串对象的intern方法，会将该字符串对象尝试放入到串池中
     * 如果串池中没有该字符串对象，会将该字符串对象复制一份，再放入到串池中
     * 如果有该字符串对象，则放入失败
     * 无论放入是否成功，都会返回串池中的字符串对象
     * 注意：此时无论调用intern方法成功与否，串池中的字符串对象和堆内存中的字符串对象都不是同一个对象
     *
     * intern方法 1.8
     * 调用字符串对象的intern方法，会将该字符串对象尝试放入到串池中
     * 如果串池中没有该字符串对象，则放入成功
     * 如果有该字符串对象，则放入失败
     * 无论放入是否成功，都会返回串池中的字符串对象
     * 注意：此时如果调用intern方法成功，堆内存与串池中的字符串对象是同一个对象；如果失败，则不是同一个对象
     */

    public static void test1(){
        //"a" "b" 被放入串池中，str则存在于堆内存之中
        String str = new String("a") + new String("b");
//             调用str的intern方法，这时串池中没有"ab"，则会将该字符串对象放入到串池中，此时堆内存与串池中的"ab"是同一个对象
        String st2 = str.intern();
//             给str3赋值，因为此时串池中已有"ab"，则直接将串池中的内容返回
        String str3 = "ab";
//             因为堆内存与串池中的"ab"是同一个对象，所以以下两条语句打印的都为true
        System.out.println(str == st2);// true
        System.out.println(str == str3);// true
    }
    public static void test2(){
//        此处创建字符串对象"ab"，因为串池中还没有"ab"，所以将其放入串池中
        String str3 = "ab";
//         "a" "b" 被放入串池中，str则存在于堆内存之中
        String str = new String("a") + new String("b");
//         此时因为在创建str3时，"ab"已存在与串池中，所以放入失败，但是会返回串池中的"ab"
        String str2 = str.intern();
//         false
        System.out.println(str == str2);
//         false
        System.out.println(str == str3);
//         true
        System.out.println(str2 == str3);
    }

    /**
     * 首先在栈中存放变量引用 str1，然后通过符号引用去常量池中找是否有，没有，则将“abc”存储在常量池中，
     * 然后将str指向常量池中的“abc”，当创建str2时,去常量池中发现已经有了“abc"就将str2引用指向常量池中的”abc",
     *所以str1 == str2 指向的是同一个内存地址
     */
    public static void strTest3(){
        String str1 = "abcd";
        String str2 = "abcd";
        System.out.print(str1 == str2);
    }

    /**
     * 首先在栈中存放变量引用 str1,在堆中创建String对象“ancd",
     * 同理str2指向的也是new出来的一个新对象， str1和str2分别指向两个不同的对象，所以返回false
     */
    public static void strTest4(){
        String str1 = new String("abcd");
        String str2 = new String("abcd");
        System.out.print(str1 == str2);
    }

    /**
     * 对于字符串常量的 + 号连接，在程序编译期，JVM就会将其优化为 + 号连接后的值。所以在编译期其字符串常量的值就确定了
     */
    public static void strTest5(){
//        String a = "a1";
//        String b = "a" + 1;
//        System.out.println((a == b));//true

//        String a = "atrue";
//        String b = "a" + "true";
//        System.out.println((a == b));//true

        String a = "a3.4";
        String b = "a" + 3.4;
        System.out.println((a == b));//true

    }

    /**
     *引用类型在编译器是无法确定的，在程序的运行期动态分配并创建新的地址存储对象
     * str3实际的执行过程是：
     * String str3 = (new StringBuilder()).append(str1).append("b").toString();
     * 又在堆上创建了一个对象， str2指向的是常量池中的对象，因此str2跟 str3指向的内存地址是不一样的
     */
    public static void strTest6(){
        String str1 = "a";
        String str2 = "ab";
        String str3 = str1 + "b";
        System.out.print(str2 == str3);//false
    }

    /**
     * i1 和 i2 使用 new 关键字，每 new 一次都会在堆上创建一个对象，所以 i1 == i2 为 false
     *i3  和 i4声明为Integer 赋值过程中有一个装箱动作，在Integer的IntegerCache 中缓存了-127到128的数据，
     * 如果在这个区间直接返回常量池中的内容，否则new一个Integer对象，所以 i3==i4 返回true,i5==i6返回false
     *
     */
    public static void IntegerTest1(){
        Integer i1 = new Integer(66);
        Integer i2 = new Integer(66);
        Integer i3 = 66;
        Integer i4 = 66;
        Integer i5 = 150;
        Integer i6 = 150;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);

    }

    /**
     * 由于 i1 和 i2 是 Integer 对象，是不能使用+运算符的。首先 i1 和 i2 进行自动拆箱操作，
     * 拆箱成int后再进行数值加法运算。i3 也是拆箱后再与之比较数值是否相等的。
     * 所以 i3 == i1+i2 其实是比较的 int 型数值是否相等，所以为true
     */
    public static void IntegerTest2(){
        Integer i1 = new Integer(4);
        Integer i2 = new Integer(6);
        Integer i3 = new Integer(10);
        System.out.print(i3 == i1+i2);
    }


}
