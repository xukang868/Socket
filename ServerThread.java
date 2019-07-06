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
           // ��ȡ�ͻ�������    
           input = new DataInputStream(socket.getInputStream());  
           String clientInputStr = input.readUTF();//����Ҫע��Ϳͻ����������д������Ӧ,������� EOFException  
           // ����ͻ�������    
           System.out.println(address.getHostAddress()+"�ͻ���:" + clientInputStr);    
           	// ��ͻ��˻ظ���Ϣ    
               out = new DataOutputStream(socket.getOutputStream());    
               System.out.print("������:\t");    
               // ���ͼ��������һ��    
               String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
               out.writeUTF(s);  
               out.close();  
               input.close();  
       } catch (Exception e) {    
           System.out.println("�����������쳣: " + e.getMessage());    
       } finally {    
           if (socket != null) {    
               try {    
                   socket.close();    
               } catch (Exception e) {    
                   socket = null;    
                   System.out.println("������ �ر��쳣:" + e.getMessage());    
               }    
           }    
       }   
	}
} 
