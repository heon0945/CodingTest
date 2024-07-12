#include <iostream>
#include <string>

using namespace std;

int arr[10000];

int main() {

	int n;
	cin >> n;

	int sit = 0;
	int eit = 0;

	while (n--) {
		string order;
		cin >> order;

		if (order == "push") {
			int n;
			cin >> n;
			arr[eit] = n;
			eit++;
		}
		else if (order == "pop") {
			if(eit == sit)
				cout << -1 << '\n';
			else {
				cout << arr[sit] << '\n';
				sit++;
			}
		}
		else if (order == "size") {
			cout << eit-sit << '\n';
		}
		else if (order == "empty") {
			if (eit == sit)
				cout << 1 << '\n';
			else
				cout << 0 << '\n';
		}
		else if (order == "front") {
			if (eit == sit)
				cout << -1 << '\n';
			else
				cout << arr[sit] << '\n';
		}
		else if (order == "back") {
			if (eit == sit)
				cout << -1 << '\n';
			else
				cout << arr[eit - 1] << '\n';
		}

	}
}