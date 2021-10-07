#include <iostream>
#include <iomanip>

using namespace std;

class Time {
private:
    int h,m,s;
public:
    void getTime();
    Time subtract(Time);
    void display();
};

void Time::getTime() {
    cin>>h;
    cin>>m;
    cin>>s;
}

Time Time::subtract(Time T) {
    Time T3;
    T3.h =  h - T.h;
    T3.m =  m - T.m;
    T3.s =  s - T.s;
    return T3;
}
void Time::display() {
    while(s<0)  {s=s+60; m=m-1; }
    while(m<0)  {m=m+60; h=h-1; }
    while(h<0)  {h=h+24;}
    while(s>60) {s=s-60; m=m+1; }
    while(m>60) {m=m-60; h=h+1; }
    while(h>24) {h=h-24;}
    cout<<"Time diff is ";
    cout<<setfill('0')<<setw(2)<<h<<":";
    cout<<setfill('0')<<setw(2)<<m<<":";
    cout<<setfill('0')<<setw(2)<<s<<endl;
}
int main() {
    Time t1,t2,t3;
    cout<<"What time was it?\n"<<"T1 = ";
    t1.getTime();
    cout<<"What time is it now?\n"<<"T2 = ";
    t2.getTime();
    t3 = t2.subtract(t1);
    t3.display();
    return 0;
}
