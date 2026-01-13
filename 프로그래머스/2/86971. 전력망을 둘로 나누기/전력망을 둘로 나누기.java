import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 0;
        boolean[][] graph = new boolean[n][n];
        
        for(int[] w : wires){
            int a = w[0] - 1;
            int b = w[1] - 1;
            graph[a][b] = true;
            graph[b][a] = true;
        }
        
        answer = n; //송전탑 개수 차의 최솟값을 찾기 위한 초기값
        for(int[] w : wires){
            int a = w[0] - 1;
            int b = w[1] - 1;
            
            //전선 끊기
            graph[a][b] = false;
            graph[b][a] = false;
            
            //각 송전탑의 개수 세기
            boolean[] visited = new boolean[n];
            List<Integer> cntList = new ArrayList<>();
            
            for(int i = 0; i < n; i++){
                //아직 방문 안 된 송전탑만
                if(visited[i]) continue;
                
                int cnt = 0;
                
                //bfs로 개수 세기
                ArrayDeque<Integer> q = new ArrayDeque<>();
                q.add(i);
                visited[i] = true;
                cnt++;
                
                while(!q.isEmpty()){
                    int cur = q.poll();
                    for(int j = 0; j < n; j++){
                        if(visited[j]) continue;
                        if(!graph[cur][j]) continue;
                        
                        q.add(j);
                        visited[j] = true;
                        cnt++;
                    }
                }
                
                cntList.add(cnt);
            }
            
            int cnt1 = cntList.get(0);
            int cnt2 = cntList.get(1);
            
            answer = Math.min(Math.abs(cnt1 - cnt2), answer);
            
            //전선 다시 잇기
            graph[a][b] = true;
            graph[b][a] = true;
            
        }
        
        return answer;
        
    }
}