import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static TreeSet<Problem> tree;
    static TreeSet<Problem>[] algoTree;
    static Problem[] problems;

    public static class Problem implements Comparable<Problem>{
        int p, l, g;
        public Problem(int p, int l, int g){
            this.p = p;
            this.l = l;
            this.g = g;
        }
        public int compareTo(Problem o){
            if(this.l == o.l) return this.p - o.p;
            return this.l - o.l;

        }
    }


    public static void main(String[] args) {

        // 1. 초기화
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        n = sc.nextInt();

        problems = new Problem[110001]; // 문제 번호를 인덱스로 문제의 값 저장

        tree = new TreeSet<>(); // 각 문제를 트리셋에 저장

        algoTree = new TreeSet[101]; // 알고리즘 별로 문제를 트리셋에 저장
        for(int i = 1; i < 101; i++){
            algoTree[i] = new TreeSet<>();
        }


        // 2. 문제 입력 받기
        for(int i = 0; i < n; i++){
            Problem pb = new Problem(sc.nextInt(), sc.nextInt(), sc.nextInt());

            problems[pb.p] = pb;
            tree.add(pb);
            algoTree[pb.g].add(pb);
        }

        // 3. 명령어 처리
        m = sc.nextInt();
        for(int i = 0; i < m; i++){
            String cmd = sc.next();


            if(cmd.equals("recommend")){
                int g = sc.nextInt();
                int x = sc.nextInt();

                if(x == 1) sb.append(algoTree[g].last().p).append('\n');
                else sb.append(algoTree[g].first().p).append('\n');
            }
            else if(cmd.equals("recommend2")){
                int x = sc.nextInt();
                if(x == 1) sb.append(tree.last().p).append('\n');
                else sb.append(tree.first().p).append('\n');
            }
            else if(cmd.equals("recommend3")){
                int x = sc.nextInt();
                int l = sc.nextInt();
                Problem recommend = null;
                if(x == 1) recommend = tree.ceiling(new Problem(0, l, 0));
                else recommend = tree.lower(new Problem(0, l, 0));

                if(recommend != null) sb.append(recommend.p).append('\n');
                else sb.append(-1).append('\n');

            }
            else if(cmd.equals("add")){
                int p = sc.nextInt();
                int l = sc.nextInt();
                int g = sc.nextInt();
                Problem newP = new Problem(p, l, g);
                problems[p] = newP;
                tree.add(newP);
                algoTree[g].add(newP);

            }
            else if(cmd.equals("solved")){
                int p = sc.nextInt();
                Problem pb = problems[p];
                tree.remove(pb);
                algoTree[pb.g].remove(pb);
            }
        }

        // 4. 답 출력
        System.out.print(sb);

    }
}