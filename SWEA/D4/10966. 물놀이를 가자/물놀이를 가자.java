import java.io.*;
import java.util.*;

public class Solution {
	static int tc;
	static int n, m;
	static char map[][];
	static int dist[][];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int answer;

	static class Node {
		int x, y, level;

		public Node(int x, int y, int level) {
			super();
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			answer = 0;

			map = new char[n][m];
			dist = new int[n][m];

			Queue<Node> q = new ArrayDeque<>();

			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < m; j++) {
					map[i][j] = str.charAt(j);
					dist[i][j] = Integer.MAX_VALUE;

					if (map[i][j] == 'W') {
						q.add(new Node(i, j, 0));
						dist[i][j] = 0;
					}
				}
			}

			while (!q.isEmpty()) {
				Node cur = q.poll();
				int curx = cur.x;
				int cury = cur.y;
				int curlv = cur.level;
				
				for (int i = 0; i < 4; i++) {
					int tx = curx + dx[i];
					int ty = cury + dy[i];

					if (OOB(tx, ty))
						continue;
					if (dist[tx][ty] <= curlv + 1)
						continue;
					dist[tx][ty] = curlv + 1;
					q.add(new Node(tx, ty, curlv + 1));
				}
			}
			
			// L칸의 거리 합 구하기

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 'L')
						answer += dist[i][j];
				}
			}

			sb.append(answer).append('\n');

		}
		System.out.println(sb);
	}

	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
}