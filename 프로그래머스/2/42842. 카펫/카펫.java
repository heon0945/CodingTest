class Solution {
    public int[] solution(int brown, int yellow) {
        int pair = brown / 2 - 2; //x + y
        for(int i = 1; i < pair; i++){
            int y = i; //세로
            int x = pair - i; //가로
            if(x < y) break;
            
            if(x * y != yellow) continue;
            return new int[]{x + 2, y + 2};
        }
        return new int[]{0, 0};
    }
}