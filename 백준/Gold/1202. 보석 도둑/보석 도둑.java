import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static TreeMap<Long, Integer> bag;
    static Jewelry[] jew;
    static long price;

    static class Jewelry implements Comparable<Jewelry>{
        long m;
        int v;
        Jewelry(long m, int v){
            this.m = m;
            this.v = v;
        }
        public int compareTo(Jewelry o){
            return o.v - this.v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jew = new Jewelry[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            jew[i] = new Jewelry(Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(jew);

        bag = new TreeMap<>();
        for(int i = 0; i < k; i++){
            long c = Long.parseLong(br.readLine());
            bag.put(c, bag.getOrDefault(c, 0) + 1);
        }

        int cnt = 0;
        for(int i = 0; i < n; i++){
            //이미 가방 가득찬 경우 break
            if(cnt >= k) break;

            //가격 높은 순대로 가방에 넣기
            Jewelry cur = jew[i];

            Map.Entry<Long, Integer> find = bag.ceilingEntry(cur.m);

            if(find == null) continue;

            cnt++;
            price += cur.v;
            if(find.getValue() > 1) bag.put(find.getKey(), find.getValue() - 1);
            else bag.remove(find.getKey());

        }

        System.out.println(price);


    }
}