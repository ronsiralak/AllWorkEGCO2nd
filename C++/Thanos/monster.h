//
//  monster.h
//  Thanos
//
//  Created by Mingmanas Sivaraksa on 24/4/2563 BE.
//  Copyright © 2563 Mingmanas Sivaraksa. All rights reserved.
//

#ifndef monster_h
#define monster_h
class monster {
private:
    int hp;
    string name;
public:
    void fight(monster*); // Print Fight hp is reduct ...
    void heal(); // heal by 1
    //constructor and destructor;
    monster(string,int = 100);
    ~monster();
    void operator++();
    void operator+=(int);
    void kill();
    int get_hp();
    string get_name();
};

void monster::kill() {
    hp = 0;
}

int monster::get_hp() {
    return hp;
}

string monster::get_name() {
    return name;
}

void monster::operator++() {
    hp++;
}

void monster::operator+=(int x) {
    hp+=x;
}

void monster::fight(monster *A) {
    if(hp>7) {
        hp = hp-50;
        cout<<name<<" fight. "<<"Thanos took 0 damage. But "<<name<<" take 50 damage remain HP: "<< hp<<endl;
    } else if(hp<7 && hp>0) {
        cout <<"Lucky " <<name<<" 1 hit kill Thanos."<<endl;
        A->kill();

    } else
        cout<<"Sand("<<name<< ") can't fight lol."<<endl;
}

void monster::heal() {
    if(hp>0) {
        hp++;
        cout << name << " is heal HP: " << hp <<endl;
    } else
        cout<< name <<" HP is 0 can't heal"<<endl;
}
monster::monster(string n,int h) {
    name=  n;
    hp = h;
    cout << name <<" is here hp: "<<hp<<endl;
}
monster::~monster() {
    cout << name << " is deleted with hp: "<<hp<<endl;
}

#endif /* monster_h */
