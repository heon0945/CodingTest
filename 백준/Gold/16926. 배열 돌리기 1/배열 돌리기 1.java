import java.util.*;
import java.io.*;

public class Main {
	//바깥 쪽 둘레부터 회전 -> 안쪽 둘레 회전
	//회전 시 회전수 / 주기 : n * 2 + (m-2) * 2 만큼 회전
	//안 쪽 들어가면 n = n - 2, m = m - 2, 경계 조절 0 + k (k = 0, 1, 2)
	
	static int n, m, k;
	static int[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0}; //우, 하, 좌, 상
	
	
	static void rotate(int x, int y, int b) {
		int tmpx = x;
		int tmpy = y;
		int tmp = board[tmpx][tmpy];
		int i = 0;
		while(true) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if(tx < 0 + b || tx >= n - b || ty < 0 + b || ty >= m - b) {
				i++;
				tx = x + dx[i];
				ty = y + dy[i];
			}
			if(tx == tmpx && ty == tmpy) {
				board[x][y] = tmp;
				break;
			}
			
			board[x][y] = board[tx][ty];
			x = tx;
			y = ty;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = scanner.nextInt();
		m = scanner.nextInt();
		k = scanner.nextInt();
		
		board = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				board[i][j] = scanner.nextInt();
			}
		}
	
		//바깥쪽 -> 안쪽
		int b = 0;
		while(m - b - 1 >= 0 + b  && n - b - 1 >= 0 + b) {
			int tmpn = n - 2 * b;
			int tmpm = m - 2 * b;
			int tmpk = k % (tmpn * 2 + (tmpm-2) * 2);
			
			for(int i = 0; i < tmpk; i++) {
				//tmpk번 회전
				rotate(0 + b, 0 + b, b);
			}
			
			b++;
		}
		
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
	
	
}