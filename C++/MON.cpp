#include <iostream>
using namespace std;

class monster{
        friend void rip_monster_off(monster&);
        int hp,potion;
        string name;
  public:
      monster(string="Pokemon",int=5,int = 100);
      ~monster();
      void show();
};
monster::monster(string b,int a,int a2){
hp =a;
potion = a2;
name = b;
cout<<"Use constructor"<<endl;
}

monster::~monster(){
cout<<"Destructor "<<name<<endl<<endl;
}

void monster::show(){
    cout<<"{"<< "My hp is "<<hp<<endl;
    cout<< "My name is "<<name<<endl;
    cout<< "My potion is "<<name<<"}"<<endl;
}

void rip_monster_off(monster &x){
    x.hp = 0;
}


int main(){
    /*monster x(10,20,"Ron"),b(20),c[4];
    x.show();
    b.show();
    c[3].show();
    rip_monster_off(x);
    x.show();*/
    monster *p,x("Wiwat");
    p=&x;
    p = new monster();
    delete(p);
    return 0;
    p=new monster[3];
    delete[]p;
    return 0;
}
