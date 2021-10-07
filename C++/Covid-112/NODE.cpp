#include <iostream>
#include <string>
using namespace std;
#include"NODE.h"
#include <cstdlib>

NODE::NODE(string ID) {
    id=ID;
    name = "";
    last_name = "";
    job = "";
    salary =0;
    tax = 0;
    next=NULL;
    score = 0;
    cout<< "adding NEW ID:"<< id <<endl<<endl;
}
NODE::NODE(string ID,string n,string ln,string j,int s,int t) {
    name = n;
    last_name = ln;
    job = j;
    salary =s;
    tax = t;
    id=ID;
    score = 0;
    next=NULL;
    cout<< "adding ID:"<< id <<endl<<endl;
}
NODE:: ~NODE() {
    cout<<"ID "<<id<<" is being deleted"<<endl<<endl;
}
NODE* NODE::move_next() {
    return next;
}

void NODE::insert(NODE*& x) {
    x->next=this;
}

void  NODE:: show_node() {
    cout<<"DATA "<<id<<"  "<<name<<" "<<last_name<<" "<<job<<" "<<salary<<" "<<tax<<" "<<endl<<endl;
}


string  NODE:: get_ID() {
    return id;
}

string  NODE:: get_job() {
    return job;
}

void NODE::set_jobID(int jobID) {
    job_id = jobID;
}

int NODE::calculate_score() {
    int score_job,score_salary,score_tax;

    return score;
}
int NODE::get_score() {
    return score;
}

void NODE::swap_NODE(NODE*&A,NODE*&B,NODE*&C) { // a b c-> b a c
    A->next = C;
    B->next = A;
}

string NODE::create_data(){
    string out;
    out = id+","+name+","+last_name+","+to_string(salary)+","+to_string(tax);
    return out;
}
void NODE::import_data(string n,string ln,string j,int s,int t) {
    name = n;
    last_name = ln;
    job = j;
    salary =s;
    tax = t;
    job_id = 0;
    score = 0;

}

void NODE::edit_data(string n,string ln,string j,int s,int t) {
    name = n;
    last_name = ln;
    job = j;
    salary =s;
    tax = t;
    job_id = 0;
    score = 0;
}
