//
//  Thanos.h
//  Thanos
//
//  Created by Mingmanas Sivaraksa on 24/4/2563 BE.
//  Copyright © 2563 Mingmanas Sivaraksa. All rights reserved.
//

#ifndef Thanos_h
#define Thanos_h
#include "monster.h"
class Thanos:public monster {
private:
    int stones;
    int hp; //extra hp;
public:
    Thanos(string ="Thanos",int = 10000,int = 0);
    ~Thanos();
    /* constructor and destructor */
    void snap_finger(monster[]);
    /* show all hps
    / clear half of monster hp, if stones =6*/
    void operator++(); // increase the stone;
    void dead();

};
void Thanos::dead() {
    hp=0;
}
void Thanos::operator++() {
    stones++;
    cout<<"\nThanos get a stone"<<endl;
    cout<<"Thanos have "<<stones<<" of 6"<<endl;
}


void Thanos::snap_finger(monster m[]) {
    if(stones==6) {
        int mon_hp;
        string mon_name;
        cout <<"\nThanos snap"<<endl;
        cout <<"Have "<< stones <<" stones. Requirement complete"<<endl;
        for(int i=0; i<Monster_Size; i++) {
            mon_hp = m[i].get_hp();
            mon_name= m[i].get_name();
            if(mon_hp<50) {
                m[i].kill();
                cout<<mon_name <<" become sand so sad." << endl;
            } else
                cout<< mon_name <<" survive."<<endl;
        }
        cout<<endl;
    } else {
        cout<<"\nThanos snap"<<endl;
        cout << "Have "<< stones <<" stones of 6. Can't use"<<endl;
    }

}

Thanos::Thanos(string n,int h,int s):monster(n,h) {
    stones = s;
    hp = 1000;
    cout << "Thanos is coming. hp: "<<hp <<" stone: "<<stones<<endl;
}
Thanos::~Thanos() {
    cout << "\nBegin destruct"<<endl;
    cout << "Thanos is destructed by system with hp: "<<hp<<endl;
    if(hp>0)
        cout << "God destroy Thanos nub Avengers team."<<endl;
    else
        cout << "Congratulation! Thanos is defeated."<<endl;
}


#endif /* Thanos_h */
