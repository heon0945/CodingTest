import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int n;
    static Computer[] computers;
    static long total;

    static class Computer {
        int number, step, runtime;
        long endtime;

        public Computer(int number, int step, int runtime) {
            super();
            this.runtime = runtime;
            this.step = step;
            this.number = number;
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        computers = new Computer[n];

        for(int i = 0; i < n; i++){
            computers[i] = new Computer(i, sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(computers, (e1, e2) -> e1.step-e2.step);

        List<Computer> prev = new ArrayList<>();
        List<Computer> cur = new ArrayList<>();
        int  curstep = 0;
        for(int i = 0; i < n; i++){
            Computer c = computers[i];

            //계급이 바뀐 경우
            if(curstep != c.step){
                prev.clear();
                curstep = c.step;
                prev.addAll(cur);
                cur.clear();
            }

            //현재 컴퓨터의 계급이 1인 경우 (시작 계급)
            if(c.step == 1){
                c.endtime = c.runtime;
            }
            //기본 동작
            else{
                long tp = 0;
                for(Computer tmp : prev){
                    tp = Math.max((long) (c.number - tmp.number) * (c.number - tmp.number) + tmp.endtime , tp);
                }
                c.endtime = tp + c.runtime;
            }
            cur.add(c);
            total = Math.max(total, c.endtime);
        }

        System.out.println(total);

    }
}