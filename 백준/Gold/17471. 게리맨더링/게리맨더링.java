import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int population[];
	static boolean adjMat[][];
	static boolean select[];
	static ArrayList<Integer> inA, inB;
	static int answer;
	static int total;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		population = new int[n+1];
		adjMat = new boolean[n+1][n+1];
		select = new boolean[n+1];
		
		for(int i = 1; i <= n; i++) {
			population[i] = sc.nextInt();
			total += population[i];
		}
		answer = total;
		
		for(int i = 1; i <= n; i++) {
			int size = sc.nextInt();
			for(int j = 0; j < size; j++) {
				int next = sc.nextInt();
				adjMat[i][next] = true;
				adjMat[next][i] = true;
			}
		}
		
		// 구역 나누기
		separating(1);
		
		// 정답 출력
		if(answer == total)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
	
	static boolean connecting(ArrayList<Integer> list, boolean flag) {
		boolean visit[] = new boolean [n+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(list.get(0));
		visit[list.get(0)] = true;
		
		while(!q.isEmpty()) {
			
			int cur = q.poll();
			
			for(int i = 1; i <= n; i++) {
				
				if(!adjMat[cur][i]) continue;
				if(visit[i]) continue;
				if(select[i] != flag) continue;
				visit[i] = true;
				q.add(i);
			}
		}
		
		
		for(int i = 0; i < list.size(); i++) {
			if(!visit[list.get(i)]) return false;
		}
		return true;
		
	}
	
	static void separating(int order) {
		if(order == n+1) {
			inA = new ArrayList<>();
			inB = new ArrayList<>();
			
			for(int i = 1; i <= n; i++) {
				if(select[i]) inA.add(i);
				else inB.add(i);
			}
			
			//적어도 하나 포함
			if(inA.size() == 0 || inB.size() == 0) return;
			//모두 연결
			if(!connecting(inA, true) || !connecting(inB, false)) return;
			
			int ap = 0, bp = 0;
			for(int i = 0; i < inA.size(); i++) {
				ap += population[inA.get(i)];
			}
			bp = total - ap;
			
			answer = Math.min(answer, Math.abs(ap-bp));
			
			
			return;
		}
		
		select[order] = true;
		separating(order + 1);
		select[order] = false;
		separating(order + 1);
		
		
	}
}