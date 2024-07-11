#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int main(){

	int n;
	cin >> n;

	int ti[6];
	for (int i = 0; i < 6; i++) {
		cin >> ti[i];
	}

	int t, p;
	cin >> t >> p;

	
	int bundle = 0;
	for (int i = 0; i < 6; i++) {
		if(ti[i] % t == 0)
			bundle += (ti[i] / t);
		else
			bundle += (ti[i] / t + 1);
	}



	cout << bundle << '\n';
	cout << n / p << " " <<  n % p;

	return 0;

}