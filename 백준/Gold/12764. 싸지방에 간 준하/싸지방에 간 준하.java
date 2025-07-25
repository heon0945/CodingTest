import java.io.*;
import java.util.*;

// 사용자는 시작 시간이 빠른 순서부터 입장
// 컴퓨터 사용은 번호가 작은 순서부터 사용
// 사용 가능한 컴퓨터는 종료 시간이 빠른 순서부터

// 1. 사용자 정보를 시작 시간이 빠른 순서대로 저장
// 2. 시작 시간이 빠른 사용자부터 입장하여 사용 가능한 컴퓨터 탐색
    // 현재 사용 중인 컴퓨터에서 사용자의 시작시간 보다 빠른 종료 시간을 가진 컴퓨터
    // 위의 조건에 해당하는 컴퓨터를 사용 가능한 컴퓨터로 업데이트
// 3. 사용 가능한 컴퓨터 중 가장 번호가 작은 컴퓨터 선택
// 4. 해당 컴퓨터를 현재 사용 중인 컴퓨터 목록으로 업데이트
    // 만약 사용 가능한 컴퓨터가 한 대도 없는 경우 컴퓨터를 하나 증설, 사용 중인 컴퓨터 목록에 업데이트

// 사용자 정보를 관리하는 pq (시작 시간 빠른 순서)
// 사용 중인 컴퓨터를 관리하는 pq (종료 시간 빠른 순서)
// 사용 가능한 컴퓨터 관리하는 pq (컴퓨터 번호 작은 순서)


public class Main {

    static int n;
    static int max;
    static int cnt[];

    static class User implements Comparable<User>{
        int start, end;
        User(int start, int end){
            this.start = start;
            this.end = end;
        }
        public int compareTo(User o){
            if(this.start == o.start){
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    static class Computer implements Comparable<Computer>{
        int number, end;
        Computer(int number, int end){
            this.number = number;
            this.end = end;
        }

        public int compareTo(Computer o){
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cnt = new int[n+1];

        PriorityQueue<User> users = new PriorityQueue<>();

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            users.add(new User(start, end));
        }

        PriorityQueue<Computer> computers = new PriorityQueue<>();
        PriorityQueue<Integer> available = new PriorityQueue<>();

        while(!users.isEmpty()){

            User cur = users.poll();

            while(!computers.isEmpty() && computers.peek().end < cur.start){
                available.add(computers.poll().number);
            }

            if(available.isEmpty()){
                available.add(++max);
            }


            int number = available.poll();
            Computer curCom = new Computer(number, cur.end);
            computers.add(curCom);
            cnt[number]++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n');
        for(int i = 1; i <= max; i++){
            sb.append(cnt[i]).append(" ");
        }

        System.out.println(sb);

    }
}