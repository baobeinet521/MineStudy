package com.zd.study.java.practice;

import java.util.Map;



public class TestJava {

    public static void main(String[] args) throws Exception {
//        System.out.println("TestClass, show t = ");
//        test();
        testTinyApp();
    }

    public static void testTinyApp() throws Exception {
//        String priHex = "3C37D4D3384F15547754D8B8CC38BCE9B82451BFCD7E684222DEBAC7CE3C16C5";
//        String xHex = "D483DF90C232ED64560635BBC8E3905235016C3B56C42733A618B3CBE51A72D4";
//        String yHex = "41E1422B187B861DCB5C74B6DC5AFBBC644ED4532F0E0F418270B3F42DC3B611";
//        String message = "{\"requestTime\":\"" + System.currentTimeMillis() + "\",\"openid\":\"36ed081c-42ce-41f6-88ba-aad5d430fb27\"}";
//        System.out.println("加密前明文为：" + message);
//        Map<String, Object> encryptMap = GMHelp.encrypt(xHex, yHex, priHex, message);
//        String appletResponse = (String)encryptMap.get("appletResponse");
//        System.out.println("加密后密文为：appletRequest:" + appletResponse);
//        String appletKey = (String)encryptMap.get("appletKey");
//        System.out.println("加密后秘钥为：appletKey:" + appletKey);
//        String appletSign = (String)encryptMap.get("appletSign");
//        System.out.println("加密后签名为：appletSign:" + appletSign);
    }

    public static void test(){

        Bank bank = new Bank();

        Bank bank1 = new Bank();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.addMoney(300);
                    bank.checkMoney();
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank1.getMoney(300);
                    bank1.checkMoney();
                }

            }
        }).start();
    }
}


class Bank{
    static float moneyA = 0;

    public void addMoney(float money){
        synchronized (Bank.class){
            moneyA += money;
        }

    }

    public float getMoney(float money){
        synchronized (Bank.class){
            if(moneyA < money){
                System.out.println("账户余额不足");
                return 0;
            }
            moneyA -= money;
            System.out.println("取出   " + money);
            return money;
        }

    }

    public void checkMoney(){
        System.out.println("账户余额   " + moneyA);
    }


}