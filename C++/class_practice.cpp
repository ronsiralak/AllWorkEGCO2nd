#include <iostream>
#include <iomanip>

using namespace std;

class student {
private:
    int age;
    string name;

public:
    void set_data(string = "Ron",int=18);
    string request_name();
    void show_info();
    void age_plus(int);

};

void student::set_data(string n,int a) {
    age =a;
    name = n;
}
string student::request_name() {
    return name;
}
void student::show_info() {
    cout<< "Name: "<< name<<endl;
    cout<< "Age: "<<age<<endl;
}
void student::age_plus(int n) {
    age += n;
    }
int main() {
    student st1;
    st1.set_data();
    cout << "My name is "<<st1.request_name()<<endl;
    st1.show_info();
    st1.age_plus(2);
    st1.show_info();
    return 0;
}
