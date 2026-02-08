import java.util.*;

class UserSolution {

    public static int[][] roads;
    public void init(int N, int K, int sCity[], int eCity[], int mLimit[]) {

        //도로를 2차원 배열 형태로 저장
        roads = new int[N][N];

        //도로 정보 저장
        for(int i = 0; i < K; i++){
            int start = sCity[i];
            int end = eCity[i];
            int limit = mLimit[i];

            //양방향 처리
            roads[start][end] = limit;
            roads[end][start] = limit;
        }

        return;

    }


    public void add(int sCity, int eCity, int mLimit) {

        roads[sCity][eCity] = mLimit;
        roads[eCity][sCity] = mLimit;
        return;

    }


    public int calculate(int sCity, int eCity, int M, int mStopover[]) {

        int N = roads.length;

        //도시의 최대중량 값 (도시 번호, 각 방문 경유지 마스킹 값)
        //해당 도시에 도착했을 때 어떤 경유지를 들러서 왔는지 함께 저장
        //마스킹 방법 - 각 자릿수가 경유지 (첫 자리 제외, 경유지가 3개면 4자리수) 방문한 경유지 : 1, 방문하지 않은 경유지 : 0
        int[][] maxWeight = new int[N][1 << M];
        for (int i = 0; i < N; i++)
            Arrays.fill(maxWeight[i], -1);

        //경유지 : 마스킹 위치와 도시 번호 매핑
        Map<Integer, Integer> stopIndex = new HashMap<>();
        for (int i = 0; i < M; i++)
            stopIndex.put(mStopover[i], i);

        //우선순위 큐 : 도시번호, 경유지 마스킹, 최대중량
        //최대중량이 큰 순서대로 정렬
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> b[2] - a[2]);

        //시작도시부터 우선순위 큐 삽입
        //시작도시의 경우, 아무 경유지도 방문하지 않았으므로 마스킹 0
        //최대중량도 가장 최댓값으로 초기화
        maxWeight[sCity][0] = Integer.MAX_VALUE;
        pq.add(new int[]{sCity, 0, Integer.MAX_VALUE});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int city = cur[0];
            int mask = cur[1];
            int w = cur[2];

            if (w < maxWeight[city][mask]) continue;

            for (int next = 0; next < N; next++) {
                //도로가 없는 경우
                if (roads[city][next] == 0) continue;

                //방문한 경유지 마스크
                int newMask = mask;

                //다음 도시가 경유지 중 하나라면, 경유지 마스크 업데이트
                if (stopIndex.containsKey(next))
                    newMask |= 1 << stopIndex.get(next);

                //새로운 경로의 최대중량
                int newLimit = Math.min(w, roads[city][next]);

                //원래 해당 도시의 최대중량과 비교
                //새로운 최대중량이 더 큰 경우, 업데이트하고 pq 삽입(경로 이어 탐색)
                if (newLimit > maxWeight[next][newMask]) {
                    maxWeight[next][newMask] = newLimit;
                    pq.add(new int[]{next, newMask, newLimit});
                }
            }
        }

        //모든 경유지를 방문한 경로의, 최대중량 값
        return maxWeight[eCity][(1 << M) - 1];

    }

}

