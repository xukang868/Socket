import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThread extends Thread{
	private Socket socket ; 
	private InetAddress address;
	 public ServerThread(Socket client,InetAddress address) {    
        this.socket = client;    
        this.address = address;
    }    

	@Override
	public void run() {	
		DataOutputStream out = null;
		DataInputStream input = null;
		try {    
           // 读取客户端数据    
           input = new DataInputStream(socket.getInputStream());  
           String clientInputStr = input.readUTF();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException  
           // 处理客户端数据    
           System.out.println(address.getHostAddress()+"客户端:" + clientInputStr);    
           	// 向客户端回复信息    
               out = new DataOutputStream(socket.getOutputStream());    
               System.out.print("服务器:\t");    
               // 发送键盘输入的一行    
               String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
               out.writeUTF(s);  
               out.close();  
               input.close();  
       } catch (Exception e) {    
           System.out.println("服务器运行异常: " + e.getMessage());    
       } finally {    
           if (socket != null) {    
               try {    
                   socket.close();    
               } catch (Exception e) {    
                   socket = null;    
                   System.out.println("服务器 关闭异常:" + e.getMessage());    
               }    
           }    
       }   
	}
} 
