import java.io.*;
import java.util.*;

public class 가로등설치 {
    static int Q; //명령 개수
    static int N, M; //거리 길이, 초기 가로등 개수
    static Map<Integer, Integer> list; //가로등 리스트 (가로등 ID, 위치)
    static Map<Integer, Integer> leftList; //왼쪽 가로등 존재 리스트 (가로등 ID, 왼쪽 가로등 ID)
    static Map<Integer, Integer> rightList; //오른쪽 가로등 존재 리스트 (가로등 ID, 오른쪽 가로등 ID)
    static PriorityQueue<Lamp> pq; //가로등 거리 순으로 정렬 (양쪽 끝과 가로등 거리 제외)
    static int first, last; //첫번째와 마지막 가로등 ID

    //우선순위 큐를 위한 가로등 정보 클래스
    //거리 순으로 정렬
    public static class Lamp implements Comparable<Lamp>{
        int leftId;
        int rightId;
        int leftPos;
        int distance;

        public Lamp(int leftId, int rightId, int leftPos, int distance){
            this.leftId = leftId;
            this.rightId = rightId;
            this.leftPos = leftPos;
            this.distance = distance;
        }

        public int compareTo(Lamp o){
            if(distance == o.distance){
                return leftPos - o.leftPos;
            }
            return o.distance - distance; //내림차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        list = new HashMap<>();
        leftList = new HashMap<>();
        rightList = new HashMap<>();
        pq = new PriorityQueue<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Q = Integer.parseInt(br.readLine());

        while(Q-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("100")){ //초기화
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());

                first = 1; last = M;

                for(int i = 1; i <= M; i++){
                    int pos = Integer.parseInt(st.nextToken());
                    list.put(i, pos);
                    if(i != 1) leftList.put(i, i-1);
                    if(i != M) rightList.put(i, i+1);
                }

                for(int i = 1; i < M; i++){
                    int right = list.get(i+1);
                    int left = list.get(i);
                    pq.add(new Lamp(i, i+1, left, right - left));
                }

            }
            else if(cmd.equals("200")){ //가로등 추가
                add();
            }
            else if(cmd.equals("300")){ //가로등 제거
                int id = Integer.parseInt(st.nextToken());
                remove(id);
            }
            else{ //최소 전력 계산
                calculateMinPower();
            }
        }
    }

    public static void add(){
        Lamp lamp = getMaxDistanceLamp();

        M++; //가로등 추가

        int point = (list.get(lamp.leftId) + list.get(lamp.rightId) + 1) / 2;
        list.put(M, point);

        leftList.put(lamp.rightId, M);
        leftList.put(M, lamp.leftId);
        rightList.put(lamp.leftId, M);
        rightList.put(M, lamp.rightId);

        int distanceLeft = list.get(M) - list.get(lamp.leftId);
        int distanceRight = list.get(lamp.rightId) - list.get(M);
        pq.add(new Lamp(lamp.leftId, M, list.get(lamp.leftId), distanceLeft));
        pq.add(new Lamp(M, lamp.rightId, list.get(M), distanceRight));
    }

    public static void remove(int id){
        list.remove(id);

        if(leftList.containsKey(id) && rightList.containsKey(id)){
            int leftId = leftList.get(id);
            int rightId = rightList.get(id);

            leftList.remove(id);
            leftList.put(rightId, leftId);

            rightList.remove(id);
            rightList.put(leftId, rightId);

            int distance = list.get(rightId) - list.get(leftId);

            pq.add(new Lamp(leftId, rightId, list.get(leftId), distance));
        }

        else if(leftList.containsKey(id)){
            int leftId = leftList.get(id);

            leftList.remove(id);
            rightList.remove(leftId);

            last = leftId;
        }

        else if(rightList.containsKey(id)){
            int rightId = rightList.get(id);

            rightList.remove(id);
            leftList.remove(rightId);

            first = rightId;
        }

    }

    public static Lamp getMaxDistanceLamp(){
        while(!pq.isEmpty()){
            Lamp candidate = pq.poll();

            int left = candidate.leftId;
            int right = candidate.rightId;

            //최대 거리가 현재 유효한지 (가로등 두 개가 존재하는지, 이웃하는지)
            if(leftList.containsKey(right) && rightList.containsKey(left)){
                if(leftList.get(right) == left && rightList.get(left) == right){
                    return candidate;
                }
            }
        }

        return null; //가로등이 하나만 남은 경우 (사이 간 거리 존재하지 않는 상태)
    }

    public static void calculateMinPower(){
        int leftEdge = list.get(first) - 1; //길은 1부터 시작(보정)
        int rightEdge = N - list.get(last);
        int distance = 0;

        Lamp lamp = getMaxDistanceLamp();

        if(lamp != null) distance = lamp.distance;

        int maxDistance = Math.max(leftEdge * 2, rightEdge * 2);
        maxDistance = Math.max(maxDistance, distance); //distance 보정하는 이유 : 사이 간 거리가 홀수일 경우 전체 보완

        System.out.println(maxDistance);

        pq.add(lamp);
    }
}