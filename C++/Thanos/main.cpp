//
//  main.cpp
//  Thanos
//
//  Created by Mingmanas Sivaraksa on 24/4/2563 BE.
//  Copyright © 2563 Mingmanas Sivaraksa. All rights reserved.
//

#include <iostream>
using namespace std;
#define Monster_Size 3
#include "Thanos.h"
int main() {
    monster m[3]= {monster("Iron Man",7),
                   monster("Hulk"),
                   monster("Captain",15)
                  };
    ++m[0];
    m[1]+=5;
    Thanos T;
    monster *t;
    t=&T;
    ++T;
    T.snap_finger(m);
    ++T;
    ++T;
    ++T;
    ++T;
    ++T;
    T.snap_finger(m);
    for(int i =0; i<Monster_Size;i++) {
        m[i].fight(t);
        m[i].heal();
    }
    m[1].fight(t);
    m[1].fight(t);
    if(t->get_hp()==0){
        T.dead();
    }
    //delete t;
    return 0;

}
