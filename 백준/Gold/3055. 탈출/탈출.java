import java.io.*;
import java.util.*;

public class Main {
	
	static int r, c;
	static char map[][];
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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		
		Queue<Pair> hedge = new ArrayDeque<>();
		Queue<Pair> water = new ArrayDeque<>();
		boolean visitH[][] = new boolean[r][c];
		boolean visitW[][] = new boolean[r][c];
		
		for(int i = 0; i < r; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < c; j++) {
				map[i][j] = tmp.charAt(j);
				
				if(map[i][j] == 'S') {
					hedge.add(new Pair(i, j));
					visitH[i][j] = true;
				}
				else if(map[i][j] == '*') {
					water.add(new Pair(i, j));
					visitW[i][j] = true;
				}
			}
		}
		
		// bfs : 고슴도치 이동 -> 물 퍼지기 
		int time = 0;
		while(true) {
			if(hedge.isEmpty()) {
				System.out.println("KAKTUS");
				System.exit(0);
			}
			
			time++;
			
			//고슴도치 이동
			int size = hedge.size();
			//System.out.println("고슴" + size);
			while(size > 0) {
				Pair cur = hedge.poll();
				size--;
				
				//물이 번진 경우 -> 이동 불가
				if(visitW[cur.x][cur.y]) continue;
				//System.out.println(cur.x + " " + cur.y);
				for(int i = 0; i < 4; i++) {
					int tx = cur.x + dx[i];
					int ty = cur.y + dy[i];
					
					if(OOB(tx, ty)) continue;
					if(visitH[tx][ty] || visitW[tx][ty]) continue;
					if(map[tx][ty] == 'X') continue;
					
					//목적지 도착
					if(map[tx][ty] == 'D') {
						System.out.println(time);
						System.exit(0);
					}
					
					hedge.add(new Pair(tx, ty));
					visitH[tx][ty] = true;
				}
			}
			
			//물 번짐
			size = water.size();
			//System.out.println("물" + size);
			while(size > 0) {
				Pair cur = water.poll();
							
				for(int i = 0; i < 4; i++) {
					int tx = cur.x + dx[i];
					int ty = cur.y + dy[i];
					
					if(OOB(tx, ty)) continue;
					if(visitW[tx][ty]) continue;
					if(map[tx][ty] == 'X' || map[tx][ty] == 'D') continue;
					
					water.add(new Pair(tx, ty));
					visitW[tx][ty] = true;
				}
				size--;
			}
			
			
		}
		
		
		
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= r || y < 0 || y >= c;
	}
}