import java.io.*;
import java.util.*;

public class Main {

    static int t;
    static String str;
    static int k;
    static Map<Character, List<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            int a = 10001;
            int b = 0;

            str = br.readLine();
            k = Integer.parseInt(br.readLine());

            map = new HashMap<>();

            for(int i = 0; i < str.length(); i++){
                map.putIfAbsent(str.charAt(i), new ArrayList<>());

                map.get(str.charAt(i)).add(i);
            }

            for(int i = 0; i < 26; i++){
                char c = (char)('a' + i);

                if(map.get(c) == null) continue;
                List<Integer> list = map.get(c);
                if(list.size() < k) continue;

                for(int j = 0; j <= list.size() - k; j++){
                    int length = list.get(j+k-1) - list.get(j) + 1;
                    a = Math.min(a, length);
                    b = Math.max(b, length);
                }
            }

            if(a != 10001 && b != 0){
                sb.append(a).append(" ").append(b).append('\n');
            }
            else{
                sb.append(-1).append('\n');
            }
        }

        System.out.println(sb);
    }
}
