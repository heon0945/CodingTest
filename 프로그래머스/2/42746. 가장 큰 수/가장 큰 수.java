import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] sorted_numbers = new String[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            sorted_numbers[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(sorted_numbers, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
            
        StringBuilder sb = new StringBuilder();
        for(String n : sorted_numbers)
            sb.append(n);
        
        String answer = sb.toString();
        if(answer.charAt(0) == '0') return "0";
        return answer;
    }
}