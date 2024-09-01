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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adj = new Node[V];
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int e1 = Integer.parseInt(st.nextToken())-1;
				int e2 = Integer.parseInt(st.nextToken())-1;
				int weight = Integer.parseInt(st.nextToken());
				adj[e1] = new Node(e2, adj[e1], weight);
				adj[e2] = new Node(e1, adj[e2], weight);
			}
			
			pq = new PriorityQueue<Vertex>((e1, e2) -> e1.w - e2.w);
			visited = new boolean[V];
			long cost = 0;
			int cnt = 0;
			pq.add(new Vertex(0, 0));
			
			while(!pq.isEmpty()) {
				
				Vertex cur = pq.poll();
				
				
				if(visited[cur.num]) continue;
				
				cost += cur.w;
				visited[cur.num] = true;
				cnt++;
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