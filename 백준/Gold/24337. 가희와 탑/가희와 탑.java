import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int a, b;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        if(n < (a + b - 1)) {
            System.out.println(-1);
            return;
        }

        List<Integer> height = new ArrayList<>();

        int maxHeight = Math.max(a, b);

        for(int i = 1; i < a; i++){
            height.add(i);
        }

        height.add(maxHeight);

        for(int i = b-1; i >= 1; i--){
            height.add(i);
        }

        while(height.size() < n){
            height.add(1, 1);
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < height.size(); i++){
            sb.append(height.get(i)).append(" ");
        }

        System.out.println(sb);
    }

}
