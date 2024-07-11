#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;


int main() {

	double a, b;
	cin >> a >> b;

	cout.setf(ios::fixed);
	cout.precision(9);
	cout << a / b;
}