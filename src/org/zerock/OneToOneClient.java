package org.zerock;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class OneToOneClient {

    public static void main(String[] args) {

        System.out.println("연결 시작합니다.");
        try(
                Socket socket = new Socket("10.10.10.96",7777);//해당 아이피와 포트에 연결
                DataInputStream din = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                ){
            //쓰레드 하나 생성
            new Thread(()->{
                try{
                    while(true) {
                        String myMsg = scanner.nextLine();//메세지 쓰기
                        dos.writeUTF(myMsg);//메세지 보내기
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }).start();

            while(true){
                String otherMsg = din.readUTF();//메세지 읽기
                System.out.println(otherMsg);//메시지 출력
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }//end catch

    }//end main
}//end class
