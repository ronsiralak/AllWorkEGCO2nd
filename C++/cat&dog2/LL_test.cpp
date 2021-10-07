#include <iostream>
#include<cstdlib>
using namespace std;
#include"NODE.h"
#include"cat_dog.h"
#include"LL.h"


int main(int argc, char *argv[])
{
   LL A;
   int i,animal;
   NODE *t;

   for(i=1;i<argc;i++) {
       cout << "Select animal 1 = dog     2 = cat"<<endl;
       cin>>animal;

         if(animal ==2)   t=new cat(i,atoi(argv[i]));

         else  t=new dog(i,atof(argv[i]));

        A.add_node(t);
   }
   A.show_all();

}
