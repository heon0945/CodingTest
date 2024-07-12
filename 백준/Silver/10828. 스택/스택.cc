#include <iostream>
#include <string>

using namespace std;

int arr[10000];

int main() {

	int n;
	cin >> n;

	int it = 0;

	while (n--) {
		string order;
		cin >> order;

		if (order == "push") {
			int n;
			cin >> n;
			arr[it] = n;
			it++;
		}
		else if (order == "pop") {
			if(it == 0)
				cout << -1 << '\n';
			else {
				cout << arr[it - 1] << '\n';
				it--;
			}
		}
		else if (order == "size") {
			cout << it << '\n';
		}
		else if (order == "empty") {
			if (it == 0)
				cout << 1 << '\n';
			else
				cout << 0 << '\n';
		}
		else if (order == "top") {
			if (it == 0)
				cout << -1 << '\n';
			else
				cout << arr[it - 1] << '\n';
		}

	}
}