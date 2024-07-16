#include <iostream>
#include <stack>

using namespace std;

int main()
{
    int count = 0;

    int num;
    cin >> num;

    for (int i = 0; i < num; i++) {
        string word;
        cin >> word;

        stack<char> stack;
        for (int w = 0; w < word.size(); w++) {
            if (stack.empty()) {
                stack.push(word[w]);
            }
            else {
                if (stack.top() != word[w]) {
                    stack.push(word[w]);
                }
                else
                    stack.pop();
            }
            if (w == word.size() - 1) {
                if (stack.empty())
                    count++;
            }
        }

    }
        cout << count;
}