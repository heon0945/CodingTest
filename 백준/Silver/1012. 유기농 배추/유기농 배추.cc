#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int board[51][51];
int check[51][51];
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int m, n; // 열 행
int cab; // 배추

bool OOB(int x, int y) {
	if (x >= 0 && x < n) {
		if (y >= 0 && y < m)
			return false;
	}
	return true;
}

void bfs(int x, int y) {
	queue<pair<int, int>> q;
	q.push({ x, y }); 
	check[x][y] = 1;

	while (!q.empty()) {
		int tmpx = q.front().first;
		int tmpy = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = tmpx + dx[i];
			int ny = tmpy + dy[i];

			if (check[nx][ny] == 0 && !OOB(nx, ny) && board[nx][ny]) {
				q.push({ nx, ny });
				check[nx][ny] = 1;
			}
		}
	}
}

int main() {
	
	int tc;
	cin >> tc;
	
	while (tc--) {
		cin >> m >> n >> cab;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = 0;
				check[i][j] = 0;
			}
		}

		for (int i = 0; i < cab; i++) {
			int a, b;
			cin >> a >> b;
			board[b][a] = 1;
		}

		int answer = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 1 && check[i][j] == 0) {
					answer++;
					bfs(i, j);
				}
			}
		}

		cout << answer << '\n';
		
	}


	return 0;
}