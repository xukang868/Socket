import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MulipteClient {  
    public static final String IP_ADDR = "localhost";//��������ַ   127.0.0.1
    public static final int PORT = 30000;//�������˿ں�    
    public static boolean isRunning = true;
    public static void main(String[] args) {    
        System.out.println("�ͻ�������...");    
        System.out.println("�����յ����������ַ�Ϊ \"Bye\" ��ʱ��, �ͻ��˽���ֹ\n");   
        while (isRunning) {    
            Socket socket = null;  
            try {  
                //����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�  
                socket = new Socket(IP_ADDR, PORT); 
                //��ȡ������������    
                DataInputStream input = new DataInputStream(socket.getInputStream());    
                //��������˷�������    
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
                System.out.print("�ͻ���: \t");    
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
                out.writeUTF(str);    
                    
                String ret = input.readUTF();     
                System.out.println("������: " + ret);    
                // ����յ� "Bye" ��Ͽ�����    
                if ("Bye".equals(ret)) {    
                    System.out.println("�ͻ��˽��ر�����");    
                    Thread.sleep(500);    
                    break;    
                }    
                out.close();  
                input.close();  
            } catch (Exception e) {  
                System.out.println("�ͻ��������쳣:" + e.getMessage()); 
                isRunning = false;
            } finally {  
                if (socket != null) {  
                    try {  
                        socket.close();  
                    } catch (IOException e) { 
                        socket = null;   
                        isRunning = false;
                        System.out.println("�ͻ��˹ر��쳣:" + e.getMessage());   
                    }  
                }  
            }  
        }    
    }    
}
