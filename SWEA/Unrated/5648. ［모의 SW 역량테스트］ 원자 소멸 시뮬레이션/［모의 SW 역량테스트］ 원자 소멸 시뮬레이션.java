
import java.io.*;
import java.util.*;

public class Solution {
	
	static int tc;
	static int n;
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	static int map[][] = new int[4001][4001];
	static ArrayList<Atom> atoms = new ArrayList<>();
 	static int answer;
	
	static class Atom{
		int x, y;
		int dir;
		int power;
		public Atom(int x, int y, int dir, int power) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
		}	
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			n = sc.nextInt();
			
			for(int i = 0; i < n; i++) {
				//중심 2000, 2000
				int x = 2000 + sc.nextInt() * 2;
				int y = 2000 + sc.nextInt() * 2;
				int dir = sc.nextInt();
				int energy = sc.nextInt();
				atoms.add(new Atom(x, y, dir, energy));
				map[x][y] = energy;
			}
				answer = solve();
			
			sb.append(answer).append('\n');
			
		}
		
		System.out.println(sb);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x > 4000 || y < 0 || y > 4000;
	}
	
	static int solve() {
		int sum = 0;
		
		while(!atoms.isEmpty()) {
			for(int i = atoms.size()-1; i >= 0; i--) {
				Atom atom = atoms.get(i);
				
				map[atom.x][atom.y] = 0;
				atom.x += dx[atom.dir];
				atom.y += dy[atom.dir];
				
				if(OOB(atom.x, atom.y)) {
					atoms.remove(i);
					continue;
				}
				
				map[atom.x][atom.y] += atom.power;
			}
			
			for(int i = atoms.size()-1; i >= 0; i--) {
				Atom atom = atoms.get(i);
				
				if(map[atom.x][atom.y] != atom.power) { //충돌 발생
					sum += map[atom.x][atom.y];
					map[atom.x][atom.y] = 0;
					atoms.remove(i);
				}
			}
		}
		
		return sum;
	}
}
