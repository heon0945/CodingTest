import java.util.*;

class Solution {
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    public void dfs(int order, int sum, int[] numbers, int target){
        if(order == numbers.length){
            if(sum == target) {
                answer++;
            }
            return;
        }
            
        dfs(order + 1, sum + numbers[order], numbers, target);
        dfs(order + 1, sum - numbers[order], numbers, target);
    }
}