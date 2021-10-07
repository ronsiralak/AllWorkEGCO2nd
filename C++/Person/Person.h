//
//  Person.h
//  Inherit
//
//  Created by 6272 on 19/4/2562 BE.
//  Copyright © 2562 6272. All rights reserved.
//

#ifndef Person_h
#define Person_h
class person {
protected:
    int id;
    string name;
public:
    person(int=5,string="Moomin");
    void change_name(string);
    ~person();
};

void person::change_name(string n) {
    name=n;
}
person::person(int i,string n) {
    id=i;
    name=n;
    cout<<"Person Constructor "<<name<<endl;
}
person::~person() {
    cout<<"destructor person"<<endl<<endl;
}
#endif /* Person_h */
