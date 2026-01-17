class Solution {
    int[] permu;
    boolean[] visited;
    int size;
    int answer;
    public int solution(int k, int[][] dungeons) {
        size = dungeons.length;
        permu = new int[size];
        visited = new boolean[size];
        answer = 0;
        
        permutation(dungeons, k, 0);
        
        return answer;
    }
    
    public void permutation(int[][] dungeons, int k, int order){
        if(order == size){
            int cnt = 0;
            for(int i = 0; i < size; i++){
                int d = permu[i];
                if(dungeons[d][0] > k) continue;
                k -= dungeons[d][1];
                cnt++;
            }
            answer = Math.max(answer, cnt);
            return;
        }
        
        for(int i = 0; i < size; i++){
            if(visited[i]) continue;
            permu[order] = i;
            visited[i] = true;
            permutation(dungeons, k, order + 1);
            visited[i] = false;
        }
    }
}