import java.util.*;

class Solution {
    int[] parents;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        Set<Integer> set = new HashSet<>();
        for(int[] bridge : costs){
            set.add(bridge[0]);
            set.add(bridge[1]);
        }
        
        parents = new int[set.size()];
        Arrays.fill(parents, -1);
        
        int answer = 0;
        int cnt = 0;
        for(int[] bridge : costs){
            
            if(cnt >= set.size() - 1) break;
            
            int island1 = bridge[0];
            int island2 = bridge[1];
            int cost = bridge[2];
            
            if(!union(island1, island2)) continue;
            
            cnt++;
            answer += cost;
        }
        
        return answer;
    }
    
    public int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    public boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        
        if(parentA == parentB) return false;
        
        if(parents[parentA] < parents[parentB]){
            parents[parentA] += parents[parentB];
            parents[parentB] = parentA;
        }
        else{
            parents[parentB] += parents[parentA];
            parents[parentA] = parentB;
        }
        return true;
    }
}