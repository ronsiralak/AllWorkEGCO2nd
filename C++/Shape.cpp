#include <iostream>
using namespace std;

class shape {
public:
    shape(int,int = 0);
    virtual double area()  = 0;
    virtual  ~shape();
protected :
    int a,b;
};

shape::shape(int x,int y) {
    a=x;
    b=y;
    cout<<"Create Shape"<<endl;
}
 shape::~shape(){
 cout<<"Destruct Shape"<<endl<<endl;
 }

class rectangle:public shape {
public:
    rectangle(int x,int y):shape(x,y) {
        cout<<"Create Rectangle"<<endl;
    }
    ~rectangle(){
     cout<<"Destruct Rectangle"<<endl;
    }
    double area() {
        return a*b;
    }
};

class circle:public shape {
public:
    circle(int x):shape(x) {
        cout<<"Create Circle"<<endl;
    }
    ~circle(){
     cout<<"Destruct Circle"<<endl;
    }
    double area() {
        return 3.14*a*a;
    }
};

int main() {
    shape * A ;/*= new shape(3,2);
    cout << A->area() <<endl;
    delete A;*/

    A = new circle(5);
    cout << A->area() <<endl;
    delete A;

    A = new rectangle(2,3);
    cout << A->area() <<endl;
    delete A;

};
