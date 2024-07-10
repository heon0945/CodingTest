#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int board[15][15];
int checked[15][15];

vector<pair<int, int>> obs; //장애물 위치 저장

int mx[4] = { 0, 1, 0, -1 };
int my[4] = { 1, 0, -1, 0 };

bool OOB(int n, int x, int y) {
	if (x >= 0 && x < n) {
		if (y >= 0 && y < n)
			return false;
	}
	return true;
}

int bfs(int sx, int sy, int dx, int dy, int n) {

	int limit = n * n;

	queue<pair<int, int>> q;
	q.push({ sx, sy });
	checked[sx][sy] = 1;

	int time = 0;

	while (!q.empty()) {

		int sz = q.size();
		for (int i = 0; i < sz; i++) {

			int x = q.front().first;
			int y = q.front().second;

			q.pop();

			//startpoint 기반으로 전부 4방향 탐색
			for (int j = 0; j < 4; j++) {

				//벽이 없고, checked 안 된 경우 진행
				if (!OOB(n, x + mx[j], y + my[j])
					&& board[x + mx[j]][y + my[j]] != 1
					&& checked[x + mx[j]][y + my[j]] == 0) {

					//길이 있는 경우
					if (board[x + mx[j]][y + my[j]] == 0) {
						q.push({ x + mx[j], y + my[j] });
						checked[x + mx[j]][y + my[j]] = 1;
					}
					//소용돌이가 지날 수 있는 경우
					else if (time % 3 == 2) {
						q.push({ x + mx[j], y + my[j] });
						checked[x + mx[j]][y + my[j]] = 1;
					}
					//소용돌이 지날 수 없는 경우 (제자리 머무름)
					else
						q.push({ x, y });

					//startpoint가 목적지인 경우 종료
					if (x + mx[j] == dx && y + my[j] == dy) {
						return ++time;
					}

				}
			}
		}
		//t증가
		time++;
	}

	return limit;


}



int main() {

	int tc;
	cin >> tc;

	vector<int> answer;
	while (tc) {

		int n;
		cin >> n;


		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> board[i][j];
				checked[i][j] = 0;
				//2 : tornado 위치 저장 필
				if (board[i][j] == 2) {
					obs.push_back({ i, j });
				}
			}
		}

		int sx, sy; //startpoint
		int dx, dy; //destination

		cin >> sx >> sy >> dx >> dy;

		int ans = bfs(sx, sy, dx, dy, n);

		if (ans == n * n)
			answer.push_back(-1);
		else
			answer.push_back(ans);

		tc--;
	}

	for (int i = 0; i < answer.size(); i++)
		cout << "#" << i + 1 << " " << answer[i] << '\n';

	return 0;
}