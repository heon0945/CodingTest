class Solution {
    public int solution(int[] money) {
        //첫 집을 무조건 터는 경우 (마지막 집 털 수 없음)
        int[] dp_first = new int[money.length];
        //첫 집을 무조건 털지 않는 경우
        int[] dp_last  = new int[money.length];
        
        dp_first[0] = money[0];
        dp_first[1] = dp_first[0];

        dp_last[0] = 0;
        dp_last[1] = money[1];
        
        for(int i = 2; i < money.length; i++){
            
            if(i == money.length-1){
                dp_first[i] = dp_first[i-1];
                dp_last[i] = Math.max(dp_last[i-2] + money[i], dp_last[i-1]);
                continue;
            }
            
            dp_first[i] = Math.max(dp_first[i-2] + money[i], dp_first[i-1]);
            dp_last[i] = Math.max(dp_last[i-2] + money[i], dp_last[i-1]);
            
        }
        
        return Math.max(dp_first[money.length-1], dp_last[money.length-1]);
    }
}