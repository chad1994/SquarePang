
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
 
public class MyService {
    //클라이언트 소켓이 접속될 때 저장할 Collection선언
    private ArrayList<ServerThread> clist;
    private ServerSocket serverSocket;
    private String reip;
    Model model;
    public static void main(String[] args) {
        new MyService();
    }
    public MyService() {        
        try {
            // 서버 소켓 생성
            serverSocket = new ServerSocket(9999);
            model = new Model();
            System.out.println("Server Start!");
             
            //ArrayList 생성
            clist = new ArrayList<ServerThread>();
             
            while(true) {
            	Socket socket = serverSocket.accept();
            	System.out.println("receiving...");
            	ServerThread thread = new ServerThread(socket,model);
            	thread.start();
            	System.out.println("시작했어 ");
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
     
//    public void exe() {
//        while(true) { //지속적인 서비스
//            // 클라이언트의 소켓이 접속했을 때 동작하는 메서드
//            Socket s=null;
//            Model model = null;
//            try {
//                s = serverSocket.accept();
//                String reip = s.getInetAddress().getHostAddress();
//                System.out.println("Log : " +reip);
//                // 접속해온 클라이언트의 서비스를 위임받는 클래스를 객체로 생성하면서
//                // 생성자를 통해 필요한 정보를 주입한다.
//                model = new Model();
//                ServerThread st = new ServerThread(s, this,model);
//                // 접속자의 주소를 기억하기 위해서 ArrayList에 기억
//                clist.add(st);
//                // 각 스레드를 시작한다.
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