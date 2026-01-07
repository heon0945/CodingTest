import java.util.*;

class Solution {

    
    public int solution(int[][] jobs) {
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int count = 0; //현재 처리한 작업 개수
        int order = 0; //대기큐에 들어간 작업 개수 (대기큐에 들어가야 할 작업 순서)
        int total = 0; //반환 시간의 합
        int time = 0; //현재 시간
        
        while(count < jobs.length){
            
            while(order < jobs.length && jobs[order][0] <= time) 
                queue.add(jobs[order++]);
            
            if(queue.isEmpty()){
                time = jobs[order][0];
            }
            else{
                int[] cur = queue.poll();
                time += cur[1];
                total += (time - cur[0]);
                count++;
            }
        }
        
        return total / jobs.length;
    }
}