import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int s : scoville) pq.add(s);
        
        while(true){
            int cur = pq.poll();
            
            if(cur >= K) break;
            
            if(pq.isEmpty()) return -1;
            
            answer++;
            
            int next = pq.poll();
            
            pq.add(cur + next * 2);
            
        }
        return answer;
    }
}