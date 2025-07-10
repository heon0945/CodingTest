import java.io.*;
import java.util.*;

class Solution {
    
    Map<String, Boolean> checked;
    List<String>[] mappedIds;
    HashSet<String> result;
    
    public int solution(String[] user_id, String[] banned_id) {
        
        // 응모 아이디와 불량 사용자 아이디 비교
        // 불량 사용자 아이디 별 가능성 있는 응모 아이디를 추출
         mappedIds = new ArrayList[banned_id.length];
        
        for(int i = 0; i < banned_id.length; i++){
            mappedIds[i] = new ArrayList<>();
            
            for(String id : user_id){
                if(!same(id, banned_id[i])) continue;
                
                mappedIds[i].add(id);
            }
            
        }
        
        
        // 경우의 수 구하기
        // 하나의 아이디가 여러 불량 아이디에 매핑되지 않도록 처리
        checked = new HashMap<>();
        for(int i = 0; i < user_id.length; i++){
            checked.put(user_id[i], false);
        }
        
        result = new HashSet<>();
        select(0, new ArrayList<>());
        
        
        
        return result.size();
    }
    
    public void select(int cur, ArrayList<String> arr){
        
        if(cur == mappedIds.length){
            List<String> copy = new ArrayList<>(arr);
            Collections.sort(copy);
            String key = String.join(",", copy);
            result.add(key);
            return;
        }
        
        
        List<String> list = mappedIds[cur];
        for(int i = 0; i < list.size(); i++){
            String str = list.get(i);
            if(checked.get(str)) continue;
            
            checked.put(str, true);
            arr.add(str);
            select(cur + 1, arr);
            checked.put(str, false);
            arr.remove(str);
        }
    }
    
    public boolean same(String id, String banned_id){
        if(id.length() != banned_id.length()) return false;
        
        for(int i = 0; i < id.length(); i++){
            if(banned_id.charAt(i) != '*' && banned_id.charAt(i) != id.charAt(i)) return false;
        }
        
        return true;
    }
}