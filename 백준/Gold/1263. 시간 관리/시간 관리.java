import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Work[] works;

    public static class Work implements Comparable<Work>{
        int t, s;
        Work(int t, int s){
            this.t = t;
            this.s = s;
        }
        public int compareTo(Work o){
            return this.s - o.s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        works = new Work[n];
        int total = 0;
        int max = 0;
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            works[i] = new Work(t, s);

            max = Math.max(max, s);
            total += t;
        }

        Arrays.sort(works);

        int start = max-total;

        for(int i = start; i >= 0; i--) {

            int cur = i;
            boolean valid = true;

            for (int j = 0; j < works.length; j++) {
                if (cur + works[j].t > works[j].s) {
                    valid = false;
                    break;
                }

                cur += works[j].t;
            }

            if (valid) {
                System.out.println(i);
                System.exit(0);
            }

        }

        System.out.println(-1);
    }
}