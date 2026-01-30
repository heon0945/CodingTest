class Solution {
    final int mod = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        int dp[][] = new int[n][m];
        
        for(int[] p : puddles){
            dp[p[1]-1][p[0]-1] = -1;
        }
        
        dp[0][0] = 1;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                
                if(i == 0 && j == 0) continue;
                if(dp[i][j] == -1) continue;
                
                //left
                if(j-1 >= 0 && dp[i][j-1] > 0)
                    dp[i][j] = ( dp[i][j] + dp[i][j-1] ) % mod;
                
                //up
                if(i-1 >= 0 && dp[i-1][j] > 0) 
                    dp[i][j] = ( dp[i][j] + dp[i-1][j] ) % mod;
            }
        }
        
        return dp[n-1][m-1];
    }
}