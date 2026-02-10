import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] cloth = new int[n+1];
        Arrays.fill(cloth, 1);
        
        for(int r : reserve) cloth[r]++;
        for(int l : lost) cloth[l]--;
        
        Arrays.sort(lost);
        for(int l : lost){
            if(cloth[l] > 0) continue;
            
            if(l-1 > 0 && cloth[l-1] == 2){
                cloth[l-1]--;
                cloth[l]++;
                continue;
            }
            
            if(l+1 <= n && cloth[l+1] == 2){
                cloth[l+1]--;
                cloth[l]++;
                continue;
            }
        }
        
        int answer = 0;
        for(int i = 1; i < cloth.length; i++){
            if(cloth[i] > 0) answer++;
        }
        
        return answer;
        
    }
}