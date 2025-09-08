import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] parents;


    public static void make(){
        parents = new int[n];
        Arrays.fill(parents, -1);
    }

    public static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        if(parents[rootA] < parents[rootB]){
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        }
        else{
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            sb.append("Case ").append(t).append(": ");

            make();

            while(m-- > 0){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                if(!union(a, b)) {
                    int parent = find(a);
                    parents[parent] = -n-1;
                }

            }

            int tree = 0;
            for(int i = 0; i < n; i++){
                if(parents[i] < 0 && parents[i] >= -n) tree++;
            }

            if(tree == 0) sb.append("No trees.");
            else if(tree == 1) sb.append("There is one tree.");
            else sb.append("A forest of ").append(tree).append(" trees.");

            sb.append('\n');
            t++;
        }

        System.out.println(sb);
    }
}
