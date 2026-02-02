import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int len = name.length();
        int move = len - 1; // 오른쪽으로 계속 이동하는 경우
        
        for(int i = 0; i < len; i++){
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
            
            int iter = i+1;
            while(iter < len){
                if(name.charAt(iter) != 'A') break;
                iter++;
            }
            
            int turn = i * 2 + len - iter; //현재 i에서 왼쪽으로 돌아서, A연속 지점까지 오는 경우
            int turn2 = (len - iter) * 2 + i; //시작점에서 바로 왼쪽으로 돌아가다가, A연속 지점에서 다시 돌아서 i로 오는 경우
            
            move = Math.min(move, turn);
            move = Math.min(move, turn2);
            
        }
        
        return answer + move;
    }
}