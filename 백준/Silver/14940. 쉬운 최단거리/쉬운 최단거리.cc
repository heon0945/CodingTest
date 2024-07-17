#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n, m;
int board[1001][1001];
int check[1001][1001];
pair<int, int> des;
int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };



bool OOB(int x, int y) {
	if (x >= 0 && x < n) {
		if(y >= 0 && y < m)
			return false;
	}
	return true;
}

int main() { 

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int tmp;
			cin >> tmp;
			board[i][j] = tmp;
			if (tmp == 0) {
				check[i][j] = 0;
			}
			else if (tmp == 2) {
				des = { i, j };
				check[i][j] = 0;
			}
			else
				check[i][j] = -1;
		}
	}


	queue<pair<int, int>> q;
	q.push({ des.first, des.second });

	while (!q.empty()) {

		int tx = q.front().first;
		int ty = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = tx + dx[i];
			int ny = ty + dy[i];

			if (!OOB(nx, ny) && board[nx][ny] == 1 && check[nx][ny] == -1) {
				q.push({ nx, ny });
				check[nx][ny] = check[tx][ty] + 1;
			}
		}


	}


	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << check[i][j] << " ";
		}

		cout << '\n';
	}

	return 0;
}