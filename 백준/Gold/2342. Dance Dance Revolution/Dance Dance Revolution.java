import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> cmd;

    //dp[idx][l][r] : idx부터 끝까지 발판 밟는데 필요한 최소 힘
    //idx : 현재 몇 번째 발판을 밟으려고 하는지
    //l : 왼발이 현재 있는 발판
    //r : 오른발이 현재 있는 발판
    static int[][][] dp;

    //force[i][j] : i -> j 발판을 밟을 때 드는 힘
    static int[][] force = {
            {1, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cmd = new ArrayList<>();

        while(true){
            int c = Integer.parseInt(st.nextToken());
            if(c == 0) break;

            cmd.add(c);
        }

        n = cmd.size();
        dp = new int[n][5][5];

        System.out.println(solution(0, 0, 0));
    }

    private static int solution(int idx, int l, int r){
        if(idx == n) return 0; //마지막 지시까지 완료

        if(dp[idx][l][r] != 0) return dp[idx][l][r]; //메모이제이션

        int next = cmd.get(idx); //지시 상으로 다음 밟아야 하는 발판

        dp[idx][l][r] = Math.min(
                solution(idx + 1, next, r) + force[l][next], //왼발 이동
                solution(idx + 1, l, next) + force[r][next] //오른발 이동
        );

        return dp[idx][l][r];
    }
}