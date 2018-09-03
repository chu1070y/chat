package org.zerock;

public class Ex1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+this);
        }
    }

    public static void main(String[] args) {
        Ex1 obj = new Ex1();

        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
    }

}
