import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        List<Integer> camera = new ArrayList<>();
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        for(int[] r : routes){
            int start = r[0];
            int end = r[1];
            
            boolean flag = false;
            for(int c : camera){
                if(start <= c && end >= c) {
                    flag = true;
                    break;
                }
            }
            
            if(!flag) camera.add(end);
            
        }
        
        return camera.size();
        
    }
}