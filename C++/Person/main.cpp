//
//  main.cpp
//  Inherit
//
//  Created by 6272 on 19/4/2562 BE.
//  Copyright © 2562 6272. All rights reserved.
//

#include <iostream>
using namespace std;
#include "Person.h"

class student:private person {
private:
    double gpa;
    int year;
public:
    student(int=611111,string="Jacky",int = 1);
    void display();/* display name, id ,gpa of a student*/
    ~student();
    void change(string s){
        change_name(s);
    }
};
student::student(int i,string s,int y):person(i,s) {
    cout<<"Input your GPA:";
    cin>>gpa;
    year = y;
}
student::~student() {
    cout<<"destructor student "<<name<<endl<<endl;
}
void student::display() {
    cout<<"Name:"<<name<<endl;
    cout<<"ID:"<<id<<endl;
    cout<<"Gpa:"<<gpa<<endl;
    cout << "Year: "<<year<<endl<<endl;
}
int main(int argc, const char * argv[]) {
    student s(6213133,"Ron");
    s.change("Oily");
    s.display();
    return 0;
}


