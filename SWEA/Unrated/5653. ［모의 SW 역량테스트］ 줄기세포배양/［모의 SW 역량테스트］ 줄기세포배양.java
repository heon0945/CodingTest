import java.util.*;
import java.io.*;

public class Solution {
	
	static int tc;
	static int n, m, k;
	static int map[][];
	static ArrayList<Cell> cells;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int xsz, ysz;
	
	static class Cell{
		int status;
		int vital;
		int x, y;
		public Cell(int status, int vital, int x, int y) {
			super();
			this.status = status;
			this.vital = vital;
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			cells = new ArrayList<>();
			
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();
			xsz = n + 2 * k;
			ysz = m + 2 * k;
			map = new int[xsz][ysz];
			int ox = k, oy = k;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					int vital = sc.nextInt();
					map[ox + i][oy + j] = vital;
					if(vital != 0)
						cells.add(new Cell(0, vital, ox + i, oy + j));
				}
			}
			
			while(k >= 0) {
				
				//활성화 상태 세포 번식
				reproduction();
				
				//세포 상태 조정
				timePass();
				
//				for(int i = 0; i < xsz; i++) {
//					for(int j = 0; j < ysz; j++) {
//						System.out.print(map[i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println(cells.size());
//				System.out.println();
				
				k--;
			}
			
			sb.append(cells.size()).append('\n');
		}
		System.out.println(sb);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= xsz || y < 0 || y >= ysz;
	}
	
	static void timePass() {
		for(int i = cells.size()-1; i >= 0; i--) {
			Cell cur = cells.get(i);
			
			cur.vital--;
			if(cur.vital > 0) continue;
			
			if(cur.vital < 0 && cur.status == 0) {
				cur.status = 1;
				cur.vital = map[cur.x][cur.y];
			}
			else if(cur.vital <= 0 && cur.status == 1) {
				map[cur.x][cur.y] = -1;
				cells.remove(i);
			}
		}
	}
	
	static void reproduction() {
		Queue<Cell> q = new ArrayDeque<>();
		
		for(Cell cell : cells) {
			if(cell.status == 1) {
				q.add(cell);
			}
		}
		
		PriorityQueue<Cell> pq = new PriorityQueue<>((e1, e2) -> e2.vital-e1.vital);
		while(!q.isEmpty()) {
			
			Cell cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(OOB(tx, ty)) continue;
				if(map[tx][ty] != 0) continue;
				pq.add(new Cell(0, map[cur.x][cur.y], tx, ty));
			}	
		}
		
		while(!pq.isEmpty()) {
			Cell cur = pq.poll();
			if(map[cur.x][cur.y] != 0) continue;
			map[cur.x][cur.y] = cur.vital;
			cells.add(cur);
		}
		
	}
}