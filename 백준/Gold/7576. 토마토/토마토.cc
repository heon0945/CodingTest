#include <iostream>
#include <vector>
#include <queue>

using namespace std;


//방향이 없는 그래프 -> 인접리스트, 노드 별 bfs/dfs
int n, m; //노드 수, 엣지 수
int map[1001][1001]; //인접리스트
int check[1001][1001];
int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

bool OOB(int x, int y) {
	if (x >= 0 && x < n
		&& y >= 0 && y < m)
		return false;
	return true;
}

int main() {
	
	cin >> m >> n;
	
	queue<pair<int, int>> q;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int tmp;
			cin >> tmp;
			if (tmp == 1) {
				q.push({ i, j });
				check[i][j] = 0;
			}
			else if (tmp == 0)
				check[i][j] = -1;
			else //tmp == -1
				check[i][j] = -2;
			map[i][j] = tmp;
		}
	}


	int mx = 0;
	while(!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!OOB(nx, ny)
				&& check[nx][ny] == -1
				&& map[nx][ny] == 0) {
				q.push({ nx, ny });
				check[nx][ny] = check[x][y] + 1;
				mx = max(mx, check[nx][ny]);
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (check[i][j] == -1) {
				cout << -1;
				return 0;
			}
		}
	}

	cout << mx;

	return 0;
}