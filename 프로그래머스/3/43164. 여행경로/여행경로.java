import java.io.*;
import java.util.*;

class Solution {
    List<String> answers;
    boolean[] used;
    
    public String[] solution(String[][] tickets) {
        answers = new ArrayList<>();
        used = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN", tickets);
        
        Collections.sort(answers);
        
        StringTokenizer st = new StringTokenizer(answers.get(0));
                
        String[] ans = new String[st.countTokens()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = st.nextToken();
        }
        
        return ans;
        
    }
    
    public void dfs(int order, String path, String depart, String[][] tickets){
        if(order == tickets.length){
            answers.add(path);
            return;
        }
        
        for(int i = 0; i < tickets.length; i++){
            if(used[i]) continue;
            if(!tickets[i][0].equals(depart)) continue;
            
            used[i] = true;
            dfs(order + 1, path + " " + tickets[i][1], tickets[i][1], tickets);
            used[i] = false;
        }
    }
}