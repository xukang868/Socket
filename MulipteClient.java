import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MulipteClient {  
    public static final String IP_ADDR = "localhost";//服务器地址   127.0.0.1
    public static final int PORT = 30000;//服务器端口号    
    public static boolean isRunning = true;
    public static void main(String[] args) {    
        System.out.println("客户端启动...");    
        System.out.println("当接收到服务器端字符为 \"Bye\" 的时候, 客户端将终止\n");   
        while (isRunning) {    
            Socket socket = null;  
            try {  
                //创建一个流套接字并将其连接到指定主机上的指定端口号  
                socket = new Socket(IP_ADDR, PORT); 
                //读取服务器端数据    
                DataInputStream input = new DataInputStream(socket.getInputStream());    
                //向服务器端发送数据    
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
                System.out.print("客户端: \t");    
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
                out.writeUTF(str);    
                    
                String ret = input.readUTF();     
                System.out.println("服务器: " + ret);    
                // 如接收到 "Bye" 则断开连接    
                if ("Bye".equals(ret)) {    
                    System.out.println("客户端将关闭连接");    
                    Thread.sleep(500);    
                    break;    
                }    
                out.close();  
                input.close();  
            } catch (Exception e) {  
                System.out.println("客户端连接异常:" + e.getMessage()); 
                isRunning = false;
            } finally {  
                if (socket != null) {  
                    try {  
                        socket.close();  
                    } catch (IOException e) { 
                        socket = null;   
                        isRunning = false;
                        System.out.println("客户端关闭异常:" + e.getMessage());   
                    }  
                }  
            }  
        }    
    }    
}
