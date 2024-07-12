#include <iostream>
#include <vector>


using namespace std;

int main() {

	int a, b;
	cin >> a >> b;

	if (b > a - b) {
		int tmp = a - b;
		b = tmp;
	}

	int div = 1;
	int mul = 1;
	for (int i = 1; i <= b; i++) {
		mul *= a - (i - 1);
		div *= i;
	}

	cout << mul/div;

	return 0;

}