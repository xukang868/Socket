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
		//����Socket����ָ���������ĵ�ַ�Ͷ˿ں�
		//�������Ӻ�ͨ���������������պͷ�����Ϣ
		//�ر���Դ��Ϣ
	}

	private void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataOutputStream out = null;
		DataInputStream input = null;
		try {
			
			//1. ����������ServerSocket,����accpet()�ȴ��ͻ�������
			serverSocket = new ServerSocket(30000); 
			System.out.println("�������ȴ�����....");
			socket = serverSocket.accept(); 
			System.out.println("���ӳɹ�");
			
			//2. ��ȡ��Socket����󣬽������������������ڴ�ӡ�ͷ������ݣ�
			 input = new DataInputStream(socket.getInputStream());  
			 String clientInputStr = input.readUTF();
			 System.out.println("�ͻ���:" + clientInputStr); 
			 
			 out = new DataOutputStream(socket.getOutputStream());    
			 System.out.print("������:\t");    
			 // ͨ����������    
			 String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
			 out.writeUTF(s);
		} catch (IOException e) {
			 System.out.println("������ �����쳣: " + e.getMessage());   
		}finally {
//			3.���ݽ��������󣬹رսӿڡ�
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
				System.out.println("�������رճɹ�");
			} catch (IOException e) {
				System.out.println("�������쳣�رգ�"+e.getMessage());
			}
		}
	}
}
