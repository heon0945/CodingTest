import java.util.*;

class Solution {
    
    class Node{
        String str;
        int cnt;
        
        public Node(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        
        int size = begin.length();
        boolean[] visited = new boolean[words.length];
        
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(begin, 0));
        
        while(!q.isEmpty()){
            
            Node cur = q.poll();
            String curStr = cur.str;
            int curCnt = cur.cnt;
            
            if(curStr.equals(target)) return curCnt;
            
            //현재 단어가 목표 단어랑 같은지 체크
            for(int i = 0; i < size; i++){
                for(int j = 0; j < 26; j++){
                    String nStr = curStr.substring(0, i) + (char)('a' + j) + curStr.substring(i + 1);     
                    for(int k = 0; k < words.length; k++){
                        if(visited[k]) continue;
                        if(nStr.equals(words[k])){
                            System.out.println(nStr);
                            q.add(new Node(nStr, curCnt + 1));
                            visited[k] = true;
                        }
                    }
                }
            }    
            
        }
        
        return 0;
    }
}