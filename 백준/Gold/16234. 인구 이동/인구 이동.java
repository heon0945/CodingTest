import java.io.*;
import java.util.*;

public class Main {
	static int n, l, r;
	static int population[][];
	static int day;
	static boolean open;
	static boolean visited[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	
	
	static public class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		l = sc.nextInt();
		r = sc.nextInt();
		
		population = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				population[i][j] = sc.nextInt();
			}
		}
		
		while(true) {
			open = false;
			
			//국경 오픈
			visited = new boolean[n][n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			if(!open) break;
			day++;
		}
		
		System.out.println(day);
		
	}
	
	static public void bfs(int i, int j) {
		
		Queue<Pair> q = new ArrayDeque<Pair>();
		Queue<Pair> check = new ArrayDeque<Pair>();
		
		int total = population[i][j];
		q.add(new Pair(i, j));
		check.add(new Pair(i, j));
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();
			
			for(int k = 0; k < 4; k++) {
				int tx = x + dx[k];
				int ty = y + dy[k];
				
				if(OOB(tx, ty)) continue;
				if(visited[tx][ty]) continue;
				int sub = Math.abs(population[tx][ty] - population[x][y]);
				if(l > sub || sub > r) continue;
				
				total += population[tx][ty];
				q.add(new Pair(tx, ty));
				check.add(new Pair(tx, ty));
				visited[tx][ty] = true;
			}
		}
		
		if(check.size() > 1) {
			open = true;
			int tmp = total / check.size();
			while(!check.isEmpty()) {
				int x = check.peek().x;
				int y = check.peek().y;
				population[x][y] = tmp;
				check.poll();
			}
		}
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
}