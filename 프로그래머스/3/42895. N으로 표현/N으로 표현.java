import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        if (N == number) {
            return 1;
        }
        
        
        List<Set<Integer>> dp = new ArrayList<>();
        for(int i = 0; i <= 8; i++){
            dp.add(new HashSet<>());
        }
        
        dp.get(1).add(N);
        
        //숫자 i개로 만들 수 있는 숫자 탐색
        for(int i = 2; i <= 8; i++){
            
            //i개 숫자 붙여서 만든 숫자
            String str = "";
            for(int j = 1; j <= i; j++){
                str += N;
            }
            dp.get(i).add(Integer.parseInt(str));
            
            
            //j개로 만들어진 숫자와 i-j개로 만들어진 숫자를 활용해서 -> i개로 만들어진 숫자 만들기 (사칙연산)
            for(int j = 1; j < i; j++){
                int k = i - j;
                
                for(int num1 : dp.get(j)){
                    for(int num2 : dp.get(k)){
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if(num2 != 0)
                            dp.get(i).add(num1 / num2);
                    }
                }
            }
            
            if(dp.get(i).contains(number)) return i;
            
        }
        
        return -1;
    }
}