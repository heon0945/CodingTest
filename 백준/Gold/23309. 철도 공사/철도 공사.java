import java.io.*;
import java.util.*;

public class Main {
    static int n, m;

    static Station[] stations;

    public static class Station{
        int pre;
        int next;

        public Station(int pre, int next){
            this.pre = pre;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        stations = new Station[2000001];



        // 원형 역 만들기
        st = new StringTokenizer(br.readLine());
        int pre = -1;
        int next = -1;
        int cur = Integer.parseInt(st.nextToken());
        int first = cur;

        for(int i = 0; i < n-1; i++){
            next = Integer.parseInt(st.nextToken());

            stations[cur] = new Station(pre, next);

            pre = cur;
            cur = next;
        }

        stations[cur] = new Station(pre, first);
        stations[first].pre = cur;



        // 공사 하기
        for(int i = 0; i < m; i++){

            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if(command.equals("BN")){
                int pivot = Integer.parseInt(st.nextToken());
                int newStation = Integer.parseInt(st.nextToken());

                int pivotNext = stations[pivot].next;

                sb.append(pivotNext).append('\n');
                stations[newStation] = new Station(pivot, pivotNext);
                stations[pivot].next = newStation;
                stations[pivotNext].pre = newStation;

            }
            else if(command.equals("BP")){
                int pivot = Integer.parseInt(st.nextToken());
                int newStation = Integer.parseInt(st.nextToken());

                int pivotPre = stations[pivot].pre;

                sb.append(pivotPre).append('\n');
                stations[newStation] = new Station(pivotPre, pivot);
                stations[pivot].pre = newStation;
                stations[pivotPre].next = newStation;

            }
            else if(command.equals("CN")){
                int pivot = Integer.parseInt(st.nextToken());

                int pivotNext = stations[pivot].next;
                int pivotNextNext = stations[pivotNext].next;

                sb.append(pivotNext).append('\n');

                stations[pivot].next = pivotNextNext;
                stations[pivotNextNext].pre = pivot;

                stations[pivotNext] = null;


            }
            else{ // command.equals("CP")
                int pivot = Integer.parseInt(st.nextToken());

                int pivotPre = stations[pivot].pre;
                int pivotPrePre = stations[pivotPre].pre;

                sb.append(pivotPre).append('\n');

                stations[pivot].pre = pivotPrePre;
                stations[pivotPrePre].next = pivot;

                stations[pivotPre] = null;

            }


        }

        System.out.println(sb);

    }

}
