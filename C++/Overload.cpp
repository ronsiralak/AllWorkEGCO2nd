#include <iostream>
#include <iomanip>

using namespace std;

class student {
private:
    int age;
    float gpa;
    string name;
public:
    student(int = 15);
    student(string,int = 19);
    bool operator==(student);
    void operator++();
    void operator+=(int);
    void operator+=(student);
    student operator+(student);
};

student student::operator+(student x){
    student a=*this;
    a.age = age+x.age;
    return a;
}

bool student::operator == (student st) {
    return age == st.age;
}

void student::operator ++ () {
    age+=4;
}

void student::operator += (int x) {
    age+=x;
}

void student::operator += (student x) {
    age+=x.age;
}

student::student(int x) {
    age = x;
}
student::student(string n,int a) {
    name  = n;
    age = a;
}

int main() {
    student s, s2("Doraemon");
    student s3 = s+s2;
    ++s;
    s+=4; //s.oprator+=(4);
    s+=s2;
    if(s==s2)
        cout << "Yes" << endl;
    else
        cout << "No" << endl;
}
