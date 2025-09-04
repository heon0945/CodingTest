import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] works;
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

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        make();

        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            union(a, b);
        }

        works = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            works[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int answer = 0;
        for(int i = 1; i < n; i++){
            if(find(works[i-1]) != find(works[i])) answer++;
        }

        System.out.println(answer);
    }
}
