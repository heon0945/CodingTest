class Solution {
    public int solution(String arr[]) {
        int n = (arr.length + 1) / 2;  // 숫자 개수
        
        int[][] dpMax = new int[n][n];
        int[][] dpMin = new int[n][n];
        
        // 구간 길이 1 : 숫자 하나짜리 구간 (자신 그대로의 값)
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(arr[i * 2]);
            dpMax[i][i] = num;
            dpMin[i][i] = num;
        }
        
        // 구간 길이
        for (int len = 2; len <= n; len++) {
            //구간 시작
            for (int i = 0; i <= n - len; i++) {
                //구간 마지막
                int j = i + len - 1;
                
                dpMax[i][j] = Integer.MIN_VALUE;
                dpMin[i][j] = Integer.MAX_VALUE;
                
                // 분할 지점 (괄호 처리)
                for (int k = i; k < j; k++) {
                    String op = arr[k * 2 + 1];
                    
                    int leftMax = dpMax[i][k];
                    int leftMin = dpMin[i][k];
                    int rightMax = dpMax[k + 1][j];
                    int rightMin = dpMin[k + 1][j];
                    
                    if (op.equals("+")) {
                        dpMax[i][j] = Math.max(dpMax[i][j], leftMax + rightMax);
                        dpMin[i][j] = Math.min(dpMin[i][j], leftMin + rightMin);
                    } else { // "-"
                        dpMax[i][j] = Math.max(dpMax[i][j], leftMax - rightMin);
                        dpMin[i][j] = Math.min(dpMin[i][j], leftMin - rightMax);
                    }
                }
            }
        }
        
        return dpMax[0][n - 1];
    }
}