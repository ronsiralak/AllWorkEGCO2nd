//
//  cat_dog.h
//  LLC1
//
//  Created by 6272 on 22/3/2562 BE.
//  Copyright © 2562 6272. All rights reserved.
//
#ifndef cat_dog_h
#define cat_dog_h
class cat:public NODE {
    int size;
public:
    cat(int,int);
    ~cat();
    void show_node() {
        cout<<"Meaw  ";
        NODE::show_node();
    }
};
cat::cat(int n,int s):NODE(n) {
    size =s;
    cout << "cat size = "<<size<<endl;
}
cat::~cat(){
cout<< "cat is deleted"<<endl<<endl;
}

class dog/*:public NODE*/ {
    float height;
public:
    dog(/*int,*/float);
    ~dog();
    void show_node() {
        cout<<"Hong Hong  ";
        //NODE::show_node();
    }
};
dog::dog(/*int n,*/float h)/*:NODE(n)*/ {
    height = h;
    cout << "dog height = "<<height<<endl<<endl;
}
dog::~dog(){
cout<< "dog is deleted"<<endl<<endl;
}
#endif /* cat_dog_h */
