import java.io.*;
import java.util.*;

public class Solution {
	
	static int n, adjMat[][];
	static int cnt;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			
			adjMat = new int[n+1][n+1];
			StringTokenizer st;
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMat[a][b] = 1;
			}
			
			
			for(int i = 1; i <= n; i++) {
				cnt = 0;
				//bfs
//				if(gtBFS(i) + ltBFS(i) == n-1) {
//					answer++;
//				}	
				//dfs
				gtDFS(i, new boolean[n+1]);
				ltDFS(i, new boolean[n+1]);
				if(cnt == n-1) answer++;
				
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	private static void gtDFS(int cur, boolean[] visit) {
		
		visit[cur] = true;
		
		for(int i = 1; i <= n; i++) {
			if(visit[i]) continue;
			if(adjMat[cur][i] != 0) {
				gtDFS(i, visit);
				cnt++;
			}
		}
	}
	
private static void ltDFS(int cur, boolean[] visit) {
		
		visit[cur] = true;
		
		for(int i = 1; i <= n; i++) {
			if(visit[i]) continue;
			if(adjMat[i][cur] != 0) {
				ltDFS(i, visit);
				cnt++;
			}
		}
	}
	
	private static int gtBFS(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean visit[] = new boolean[n+1];
		
		q.add(start);
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 1; i <= n ; i++) {
				if(visit[i]) continue;
				if(adjMat[cur][i] != 0) {
					q.add(i);
					visit[i] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static int ltBFS(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean visit[] = new boolean[n+1];
		
		q.add(start);
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 1; i <= n ; i++) {
				if(visit[i]) continue;
				if(adjMat[i][cur] != 0) {
					q.add(i);
					visit[i] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
}