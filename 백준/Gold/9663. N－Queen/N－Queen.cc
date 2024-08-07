#include <iostream>
#include <vector>

using namespace std;

int n;
int cnt;
int board[16];

bool possible(int cur, int tmp, int board[]) {
	for (int i = 0; i < cur; i++) {
		if (tmp == board[i] || abs(cur - i) == abs(tmp - board[i])) {
			return false;
		}
	}
	return true;
}

void nqueen(int cur, int board[]) {
	if (cur == n) {
		cnt++;
		return;
	}
	else {

		for (int i = 0; i < n; i++) {
			if (possible(cur, i, board)) {
				board[cur] = i;
				nqueen(cur + 1, board);
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	nqueen(0, board);
	
	cout << cnt;

	return 0;
}