#include <iostream>
#include <iomanip>

using namespace std;
void square(int x) { *x*=*(x); }
int main(){
    /*int age;
    cin >> age;
    cout << "Hello World" << endl;
    cout << "Age : " <<age << endl;*/
    float x=10.2567;
    cout<<setprecision(2)<<x<<endl;
    cout<<setprecision(3)<<x<<endl;
    cout<<fixed<<setprecision(2)<<x<<endl;
    int a =3;
   // square(3);
    //cout<<a;
   // cout<<square(5);
   int a=3; square(3); cout<<a;
    return 0;
}
