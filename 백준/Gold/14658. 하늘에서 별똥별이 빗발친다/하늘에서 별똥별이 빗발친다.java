import java.io.*;
import java.util.*;

public class Main {
    static int n, m, l, k;
    static int[] starX;
    static int[] starY;

    static class Pair implements Comparable<Pair>{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int compareTo(Pair o){
            if(this.x == o.x){
                return this.y - o.y;
            }
            return this.x - o.x;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        starX = new int[k];
        starY = new int[k];

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            starX[i] = x;
            starY[i] = y;
        }


        int cover = 0;
        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                int cnt = 0;
                for(int star = 0; star < k; star++){
                    if(starX[star] >= starX[i] && starX[star] <= starX[i] + l){
                        if(starY[star] >= starY[j] && starY[star] <= starY[j] + l){
                            cnt++;
                        }
                    }
                }
                cover = Math.max(cover, cnt);
            }
        }

        System.out.println(k - cover);

    }
}
