import java.io.*;
import java.util.*;

// 1. 수열을 입력 받고, 만들어낼 목표 수열(오름차순)을 저장
// 2.  bfs 탐색 : k의 전환이 가능한 경우 모든 수열의 형태를 큐에 삽입
    // 큐에 들어갈 값 : 현재 수열, 현재 전환한 횟수
    // 이미 만든 적 있는 수열 제외 -> visited 체크 (해시셋으로 O(1) 만에 체크)
    // 오른쪽(자신 포함)에 k개의 수가 없는 경우 제외 (k개 전환 불가)
// 3-1. 큐에서 poll한 값이 목표 값과 같은 경우 종료 후 현재 전환 횟수 반환
// 3-2. bfs 탐색이 끝난 후에도 목표 값과 같은 값을 못찾은 경우 -1 반환

public class Main {

    static int n, k;
    static char arr[];
    static String init; // 초기 수열 저장
    static String target; // 목표 오름차순 수열 저장


    public static class Cell{
        String str;
        int cnt;
        public Cell(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new char[n];

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(st.nextToken());
        }
        init = sb.toString();

        // 오름차순 정렬
        arr = init.toCharArray();
        Arrays.sort(arr);

        // 목표 수열 저장
        target = new String(arr);

        System.out.println(bfs(init));

    }


    public static int bfs(String init){

        // bfs 활용할 큐
        Queue<Cell> q = new ArrayDeque<>();

        // visited 처리할 해시셋
        Set<String> visited = new HashSet<>();

        // 큐에 초기값 넣기
        q.add(new Cell(init, 0));
        //방문 체크
        visited.add(init);

        while(!q.isEmpty()){

            Cell cur = q.poll();

            if(cur.str.equals(target)) return cur.cnt;

            // 첫글자부터 k개의 수를 전환할 수 있을 때까지 반복
            for(int i = 0; i <= n - k; i++){


                // 특정 범위 오른쪽 범위 전환 시행
                StringBuilder sb = new StringBuilder();
                sb.append(cur.str, 0, i);

                String tmp = cur.str.substring(i, i + k);

                for(int iter = k - 1; iter >= 0; iter--){
                    sb.append(tmp.charAt(iter));
                }

                sb.append(cur.str, i + k, n);


                // 큐에 새로 만들어진 수열 삽입
                String newStr = sb.toString();

                // 이미 만들어진 적 있는 수열은 제외
                if(visited.contains(newStr)) continue;

                // 수열 삽입(전환 cnt 증가) 후 방문 체크
                q.add(new Cell(newStr, cur.cnt + 1));
                visited.add(newStr);
            }


        }

        return -1;
    }
}