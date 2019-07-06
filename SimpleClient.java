import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
	public static final String IP_ADDR = "localhost";//服务器地址   127.0.0.1
	public static final int PORT = 30000;//服务器端口号 
	public static void main(String[] args) {
		SimpleClient myClient = new SimpleClient();
		myClient.start();
		//创建Socket对象，指明服务器的地址和端口号
		//建立连接后，通过输入和输出流接收和发送信息
		//关闭资源信息
	}

	private void start() {
		Socket socket = null;
		try {  
            //创建一个流套接字并将其连接到指定主机上的指定端口号  
            socket = new Socket("localhost", 30000);  
            System.out.println("客户端连接成功....");
            //读取服务器端数据    
            DataInputStream input = new DataInputStream(socket.getInputStream());    
            //向服务器端发送数据    
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
            System.out.print("客户端: \t");    
            String str = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
            out.writeUTF(str);    
                
            String ret = input.readUTF();     
            System.out.println("服务器: " + ret);    
            out.close();  
            input.close();  
        } catch (Exception e) {  
            System.out.println("客户端连接异常:" + e.getMessage());   
        } finally {  
            if (socket != null) {  
                try {  
                    socket.close();  
                    System.out.println("客户端关闭成功"); 
                } catch (IOException e) {  
                    socket = null;   
                    System.out.println("客户端关闭异常:" + e.getMessage());   
                }  
            }  
        }
	}
}
