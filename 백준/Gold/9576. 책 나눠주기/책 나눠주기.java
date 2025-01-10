import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static int tc, n, m;
    static Person[] people;

    public static class Person {
        int a, b;
        boolean have = false;

        public Person(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "a=" + a +
                    ", b=" + b +
                    ", have=" + have +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        tc = sc.nextInt();

        while(tc > 0){

            n = sc.nextInt();
            m = sc.nextInt();

            people = new Person[m];

            for(int i = 0; i < m; i++){
               people[i] = new Person(sc.nextInt()-1, sc.nextInt()-1);
            }

            //사람이 선택한 범위 순서대로 정렬
            Arrays.sort(people, (e1, e2) -> {
                if(e1.b == e2.b)
                    return e1.a - e2.a;
                return e1.b  - e2.b;
            });

            //책 나눠주기 (0~n-1번)
            int total = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    Person cur = people[j];
                    if(cur.have) continue;

                    if(cur.a <= i && i <= cur.b){
                        cur.have = true;
                        total++;
                        break;
                    }
                }
            }
            System.out.println(total);
            tc--;
        }
    }
}