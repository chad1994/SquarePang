package com.example.jun.squarepang;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jun on 2017-11-17.
 */

public class Server_Game_Logic {

    int maxarrA=14,maxarrB=7;
    static int timeItemCount[]=new int[]{0,0,0,0,0,0};



    public int[] check_Block(int[][] arr,int arrA,int arrB,Point[] point){ //사방의 블록색을 체크.
        int arr_result[]={0,0,0,0};//배열0,1,2,3 순으로 상하좌우;

        for(int i=arrA;i>=0;i--){//상 체크
            if(arr[i][arrB]!=0) {
                arr_result[0] = arr[i][arrB];
                point[0].x = i;
                point[0].y = arrB;
                break;
            }
            else{
                point[0].x=-1;point[0].y=-1;
            }
        }
        for(int i=arrA;i<=maxarrA;i++){//하 체크
            if(arr[i][arrB]!=0){
                arr_result[1]=arr[i][arrB];
                point[1].x=i;point[1].y=arrB;
                break;
            }
            else{
                point[1].x=-1;point[1].y=-1;
            }
        }
        for(int i=arrB;i>=0;i--){//좌 체크
            if(arr[arrA][i]!=0){
                arr_result[2]=arr[arrA][i];
                point[2].x=arrA;point[2].y=i;
                break;
            }
            else{
                point[2].x=-1;point[2].y=-1;
            }
        }
        for(int i=arrB;i<=maxarrB;i++){//우 체크
            if(arr[arrA][i]!=0){
                arr_result[3]=arr[arrA][i];
                point[3].x=arrA;point[3].y=i;
                break;
            }
            else{
                point[3].x=-1;point[3].y=-1;
            }
        }


        return arr_result;
    }

    public void delete_Block(Point[] point,Server_Map_info map_info){
        ArrayList<Point> point_list[] = new ArrayList[5];//사방의 색상 별 좌표 리스트.0빨강,1노랑,2초록,3파랑,4보라,6빨강,7노랑,8초록,9파랑,10보라(1~5그냥6~10타이머)
        for(int i=0;i<5;i++)
            point_list[i] = new ArrayList<Point>();
        for(int i=0;i<4;i++){ //해당 색의 배열에 배열인덱스값 넣기.
            if(point[i].x!=-1&&point[i].y!=-1) {
                switch (map_info.my_map_arr[point[i].x][point[i].y]) {
                    case 1:
                        point_list[0].add(point[i]);
                        break;
                    case 2:
                        point_list[1].add(point[i]);
                        break;
                    case 3:
                        point_list[2].add(point[i]);
                        break;
                    case 4:
                        point_list[3].add(point[i]);
                        break;
                    case 5:
                        point_list[4].add(point[i]);
                        break;
                    case 6:
                        point_list[0].add(point[i]);
                        timeItemCount[1]++;
                        break;
                    case 7:
                        point_list[1].add(point[i]);
                        timeItemCount[2]++;
                        break;
                    case 8:
                        point_list[2].add(point[i]);
                        timeItemCount[3]++;
                        break;
                    case 9:
                        point_list[3].add(point[i]);
                        timeItemCount[4]++;
                        break;
                    case 10:
                        point_list[4].add(point[i]);
                        timeItemCount[5]++;
                        break;
                }
            }
        }
        HashMap<Integer,ArrayList<Point>> color_list = new HashMap<Integer,ArrayList<Point>>();//사방의 색 별로 좌표리스트.
        color_list.put(1,point_list[0]);
        color_list.put(2,point_list[1]);
        color_list.put(3,point_list[2]);
        color_list.put(4,point_list[3]);
        color_list.put(5,point_list[4]);

        for(int i=1;i<=5;i++){
            if(color_list.get(i).size()==4){
                if(map_info.item1act==true) {
                    map_info.plus_score += 600;
                    map_info.item1act=false;
                } else
                    map_info.plus_score+=300;

                for(int j=0;j<color_list.get(i).size();j++){
                    map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
                    map_info.my_map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                }
                map_info.game_time+=timeItemCount[i]*3;

                break;
            }
            else if(color_list.get(i).size()==3){
                if(map_info.item1act==true) {
                    map_info.plus_score += 200;
                    map_info.item1act=false;
                } else
                    map_info.plus_score+=100;

                for(int j=0;j<color_list.get(i).size();j++){
                    map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
                    map_info.my_map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                }
                map_info.game_time+=timeItemCount[i]*3;

                break;
            }
            else if(color_list.get(i).size()==2){
                if(map_info.plus_score==20||map_info.plus_score==40){//두개짜리가 두개인경우
                    if(map_info.plus_score==40) {
                        map_info.plus_score+=360;
                    } else
                        map_info.plus_score+=180;

                    for(int j=0;j<color_list.get(i).size();j++){
                        map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
                        map_info.my_map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                    }
                    map_info.game_time+=timeItemCount[i]*3;
                    break;
                }

                else { //두개짜리가 하나인경우
                    if(map_info.item1act==true) {
                        map_info.plus_score += 40;
                        map_info.item1act=false;
                    } else
                        map_info.plus_score += 20;

                    for(int j=0;j<color_list.get(i).size();j++){
                        map_info.delete_block.add(color_list.get(i).get(j));//저장된 좌표들을 map info 의 삭제될 좌표값에 저장
                        map_info.my_map_arr[color_list.get(i).get(j).x][color_list.get(i).get(j).y]=0;
                    }
                    map_info.game_time+=timeItemCount[i]*3;
                }
            }
        }
    }
}
