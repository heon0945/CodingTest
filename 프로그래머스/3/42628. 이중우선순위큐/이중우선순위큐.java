import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> queue = new TreeMap<>();
        
        for(String op : operations){
            
            StringTokenizer st = new StringTokenizer(op);
            
            char cmd = st.nextToken().charAt(0);
            int number = Integer.parseInt(st.nextToken());
            
            if(cmd == 'I'){
                if(queue.get(number) == null) queue.put(number, 1);
                else queue.put(number, queue.get(number) + 1);
            }
            else{
                if(queue.isEmpty()) continue;
                
                if(number == 1){
                    Map.Entry<Integer, Integer> max = queue.lastEntry();
                    if(max.getValue() == 1) queue.remove(max.getKey());
                    else queue.put(max.getKey(), max.getValue() - 1);
                }
                else{
                    Map.Entry<Integer, Integer> min = queue.firstEntry();
                    if(min.getValue() == 1) queue.remove(min.getKey());
                    else queue.put(min.getKey(), min.getValue() - 1);
                }
            }
            
        }
        
        if(queue.isEmpty()) return new int[]{0, 0};
        else return new int[]{queue.lastKey(), queue.firstKey()};
    }
}