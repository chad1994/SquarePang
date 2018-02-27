
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
 
public class MyService {
    //Ŭ���̾�Ʈ ������ ���ӵ� �� ������ Collection����
    private ArrayList<ServerThread> clist;
    private ServerSocket serverSocket;
    private String reip;
    Model model;
    public static void main(String[] args) {
        new MyService();
    }
    public MyService() {        
        try {
            // ���� ���� ����
            serverSocket = new ServerSocket(9999);
            model = new Model();
            System.out.println("Server Start!");
             
            //ArrayList ����
            clist = new ArrayList<ServerThread>();
             
            while(true) {
            	Socket socket = serverSocket.accept();
            	System.out.println("receiving...");
            	ServerThread thread = new ServerThread(socket,model);
            	thread.start();
            	System.out.println("�����߾� ");
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
     
//    public void exe() {
//        while(true) { //�������� ����
//            // Ŭ���̾�Ʈ�� ������ �������� �� �����ϴ� �޼���
//            Socket s=null;
//            Model model = null;
//            try {
//                s = serverSocket.accept();
//                String reip = s.getInetAddress().getHostAddress();
//                System.out.println("Log : " +reip);
//                // �����ؿ� Ŭ���̾�Ʈ�� ���񽺸� ���ӹ޴� Ŭ������ ��ü�� �����ϸ鼭
//                // �����ڸ� ���� �ʿ��� ������ �����Ѵ�.
//                model = new Model();
//                ServerThread st = new ServerThread(s, this,model);
//                // �������� �ּҸ� ����ϱ� ���ؼ� ArrayList�� ���
//                clist.add(st);
//                // �� �����带 �����Ѵ�.
//                st.start();
//                System.out.println("Current number of Clients :" + clist.size());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            reip = s.getInetAddress().getHostAddress();
//            System.out.println("Ip : " + reip);     
//        }
//    }
     
    
//    public void sendMsg(String str1, String str2, String str3, String str4) {
//        // protocol
//        // ex) talk/user/msg/null
//        // ex) draw/color/x/y
//        StringBuffer sb = new StringBuffer();
//        if(str1.equals("talk")) {
//            sb.append("talk/"+str2+"/["+reip+"]/"+str4+ "/null" );
//        }else {
//            sb.append(str1).append("/");
//            sb.append(str2).append("/");
//            sb.append(str3).append("/");
//            sb.append(str4);
//        }
//         
//        // 
//        for(ServerThread e : clist) {
//            e.getPw().println(sb.toString());
//        }
//         
//    }   
}