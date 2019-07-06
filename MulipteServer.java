import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MulipteServer {  
    public static final int PORT = 30000;//监听的端口号     
      
    public static void main(String[] args) {    
        System.out.println("服务器启动...\n");    
        MulipteServer server = new MulipteServer();    
        server.init();    
        //服务器创建ServerSocket,循环调用accpet()等待客户端连接
        //客户端创建一个socket并请求和服务端建立连接
        //服务器接收该客户端请求，创建socket与该客户端建立专线连接
        //服务器端和客户端建立的socket在一个单独的线程中处理
        //服务器端等待新的连接
        
    }    
    
    public void init() {    
        try {    
            ServerSocket serverSocket = new ServerSocket(PORT);  
            while (true) {    
                // 一旦有堵塞, 则表示服务器与客户端获得了连接    
                Socket client = serverSocket.accept(); 
                InetAddress address = client.getInetAddress();
                new ServerThread(client,address).start();
            }    
        } catch (Exception e) {    
            System.out.println("服务器异常: " + e.getMessage());    
        }    
    }    
}