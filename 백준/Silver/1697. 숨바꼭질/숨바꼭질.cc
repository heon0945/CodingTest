#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int pos;
int target;
int time = 0;
int check[200002];

int main() {

	cin >> pos >> target;

	queue<pair<int, int>> q;
	q.push({ pos, 0 });
	check[pos] = 1;

	while(q.front().first != target) {

		int tmp = q.front().first;
		int cnt = q.front().second;
		q.pop();

		if (target < tmp) {
			if (check[tmp - 1] == 0) {
				q.push({ tmp - 1, cnt + 1 });
				check[tmp - 1] = 1;
			}
		}
		else if (tmp <= 0) {
			if (check[tmp + 1] == 0) {
				q.push({ tmp + 1, cnt + 1 });
				check[tmp + 1] = 1;
			}
		}
		else {
			if (check[tmp - 1] == 0) {
				q.push({ tmp - 1, cnt + 1 });
				check[tmp - 1] = 1;
			}
			if (check[tmp + 1] == 0) {
				q.push({ tmp + 1, cnt + 1 });
				check[tmp + 1] = 1;
			}
			if (check[tmp * 2] == 0) {
				q.push({ tmp * 2, cnt + 1 });
				check[tmp * 2] = 1;
			}
		}
	}

	cout << q.front().second;


	
	return 0;
}