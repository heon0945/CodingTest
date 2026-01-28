import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for(int i = triangle.length - 2; i >= 0; i--) { // 아래층부터 위로 채워나감
            for(int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
    
        answer = triangle[0][0];
        return answer;
    }
}