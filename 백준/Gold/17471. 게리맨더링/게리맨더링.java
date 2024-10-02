import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int population[];
	static boolean isA[];
	static boolean city[][];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		population = new int[n];
		isA = new boolean[n];
		city = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			population[i] = sc.nextInt();
		}
		
		for(int i = 0; i < n; i++) {
			int size = sc.nextInt();
			for(int j = 0; j < size; j++) {
				city[i][sc.nextInt()-1] = true;
			}
		}
		
		answer = Integer.MAX_VALUE;
		//선거구 나누기 : 부분집합
		separating(0);
		
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	
	static boolean isConnected(List<Integer> list, boolean flag) {
		int size = list.size();
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean visit[] = new boolean[n];
		
		int cnt = 0;
		int first = list.get(0);
		visit[first] = true;
		q.add(first);
		cnt++;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < n; i++) {
				if(visit[i]) continue;
				
				if(city[cur][i] && isA[i] == flag) {
					q.add(i);
					visit[i] = true;
					cnt++;
				}
			}
		}
		
		if(size == cnt) return true;
		else return false;
	}
	
	static void separating(int order) {
		if(order == n) {
			List<Integer> A = new ArrayList<>();
			List<Integer> B = new ArrayList<>();
			int popA = 0, popB = 0;
			//A와 B 구분
			for(int i = 0; i < n; i++) {
				if(isA[i]) {
					A.add(i);
					popA += population[i];
				}
				else {
					B.add(i);
					popB += population[i];
				}
			}
			
			//하나 이상인지 확인
			if(A.isEmpty() || B.isEmpty()) return;

			//연결되었는지 확인
			if(!isConnected(A, true) || !isConnected(B, false)) return;
			
			answer = Math.min(answer, Math.abs(popA-popB));
			return;
		}
		
		isA[order] = true;
		separating(order + 1);
		isA[order] = false;
		separating(order + 1);
		
	}
}