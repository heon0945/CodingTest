import java.util.*;
import java.io.*;

public class Solution {

	static int tc;
	static int n, x;
	static int map[][];
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			answer = 0;

			n = sc.nextInt();
			x = sc.nextInt();

			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// 행 탐색
			//System.out.println("행");
			for (int i = 0; i < n; i++) {
				if (calcRow(i)) {
					//System.out.println(i);
					answer++;
				}
			}

			// 열 탐색
			//System.out.println("열");
			for (int i = 0; i < n; i++) {
				if (calcCol(i)) {
					//System.out.println(i);
					answer++;
				}
			}

			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}

		System.out.println(sb);
	}

	static boolean calcCol(int col) {

		int cnt = 1;
		int slope = 0;
		int height = map[0][col];

		for (int i = 1; i < n; i++) {
			// 높이가 같은 경우
			if (height == map[i][col]) {
				if (slope > 0)
					slope--;
				else
					cnt++;
			}

			// 높이가 낮은 경우
			else if (height > map[i][col]) {
				if(height - map[i][col] > 1) return false;
				if (slope > 0)
					return false;
				height = map[i][col];
				slope = x-1;
				cnt = 0;
			}

			// 높이가 높은 경우
			else {
				if(map[i][col] - height> 1) return false;
				if (slope > 0)
					return false;
				if (cnt < x)
					return false;
				height = map[i][col];
				cnt = 1;
			}
		}

		if (slope > 0)
			return false;

		return true;
	}

	static boolean calcRow(int row) {

		int cnt = 1;
		int slope = 0;
		int height = map[row][0];

		for (int i = 1; i < n; i++) {
			// 높이가 같은 경우
			if (height == map[row][i]) {
				if (slope > 0)
					slope--;
				else
					cnt++;
			}

			// 높이가 낮은 경우
			else if (height > map[row][i]) {
				if(height - map[row][i] > 1) return false;
				if (slope > 0)
					return false;
				height = map[row][i];
				slope = x-1;
				cnt = 0;
			}

			// 높이가 높은 경우
			else {
				if(map[row][i] - height > 1) return false;
				if (slope > 0)
					return false;
				if (cnt < x)
					return false;
				height = map[row][i];
				cnt = 1;
			}
		}

		if (slope > 0)
			return false;

		return true;
	}
}