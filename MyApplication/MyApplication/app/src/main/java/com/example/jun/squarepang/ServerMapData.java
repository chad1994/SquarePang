package com.example.jun.squarepang;

/**
 * Created by Jun on 2017-11-19.
 */

public class ServerMapData {
    private int gamestate;
    private int p1_map[][];
    private int p2_map[][];
    private boolean endplayer1;
    private boolean endplayer2;
    private boolean endgame;
    private String player1name;
    private String player2name;
    private int whowin; //1,2, 3=무
    private int player1score;
    private int player2score;
    private int p1win;
    private int p1draw;
    private int p1lose;
    private int p2win;
    private int p2draw;
    private int p2lose;
    private boolean p1attacked;
    private boolean p2attacked;

    public boolean isP1attacked() {
        return p1attacked;
    }

    public void setP1attacked(boolean p1attacked) {
        this.p1attacked = p1attacked;
    }

    public boolean isP2attacked() {
        return p2attacked;
    }

    public void setP2attacked(boolean p2attacked) {
        this.p2attacked = p2attacked;
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

    public int getPlayer1score() {
        return player1score;
    }

    public void setPlayer1score(int player1score) {
        this.player1score = player1score;
    }

    public int getPlayer2score() {
        return player2score;
    }

    public void setPlayer2score(int player2score) {
        this.player2score = player2score;
    }

    public int getWhowin() {
        return whowin;
    }

    public void setWhowin(int whowin) {
        this.whowin = whowin;
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
