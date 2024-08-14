import java.io.*;
import java.util.*;

// 18,724 kb, 118 ms

public class Solution {

	//nxn 배열 생성, 배열의 값을 축적하는 배열 생성 
	//가로 세로 m-1~n-1까지 탐색
	//파리 수 확인하면서 최댓값 업데이트
	
	static int tc, n, m;
	static int[][] board;
	static int[][] accum;
	
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
			
			//make basic array
			board = new int[n+1][n+1];
			accum = new int[n+1][n+1];
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//make accumulation array		
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					accum[i][j] = board[i][j] + accum[i-1][j] + accum[i][j-1] - accum[i-1][j-1];
				}
			}
			
			//search
			int flies = 0;
			for(int i = m; i <= n; i++) {
				for(int j = m; j <= n; j++) {
					flies = accum[i][j] - accum[i-m][j] - accum[i][j-m] + accum[i-m][j-m];
					if(answer < flies)
						answer = flies;
				}
			}
			
			
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		
		
		
		System.out.println(sb);
		
	}
}