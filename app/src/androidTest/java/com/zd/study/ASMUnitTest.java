//package com.zd.study;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.lang.reflect.Method;
//
//public class ASMUnitTest {
//    @Test
//    public void test(){
//        //1.获得待插桩的字节码数据
//        FileInputStream fis = new FileInputStream("../../../main/java/com/zd/study/InjectTest.class");
//        //2.执行插桩 修改class数据
//        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//
//        ClassReader cr = new ClassReader(fis) {};
//
//        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM7, classWriter) {
//            @Override//该方法能拿到class文件里的方法名
//            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//                //System.out.println(name);
//                MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
//                return new MyMethodVisitor(Opcodes.ASM7, methodVisitor, access, name, descriptor);
//
//            }
//        };
//        cr.accept(classVisitor, 0);
//
//
//        //3.输出结果
//        byte[] bytes = classWriter.toByteArray();
//        FileOutputStream fos = new FileOutputStream("C:\\...\\InjectTest1.class");//输出文件
//        fos.write(bytes);
//        fos.close();
//    }
//
//    static class MyMethodVisitor extends AdviceAdapter {
//        boolean isInject = false;//用于在指定的注解中插入代码
//
//        protected MyMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
//            super(api, methodVisitor, access, name, descriptor);
//        }
//
//        @Override//能遍历class里的注解，可根据注解名称来指定插桩
//        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {//根据注解来指定插桩
//            System.out.println(descriptor);
//            if ("Lcom/.../ASMTest;".equals(descriptor)){
//                isInject = true;
//            }
//            return super.visitAnnotation(descriptor, visible);
//        }
//
//        @Override//当进入方法时
//        protected void onMethodEnter() {
//            super.onMethodEnter();
//            if (!isInject) return;
//            //long L= System.currentTimeMills();//正常在java编写
//            //使用字节码编写
//            invokeStatic(Type.getType("Ljava/long/system"),//哪个类下面的
//                    new Method("currentTimeMills", "()J"));//什么方法 和 签名
//            int start = newLocal(Type.LONG_TYPE);//指定类型
//            storeLocal(start);//把结果赋值给局部变量
//        }
//
//        @Override
//        protected void onMethodExit(int opcode) {//当退出方法时
//            super.onMethodExit(opcode);
//            if (!isInject) return;
//        }
//    }
//}
