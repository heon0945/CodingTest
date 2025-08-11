import java.io.*;
import java.util.*;

// 다리마다 허용 가능한 무게가 존재
// 출발점에서 도착점까지 사용한 다리들의 허용 무게 중 최솟값(구하고자 하는 빼빼로 무게)이 최대가 되도록
// 집과 다리 -> 전형적인 그래프 문제

// 주의 : 다익스트라를 사용하면 무게 '합'이 최대가 되도록 함 -> 개별 다리의 무게 초점 x
// 선택 마다 최대 무게를 가지는 다리를 선택할 수 있도록 MST 크루스칼 알고리즘 사용

// 크루스칼 알고리즘
    // 허용 무게가 큰 순서대로 다리 선택
    // 출발점과 도착점이 union된 순간, 두 지점 사이의 통로가 생김을 의미
    // 이때 선택된 다리들 중 무게의 최솟값이 정답 (두 지점을 이동하기 위해 필요한 다리의 최소 허용 무게 = 최대 빼빼로 무게)



public class Main {

    static int n, m;
    static int s, e;
    static Bridge[] bridges;

    static class Bridge implements Comparable<Bridge>{
        int start, end;
        int weight;

        Bridge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Bridge o){
            return o.weight - this.weight; // 내림차순 정렬
        }
    }

    //union-find
    static int[] parents;

    public static void make(){
        parents = new int[n+1];
        for(int i = 1; i <= n; i++){
            parents[i] = -1;
        }
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

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        bridges = new Bridge[m];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            bridges[i] = new Bridge(start, end, weight);
        }

        Arrays.sort(bridges);

        make();

        int cnt = 0;
        int min = 3000000;
        for(int i = 0; i < m; i++){

            if(cnt >= n-1) break; // MST 완성

            Bridge cur = bridges[i];
            if(union(cur.start, cur.end)) cnt++;

            min = Math.min(min, cur.weight);

            // 출발점과 도착점이 연결 완료
            if(find(s) == find(e)) {
                System.out.println(min);
                System.exit(0);
            }
        }

        // 출발점과 도착점이 이어지지 않는 예외 처리
        System.out.println(0);

    }
}