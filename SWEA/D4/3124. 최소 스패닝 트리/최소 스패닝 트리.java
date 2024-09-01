import java.util.*;
import java.io.*;

public class Solution {

	static int tc;
	static int V, E;
	static Node adj[];
	static int minEdge[];
	static boolean visited[];
	static PriorityQueue<Vertex> pq;
	
	
	static class Node{
		int to;
		Node next;
		int weight;
		public Node(int to, Node next, int weight) {
			super();
			this.to = to;
			this.next = next;
			this.weight = weight;
		}
	}
	
	static class Vertex{ // pq에 저장할 때 사용
		int num;
		int w;
		public Vertex(int num, int w) {
			super();
			this.num = num;
			this.w = w;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			V = sc.nextInt();
			E = sc.nextInt();
			adj = new Node[V];
			
			for(int i = 0; i < E; i++) {
				int e1 = sc.nextInt()-1;
				int e2 = sc.nextInt()-1;
				int weight = sc.nextInt();
				adj[e1] = new Node(e2, adj[e1], weight);
				adj[e2] = new Node(e1, adj[e2], weight);
			}
			
			pq = new PriorityQueue<Vertex>((e1, e2) -> e1.w - e2.w);
			visited = new boolean[V];
			int minVertex = 0;
			long cost = 0;
			int cnt = 0;
			pq.add(new Vertex(0, 0));
			
			while(!pq.isEmpty()) {
				
				Vertex cur = pq.poll();
				
				
				if(visited[cur.num]) continue;
				
				cost += cur.w;
				cnt++;
				visited[cur.num] = true;
				
				if(cnt == V) break;
				
				
				//step2 : minEdge 업데이트
				Node n = adj[cur.num];
				while(n != null) {
					if(visited[n.to]) {
						n = n.next;
						continue;
					}
					pq.add(new Vertex(n.to, n.weight));
					n = n.next;
				}
				
			}
			sb.append(cost).append('\n');
		}
		System.out.println(sb);
		
	}
}