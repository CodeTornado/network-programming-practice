package com.xiaowenai.network.udpdemo;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Description: 利用DatagramSocket实现Udp方式数据传输
 *
 * @author Shimmer
 * @version 1.0
 * @date 2020-04-01
 */
public class UdpTransmissionByDatagramSocketDemo01 {

    static class RunUcpRecipient {
        public static void main(String[] args) {
            startUcpRecipient();
        }
    }

    static class UdpSenderA {
        public static void main(String[] args) {
            startUdpSender("小王");
        }
    }

    static class UdpSenderB {
        public static void main(String[] args) {
            startUdpSender("小李");
        }
    }

    static class UdpSenderC {
        public static void main(String[] args) {
            startUdpSender("小芳");
        }
    }

    /**
     * @author Shimmer
     * @description 启动Ucp收件人
     * @date 11:48 2020-04-02
     **/
    public static void startUcpRecipient() {
        System.out.println("开始演示");
        System.out.println("启动Ucp收件人");
        new Thread(new UdpAddressee()).start();
    }

    /**
     * @author Shimmer
     * @description 启动Udp寄件人
     * @date 11:48 2020-04-02
     **/
    public static void startUdpSender(String sendName) {
        System.out.println("启动Udp寄件人" + sendName);
        new Thread(new UdpSender(sendName)).start();
    }
}

/**
 * Description: udp 寄件人
 *
 * @author Shimmer
 * @version 1.0
 * @date 2020-04-01
 */
class UdpSender implements Runnable {
    String sendName;

    public UdpSender(String name) {
        this.sendName = name;
    }

    public void run() {
        DatagramSocket socket = null;
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            while (true) {
                //新建DatagramSocket指定发送给谁
                socket = new DatagramSocket();
                //获取用户cmd窗口输入信息
                System.out.println("请寄件人[" + sendName + "]输入需要发送数据:");
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String data = bufferedReader.readLine();
                byte[] dataBytes = (sendName + ": " + data).getBytes();
                //制作数据报包DatagramPacket并塞入用户需要传递给客户端的信息
                DatagramPacket datagramPacket = new DatagramPacket(dataBytes, 0, dataBytes.length, new InetSocketAddress("localhost", 8387));
                //DatagramSocket发送数据报包
                socket.send(datagramPacket);
                //关闭资源
                closeResources(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            closeResources(bufferedReader);
            closeResources(inputStreamReader);
            closeResources(inputStream);
            closeResources(socket);
        }
    }

    /**
     * 关闭资源
     *
     * @param resources 可关闭的资源
     */
    private void closeResources(Closeable resources) {
        if (resources != null) {
            try {
                resources.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * Description: udp 收件人
 *
 * @author Shimmer
 * @version 1.0
 * @date 2020-04-01
 */
class UdpAddressee implements Runnable {

    public void run() {
        DatagramSocket datagramSocket = null;
        try {
            //开启DatagramSocket
            datagramSocket = new DatagramSocket(8387);
            while (true) {
                byte[] bytes = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
                //DatagramSocket阻塞接收数据报包
                datagramSocket.receive(datagramPacket);
                //通过DatagramSocket获取数据报包中信息,输出控制台
                byte[] data = datagramPacket.getData();
                System.out.println(new String(data, 0, datagramPacket.getLength()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            closeResources(datagramSocket);
        }
    }

    /**
     * 关闭资源
     *
     * @param resources 可关闭的资源
     */
    private void closeResources(Closeable resources) {
        if (resources != null) {
            try {
                resources.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
