import java.io.*;
import java.util.*;

public class Solution {

	//nxn 배열 생성, 가로 세로 0~n-m까지 탐색
	//현재 자신이 왼쪽 꼭짓점이라 생각 -> 좌우로 0~m-1 정사각형 탐색 -> 파리 개수 업데이트
	//파리 개수가 현재 최댓값보다 큰 경우 정답 업데이트
	
	static int tc, n, m;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		tc = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= tc; t++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			board = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i <= n-m; i++) {
				for(int j = 0; j <= n-m; j++) {
					int flies = 0;
					for(int w = i; w <= i + m-1; w++) {
						for(int h = j; h <= j + m-1; h++) {
							flies += board[w][h];
						}
					}
					if(answer < flies)
						answer = flies;
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		
		
		
		System.out.println(sb);
		
	}
}