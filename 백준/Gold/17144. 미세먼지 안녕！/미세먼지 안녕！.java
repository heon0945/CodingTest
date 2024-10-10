import java.io.*;
import java.util.*;

public class Main {

	static int r, c, t;
	static int map[][];
	static int up = -1, down = -1; // 공기 청정기 위치
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int tdx[] = { 0, 1, 0, -1 };
	static int tdy[] = { 1, 0, -1, 0 };

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();
		t = sc.nextInt();

		map = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1) {
					if (up == -1)
						up = i;
					else
						down = i;
				}
			}
		}

		while (t > 0) {

			// 미세먼지 확산
			spread();
			//printMap(map);

			// 공기 청정기 작동
			cleaning(0, up, tdx, tdy);
			//printMap(map);
			
			cleaning(down, r - 1, dx, dy);
			//printMap(map);
			t--;
		}

		// 미세먼지 수 세기
		int cnt = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(map[i][j] == -1) continue;
				cnt += map[i][j];
			}
		}
		System.out.println(cnt);
		
	}

	static void cleaning(int start, int end, int dx[], int dy[]) {
		int tmp = map[start][0];
		if(tmp == -1) tmp = 0;
		int dir = 0;
		int x = start; int y = 0;
		while(true) {
				int nextX = x + dx[dir];
				int nextY = y + dy[dir];
				if(OOB(nextX, nextY, start, end, 0, c-1)) {
					dir = (dir + 1) % 4;
					nextX = x + dx[dir];
					nextY = y + dy[dir];
				}
				
				map[x][y] = map[nextX][nextY];
				if(nextX == start && nextY == 0) {
					map[x][y] = tmp;
					break;
				}
				if(map[nextX][nextY] == -1) map[x][y] = 0;
				x = nextX; y = nextY;
		}
		map[up][0] = -1;
		map[down][0] = -1;


	}

	static void spread() {
		int tmp[][] = new int[r][c];
		Queue<Pair> q = new ArrayDeque<>();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == -1 || map[i][j] == 0)
					continue;
				q.add(new Pair(i, j));
			}
		}

		int size = q.size();

		while (size > 0) {

			Pair cur = q.poll();
			ArrayList<Pair> sp = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				if (OOB(tx, ty, 0, r-1, 0, c-1))
					continue;
				if (map[tx][ty] == -1)
					continue;

				sp.add(new Pair(tx, ty));
			}

			int amount = map[cur.x][cur.y] / 5;
			tmp[cur.x][cur.y] += map[cur.x][cur.y] - map[cur.x][cur.y] / 5 * sp.size();
			for (Pair p : sp) {
				tmp[p.x][p.y] += amount;
			}

			size--;
		}

		tmp[up][0] = -1;
		tmp[down][0] = -1;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	static void printMap(int arr[][]) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static boolean OOB(int x, int y, int sr, int er, int sc, int ec) {
		return x < sr || x > er || y < sc || y > ec;
	}
}