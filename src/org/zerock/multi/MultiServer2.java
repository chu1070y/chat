package org.zerock.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MultiServer2 {

    ONE;

    private ServerSocket serverSocket;
    private List<ServerAgent> agentList;
    private Map<String, ServerAgent> agentMap;

    MultiServer2(){
        try {
            serverSocket = new ServerSocket(7777);
            agentList = new ArrayList<>();
            agentMap = new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runServer()throws Exception{

        while(true){
            Socket socket = serverSocket.accept();//메세지 받을 준비

            String ipStr = socket.getInetAddress().getHostAddress();//ip받음

            System.out.println("a user connected...."); //연결이 되었다
            ServerAgent agent =  new ServerAgent(socket);// serveragent 생성

            agentMap.put(ipStr, agent);//해당 ip마다 에이전트 하나씩 생성
            System.out.println(agentMap);
            agentList.add(agent);
            agent.start();

        }
    }

    public static void main(String[] args)throws Exception {

        MultiServer2.ONE.runServer();
    }

    public synchronized void broadcast(String msg) {

        System.out.println(msg);
        for (ServerAgent agent:agentList ) {
            try {
                agent.write(msg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                agentList.remove(agent);
            }//end catch
        }//end for
    }

    public void whisper(String ip, String side, String s1) {
        try {
            ServerAgent target = agentMap.get("10.10.10." + side);
            target.write(ip + ":" + s1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}