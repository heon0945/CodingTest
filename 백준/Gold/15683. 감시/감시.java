import java.io.*;
import java.util.*;

public class Main {
	// 카메라의 각 방향 별로 조합 구하기 -> 4^8
	// 정해진 조합대로 맵에 볼 수 있는 구역 표시
	// 사각지대 개수 세기
	
	static int n, m;
	static int map[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0}; //우 하 좌 상
	static int dir[];
	static ArrayList<Cam> cams;
	static int answer;
	
	static class Cam{
		int x, y;
		int type;
		public Cam(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		cams = new ArrayList<>();
		answer = n * m;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] != 0 && map[i][j] != 6)
					cams.add(new Cam(i, j, map[i][j]-1));
			}
		}
		dir = new int[cams.size()];
		
		dircombi(0);
		
		System.out.println(answer);
	}
	
	static void dircombi(int order) {
		if(order == cams.size()) {
			answer = Math.min(answer, calc());
			return;
		}
		
		int type = cams.get(order).type;
		if(type == 4) {
			dir[order] = 0;
			dircombi(order + 1);			
		}
		else if(type == 1) {
			dir[order] = 0;
			dircombi(order + 1);
			dir[order] = 1;
			dircombi(order + 1);
		}
		else {
			for(int i = 0; i < 4; i++) {
				dir[order] = i;
				dircombi(order + 1);
			}
		}
			
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
	
	static int calc() {
//		System.out.println("CAMERA SIZE" + cams.size());
		for(int i = 0; i < cams.size(); i++) {
			Cam cur = cams.get(i);
			int tx = cur.x;
			int ty = cur.y;
			int type = cur.type;
			int dirx = 0, diry = 0;
			switch(type) {
				case 0 :
					dirx = dx[dir[i]];
					diry = dy[dir[i]];
					while(true) {
						tx += dirx;
						ty += diry;
						
						if(OOB(tx, ty)) break;
						if(map[tx][ty] == 6) break;
						if(map[tx][ty] > 0) continue;
						map[tx][ty] = -1;
					}					
					break;
				case 1 : 
					for(int t = 0; t < 2; t++) {
						tx = cur.x; ty = cur.y;
						dirx = dx[(dir[i] + t * 2) % 4];
						diry = dy[(dir[i] + t * 2) % 4];
						while(true) {
							tx += dirx;
							ty += diry;
							
							if(OOB(tx, ty)) break;
							if(map[tx][ty] == 6) break;
							if(map[tx][ty] > 0) continue;
							map[tx][ty] = -1;
						}		
					}
					break;
				case 2 : 
					for(int t = 0; t < 2; t++) {
						tx = cur.x; ty = cur.y;
						dirx = dx[(dir[i] + t) % 4];
						diry = dy[(dir[i] + t) % 4];
						while(true) {
							tx += dirx;
							ty += diry;
							
							if(OOB(tx, ty)) break;
							if(map[tx][ty] == 6) break;
							if(map[tx][ty] > 0) continue;
							map[tx][ty] = -1;
						}		
					}
					break;
				case 3 :
					for(int t = 0; t < 3; t++) {
						tx = cur.x; ty = cur.y;
						dirx = dx[(dir[i] + t) % 4];
						diry = dy[(dir[i] + t) % 4];
						while(true) {
							tx += dirx;
							ty += diry;
							
							if(OOB(tx, ty)) break;
							if(map[tx][ty] == 6) break;
							if(map[tx][ty] > 0) continue;
							map[tx][ty] = -1;
						}		
					}
					break;
				case 4 :
					
					for(int t = 0; t < 4; t++) {
						tx = cur.x; ty = cur.y;
						dirx = dx[t]; diry = dy[t];
						while(true) {
							tx += dirx;
							ty += diry;
							
							if(OOB(tx, ty)) break;
							if(map[tx][ty] == 6) break;
							if(map[tx][ty] > 0) continue;
							map[tx][ty] = -1;
						}		
					}
					
					break;
				default :
					break;
			
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == -1) map[i][j] = 0;
			}
		}
		
		return cnt;
	}
}