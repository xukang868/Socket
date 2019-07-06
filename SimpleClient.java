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
	public static final String IP_ADDR = "localhost";//��������ַ   127.0.0.1
	public static final int PORT = 30000;//�������˿ں� 
	public static void main(String[] args) {
		SimpleClient myClient = new SimpleClient();
		myClient.start();
		//����Socket����ָ���������ĵ�ַ�Ͷ˿ں�
		//�������Ӻ�ͨ���������������պͷ�����Ϣ
		//�ر���Դ��Ϣ
	}

	private void start() {
		Socket socket = null;
		try {  
            //����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�  
            socket = new Socket("localhost", 30000);  
            System.out.println("�ͻ������ӳɹ�....");
            //��ȡ������������    
            DataInputStream input = new DataInputStream(socket.getInputStream());    
            //��������˷�������    
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
            System.out.print("�ͻ���: \t");    
            String str = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
            out.writeUTF(str);    
                
            String ret = input.readUTF();     
            System.out.println("������: " + ret);    
            out.close();  
            input.close();  
        } catch (Exception e) {  
            System.out.println("�ͻ��������쳣:" + e.getMessage());   
        } finally {  
            if (socket != null) {  
                try {  
                    socket.close();  
                    System.out.println("�ͻ��˹رճɹ�"); 
                } catch (IOException e) {  
                    socket = null;   
                    System.out.println("�ͻ��˹ر��쳣:" + e.getMessage());   
                }  
            }  
        }
	}
}
