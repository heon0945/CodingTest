import java.io.*;
import java.util.*;

// 완전 탐색을 하면 10만 x 10만 -> 시간 초과
// 2차원 배열의 경우 10만 x 10만 -> 메모리 초과
// 정렬한 후 점프 가능한 통나무를 분류하면 최대 10만
// 통나무 : 시작 지점, 끝 지점, 높이, 원래 인덱스, 그룹(점프 가능한 통나무끼리 분류)
// 1. 정보 입력 받기
// 2. 통나무 정렬 -> 시작 지점 빠른 순서대로
// 3. 통나무 순회하면서 분류화 (통나무가 끝나는 가장 긴 지점을 업데이트, 해당 통나무 분류)
    // 4-1. 점프 가능한 경우 (현재 끝나는 가장 긴 지점 안에, 통나무의 시작하는 지점이 있는 경우) -> 같은 그룹으로 분류, 긴 지점 업데이트
    // 4-2. 점프 못하는 경우 (현재 끝나는 가장 긴 지점보다, 통나무의 시작하는 지점이 더 바깥에 있는 경우) -> 새로운 분류로 업데이트, 긴 지점을 현재 통나무의 끝 지점으로 업데이트
// 5. 통나무 정렬 -> 원래 인덱스의 순서대로
// 6. 질문 받으면서 순회하면서 응답 -> 해당 통나무의 분류가 같은지 확인


public class Main {

    static int n, q;
    static Log[] logs;

    public static class Log implements Comparable<Log>{
        int idx;
        int x1, x2, y;
        int group = -1;
        public Log(int idx, int x1, int x2, int y){
            this.idx = idx;
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
        }

        public int compareTo(Log o){
            return this.x1 - o.x1;
        }

    }

    public static void main(String[] args) throws IOException {

        // 정보 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        logs = new Log[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            logs[i] = new Log(i, x1, x2, y);
        }

        // 통나무 정렬 (시작 지점 순서대로)
        Arrays.sort(logs);

        // 통나무 분류화
        int curG = 1; // 그룹 초기값 할당
        int last = logs[0].x2; // 가장 큰 길이 초기값 할당
        logs[0].group = 1; // 가장 첫번째 그룹 지정
        
        for(int i = 1; i < n; i++){

            // 점프 가능한 통나무
            if(logs[i].x1 <= last){
                logs[i].group = curG;
                last = Math.max(last, logs[i].x2);
            }

            // 점프 불가능한 통나무
            else{
                curG++;
                logs[i].group = curG;
                last = logs[i].x2;
            }
        }


        // 통나무 정렬 (원래 인덱스 순서대로)
        Arrays.sort(logs, new Comparator<Log>(){
           public int compare(Log o1, Log o2){
               return o1.idx - o2.idx;
           }
        });


        // 정답 출력
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken())-1;
            int second = Integer.parseInt(st.nextToken())-1;

            if(logs[first].group == logs[second].group) sb.append(1);
            else sb.append(0);
            sb.append('\n');
        }

        System.out.println(sb);


    }
}