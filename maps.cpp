#include<map>
#include<iostream>
using namespace std;
int main()
{
    pair<int, int> p = make_pair(6,21);
    cout << p.first << "    " << p.second;
    multimap<int, int> m;
   m[1] = 33;
   m[4] = 45;
    m.insert(make_pair(6, 200));
    
    for (auto it = m.begin(); it != m.end();it++)
    {
        cout <<it->first<< '---'<<it->second<<endl;
    }

        return 0;
}
