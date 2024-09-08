import java.io.*;
import java.util.*;

public class Solution {
	static int tc;
	static int n;
	static int input[];
	static boolean visit[];
	static int weight[];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			answer = 0;
			n = sc.nextInt();
			input = new int[n];
			visit = new boolean[n];
			weight = new int[n];
			for(int i = 0; i < n; i++) {
				input[i] = sc.nextInt();
			}
			
			
			// 순열 만들기
			permu(0);

			
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static void permu(int order) {
		if(order == n) {
			dfs(0, 0, 0);
			return;
		}
		for(int i = 0; i < n; i++) {
			if(visit[i]) continue;
			weight[order] = input[i];
			visit[i] = true;
			permu(order + 1);
			visit[i] = false;
		}
		
	}
	
	
	static void dfs(int order, int left, int right) {
		if(order == n) {
			answer++;
			return;
		}
		
		//왼쪽에 올리는 경우
		dfs(order + 1, left + weight[order], right);
		//오른쪽에 올리는 경우
		if(right + weight[order] <= left)
			dfs(order + 1, left, right + weight[order]);
	}
}