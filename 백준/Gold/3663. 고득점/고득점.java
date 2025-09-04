import java.io.*;
import java.util.*;

public class Main {
    static int tc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        tc = Integer.parseInt(br.readLine());

        while(tc-- > 0){
            String str = br.readLine();
            int len = str.length();

            int total = 0;

            //각 알파벳 찾기 횟수
            for(int i = 0; i < len; i++){
                int order = str.charAt(i) - 'A';
                total += Math.min(order, 26-order);
            }

            //최적 이동 횟수
            //1. 처음부터 끝까지 쭉 진행한 경우
            int move = len-1;

            //2. 돌아서 가는 경우 : 첫칸 - 경유지 - 첫칸 - 목적지
            for(int i = 0; i < len; i++){

                //목적지
                int next = i + 1;

                //목적지가 A라면 건너뛰기 (굳이 방문 x)
                while(next < len && str.charAt(next) == 'A') next++;

                //첫칸 - 오른쪽 이동 - 돌아서 왼쪽 이동 - 목적지
                move = Math.min(move, i + i + len - next);
                //첫칸 - 왼쪽 이동 - 돌아서 오른쪽 이동 - 목적지
                move = Math.min(move, len - next + len - next + i);
            }

            total += move;

            sb.append(total).append('\n');
        }
        System.out.println(sb);
    }
}