package com.zd.study.java;

public class TestJava {

    public static void main(String[] args){
//        System.out.println("TestClass, show t = ");
        test();
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