package org.zerock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneToOneServer {

    public static void main(String[] args) {

        System.out.println("Server Run>>>>>>>");

        try(ServerSocket serverSocket = new ServerSocket(7777);
            Socket socket = serverSocket.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            ){
            System.out.println("대화 요청이 들어왔습니다.");
            //쓰레드 생성
            new Thread(() ->{
                try{
                    while(true) {
                        //read and then write - B
                        String otherMsg = din.readUTF();//메세지 읽기
                        System.out.println(otherMsg);//메세지 출력
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }).start();

            while(true){
                String myMsg = scanner.nextLine();//메세지 작성
                dos.writeUTF(myMsg);//메세지 보내기
                dos.flush();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }//end try/catch

    }//end main
}//end class


