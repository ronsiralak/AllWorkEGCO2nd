#include <iostream>
#include"NODE.h"
#include"LL.h"
using namespace std;
LL::LL(){
       hol=NULL;
       size=0;
}

LL::~LL(){
    NODE* t;
    int i;
         for(i=0;i<size;i++){
             t=hol;
             hol=t->move_next();
             delete(t); // call destruct of each node
         }
}

void LL::show_all(){
     NODE* t=hol;
     int i;
     for(i=0;i<size;i++){
         t->show_node();
         t=t->move_next();
                 /**
                 complete this part
                 */
     }
}
void LL::add_node(NODE *&A){

          hol->insert(A);
          hol=A;

       size++;

 }
