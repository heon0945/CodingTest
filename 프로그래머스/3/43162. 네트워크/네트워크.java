import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] check = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(check[i]) continue;
            
            bfs(i, computers, n, check);
            
            answer++;
        }
        
        return answer;
    }
    
    public void bfs(int order, int[][] computers, int n, boolean[] check){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(order);
        check[order] = true;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int i = 0; i < n; i++){
                if(cur == i) continue;
                if(computers[cur][i] == 0) continue;
                if(check[i]) continue;
                
                q.add(i);
                check[i] = true;
            }
        }
    }
}