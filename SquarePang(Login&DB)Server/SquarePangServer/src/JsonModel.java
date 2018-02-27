
public class JsonModel {
	private int gamestate;
	private int player;
	private int p1_map[][];
    private int p2_map[][];
    private boolean endplayer1=false;
	private boolean endplayer2=false;
	private boolean endgame = false;
    private int totalscore1;
    private int totalscore2;
    private boolean player1_item0 =false;
    private boolean player2_item0 =false;
	
    
    
    
    
    public boolean isPlayer1_item0() {
		return player1_item0;
	}

	public void setPlayer1_item0(boolean player1_item0) {
		this.player1_item0 = player1_item0;
	}

	public boolean isPlayer2_item0() {
		return player2_item0;
	}

	public void setPlayer2_item0(boolean player2_item0) {
		this.player2_item0 = player2_item0;
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

	public boolean isEndgame() {
		return endgame;
	}

	public void setEndgame(boolean endgame) {
		this.endgame = endgame;
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



    public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
    public int[][] getP1_map() {
        return p1_map;
    }

    public void setP1_map(int[][] p1_map) {
        this.p1_map = p1_map;
    }

    public int[][] getP2_map() {
        return p2_map;
    }

    public void setP2_map(int[][] p2_map) {
        this.p2_map = p2_map;
    }

    public int getGamestate() {
        return gamestate;
    }

    public void setGamestate(int gamestate) {
        this.gamestate = gamestate;
    }
}
