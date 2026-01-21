import java.io.*;
import java.util.*;

public class 미생물연구 {
    public static int[][] map;
    public static int q;
    public static int n;
    public static Map<Integer, Integer> micro;
    public static int cnt;

    public static int[] dc = {-1, 0, 1, 0};
    public static int[] dr = {0, -1, 0, 1};

    public static class Pair{
        int x, y;
        public Pair(int x, int y){
            //정규화
            this.x = Math.min(x, y);
            this.y = Math.max(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        micro = new HashMap<>();

        while(q-- > 0){
            cnt++;

            st = new StringTokenizer(br.readLine());

            //좌측 하단
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = n - 1 - Integer.parseInt(st.nextToken());

            //우측 상단
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = n - 1 - (Integer.parseInt(st.nextToken()) - 1);

            //미생물 투입
            input(r1, c1, r2, c2);

            //배양 용기 이동
            moving();

//            for(int i = 0; i < n; i++){
//                for(int j = 0; j < n; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            //실험 결과 기록
            sb.append(record()).append('\n');


        }

        System.out.print(sb);
    }

    public static int record(){

        Set<Pair> set = new HashSet<>();

        //하나씩 돌아가면서 이웃하는 미생물 찾기
        for(int id : micro.keySet()){

            //bfs 시작점 찾기
            int pr = -1, pc = -1;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] == id){
                        pc = i; pr = j;
                        break;
                    }
                }
                if(pr != -1 && pc != -1) break;
            }

            //bfs를 통해 이웃하는 미생물 찾기
            boolean[][] visited = new boolean[n][n];
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{pc, pr});
            visited[pc][pr] = true;

            while(!q.isEmpty()){
                int[] cur = q.poll();

                for(int i = 0; i < 4; i++){
                    int nc = cur[0] + dc[i];
                    int nr = cur[1] + dr[i];

                    if(nc < 0 || nc >= n || nr < 0 || nr >= n) continue;
                    if(visited[nc][nr]) continue;
                    if(map[nc][nr] == 0) continue;
                    if(map[nc][nr] != id){
                        set.add(new Pair(id, map[nc][nr]));
                        continue;
                    }

                    q.add(new int[]{nc, nr});
                    visited[nc][nr] = true;
                }
            }
        }


        //이웃하는 미생물 정보를 바탕으로 결과 출력
        int result = 0;
        for(Pair p : set){
            int id1 = p.x;
            int id2 = p.y;

            result += (micro.get(id1) * micro.get(id2));
        }

        return result;
    }

    public static void moving(){
        //이동시킬 임시 격자
        int[][] temp = new int[n][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o2[1] - o1[1];
        });

        //크기가 큰 순서, 번호가 작은 순서
        for(int key : micro.keySet()){
            pq.add(new int[]{key, micro.get(key)});
        }

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int id = cur[0];

            //피벗 찾기
            int pc = -1, pr = -1;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] == id){
                        pc = i; pr = j;
                        break;
                    }
                }
                if(pc != -1 && pr != -1) break;
            }

            //새 격자에 적절한 칸 찾기
            boolean found = false;
            for(int j = 0; j < n; j++){
                for(int i = n-1; i >= 0; i--){

                    //새 피벗 위치와 차이
                    int diffC = i - pc;
                    int diffR = j - pr;

                    if(!isValidPos(id, temp, diffC, diffR)){
                        restore(id, temp);
                    }
                    else{
                        found = true;
                        break;
                    }
                }
                if(found) break;
            }
            if(!found) micro.remove(id);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = temp[i][j];
            }
        }
    }

    public static void restore(int id, int[][] temp){
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j] == id) temp[i][j] = 0;
            }
        }
    }
    public static boolean isValidPos(int id, int[][] temp, int diffC, int diffR){

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                if(map[i][j] != id) continue;

                int nc = i + diffC;
                int nr = j + diffR;

                if(nc < 0 || nc >= n || nr < 0 || nr >= n) return false;
                if(temp[nc][nr] != 0) return false;

                temp[nc][nr] = id;
            }
        }

        return true;
    }



    public static void input(int r1, int c1, int r2, int c2){
        //미생물 리스트에 추가
        int size = (r2 - r1 + 1) * (c1 - c2 + 1);
        micro.put(cnt, size);

        //사라질 수도 있는 후보
        Set<Integer> set = new HashSet<>();

        //격자에 미생물 투입
        for(int i = c2; i <= c1; i++){
            for(int j = r1; j <= r2; j++){

                //이미 미생물이 있다면 잡아먹기
                if(map[i][j] != 0){
                    set.add(map[i][j]);
                    micro.put(map[i][j], micro.get(map[i][j]) - 1);
                }

                //격자에 새로운 미생물 표시
                map[i][j] = cnt;
            }
        }

        //사라져야 할 미생물 체크
        for(int id : set){

            //나누어진 미생물인지 판단
            if(!isSurvive(id)){
                //사라지기
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        if(map[i][j] == id) map[i][j] = 0;
                    }
                }
                micro.remove(id);
            }
        }
    }

    public static boolean isSurvive(int id){
        int size = micro.get(id);

        if(size == 0) return false;

        int r = -1, c = -1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == id){
                    r = j;
                    c = i;
                    break;
                }
            }
            if(r != -1 && c != -1) break;
        }

        //남아 있는 해당 번호의 미생물이 없는 경우
        if(r == -1 && c == -1) return true;

        //해당 미생물 위치를 기반으로 bfs 실행 -> 넓이 계산
        boolean[][] visited = new boolean[n][n];
        int bfsSize = 0;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{c, r});
        visited[c][r] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            bfsSize++;

            for(int i = 0; i < 4; i++) {
                int nc = cur[0] + dc[i];
                int nr = cur[1] + dr[i];

                if (nc < 0 || nc >= n || nr < 0 || nr >= n) continue;
                if (visited[nc][nr]) continue;
                if (map[nc][nr] != id) continue;

                q.add(new int[]{nc, nr});
                visited[nc][nr] = true;
            }
        }

        if(bfsSize == size) return true;
        else return false;
    }
}