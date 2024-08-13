import java.util.*;
import java.io.*;


public class Solution {
	//크기만큼의 배열 생성
	//0, 0 좌표부터 시작해서 오른쪽 방향으로 진행 배열에 값 삽입
	//다음 좌표가 범위 벗어나거나 이미 값이 부여된 경우라면 시계 방향으로 방향 전환
		//방향 순서대로 dx dy 배열 생성
		//해당 방향의 경우 dx[i] dy[i] 만큼을 더하면서 진행
	//방향 전환할 곳이 없는 경우 종료
	//(n^2)되면 종료
	//배열 출력
	
	static int n;
	static int tc;
	
	public static boolean OOB(int value) {
		if(value >= 0 && value < n) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		tc = scanner.nextInt();
		for(int t = 1; t <= tc; t++) {
			n = scanner.nextInt();
			StringBuilder sb = new StringBuilder();
			
			
			int[][] board = new int[n][n];
			int[][] visit = new int[n][n];
			int[] dy = {1, 0, -1, 0}; //우 하 좌 상 (시계 방향으로 회전)
			int[] dx = {0, 1, 0, -1};
			
			int curdir = 0;
			
			board[0][0] = 1;
			visit[0][0] = 1;
			int cnt = 1;
			int curx = 0, cury = 0;
			while(cnt < n * n) {
				if(!OOB(curx + dx[curdir]) && !OOB(cury + dy[curdir]) 
						&& visit[curx + dx[curdir]][cury + dy[curdir]] == 0) {
					board[curx + dx[curdir]][cury + dy[curdir]] = ++cnt;
					visit[curx + dx[curdir]][cury + dy[curdir]] = 1;
				}
				else {
					curdir++;
					curdir = curdir % 4;
					board[curx + dx[curdir]][cury + dy[curdir]] = ++cnt;
					visit[curx + dx[curdir]][cury + dy[curdir]] = 1;
				}
				curx = curx + dx[curdir];
				cury = cury + dy[curdir];
			}
			
			System.out.println("#" + t);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append('\n');
			}
			System.out.print(sb);
		}
		
		
	}
}