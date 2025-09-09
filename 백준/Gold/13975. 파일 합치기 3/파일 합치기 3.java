import java.io.*;
import java.util.*;

public class Main {

    static int t;
    static int k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        while(t-->0){
            k = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < k; i++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            long total = 0;
            while(pq.size() > 1){
                long first = pq.poll();
                long second = pq.poll();

                long sum = first + second;
                total += sum;
                pq.add(sum);
            }

            sb.append(total).append('\n');
        }

        System.out.println(sb);

    }
}
