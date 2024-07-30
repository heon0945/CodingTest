#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n, m;
int board[301][301];
int check[301][301];
int update[301][301];
int time = 0;
int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

bool OOB(int x, int y) {
	if (x >= 0 && x < n && y >= 0 && y < m)
		return false;
	return true;
}

void updating() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (board[i][j] != 0) {
				for (int k = 0; k < 4; k++) {
					if (!OOB(i + dx[k], j + dy[k]) && board[i + dx[k]][j + dy[k]] == 0)
						update[i][j]++;
				}
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			board[i][j] = max(0, board[i][j] - update[i][j]);
			update[i][j] = 0;
		}
	}
	return;

}

bool bfs() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (board[i][j] != 0) {
				queue < pair<int, int>> q;
				q.push({ i, j });
				check[i][j] = 1;

				while (!q.empty()) {
					int tx = q.front().first;
					int ty = q.front().second;
					q.pop();

					for (int k = 0; k < 4; k++) {
						int nx = tx + dx[k];
						int ny = ty + dy[k];

						if (!OOB(nx, ny) &&
							board[nx][ny] != 0 &&
							check[nx][ny] == 0) {
							q.push({ nx, ny });
							check[nx][ny] = 1;
						}
					}
				}

				return true;
			}
		}
	}

	return false;
}



int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int tmp;
			cin >> tmp;
			board[i][j] = tmp;
		}
	}

	while (true) {
		time++;

		//4way search
		//ice update
		updating();

		//bfs
		if (!bfs()) {
			cout << 0;
			return 0;
		}
		else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (board[i][j] != 0 && check[i][j] == 0) {
						cout << time;
						return 0;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				check[i][j] = 0;
			}
		}


	}

	return 0;
}