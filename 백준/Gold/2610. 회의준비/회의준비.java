import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1000;// 플로이드 배열 초기화 값
    static int n, m;
    static int[] parents; // union-find를 위한 배열
    static int[][] floyd; // 플로이드 최단 거리 저장 배열
    static Map<Integer, List<Integer>> commitee; // 각 위원회 구성원 저장 맵
    static List<Integer> representatives; // 위원회의 대표 목록

    // -------------------- union-find START --------------------
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
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }
        else{
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        }
        return true;
    }
    // -------------------- union-find END --------------------

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // union-find 초기화
        make();

        // 플로이드 배열 초기화
        floyd = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j) floyd[i][j] = 0;
                else floyd[i][j] = INF;
            }
        }

        // 관계 정보 저장
        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
            floyd[a][b] = 1;
            floyd[b][a] = 1;
        }

        // 플로이드 알고리즘 실행 -> 구성원 간의 전달 거리 저장
        floyd();

        // 위원회 정보 저장
        commitee = new HashMap<>();
        for(int i = 1; i <= n; i++){
            int root = find(i);
            commitee.putIfAbsent(root, new ArrayList<>());
            commitee.get(root).add(i);
        }

        // 각 위원회별로 대표자 선정
        representatives = new ArrayList<>();
        for(List<Integer> list : commitee.values()){
            int rep = findRepresentative(list);
            representatives.add(rep);
        }

        // 대표자 번호 오름차순 정렬
        Collections.sort(representatives);

        // 대표자 정보 출력
        StringBuilder sb = new StringBuilder();
        sb.append(representatives.size()).append('\n');
        for(int i : representatives){
            sb.append(i).append('\n');
        }
        System.out.println(sb);
    }

    // 각 위원회에서 대표자를 선정하는 함수
    public static int findRepresentative(List<Integer> list){
        int curDist = INF;
        int idx = -1;

        // 위원회 구성원 중 그룹 내 다른 사람들과 거리의 최대값을 계산
        for(int i = 0; i < list.size(); i++){
            int maxDist = 0;

            for(int j = 0; j < list.size(); j++){
                maxDist = Math.max(maxDist, floyd[list.get(i)][list.get(j)]);
            }

            // 최댓값이 최소인 사람을 대표자로 선정
            if(maxDist < curDist){
                curDist = maxDist;
                idx = i;
            }
        }

        // 대표자 번호 반환
        return list.get(idx);
    }

    // 플로이드-와샬 알고리즘을 이용하여 모든 사람 간의 최단 거리 계산
    public static void floyd(){

        for(int m = 1; m <= n; m++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][m] + floyd[m][j]);
                }
            }
        }
    }
}