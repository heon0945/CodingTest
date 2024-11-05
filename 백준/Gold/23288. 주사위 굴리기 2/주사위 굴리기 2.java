import java.io.*;
import java.util.*;

public class Main {

	static int n, m; //보드 크기
	static int k; //주사위 이동 횟수
	static int board[][]; //보드 정보 저장
	static int diceX, diceY, diceDir; //주사위 위치 정보와 방향정보
	static int dice[]; //주사위 값 저장 : 위 앞 밑 뒤 왼 오 순서
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0}; //주사위 정보 저장 : 시계 방향
	static int score;
	
	static class Pair{
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
		m = sc.nextInt();
		k = sc.nextInt();
		
		board = new int[n][m];
		dice = new int[6];
		
		//초기 주사위 값 저장
		dice[0] = 1;
		dice[1] = 5;
		dice[2] = 6;
		dice[3] = 2;
		dice[4] = 4;
		dice[5] = 3;
		
		//보드 값 저장
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		//주사위 굴리기 시작
		while(k > 0) {
			k--;
			
			//이동할 수 있는지 판단
			int nx = diceX + dx[diceDir];
			int ny = diceY + dy[diceDir];
			if(OOB(nx, ny)) { //이동 불가한 경우
				diceDir = (diceDir + 2) % 4; //방향 전환
				nx = diceX + dx[diceDir];
				ny = diceY + dy[diceDir];
			}
			
			//주사위 이동
			diceX = nx; diceY = ny;
			//주사위 전체 값 조정
			roll(diceDir);
			
			//점수 계산
			score += bfs(diceX, diceY) * board[diceX][diceY];
			
			//이동 방향 결정
			int A = dice[2]; //주사위 아랫면
			int B = board[diceX][diceY]; //주사위 칸 값
			if(A > B) diceDir = (diceDir + 1) % 4; //시계 방향 변화
			else if(A < B) diceDir = (diceDir + 3) % 4; //반시계 방향 변화
		}
		
		System.out.println(score);
		
	}
	
	static void roll(int dir) {
		int tmp[] = new int[6];
		for(int i = 0; i < 6; i++)
			tmp[i] = dice[i];
		
		if(dir == 0) { //동
			tmp[0] = dice[4];
			tmp[4] = dice[2];
			tmp[2] = dice[5];
			tmp[5] = dice[0];
		}
		else if(dir == 1) { //남
			tmp[0] = dice[3];
			tmp[1] = dice[0];
			tmp[2] = dice[1];
			tmp[3] = dice[2];
		}
		else if(dir == 2) { //서
			tmp[0] = dice[5];
			tmp[4] = dice[0];
			tmp[2] = dice[4];
			tmp[5] = dice[2];
		}
		else if(dir == 3) { //북
			tmp[0] = dice[1];
			tmp[1] = dice[2];
			tmp[2] = dice[3];
			tmp[3] = dice[0];
		}
		
		for(int i = 0; i < 6; i++)
			dice[i] = tmp[i];
	}
	
	//현재 위치에서 이동할 수 있는 칸의 수 세기
	static int bfs(int x, int y) {
		
		int target = board[x][y]; //이동할 수 있는 칸의 값
		int cnt = 1; //이동할 수 있는 칸 개수 저장

		Queue<Pair> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[n][m];
		
		q.add(new Pair(x, y));
		visit[x][y] = true;
		
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(OOB(tx, ty)) continue;
				if(visit[tx][ty]) continue;
				if(board[tx][ty] != target) continue;
				
				cnt++;
				q.add(new Pair(tx, ty));
				visit[tx][ty] = true;
			}
			
		}
		return cnt;
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
}