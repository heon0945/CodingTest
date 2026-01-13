import java.util.*;
import java.io.*;

public class 해적선장코디 {
    static int t;
    static Map<Integer, Ship> ships; //선박 관리 (선박 아이디, 선박)
    static PriorityQueue<Ship> sortedShips;

    public static class Ship implements Comparable<Ship> {
        int id; //아이디
        int p; //공격력
        int r; //장전시간
        int status; //상태 (0 : 초기값(한 번도 공격 x), > 0 : 마지막으로 공격한 시점의 t값)

        public Ship(int id, int p, int r, int status){
            this.id = id;
            this.p = p;
            this.r = r;
            this.status = status;
        }

        public int compareTo(Ship o){
            //공격력으로 내림차순, 공격력 같으면 아이디로 오름차순
            if(this.p == o.p) {
                return this.id - o.id;
            }
            return o.p - this.p;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        ships = new HashMap<>();
        sortedShips = new PriorityQueue<>();

        while(t > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 100){ //공격 준비
                int n = Integer.parseInt(st.nextToken());

                while(n-- > 0){
                    int id = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());

                    Ship ship = new Ship(id, p, r, 0);

                    ships.put(id, ship);
                    sortedShips.add(ship);
                }
            }
            else if(cmd == 200){ //지원 요청
                int id = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                Ship ship = new Ship(id, p, r, 0);

                ships.put(id, ship);
                sortedShips.add(ship);
            }
            else if(cmd == 300){ // 함포 교체
                int id = Integer.parseInt(st.nextToken());
                int pw = Integer.parseInt(st.nextToken());


                Ship tmp = ships.get(id);

                Ship ship = new Ship(id, pw, tmp.r, tmp.status);

                ships.put(id, ship);
                sortedShips.add(ship);
            }
            else if(cmd == 400){ //사격 명령
                attack(t);
            }

            t--;
        }
    }

    public static void attack(int t){
        //t는 현재 시각
        //t == 0 공격 가능
        //status - t >= r 공격 가능
        //공격 후 status는 t로 업데이트

        List<Ship> attacker = new ArrayList<>();
        int totalDamage = 0;

        PriorityQueue<Ship> tmp = new PriorityQueue<>();

        while(!sortedShips.isEmpty()) {
            if(attacker.size() >= 5) break;

            Ship s = sortedShips.poll();
            if(ships.get(s.id).status != s.status || ships.get(s.id).p != s.p) {
                continue;
            }

            if(s.status == 0 || s.status - t >= s.r){
                attacker.add(s);
                totalDamage += s.p;
            }
            else tmp.add(s);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalDamage).append(" ");
        sb.append(attacker.size()).append(" ");

        for(Ship s : attacker){
            sb.append(s.id).append(" ");
            Ship ship = new Ship(s.id, s.p, s.r, t);
            ships.put(s.id, ship);
            sortedShips.add(ship);
        }

        while(!tmp.isEmpty()) sortedShips.add(tmp.poll());

        System.out.println(sb);
    }
}