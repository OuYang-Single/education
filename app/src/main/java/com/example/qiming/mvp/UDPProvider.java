package com.example.qiming.mvp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPProvider {
    public static void main(String[]args) throws IOException {
        System.out.println("UDPProvider Started.");
        DatagramSocket datagramSocket=new DatagramSocket(20000);
        final byte[]buf=new byte[5120];
        DatagramPacket receivePack=new DatagramPacket(buf,buf.length);
        //接收
        datagramSocket.receive(receivePack);
        //打印接收到的信息与发送者的信息
        String ip=receivePack.getAddress().getHostAddress();
        int port=receivePack.getPort();
        int dataLen=receivePack.getLength();
        String data=new String(receivePack.getData(),0,dataLen);
        System.out.println("UDPProvider receive form ip:"+ip
        +"\tport:"+port+"\tport:"+data);
        //构建一份回送数据
        String responseData="Receive data with len:"+dataLen;
        byte[] responseDataBytes=responseData.getBytes();
        //直接根据发送者构建一份回送
        DatagramPacket responsePacket=new DatagramPacket(responseDataBytes,responseDataBytes.length
                ,receivePack.getAddress(),receivePack.getPort());
        datagramSocket.send(responsePacket);
        //完成
        System.out.println("UDPProvider Finished.");
        datagramSocket.close();
    }
}
