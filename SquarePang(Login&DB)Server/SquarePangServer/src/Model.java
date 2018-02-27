
public class Model {
	private int p1_map_arr[][]= new int[][]
		{{1,1,1,1,1,1,1,1},
        {3,4,5,0,3,3,4,5},
        {1,1,2,0,4,5,3,2},
        {3,4,6,0,4,5,1,2},
        {1,6,0,3,4,4,5,3},
        {1,0,0,0,4,5,2,3},
        {3,4,5,5,7,0,0,0},
        {1,2,3,0,4,9,3,4},
        {4,5,5,0,8,1,2,1},
        {1,1,2,0,4,5,4,2},
        {3,3,4,5,0,0,0,2},
        {1,2,3,3,9,0,3,2},
        {5,3,2,1,2,0,1,2},
        {5,4,3,2,2,0,4,2},
        {1,2,4,5,2,0,3,2}};
    
    private int p2_map_arr[][]= new int[][]
    	{{3,1,3,4,5,2,1,2},
    	 {3,4,5,0,3,3,4,5},
         {1,1,2,0,4,5,3,2},
         {3,4,6,0,4,5,1,2},
         {1,6,0,3,4,4,5,3},
         {1,0,0,0,4,5,2,3},
         {3,4,5,5,7,0,0,0},
         {1,2,3,0,4,9,3,4},
         {4,5,5,0,8,1,2,1},
         {1,1,2,0,4,5,4,2},
         {3,3,4,5,0,0,0,2},
         {1,2,3,3,9,0,3,2},
         {5,3,2,1,2,0,1,2},
         {5,4,3,2,2,0,4,2},
         {1,2,4,5,2,0,3,2}};
        
    private String player1name;
    private String player2name;
    private boolean namestate=false; //먼저 들어온 사람의 이름이 써지면 true
    private boolean getready = false;//두번째 들어온 사람 이름이 써지면 게임 시작
    private boolean endplayer1 = false;
    private boolean endplayer2 = false;
    private boolean endgame = false;
    private int totalscore1;
    private int totalscore2;
    private int p1win;
    private int p1draw;
    private int p1lose;
    private int p2win;
    private int p2draw;
    private int p2lose;
    
    
    
    
    ////////////////////////////////////////////getter setter
    
    
    
    public boolean isNamestate() {
		return namestate;
	}

	public int getP1win() {
		return p1win;
	}

	public void setP1win(int p1win) {
		this.p1win = p1win;
	}

	public int getP1draw() {
		return p1draw;
	}

	public void setP1draw(int p1draw) {
		this.p1draw = p1draw;
	}

	public int getP1lose() {
		return p1lose;
	}

	public void setP1lose(int p1lose) {
		this.p1lose = p1lose;
	}

	public int getP2win() {
		return p2win;
	}

	public void setP2win(int p2win) {
		this.p2win = p2win;
	}

	public int getP2draw() {
		return p2draw;
	}

	public void setP2draw(int p2draw) {
		this.p2draw = p2draw;
	}

	public int getP2lose() {
		return p2lose;
	}

	public void setP2lose(int p2lose) {
		this.p2lose = p2lose;
	}

	public int getTotalscore1() {
		return totalscore1;
	}

	public void setTotalscore1(int totalscore1) {
		this.totalscore1 = totalscore1;
	}

	public int getTotalscore2() {
		return totalscore2;
	}

	public void setTotalscore2(int totalscore2) {
		this.totalscore2 = totalscore2;
	}

	public boolean isEndplayer1() {
		return endplayer1;
	}

	public void setEndplayer1(boolean endplayer1) {
		this.endplayer1 = endplayer1;
	}

	public boolean isEndplayer2() {
		return endplayer2;
	}

	public void setEndplayer2(boolean endplayer2) {
		this.endplayer2 = endplayer2;
	}

	public boolean isEndgame() {
		return endgame;
	}

	public void setEndgame(boolean endgame) {
		this.endgame = endgame;
	}

	public void setNamestate(boolean namestate) {
		this.namestate = namestate;
	}

	public boolean isGetready() {
		return getready;
	}

	public void setGetready(boolean getready) {
		this.getready = getready;
	}

	public int[][] getP2_Map_arr() {
		return p2_map_arr;
	}

	public void setP2_Map_arr(int[][] p2_map_arr) {
		this.p2_map_arr = p2_map_arr;
	}

	public int[][] getP1_Map_arr() {
		return p1_map_arr;
	}

	public void setP1_Map_arr(int[][] map_arr) {
		this.p1_map_arr = map_arr;
	}
	
	public String getPlayer1name() {
		return player1name;
	}

	public void setPlayer1name(String player1name) {
		this.player1name = player1name;
	}
	public String getPlayer2name() {
		return player2name;
	}

	public void setPlayer2name(String player2name) {
		this.player2name = player2name;
	}
	
}
