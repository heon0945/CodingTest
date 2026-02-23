import java.util.*;

class Pair{
    int x, y;
    
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(Object o){
        return this.x == ((Pair)o).x && this.y == ((Pair)o).y;
    }
    
    public int hashCode() {
        return Objects.hash(x,y);
    }
}

class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        
        Map<Pair, ArrayList<Pair>> visited = new HashMap<>();
        Pair cur = new Pair(0, 0);
        
        for(int arrow : arrows){
            for(int i = 0; i <= 1; i++){
                Pair next = new Pair(cur.x + dx[arrow], cur.y + dy[arrow]);
                
                if(!visited.containsKey(next)){
                    ArrayList<Pair> nextEdge = new ArrayList<>();
                    nextEdge.add(cur);
                    visited.put(next, nextEdge);
                    
                    if(!visited.containsKey(cur)){
                        ArrayList<Pair> curEdge = new ArrayList<>();
                        curEdge.add(next);
                        visited.put(cur, curEdge);
                    }
                    else{
                        visited.get(cur).add(next);
                    }
                }
                else if(!visited.get(next).contains(cur)){
                    visited.get(next).add(cur);
                    visited.get(cur).add(next);
                    answer++;
                }
                
                cur = next;
            }
        }
        
        return answer;
    }
}