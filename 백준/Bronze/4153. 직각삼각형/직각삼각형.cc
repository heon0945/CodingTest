#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int main(){

	vector<int> input;
	while (true) {
		input.clear();

		int a, b, c;
		cin >> a >> b >> c;

		if (a == 0)
			break;

		input.push_back(a);
		input.push_back(b);
		input.push_back(c);

		sort(input.begin(), input.end());

		if (pow(input[0], 2) + pow(input[1], 2) == pow(input[2], 2))
			cout << "right" << '\n' ;
		else
			cout << "wrong" << '\n';

	}

	return 0;

}