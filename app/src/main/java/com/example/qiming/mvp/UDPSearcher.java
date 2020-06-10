package com.example.qiming.mvp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSearcher {

    public static void main(String[]args) throws IOException {
        System.out.println("UDPSearcher Started.");
        //系统指定端口，无需指定
        DatagramSocket datagramSocket=new DatagramSocket();

        //构建一份回送数据
        String requestData="HelloWord!";
         byte[]requestDataBytes=requestData.getBytes();
        //直接构建发送者构建一份回送信息
        DatagramPacket requestPacket=new DatagramPacket(requestDataBytes,requestDataBytes.length);
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);
        datagramSocket.send(requestPacket);

        final byte[]buf=new byte[512];
        DatagramPacket receivePack=new DatagramPacket(buf,buf.length);
        //接收
        datagramSocket.receive(receivePack);
        //打印接收到的信息与发送者的信息
        String ip=receivePack.getAddress().getHostAddress();
        int port=receivePack.getPort();
        int dataLen=receivePack.getLength();
        String data=new String(receivePack.getData(),0,dataLen);
        System.out.println("UDPSearcher receive form ip:"+ip
                +"\tport:"+port+"\tport:"+data);
        //构建一份回送数据
        String responseData="Receive data with len:"+dataLen;
        byte[] responseDataBytes=responseData.getBytes();
        //直接根据发送者构建一份回送
        DatagramPacket responsePacket=new DatagramPacket(responseDataBytes,responseDataBytes.length
                ,receivePack.getAddress(),receivePack.getPort());
        datagramSocket.send(responsePacket);
        //完成
        System.out.println("UDPSearcher Finished.");
        datagramSocket.close();
    }
}
