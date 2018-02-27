import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

// Socket의 Stream을 받아내기 위한 일을 담당

public class ServerThread extends Thread {

	static final String url = "jdbc:mysql://localhost:3306/squarepang";
	static final String db_id = "root";
	static final String db_pw = "jun0203";
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());

	private Model model;
	private Socket socket; // 소켓을 담당
	private MyService server; // 브로드캐스팅을 위한 서버의 주소
	// 소켓으로 부터 연결된 스트림을 원활하게 받아낼 2차 스트림을 선언
	private PrintWriter pw;
	private Scanner in;

	// 생성하자마자 접속된 소켓의 주소와 서버의 주소를 받아와서 멤버에 등록
	public ServerThread(Socket socket, Model model) {
		this.socket = socket;
		// this.server = server;
		this.model = model;
		// 연결된 소켓으로 부터 데이터를 전송할 스트림을 생성하는데
		// 2차 스트림을 사용해서 한줄 단위로 개선한 형태로 데이터를 전송한다
		try {
			pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
			list.add(pw);
			System.out.println("접속자 수 : " + list.size());
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			if (list.size() % 2 != 0) {
				map.put("player", 1); // 내가 플레이어 몇인지.
			} else {
				map.put("player", 2);
			}
			pw.println(map);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String name = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			name = reader.readLine();
			System.out.println("name:" + name);

			if (model.isNamestate() == false) {
				model.setPlayer1name(name);
				model.setNamestate(true);
			} else {
				model.setPlayer2name(name);
				model.setGetready(true);
				model.setNamestate(false);
			}
			// System.out.println("get ready "+model.isGetready()+"");
			if (model.isGetready() == true) {
				HashMap<Object, Object> map = new HashMap<Object, Object>();
				map.put("gamestate", 1);
				map.put("player1name", model.getPlayer1name());
				map.put("player2name", model.getPlayer2name());
				// map.put("test", 2);
				map.put("p1_map", model.getP1_Map_arr());
				map.put("p2_map", model.getP2_Map_arr());

				System.out.println("game start보드 띄워짐");
				sendAll(map);
				map.clear();

			}
			while (true) {
				// System.out.println("접속자 수2 : "+list.size());
				// String str = reader.readLine();
				// str = str.substring(1, str.length() - 1);
				// String[] keyValuePairs = str.split(",");
				// HashMap<String, Integer> map = new HashMap<String, Integer>();
				// System.out.println("test:"+str);
				HashMap<Object, Object> map = new HashMap<Object, Object>();
				StringBuilder str = new StringBuilder();
				str.append(reader.readLine());
				System.out.println(str.toString());
				Gson gson = new Gson();
				JsonModel jm = gson.fromJson(str.toString(), JsonModel.class);
				
				

//				System.out.println("누구로 부터 온 값인가: " + jm.getPlayer());
				if (str.toString().contains("_map")) {
					if (jm.getPlayer() == 1) {
						model.setP1_Map_arr(jm.getP1_map());
						map.put("p1_map", model.getP1_Map_arr());
						sendAll(map);
						map.clear();
						jm.setPlayer(0);
						System.out.println("플레이어1꺼 받았어!");
					} else if (jm.getPlayer() == 2) {
						model.setP2_Map_arr(jm.getP2_map());
						map.put("p2_map", model.getP2_Map_arr());
						sendAll(map);
						map.clear();
						jm.setPlayer(0);
					}
					System.out.println("str : " + str.toString());
				}
				
				if(str.toString().contains("item0")) {
					if(jm.isPlayer1_item0()==true) {
						map.put("p2attacked", true);
						
					}
					if(jm.isPlayer2_item0()==true) {
						map.put("p1attacked", true);
						jm.setPlayer2_item0(false);
					}
					sendAll(map);
				}
				
				if(str.toString().contains("endplayer")) {
					if(jm.isEndplayer1()==true) {
						model.setEndplayer1(true);
						model.setTotalscore1(jm.getTotalscore1());
						map.put("endplayer1",true);
						sendAll(map);
						map.clear();
					}
					else if(jm.isEndplayer2()==true) {
						model.setEndplayer2(true);
						model.setTotalscore2(jm.getTotalscore2());
						map.put("endplayer2",true);
						sendAll(map);
						map.clear();
					}
					
					if(model.isEndplayer1()==true&&model.isEndplayer2()==true) {
						System.out.println("게임끝났다고 보냈어!");
						map.put("endgame", true);
						map.put("player1score", model.getTotalscore1());
						map.put("player2score", model.getTotalscore2());
						//두 클라이언트의 게임이 끝났음 판정 보내주는 작업 시작.
						Connection conn =null;
						Class.forName("com.mysql.jdbc.Driver");
						conn= (Connection) DriverManager.getConnection(url,db_id,db_pw);
						System.out.println("db connect success");
						java.sql.Statement stmt = conn.createStatement();
//						String check = "select id,pw from userinfo where id='"+model.getPlayer1name()+"';";
//						ResultSet rs = stmt.executeQuery(check);
//						while (rs.next()) {
//							String s_id = rs.getString("id"); // 작성자
//							String s_pw = rs.getString("pw");
//							System.out.println("rs check///"+s_id+"/"+s_pw);
//						}
						
						if(model.getTotalscore1()>model.getTotalscore2()) {
							map.put("whowin", 1);
							stmt.executeUpdate("update userinfo set win=win+1 where id='"+model.getPlayer1name()+"';");
							stmt.executeUpdate("update userinfo set lose=lose+1 where id='"+model.getPlayer2name()+"';");
						}
						else if(model.getTotalscore1()<model.getTotalscore2()) {
							map.put("whowin", 2);
							stmt.executeUpdate("update userinfo set win=win+1 where id='"+model.getPlayer2name()+"';");
							stmt.executeUpdate("update userinfo set lose=lose+1 where id='"+model.getPlayer1name()+"';");
						}
						else if(model.getTotalscore1()==model.getTotalscore2()) {
							map.put("whowin", 3);
							stmt.executeUpdate("update userinfo set draw=draw+1 where id='"+model.getPlayer1name()+"' or id='"+model.getPlayer2name()+"';");
						}//데이터 베이스에 전적 수정 완료.
						//사용자에게 전적 보내기 시작
						String check = "select win,draw,lose from userinfo where id='"+model.getPlayer1name()+"' or id='"+model.getPlayer2name()+"';";
						ResultSet rs = stmt.executeQuery(check);
						int count=0;
						while (rs.next()) {
							if(count==0) {
								model.setP1win(rs.getInt("win"));
								model.setP1draw(rs.getInt("draw"));
								model.setP1lose(rs.getInt("lose"));
								count++;
							}
							else {
								model.setP2win(rs.getInt("win"));
								model.setP2draw(rs.getInt("draw"));
								model.setP2lose(rs.getInt("lose"));
							}	
						}
						map.put("p1win", model.getP1win());
						map.put("p1draw", model.getP1draw());
						map.put("p1lose", model.getP1lose());
						map.put("p2win", model.getP2win());
						map.put("p2draw", model.getP2draw());
						map.put("p2lose", model.getP2lose());
						
						sendAll(map);
						map.clear();
					}
				}

			}

			// in = new Scanner(socket.getInputStream());
			/*
			 * if (model.namestate == false) { model.setPlayer1name(name); model.namestate =
			 * true; } else { model.setPlayer2name(name); model.getready=true;
			 * model.namestate=false; }
			 * 
			 * if(model.getready==true) { HashMap<Object, Object> game_info = new
			 * HashMap<Object, Object>(); game_info.put("player1map",model.getP1_Map_arr());
			 * game_info.put("player2map",model.getP2_Map_arr()); // game_info. } // 서버가
			 * 소켓으로 부터 전송되어온 데이터를 읽어 내는 영역 String res = ""; while(in.hasNext()) { res =
			 * in.nextLine(); // 스트림으로 한줄단위로 읽어 내는 메서드 System.out.println("Message : " +
			 * res); // transMsg(res); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// private void transMsg(String res) {
	// // ex) talk/user/msg/null
	// // ex) draw/color/x/y
	//
	// StringTokenizer stn = new StringTokenizer(res,"/");
	//
	// String str1 = stn.nextToken();
	// String str2 = stn.nextToken();
	// String str3 = stn.nextToken();
	// String str4 = stn.nextToken();
	//
	// // 서버의 sendMsg로 분석한 토큰을 전달한다.
	// server.sendMsg(str1,str2,str3,str4);
	// }

	public PrintWriter getPw() {
		return pw;
	}

	private void sendAll(HashMap<Object, Object> map) {
		Gson gson = new Gson();
		String result = gson.toJson(map);
		for (PrintWriter writer : list) {
			writer.println(result);
			writer.flush();
			System.out.println("보냄");
		}
	}
//	private void sendAll(HashMap<Object, Object> map) {
//		Gson gson = new Gson();
//		String result = gson.toJson(map);
//		for(PrintWriter writer;writer)
//			writer.println(result);
//			writer.flush();
//			System.out.println("보냄");
//		}
//	}

}