import java.io.*;
import java.util.*;

class Solution {
    
    public class Node implements Comparable<Node>{
        int n;
        int weight;
        
        public Node(int n, int weight){
            this.n = n;
            this.weight = weight;
        }
        
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Node>[] graph = new List[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edge.length; i++){
            int start = edge[i][0]-1;
            int end = edge[i][1]-1;
            graph[start].add(new Node(end, 1));
            graph[end].add(new Node(start, 1));
        }
        
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;
        
        dijkstra(graph, minDist);
        
        int maxDist = 0;
        for(int i = 0; i < n; i++){
            maxDist = Math.max(maxDist, minDist[i]);
        }
        
        for(int i = 0; i < n; i++){
            if(maxDist == minDist[i]) answer++;
        }
        
        return answer;
    }
    
    public void dijkstra(List<Node>[] graph, int[] minDist){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.add(new Node(0, 0));
        
        while(!pq.isEmpty()){
            
            Node cur = pq.poll();
            
            if(cur.weight > minDist[cur.n]) continue;
            
            for(Node node : graph[cur.n]){
                
                int newDist = minDist[cur.n] + node.weight;
                
                if(newDist < minDist[node.n]){
                    minDist[node.n] = newDist;
                    pq.add(new Node(node.n, newDist));
                }
            }
        }
    }
}