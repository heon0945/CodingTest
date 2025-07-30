import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long x;
    static long money;
    static long score;
    static Day[] schedule;

    static class Day implements Comparable<Day>{
        int scoreA, scoreB;
        Day(int scoreA, int scoreB){
            this.scoreA = scoreA;
            this.scoreB = scoreB;
        }
        public int compareTo(Day o){
            return (o.scoreA - o.scoreB) - (scoreA-scoreB); //오름차순 정렬
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken());

        schedule = new Day[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int scoreA = Integer.parseInt(st.nextToken());
            int scoreB = Integer.parseInt(st.nextToken());

            schedule[i] = new Day(scoreA, scoreB);
            score += scoreB;
        }

        money = 1000L * n;
        Arrays.sort(schedule);

        for(int i = 0; i < n; i++){

            int diff = schedule[i].scoreA - schedule[i].scoreB;
            if(diff <= 0) break;

            if(money + 4000 > x) break;
            money += 4000;
            score -= schedule[i].scoreB;
            score += schedule[i].scoreA;
        }

        System.out.println(score);



    }
}