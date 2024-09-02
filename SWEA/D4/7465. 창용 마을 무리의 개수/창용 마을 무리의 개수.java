import java.io.*;
import java.util.*;

public class Solution {

	static int tc;
	static int n, m;
	static boolean visit[];
	static int answer;
	static int map[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			answer = 0;
			
			n = sc.nextInt();
			m = sc.nextInt();
			
			map = new int[n+1][n+1];
			visit = new boolean[n + 1];
			
			for(int i = 0; i < m; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				map[a][b] = 1;
				map[b][a] = 1;
			}
			
			for(int i = 1; i <= n; i++) {
				if(visit[i]) continue;
				answer++;
				Queue<Integer> q = new ArrayDeque<Integer>();
				q.add(i);
				visit[i] = true;
				while(!q.isEmpty()) {
					int cur = q.poll();
					
					for(int j = 1; j <= n; j++) {
						if(visit[j]) continue;
						if(map[cur][j] == 1) {
							q.add(j);
							visit[j] = true;
						}
					}
				}
			}
			
			
			sb.append(answer).append('\n');
			
		}
		System.out.println(sb);
	}
}