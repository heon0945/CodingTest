import java.util.*;
import java.io.*;

public class Solution {
	//받아온 농작물의 가치를 2차원 배열에 저장
	//이차원 배열 전체 순회
		//현재 위치와 nxn의 중심 n/2, n/2와의 거리 비교 (x차 + y차)
		//n/2보다 작거나 같은 경우 마름모에 포함 -> 가치 더함
	
	static int t;
	static int n;
	static int[][] board;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				String tmp = br.readLine();
				for(int j = 0; j < n; j++) {
					board[i][j] = tmp.charAt(j) - '0';
				}
			}
			
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(Math.abs(i - n/2) + Math.abs(j - n/2) <= n/2)
						answer += board[i][j];
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(answer).append('\n');
		}
		System.out.println(sb);
		
	}
	
}