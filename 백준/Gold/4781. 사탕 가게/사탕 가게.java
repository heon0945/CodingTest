import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] money;
    static int[] calory;
    static int[] price;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);

            if(n == 0 && m == 0) {
                System.out.println(sb);
                return;
            }

            money = new int[m+1];

            calory = new int[n];
            price = new int[n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                calory[i] = Integer.parseInt(st.nextToken());
                price[i] = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);
            }

            money[0] = 0;
            for(int i = 1; i <= m; i++){
                money[i] = money[i-1];

                for(int j = 0; j < n; j++){
                    if(i - price[j] < 0) continue;
                    money[i] = Math.max(money[i], money[i-price[j]] + calory[j]);
                }
            }

            sb.append(money[m]).append('\n');
        }
    }

}
