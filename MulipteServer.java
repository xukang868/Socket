import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MulipteServer {  
    public static final int PORT = 30000;//�����Ķ˿ں�     
      
    public static void main(String[] args) {    
        System.out.println("����������...\n");    
        MulipteServer server = new MulipteServer();    
        server.init();    
        //����������ServerSocket,ѭ������accpet()�ȴ��ͻ�������
        //�ͻ��˴���һ��socket������ͷ���˽�������
        //���������ոÿͻ������󣬴���socket��ÿͻ��˽���ר������
        //�������˺Ϳͻ��˽�����socket��һ���������߳��д���
        //�������˵ȴ��µ�����
        
    }    
    
    public void init() {    
        try {    
            ServerSocket serverSocket = new ServerSocket(PORT);  
            while (true) {    
                // һ���ж���, ���ʾ��������ͻ��˻��������    
                Socket client = serverSocket.accept(); 
                InetAddress address = client.getInetAddress();
                new ServerThread(client,address).start();
            }    
        } catch (Exception e) {    
            System.out.println("�������쳣: " + e.getMessage());    
        }    
    }    
}