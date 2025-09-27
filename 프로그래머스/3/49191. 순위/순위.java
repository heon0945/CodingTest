class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] dist = new int[n][n];
        
        for(int i = 0; i < results.length; i++){
            int winner = results[i][0]-1;
            int loser = results[i][1]-1;
            dist[winner][loser] = 1;
            dist[loser][winner] = -1;
        }
        
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(i == j || dist[i][j] != 0) continue;
                    
                    if(dist[i][k] != 0 && dist[i][k] == dist[k][j])
                        dist[i][j] = dist[i][k];
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            int cnt = 0;
            for(int j = 0; j < n; j++){
                if(dist[i][j] != 0) cnt++;
            }
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}