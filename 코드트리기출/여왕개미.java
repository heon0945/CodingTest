import java.io.*;
import java.util.*;

public class 여왕개미 {
    static int q;
    static int[] house;
    static boolean[] isBroken;
    static int houseNumber; // 현재 마지막 집 번호 (개미 집 개수)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        q = Integer.parseInt(br.readLine());

        // 최대 개미집 개수 30000
        house = new int[30001];
        isBroken = new boolean[30001];

        StringBuilder sb = new StringBuilder();

        while(q-- > 0){ // 마을 건설
            StringTokenizer st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 100){
                int n = Integer.parseInt(st.nextToken());
                for(int i = 1; i <= n; i++){
                    int pos = Integer.parseInt(st.nextToken());
                    house[i] = pos;
                }
                houseNumber = n;
            }
            else if(cmd == 200){ // 개미집 추가
                int p = Integer.parseInt(st.nextToken());
                house[++houseNumber] = p;
            }
            else if(cmd == 300){ // 개미집 철거
                int q = Integer.parseInt(st.nextToken());
                isBroken[q] = true;
            }
            else{ // cmd == 400, 개미집 정찰
                int r = Integer.parseInt(st.nextToken());
                int reportTime = inspection(r);
                sb.append(reportTime).append('\n');
            }
        }

        System.out.print(sb);
    }

    public static int inspection(int r){
        int left = 0;
        int right = 1000000000; // 10억
        int answer = 0;

        while(left <= right){
            int mid = (left + right) / 2;

            int prePos = 0; // 이전 집의 위치
            int start = 0; // 정찰해야 할 범위
            int curCnt = 0; // 현재 정찰 개미 수

            // 첫 정찰 개미의 위치
            for(int i = 1; i <= houseNumber; i++){
                if(isBroken[i]) continue;

                prePos = house[i];
                start = i;
                curCnt++;
                break;
            }

            // 본격 정찰 시행하며 주어진 간격 별로 정찰 개미 위치
            for(int i = start; i <= houseNumber; i++){
                if(isBroken[i]) continue;

                int gap = house[i] - prePos;

                // 간격 내에 있는 집
                if(gap <= mid) continue;

                //간격 밖의 집 -> 새로운 정찰 개미 필요
                prePos = house[i];
                curCnt++;
            }

            // 현재 정찰 개미 간 간격이 충분한 경우
            // 정찰 개미의 간격 최솟값을 찾아야 함 (최소 정찰 시간)
            if(curCnt <= r) {
                right = mid - 1;
                answer = mid;
            }
            // 현재 정찰 개미 간 간격이 부족한 경우 (주어진 정찰 개미 수로 모든 개미집을 커버할 수 없음)
            // 정찰 개미의 간격을 늘려야 함
            else{
                left = mid + 1;
            }
        }

        return answer;
    }
}