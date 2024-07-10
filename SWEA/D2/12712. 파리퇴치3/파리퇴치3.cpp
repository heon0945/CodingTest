#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int z[15][15];

//OOB
bool OOB(int r, int c, int n) {
	if (r >= 0 && r < n) {
		if (c >= 0 && c < n)
			return false;
	}
	return true;
}

//x
int x(int r, int c, int n, int sp) {
	int flies = z[r][c];
	for (int i = 1; i < sp; i++) {
		if (!OOB(r-i, c, n)) flies += z[r-i][c];
		if (!OOB(r+i, c, n)) flies += z[r+i][c];
		if (!OOB(r, c-i, n)) flies += z[r][c-i];
		if (!OOB(r, c+i, n)) flies += z[r][c+i];
	}
	return flies;
}

//+
int p(int r, int c, int n, int sp) {
	int flies = z[r][c];
	for (int i = 1; i < sp; i++) {
		if (!OOB(r - i, c+i, n)) flies += z[r - i][c+i];
		if (!OOB(r + i, c+i, n)) flies += z[r + i][c+i];
		if (!OOB(r-i, c - i, n)) flies += z[r-i][c - i];
		if (!OOB(r+i, c - i, n)) flies += z[r+i][c - i];
	}
	return flies;
}

int main() {

	int tc;
	cin >> tc;

	vector<int> answer;


	while (tc) {

		int n, m;
		cin >> n >> m;


		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int f;
				cin >> f;
				z[i][j] = f;
			}
		}

		int flies = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				flies = max(flies, x(i, j, n, m));
				flies = max(flies, p(i, j, n, m));
			}
		}
		answer.push_back(flies);



		tc--;
	}


	for (int i = 0; i < answer.size(); i++) {
		cout << "#" << i + 1 << " " << answer[i] << '\n';
	}
	return 0;
}