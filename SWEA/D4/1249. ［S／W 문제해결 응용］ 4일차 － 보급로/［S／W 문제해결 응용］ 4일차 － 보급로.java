import java.util.*;
import java.io.*;

public class Solution {

	static int n;
	static int map[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j = 0; j < n; j++) {
					map[i][j] = ch[j] - '0';
				}
			}
			System.out.println("#" + t + " " + getMinDist(0, 0, n-1, n-1));
			
		}
	}
	
	static int getMinDist(int sx, int sy, int ex, int ey) {
		
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[n][n];
		int[][] minTime = new int[n][n];
		// 일차원 배열 또는 클래스로 관리
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[sx][sy] = 0;
		pq.add(new int[] {sx, sy, minTime[sx][sy]});
		
		while(!pq.isEmpty()) {
			
			// step1 : pq poll -> 최솟값
			int[] stopOver = pq.poll();
			int r = stopOver[0];
			int c = stopOver[1];
			int time = stopOver[2];
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			// 도착지에 도달
			if(r == ex && c == ey) {
				return time;
			}
			
			// step2 : 최소 거리 업데이트
			for(int i = 0; i < 4; i++) {
				int tr = r + dx[i];
				int tc = c + dy[i];
				
				if(tr < 0 || tr >= n || tc < 0 || tc >= n) continue;
				if(visited[tr][tc]) continue;
				if(minTime[tr][tc] > time + map[tr][tc]) {
					minTime[tr][tc] = time + map[tr][tc];
					pq.add(new int[] {tr, tc, minTime[tr][tc]});
				}
				
			}
		}
		
		return -1; // 도착점에 도달하지 못한 경우
	}
			
}