import java.io.*;
import java.util.*;

public class Solution {
	static int tc;
	static int n, m;
	static char map[][];
	static int ans;
	static boolean visit[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			ans = 0;
			map = new char[n][m];
			visit = new boolean[n][m];
			Queue<Pair> q = new ArrayDeque<>(); 
			
			for(int i = 0; i < n; i++) {
				String tmp = br.readLine();
				for(int j = 0; j < m; j++) {
					map[i][j] = tmp.charAt(j);
					if(map[i][j] == 'W') {
						q.add(new Pair(i, j));
						visit[i][j] = true;
					}
				}
			}
			
			//bfs
			int dist = 0;
			while(!q.isEmpty()) {
				int sz = q.size();
				dist++;
				while(sz > 0) {
					Pair cur = q.poll();
					for(int i = 0; i < 4; i++) {
						int tx = cur.x + dx[i];
						int ty = cur.y + dy[i];
						
						if(OOB(tx, ty)) continue;
						if(visit[tx][ty]) continue;
						if(map[tx][ty] == 'L') {
							visit[tx][ty] = true;
							q.add(new Pair(tx, ty));
							ans += dist;
						}
					}
					sz--;
				}
			}
			
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
	
}