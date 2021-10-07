#include <iostream>
using namespace std;
#include"NODE.h"

NODE::NODE(string n,int h,int p){
      name=n;
      hp=h;
      potion=p;
      next=NULL;
      cout<< "adding monster" << "Name: " << n << " HP: " << h << " Potion " <<p<<endl<<endl;
}
  NODE:: ~NODE(){
      cout<<"Monster "<<name<<" is being deleted"<<endl<<endl;
}
NODE* NODE::move_next(){
      return next;
}
void  NODE:: show_node(){
         cout<<"Monster DATA "<<" Name: "<<name<<" HP: "<<hp<<" Potion "<<potion<<endl<<endl;
 }
void NODE::insert(NODE*& x){
     x->next=this;

     }
