import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] cards;
    static int[] scores;
    static int limit = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        scores = new int[n+1];
        cards = new int[limit+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int v = Integer.parseInt(st.nextToken());
            cards[v] = i;
        }

        for(int i = 1; i <= limit; i++){
            if(cards[i] == 0) continue; // 해당 카드를 가진 사람 없음
            int p1 = cards[i];
            for(int j = i * 2; j <= limit; j += i){
                if(cards[j] == 0) continue;

                int p2 = cards[j];
                scores[p1]++;
                scores[p2]--;

            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            sb.append(scores[i]).append(" ");
        }

        System.out.println(sb);


    }
}