import java.util.Scanner;

public class Main {
    static int n; // 포트 개수
    static int arr[]; //포트 별 연결 번호
    static int dp[]; // 길이가 k인 수열 중 가장 끝 값을 저장 (최소로 유지 -> 최대한 긴 수열을 만들 수 있도록)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        arr = new int[n];
        dp = new int[n+1]; // 가장 긴 수열의 최댓값 n을 커버

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }


        int cur = 0; // 현재까지 진행된 최장 수열 길이
        int idx = 0; // 새로운 값 자리 탐색에 사용
        for(int i = 0; i < n; i++){

            // 1. 새로운 요소가 지금까지 수열보다 큰 경우
            if(arr[i] > dp[cur]){

                // 길이 증가
                cur++;

                // 새로운 길이의 수열의 끝 값 업데이트
                dp[cur] = arr[i];
            }

            // 2. 새로운 요소가 지금까지 수열보다 작거나 같은 경우
            else{

                // 이진탐색으로 현재 원소의 적절한 자리 탐색
                idx = binarySearch(dp, 0, cur, arr[i]);
                dp[idx] = arr[i];
            }
        }

        System.out.println(cur);
    }

    static int binarySearch(int[] dp, int left, int right, int key) {
        int mid = 0;
        
        while(left < right) {
            mid = (left + right) / 2;
            if(dp[mid] < key) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return right;
    }
}
