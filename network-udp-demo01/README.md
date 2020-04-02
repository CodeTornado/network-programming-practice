利用DatagramSocket实现Udp方式数据传输 UdpTransmissionByDatagramSocketDemo01.java



```java
 /**
  * Description: udp 寄件人
  */
class UdpSender implements Runnable{
    String sendName;

    public UdpSender(String name) {
        this.sendName = name;
    }
  
     public void run() {
       try{
       		 //新建DatagramSocket指定发送给谁
           while(true){
             	 //获取用户cmd窗口输入信息
             	 //制作数据报包DatagramPacket并塞入用户需要传递给客户端的信息
               //DatagramSocket发送数据报包
               //关闭资源
           }
       }catch(Exception e){
         
       }finally{
         //关闭资源
       }
     }
}

 /**
  * Description: udp 收件人
  */
class UdpAddressee implements Runnable{

     public void run() {
       try{
       		 //开启DatagramSocket
           while(true){
               //DatagramSocket阻塞接收数据报包
               //通过DatagramSocket获取数据报包中信息,输出控制台
               //接收完毕
               //关闭资源
           }
       }catch(Exception e){
         
       }finally{
         //关闭资源
       }
     }
 }
```



测试代码(模拟聊天室)

```java
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
        System.out.println("开启聊天室(启动Ucp收件人):");
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
```

1

测试 Ucp 方式数据传输

```shell
开启聊天室(启动Ucp收件人):
小王: 南柯一梦
小李: 梦中说梦
小芳: 梦魂颠倒
小王: 倒凤颠鸾
小李: 鸾回凤翥
小芳: 翥凤翔鸾
小王: 鸾鸾鸾鸾鸾...鸾.
小王: ...
```