import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance;
        
        while(left <= right){
            int mid = ( left + right ) / 2;
            
            int cnt = removeRocks(mid, distance, rocks);
            
            if(cnt > n){ //너무 돌을 많이 제거
                right = mid - 1; //최소 거리 줄이기 (더 많은 돌이 필요해서 적게 제거)
            }
            else{ //돌을 딱맞게 제거하거나, 적게 제거 -> 많이 제거해야 함
                answer = mid;
                left = mid + 1; //최소 거리 늘리기 (더 적은 돌이 필요해서 많이 제거)
            }
        }
        
        return answer;
    }
    
    public int removeRocks(int gap, int distance, int[] rocks){
        int pre = 0;
        int cnt = 0;
        
        for(int r : rocks){
            //최소 간격보다 작은 거리면 제거하기
            if(r - pre < gap){
                cnt++;
            }
            else{
                pre = r;
            }
        }
        
        if(distance - pre < gap) cnt++;
        
        return cnt;
    }
}