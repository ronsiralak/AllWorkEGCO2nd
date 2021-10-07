#include <iostream>
#include<cstdlib>
#include"NODE.h"
#include"LL.h"
using namespace std;
int main(int argc, char *argv[]) {
    LL A;
    int i;
    NODE *t;

    for(i=1; i<argc; i+=3) {
        t=new NODE(argv[i],atoi(argv[i+1]),atoi(argv[i+2]));
        A.add_node(t);
    }
    A.show_all();
    system("PAUSE");
    return EXIT_SUCCESS;
}
