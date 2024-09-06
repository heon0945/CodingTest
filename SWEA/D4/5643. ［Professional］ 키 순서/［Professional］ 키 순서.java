import java.io.*;
import java.util.*;

public class Solution {
	
	static int n, adjMat[][];
	static int cnt;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			
			adjMat = new int[n+1][n+1];
			StringTokenizer st;
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMat[a][b] = 1;
			}
			
			for(int i = 1; i <= n; i++) {
				adjMat[i][0] = -1;  //탐색되지 않은 학생을 나타냄 (탐색 완료 시 자신보다 큰 학생의 수를 저장)
			}
			
			
			for(int i = 1; i <= n; i++) {
				if(adjMat[i][0] != -1) continue;
				gtDFS(i);
			}
			
			//자신보다 작은 정점의 개수도 카운트
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					adjMat[0][j] += adjMat[i][j];
				}
			}
			
			for(int k = 1; k <= n; k++) {
				if(adjMat[k][0] + adjMat[0][k] == n-1)
					answer++;
			}
			System.out.println("#" + t + " " + answer);
		}
	}
	
	private static void gtDFS(int cur) {
		
		for(int i = 1; i <= n; i++) {
			if(adjMat[cur][i] == 0) continue; //관계 없는 정점은 패스 (나보다 큰 정점만 탐색)
			if(adjMat[i][0] == -1) { //아직 탐색 안 된 정점은 탐색 실행
				gtDFS(i);
			}
			//이미 탐색되어 정보가 저장된 정점
			if(adjMat[i][0] > 0) { //해당 정점보다 키가 큰 정점이 있다면 그 정보를 반영
				for(int j = 1; j <= n; j++) {
					if(adjMat[i][j] != 0)
						adjMat[cur][j] = 1; //현재 정점에도 업데이트
				}
			}
		}
		
		//자신보다 큰 정점의 정보를 전부 반영한 경우 (탐색 끝난 경우)
		adjMat[cur][0] = 0;
		for(int k = 1; k <= n; k++) {
			adjMat[cur][0] += adjMat[cur][k]; //키가 크고 작은 경우를 0과 1로만 표현하고 있기 때문에 결국 키가 큰 애들의 개수가 저장됨
		}
	}
	
}