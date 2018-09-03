package org.zerock.chat1;

import java.net.Socket;

public class ChatletClient {
    public static void main(String[] args)throws Exception {

        Socket socket = new Socket("10.10.10.96", 7777);//원하는 아이피에 연결

        Chatlet chatlet = new Chatlet(socket);//객체로 메세지 읽고 보내자
        //쓰레드 하나 생성
        new Thread(() -> {
            try {
                chatlet.read();//메세지 읽기
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        //쓰레드 하나 생성
        new Thread(() -> {

            try {
                chatlet.write();//메세지 쓰기
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("END");
    }
}
