import java.util.*;

class Solution {
    char[] vowels = new char[]{'A', 'E', 'I', 'O', 'U'};
    String target = "";
    int cnt = 0;
    boolean flag = false;
    
    public int solution(String word) {
        target = word;
        permutation(0, "");
        return cnt;
    }
    
    public void permutation(int order, String dictionary){
        if (flag) return;

        if (order > 0) {
            cnt++;
            if (dictionary.equals(target)) {
                flag = true;
                return;
            }
        }

        if (order == 5) return;
        
        for(int i = 0; i < 5; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(dictionary).append(vowels[i]);
            permutation(order + 1, sb.toString());
            
        }
    }
}