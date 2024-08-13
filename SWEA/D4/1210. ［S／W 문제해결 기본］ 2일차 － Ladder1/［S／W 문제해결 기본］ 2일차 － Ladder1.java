import java.util.*;
import java.io.*;

public class Solution {
	//bfs
	//도착점 2를 마지막 줄에서 찾음
	//해당 시작점에서 좌우 탐색
		//1인 경우 해당 방향의 좌표 q 삽입
		//밖인 경우, 없는 경우 위쪽 방향의 좌표 q 삽입
		//하나만 삽입하고 브레이크
	//진행하다 현재 방향이 첫 줄인 경우 종료하고 해당 가로 인덱스 출력
	
	static class Pair{
		int first;
		int second;
		Pair(int a, int b){
			first = a;
			second = b;
		}
	}
	
	static boolean OOB(int v1, int v2) {
		if(v1 >= 0 && v1 < 100 && v2 >= 0 && v2 < 100)
			return false;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[][] map = new int[100][100]; 
		int[][] visit = new int[100][100];
		int[] dx = {0, 0, -1};
		int[] dy = {-1, 1, 0};
		
		for(int tc = 0; tc <= 9; tc++) {
			
			//new test case
			int t = Integer.parseInt(br.readLine()); //test case number
			//make 100x100 map
			for(int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine()); //첫번째 줄
				for(int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// find start point
			Pair start = new Pair(0, 0);
			for(int i = 0; i < 100; i++) {
				if(map[99][i] == 2) {
					start = new Pair(99, i);
					break;
				}
			}
			
			//initialize visit
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					visit[i][j] = 0;
				}
			}
			
			//search exit
			int curx = start.first;
			int cury = start.second;
			visit[curx][cury] = 1;
			while(true) {
				
				if(curx == 0) {
					sb.append("#").append(t).append(" ").append(cury).append('\n');
					break;
				}
				
				for(int i = 0; i < 3; i++) {
					int tmpx = curx + dx[i];
					int tmpy = cury + dy[i];
					
					if(!OOB(tmpx, tmpy) && visit[tmpx][tmpy] == 0 && map[tmpx][tmpy] == 1) {
						curx = tmpx;
						cury = tmpy;
						visit[curx][cury] = 1;
						break;
					}
					
				}
			}
			
		}
		System.out.print(sb);
		
	}
}