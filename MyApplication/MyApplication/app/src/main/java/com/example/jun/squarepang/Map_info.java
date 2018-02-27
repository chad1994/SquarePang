package com.example.jun.squarepang;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Jun on 2017-11-07.
 */

public class Map_info {

    int map_arr[][] = new int[][]
            {{3,1,3,4,5,2,1,2},
            {3,4,5,0,3,3,4,5},
            {1,1,2,0,4,5,3,2},
            {3,4,2,0,4,5,1,2},
            {1,1,0,3,4,4,5,3},
            {1,0,0,0,4,5,2,3},
            {3,4,5,5,3,0,0,0},
            {1,2,3,0,4,5,3,4},
            {4,5,5,0,2,1,2,1},
            {1,1,2,0,4,5,4,2},
            {3,3,4,5,0,0,0,2},
            {1,2,3,3,4,0,3,2},
            {5,3,2,1,2,0,1,2},
            {5,4,3,2,2,0,4,2},
            {1,2,4,5,2,0,3,2}};

    ArrayList<Point> delete_block = new ArrayList<Point>();
    int plus_score=0;
    int total_score=0;
    int game_time = 30;


}
