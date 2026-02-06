import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        
        int cnt = 0;
        
        while(left < right){
            //두명 구출 가능한 경우
            if(people[left] + people[right] <= limit){
                cnt++;
                left++;
                right--;
            }    
            else{
                cnt++;
                right--;
            }
        }
        
        if(left == right) cnt++;
        
        return cnt;
    }
}