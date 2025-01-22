import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int tc;
	static int n, k;
	static int dev[];
	static ArrayList<Integer> adj[];
	static int time[];
	static int target;
	static int degree[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		while(tc > 0) {
			
			n = sc.nextInt();
			k = sc.nextInt();
			
			dev = new int[n];
			adj = new ArrayList[n];
			time = new int[n];
			degree = new int[n];
			
			for(int i = 0; i < n; i++) {
				dev[i] = sc.nextInt();
				adj[i] = new ArrayList<>();
			}
			
			
			for(int i = 0; i < k; i++) {
				int from = sc.nextInt()-1;
				int to = sc.nextInt()-1;
				adj[from].add(to);
				degree[to]++;
			}
			
			target = sc.nextInt()-1;
			
			
			sb.append(findTime()).append('\n');
			
			tc--;
		}
		System.out.println(sb);
	}
	
	static int findTime() {
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			if(degree[i] == 0) {
				q.add(i);
				time[i] = dev[i];
			}
		}
		
		while(!q.isEmpty()) {
			
			int cur = q.poll();
			
			for(int i = 0; i < adj[cur].size(); i++) {
				int next = adj[cur].get(i);
				
				time[next] = Math.max(time[cur] + dev[next], time[next]);
				degree[next]--;
				
				if(degree[next] == 0) q.add(next);
			}
		}
		
		return time[target];
		
	}
}