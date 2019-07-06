import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocket {
	public static void main(String[] args) {
		SimpleSocket mySocket = new SimpleSocket();
		mySocket.start();
		//创建Socket对象，指明服务器的地址和端口号
		//建立连接后，通过输入和输出流接收和发送信息
		//关闭资源信息
	}

	private void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataOutputStream out = null;
		DataInputStream input = null;
		try {
			
			//1. 服务器创建ServerSocket,调用accpet()等待客户端连接
			serverSocket = new ServerSocket(30000); 
			System.out.println("服务器等待连接....");
			socket = serverSocket.accept(); 
			System.out.println("连接成功");
			
			//2. 获取到Socket对象后，建立输入和输出流，用于打印和发送数据；
			 input = new DataInputStream(socket.getInputStream());  
			 String clientInputStr = input.readUTF();
			 System.out.println("客户端:" + clientInputStr); 
			 
			 out = new DataOutputStream(socket.getOutputStream());    
			 System.out.print("服务器:\t");    
			 // 通过键盘输入    
			 String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
			 out.writeUTF(s);
		} catch (IOException e) {
			 System.out.println("服务器 运行异常: " + e.getMessage());   
		}finally {
//			3.数据交互结束后，关闭接口。
			try {
				if(input!=null) {
					input.close();
					input = null;
				}
				if(out!=null) {
					out.close();
					out = null;
				}
				if(socket!=null) {
					socket.close();
					socket=null;
				}
				System.out.println("服务器关闭成功");
			} catch (IOException e) {
				System.out.println("服务器异常关闭："+e.getMessage());
			}
		}
	}
}
