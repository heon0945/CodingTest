import java.io.*;
import java.util.*;

public class Main {

    static int N, T;
    static int[] c; // 각 사냥터 입장 조건 경험치
    static int[] e; // 각 사냥터 1분당 경험치 획득량
    static int[][] moveTime; // 사냥터 간 이동 시간
    static int[][] dp; // dp[t][i] = t분에 i 사냥터에 있을 때 최대 경험치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        c = new int[N];
        e = new int[N];
        moveTime = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            c[i] = Integer.parseInt(st.nextToken());
            e[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                moveTime[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 초기화
        dp = new int[T + 1][N];
        for (int i = 0; i <= T; i++) {
            Arrays.fill(dp[i], -1); // 아직 방문하지 않은 상태는 -1
        }

        // 입장 조건이 0인 사냥터에서 시작 가능 (시간 0분, 경험치 0)
        for (int i = 0; i < N; i++) {
            if (c[i] == 0) {
                dp[0][i] = 0;
            }
        }

        // DP 진행
        for (int time = 0; time <= T; time++) {
            for (int i = 0; i < N; i++) {
                if (dp[time][i] < 0) continue; // 방문하지 않은 상태 스킵

                int currExp = dp[time][i];

                // 1. 같은 사냥터에서 1분 더 사냥 (시간 +1, 경험치 + e[i])
                if (time + 1 <= T) {
                    int nextExp = currExp + e[i];
                    // 입장 조건 만족 체크
                    if (nextExp >= c[i]) {
                        dp[time + 1][i] = Math.max(dp[time + 1][i], nextExp);
                    }
                }

                // 2. 다른 사냥터로 이동 (이동시간 추가, 경험치는 변하지 않음)
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    int arriveTime = time + moveTime[i][j];
                    if (arriveTime > T) continue; // 시간 초과 불가

                    // 도착 시점 경험치로 입장 조건 만족해야 함
                    if (currExp >= c[j]) {
                        dp[arriveTime][j] = Math.max(dp[arriveTime][j], currExp);
                    }
                }
            }
        }

        // T분 동안 얻을 수 있는 최대 경험치 계산
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dp[T][i]);
        }

        System.out.println(answer);
    }
}
