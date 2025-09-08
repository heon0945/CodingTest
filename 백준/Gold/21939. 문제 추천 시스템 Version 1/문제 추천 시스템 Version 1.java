import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static TreeSet<Problem> problems;
    static HashMap<Integer, Integer> map;
    static int m;
    static class Problem implements Comparable<Problem>{
        int number;
        int level;

        Problem(int number, int level){
            this.number = number;
            this.level = level;
        }

        public int compareTo(Problem o){
            if(this.level == o.level) return Integer.compare(this.number, o.number);
            return Integer.compare(this.level, o.level);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        problems = new TreeSet<>();
        map = new HashMap<>();

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            problems.add(new Problem(p, l));
            map.put(p, l);
        }

        m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("add")){
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                problems.add(new Problem(p, l));
                map.put(p, l);
            }
            else if(cmd.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());

                int number = -1;
                if(x == 1){
                    number = problems.last().number;
                }
                else{
                    number = problems.first().number;
                }
                sb.append(number).append('\n');
            }
            else if(cmd.equals("solved")){
                int p = Integer.parseInt(st.nextToken());
                int level = map.get(p);
                problems.remove(new Problem(p, level));
            }
        }

        System.out.println(sb);
    }
}
