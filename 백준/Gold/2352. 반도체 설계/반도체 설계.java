import java.io.*;
import java.util.*;


// n개의 포트를 차례대로 연결할 때
// i번째 포트가 연결된 포트를 Pi라고 한다면
// Pi-1 < Pi를 성립해야만 겹치고 교차하는 현상을 방지할 수 있음
// 즉 무조건 이전의 값보다 새로운 값은 큰 값을 가지도록 갱신
// LIS (최장 증가 부분 수열) 알고리즘 : dp + 탐색 최적화

// 1. 길이가 k까지 완성된 수열 중 가장 끝 값(가장 큰 값)을 저장하는 배열 dp[k] -> 끝 값이 가능한 작게 유지 (작아야 더 긴 수열을 만들 가능성 높아짐)
// 2. n만큼 반복하면서 현재 완성된 가장 긴 수열과 비교
    // 3-1. 현재까지 완성된 수열의 끝 값보다 크다면, 수열에 끝 값으로 추가
    // 3-2. 현재까지 완성된 수열의 끝 값보다 작다면, 완성된 수열의 중간에 적절한 차리를 대체
// 4. 완성된 수열의 최댓값 출력


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

                // 지금까지 나온 수열 중 자신의 자리 탐색 (자신보다 작은 것들 다음에 추가)
                for(idx = 1; idx <= cur; idx++){

                    //자신보다 큰 값을 발견하면 해당 자리 대체 (더 작은 값이 들어감으로써 더 긴 수열을 만들 수 있음)
                    if(arr[i] <= dp[idx]) break;
                }
                dp[idx] = arr[i];
            }
        }

        System.out.println(cur);

    }

}