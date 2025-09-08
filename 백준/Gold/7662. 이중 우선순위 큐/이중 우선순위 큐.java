import java.io.*;
import java.util.*;

public class Main {

    static int t;
    static int k;
    static TreeSet<Integer> q;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            k = Integer.parseInt(br.readLine());
            q = new TreeSet<>();

            map = new HashMap<>();

            while(k-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());

                String cmd = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                if(cmd.equals("I")){
                    q.add(value);

                    if(map.containsKey(value)){
                        map.put(value, map.get(value) + 1);
                    }
                    else{
                        map.put(value, 1);
                    }
                }
                else if(cmd.equals("D")){
                    if(!q.isEmpty()){
                        if(value == 1){
                            int max = q.last();

                            if(map.get(max) > 1) map.put(max, map.get(max) - 1);
                            else{
                                map.remove(max);
                                q.pollLast();
                            }
                        }
                        else if(value == -1){
                            int min = q.first();
                            if(map.get(min) > 1) map.put(min, map.get(min) - 1);
                            else{
                                map.remove(min);
                                q.pollFirst();
                            }
                        }
                    }
                }
            }

            if(q.isEmpty()){
                sb.append("EMPTY");
            }
            else{
                sb.append(q.last()).append(" ").append(q.first());
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
