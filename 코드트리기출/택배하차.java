import java.io.*;
import java.util.*;

public class 택배하차 {
    static int n, m;
    static int[][] map; //격자 정보
    static Map<Integer, Parcel> parcels;

    public static class Parcel implements Comparable<Parcel>{
        int idx; //번호
        int h; //세로
        int w; //너비
        int r; //행
        int c; //열

        public Parcel(int idx, int h, int w, int r, int c){
            this.idx = idx;
            this.h = h;
            this.w = w;
            this.r = r;
            this.c = c;
        }

        public int compareTo(Parcel o){
            return o.r - this.r; //행 낮은 순서대로 (내림차순)
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        parcels = new HashMap<>();

        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;

            input(idx, h, w, c);
        }

        StringBuilder sb = new StringBuilder();
        boolean dir = true;
        while(true){
            if(parcels.isEmpty()) break;
            int idx = drop(dir);
            sb.append(idx).append('\n');


            if(parcels.isEmpty()) break;

            Parcel p = parcels.get(idx);
            for(int i = p.r; i < p.r + p.h; i++){
                for(int j = p.c; j < p.c + p.w; j++){
                    map[i][j] = 0;
                }
            }
            parcels.remove(idx);

            organize();
            dir = !dir;

        }

        System.out.println(sb);
    }

    public static void organize(){
        List<Parcel> sortedParcel = new ArrayList<>();
        for(int key : parcels.keySet()){
            sortedParcel.add(parcels.get(key));
        }

        //아래에 있는 택배 순서대로 정렬
        Collections.sort(sortedParcel);

        for(Parcel p : sortedParcel){
            int idx = p.idx;
            int h = p.h;
            int w = p.w;
            int c = p.c;
            int preR = p.r;

            int r = descent(idx, h, w, preR, c);
            parcels.put(idx, new Parcel(idx, h, w, r, c));

            for(int i = preR; i < preR + h; i++){
                for(int j = c; j < c + w; j++){
                    map[i][j] = 0;
                }
            }

            for(int i = r; i < r + h; i++){
                for(int j = c; j < c + w; j++){
                    map[i][j] = idx;
                }
            }
        }
    }

    //택배가 최대로 하강할 수 있는 행
    public static int descent(int idx, int h, int w, int r, int c){
        for(int i = r; i < n; i++){
            boolean flag = false;
            for(int j = c; j < c + w; j++){
                if(map[i][j] != 0 && map[i][j] != idx){
                    flag = true;
                    break;
                }
            }
            if(flag) return i - h;
        }

        //바닥을 만남
        return n - h;
    }

    //택배 하차 (하차할 박스의 번호 반환)
    public static int drop(boolean dir){
        //dir = true 왼쪽 하차
        //dir = false 오른쪽 하차


        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        if(dir){
            for(int i = 0; i < n; i++){ //행
                int col = -1;
                for(int j = 0; j < n; j++){ //열
                    //물건을 만난 경우
                    if(map[i][j] != 0){
                        col = map[i][j];
                        break;
                    }
                }
                if(col != -1){ //물건을 만났을 때만
                    pq.add(col);
                }
            }
        }
        else {
            for(int i = 0; i < n; i++){ //행
                int col = -1;
                for(int j = n-1; j >= 0; j--){ //열
                    //물건을 만난 경우
                    if(map[i][j] != 0){
                        col = map[i][j];
                        break;
                    }
                }
                if(col != -1){ //물건을 만났을 때만
                    pq.add(col);
                }
            }
        }

        if(pq.isEmpty()) return 0; //격자에 박스가 아예 없는 경우

        int pre = pq.poll();
        int h = 1;
        while(!pq.isEmpty()){
            int cur = pq.poll();

            if(pre == cur) h++;
            else{
                if(h == parcels.get(pre).h) return pre;
                else {
                    pre = cur;
                    h = 1;
                }
            }
        }

        return pre;
    }

    //택배를 처음 격자에 투입
    public static void input(int idx, int h, int w, int c){
        int r = descent(idx, h, w, 0, c);

        parcels.put(idx, new Parcel(idx, h, w, r, c));

        for(int i = r; i < r + h; i++){
            for(int j = c; j < c + w; j++){
                map[i][j] = idx;
            }
        }
    }
}