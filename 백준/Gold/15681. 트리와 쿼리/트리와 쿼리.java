import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static int n, r, q;
    static int[] cnt;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        cnt = new int[n+1];
        tree = new ArrayList[n+1];

        for(int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        //그래프 생성 (양방향)
        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tree[start].add(end);
            tree[end].add(start);
        }

        dfs(r, -1);

        StringBuilder sb = new StringBuilder();

        while(q-- > 0){
            int cur = Integer.parseInt(br.readLine());
            sb.append(cnt[cur]).append('\n');
        }

        System.out.println(sb);

    }

    public static void dfs(int root, int pre){
        cnt[root] = 1;

        for(int next : tree[root]){
            if(next == pre) continue;
            dfs(next, root);
            cnt[root] += cnt[next];
        }
    }
}
