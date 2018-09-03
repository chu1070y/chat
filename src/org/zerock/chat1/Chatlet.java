package org.zerock.chat1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Chatlet implements Runnable {
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dos;
    private Scanner scanner;

    public Chatlet(Socket socket)throws Exception {
        this.socket = socket;
        this.din = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.scanner = new Scanner(System.in);
    }
    //메세지 쓰기
    public void write()throws Exception{
        while(true){
            String msg = scanner.nextLine();
            //A:Hello
            //S:84:Hello
            dos.writeUTF(msg);
        }
    }
    //메세지 읽기
    public void read()throws Exception {
        while(true){
            String msg = din.readUTF();
            System.out.println(msg);
        }
    }
    //모두 닫기
    public void closeAll(){
        try{ scanner.close(); }catch(Exception e){}
        try{ din.close(); }catch(Exception e){}
        try{ dos.close(); }catch(Exception e){}
        try{ socket.close(); }catch(Exception e){}
    }
    //쓰레드를 이용해 다중 채팅 실행
    @Override
    public void run() {
        try {
            read();
        } catch (Exception e) {
            e.printStackTrace();
            closeAll();
        }
    }

}//end class
