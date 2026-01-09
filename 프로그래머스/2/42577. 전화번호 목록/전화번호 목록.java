import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        
        for(String str : phone_book) set.add(str);
        
        for(String str : phone_book){
            StringBuilder prefix = new StringBuilder();
            for(int i = 0; i < str.length()-1; i++){
                prefix.append(String.valueOf(str.charAt(i)));
                
                if(set.contains(prefix.toString())) return false;
            }
        }
        
        return true;
    }
}