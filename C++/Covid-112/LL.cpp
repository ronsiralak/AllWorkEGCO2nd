#include <iostream>
#include <fstream>
#include"NODE.h"
#include"LL.h"
#include <cstdlib>
using namespace std;
int selectID(string);
LL::LL() {
    head_LL=NULL;
    tail_LL=NULL;
    size=0;
}

LL::~LL() {
    NODE* t=head_LL;
    int i;
    for(i=0; i<size; i++) {
        delete t;
        t=t->move_next();
    }

}

void LL::show_all() {
    NODE* t=head_LL;
    int i;
    for(i=0; i<size; i++) {
        t->show_node();
        t=t->move_next();
    }
}
void LL::add_node(NODE *&A) {

    head_LL->insert(A);
    head_LL=A;
    size++;
}

int LL::Find_ID(string input) {
    NODE* t=head_LL;
    int i,x=0;
    for(i=0; i<size; i++) {
        if (t->get_ID()==input) {
            x=1;
            break;
        }
        t=t->move_next();
    }
    return x;
}


NODE* LL::Find_node(string input) {
    NODE* t=head_LL;
    for(int i=0; i<size; i++) {
        if (t->get_ID()==input) {
            break;
        }
        t=t->move_next();
    }
    return t;
}


void LL::give_JobID() {
    NODE* t=head_LL;
    string j = t->get_job();
    int jobID;
    for(int i=0; i<size; i++) {
        jobID = selectID(j);
        t->set_jobID(jobID);
        t=t->move_next();
    }
}

int LL::selectID(string s) {
    int output ;
    if(s=="student") output = 1;



    return output;

}

void LL::give_score() {

}

void LL::sort_data() {
    NODE*A = head_LL;
    NODE*B = A->move_next();
    NODE*C = B->move_next();
    /*B->move_next();
    C->move_next();
    C->move_next();*/
    int i,j,A_score,B_score;
    for(i=0; i<size; i++) {
        //cout<<"for_i"<<endl;
        A_score = A->get_score();
        for(j=0; j<size-1; j++) {
            //cout<<"for_j"<<endl;
            B_score = B->get_score();
            if(A_score > B_score) {
                cout<<"will swap"<<endl;
                A-> swap_NODE(A,B,C);
            }
            A->move_next();
            B->move_next();
            C->move_next();
        }
    }
}

void LL::write() {
    NODE*h = head_LL;
    fstream fout;
    string data_write;
    fout.open("data_new2.csv", ios::out);
    fout<<"id,name,last_name,salary,tax"<<endl;
    for(int i =0; i<size; i++) {
        data_write = h->create_data();
        fout << data_write<<endl;
        h = h->move_next();
    }
}


