//
//  cat_dog.h
//  LLC1
//
//  Created by 6272 on 22/3/2562 BE.
//  Copyright © 2562 6272. All rights reserved.
//

#ifndef cat_dog_h
#define cat_dog_h
class cat:public NODE{
    int size;
public:
    cat(int,int);
    void show_node(){
        cout<<"\nMeaw  "<<endl;
        NODE::show_node();
    }
    ~cat(){ cout<<"\ncat is deleted"<<endl;}
};

cat::cat(int i,int s):NODE(i){

    size=s;
    cout<<"cat size="<<size<<endl<<endl;
}

class dog:public NODE{
    float height;
public:
    dog(int , float);
     ~dog() { cout<<"\nDog is dead"<<endl;}
    void show_node(){
        cout<<"\nHong Hong  "<<endl;
       NODE::show_node();
    }
};

dog::dog(int i, float h):NODE(i){
    height=h;
    cout<<"Dog height="<<height<<endl<<endl;
}
#endif /* cat_dog_h */
