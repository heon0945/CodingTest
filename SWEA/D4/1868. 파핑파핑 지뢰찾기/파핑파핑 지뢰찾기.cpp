#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int dx[8] = { -1, 0, 1, -1, 1, -1, 0, 1 };
int dy[8] = { -1, -1, -1, 0, 0, 1, 1, 1 };

char board[300][300];
int checked[300][300];

bool OOB(int n, int px, int py) {
	if (px >= 0 && px < n) {
		if (py >= 0 && py < n) {
			return false;
		}
	}
	return true;
}

//주변에 지뢰 개수 반환
char findLM(int n, int px, int py) {
	char cnt = '0';

	for (int k = 0; k < 8; k++) {
		if (!OOB(n, px + dx[k], py + dy[k])
			&& board[px + dx[k]][py + dy[k]] == '*')
			cnt++;
	}
	return cnt;
}

void spread(int n, int px, int py) {
	
	queue<pair<int, int>> q;

	q.push({ px, py });
	checked[px][py] = 1;

	while (!q.empty()) {

		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int k = 0; k < 8; k++) {
			if (!OOB(n, x + dx[k], y + dy[k]) 
				&& board[x + dx[k]][y + dy[k]] != '*'
				&& checked[x + dx[k]][y + dy[k]] == 0){
				checked[x + dx[k]][y + dy[k]] = 1;
				if(board[x + dx[k]][y + dy[k]] == '0')
					q.push({ x + dx[k], y + dy[k] });
			}
				
		}

	}
}


int main() {
	
	int tc;
	cin >> tc;

	vector<int> answer;

	while (tc) {

		int n;
		cin >> n;

		int click = 0;

		//make board
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				char tmp;
				cin >> tmp;
				board[i][j] = tmp;
				checked[i][j] = 0;
			}
		}

		//write LM count
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//if zerozone
				if (board[i][j] == '.')
					board[i][j] = findLM(n, i, j);
			}
		}
		
		//find zero zone
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//if zerozone
				if (board[i][j] == '0' && 
					checked[i][j] == 0) {	
					click++;
					spread(n, i, j);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//count
				if (checked[i][j] == 0
					&& board[i][j] != '*'){
					click++;
				}
			}
		}

		answer.push_back(click);

		tc--;
	}
	
	for (int i = 0; i < answer.size(); i++) {
		cout << "#" << i + 1 << " " << answer[i] << '\n';
	}

	return 0;
}