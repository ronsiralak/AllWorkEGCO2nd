#include <iostream>
#include<cstdlib>
using namespace std;
#include"NODE.h"
#include"cat_dog.h"
#include"LL.h"

class cog:public cat,private dog{
    int voice;
    public:
        cog(int,int,int,float);
        ~cog();
};

cog::cog(int a,int b,int c,float d):cat(b,c),dog(d){
voice = a;
cout<<"Cog is coming"<<endl<<endl;
}
cog::~cog(){

cout<<"no cog "<<voice<<endl;

}
int main(int argc, char *argv[])
{  LL A;
   int i;
   NODE *t;

   for(i=1;i<argc;i++) {
         if(i%2)   t=new cat(i,atoi(argv[i]));
         else  t=new dog(i,atof(argv[i]));
                       A.add_node(t);
   }
   A.show_all();
     /*  cog A(1,2,3,4.0);
    A.cat::show_node(); // A.show_node(); ->same A.cat::show_node();
    A.dog::show_node();*/

}
