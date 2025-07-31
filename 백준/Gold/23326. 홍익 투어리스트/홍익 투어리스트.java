import java.io.*;
import java.util.*;

public class Main {

    static int n, q;
    static TreeSet<Integer> sights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        sights = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            if(Integer.parseInt(st.nextToken()) == 1)
                sights.add(i);
        }

        StringBuilder sb = new StringBuilder();
        int now = 1;

        while(q-- > 0){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                int i = Integer.parseInt(st.nextToken());

                if(sights.contains(i)){
                    sights.remove(i);
                }
                else{
                    sights.add(i);
                }
            }
            else if(cmd == 2){
                int x = Integer.parseInt(st.nextToken());
                now = (now + x - 1) % n + 1;

            }else{ //cmd == 3
                if(sights.isEmpty()){
                    sb.append(-1).append('\n');
                }
                else if(sights.contains(now)){
                    sb.append(0).append('\n');
                }
                else{
                    Integer idx = sights.ceiling(now);

                    if(idx != null){
                        sb.append(idx - now).append('\n');
                    }
                    else{
                        sb.append((n - now + sights.first())).append('\n');
                    }
                }
            }

        }
        System.out.println(sb);
    }
}
