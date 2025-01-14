
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Task[] tasks;
    static boolean[] check;

    static class Task{
        int deadline, score;

        public Task(int deadline, int score) {
            super();
            this.deadline = deadline;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Task [deadline=" + deadline + ", score=" + score + "]";
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        tasks = new Task[n];
        check = new boolean[n];

        for(int i = 0; i < n; i++) {
            tasks[i] = new Task(sc.nextInt()-1, sc.nextInt());
        }

        Arrays.sort(tasks, (e1, e2) -> e2.deadline - e1.deadline); //마감기한이 느린 순서대로 정렬

        PriorityQueue<Task> pq = new PriorityQueue<>((e1,e2) -> e2.score - e1.score); //우선순위 큐 : 점수가 높은 순서대로
        int total = 0;

        //마감기한시간마다 적합한 라면 선택
        int time = tasks[0].deadline;
        int iterator = 0;

        while(time >= 0){

            //현재 라면 마감기한에 포함되는가 안되는가
            while(iterator < n && tasks[iterator].deadline == time){

                //현재 타입에 먹을 수 있는 라면 전부 pq에 넣기
                pq.add(tasks[iterator]);
                iterator++;
            }


            time--;
            //현재 타임에서 가장적합한 라면 선택
            if(pq.isEmpty()) continue;
            Task cur = pq.poll();
            total += cur.score;
        }





        System.out.println(total);

    }
}