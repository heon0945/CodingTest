#include <iostream>
#include <vector>
#include <queue>

using namespace std;


int n, m, h;
int map[101][101][101];
int check[101][101][101];
int dx[6] = { 0, 0, 1, -1, 0, 0 };
int dy[6] = { 1, -1, 0, 0, 0, 0 };
int dz[6] = { 0, 0, 0, 0, 1, -1 };

bool OOB(int x, int y, int z) {
	if (y >= 0 && y < h
		&& x >= 0 && x < n
		&& z >= 0 && z < m)
		return false;
	return true;
}

int main() {

	cin >> m >> n >> h;

	queue<vector<int>> q;

	for (int k = 0; k < h; k++) { //y
		for (int i = 0; i < n; i++) { //x
			for (int j = 0; j < m; j++) { //z
				int tmp;
				cin >> tmp;
				if (tmp == 1) {
					vector<int> v = { i, k, j };
					q.push(v);
					check[i][k][j] = 0;
				}
				else if (tmp == 0)
					check[i][k][j] = -1;
				else //tmp == -1
					check[i][k][j] = -2;
				map[i][k][j] = tmp;
			}
		}
	}


	int mx = 0;
	while (!q.empty()) {
		int x = q.front()[0];
		int y = q.front()[1];
		int z = q.front()[2];

		q.pop();

		for (int i = 0; i < 6; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nz = z + dz[i];

			if (!OOB(nx, ny, nz)
				&& check[nx][ny][nz] == -1
				&& map[nx][ny][nz] == 0) {
				vector<int> v = { nx, ny, nz };
				q.push(v);
				check[nx][ny][nz] = (check[x][y][z] + 1);
				mx = max(mx, check[nx][ny][nz]);
			}
		}
	}

	for (int k = 0; k < h; k++) { //y
		for (int i = 0; i < n; i++) { //x
			for (int j = 0; j < m; j++) { //z
				if (check[i][k][j] == -1) {
					cout << -1;
					return 0;
				}
			}
		}
	}

	cout << mx;

	return 0;
}