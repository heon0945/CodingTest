#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int gcd(int a, int b) {
	if (a % b == 0)
		return b;

	return gcd(b, a%b);
}

int lcm(int a, int b, int gcd) {
	int tmp = b / gcd;
	return tmp * a;
}


int main() {

	int a, b;
	cin >> a >> b;

	if (a < b) {
		int tmp = a;
		a = b;
		b = tmp;
	}

	cout << gcd(a, b) << '\n';
	cout << lcm(a, b, gcd(a, b)) << '\n';;
	


	return 0;
}