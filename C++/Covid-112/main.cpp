#include <iostream>
#include <cstdlib>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include "NODE.h"
#include "LL.h"
using namespace std;

vector<string> data;
NODE *t;
LL DATA;
int sizeofdata=0;

void inputdata(string name,string last_name,string job,double tax) {

}


void seperate(string str) {  //แยกข้อความ 1 บรรทัดให้เป็น vector แล้วเอาไปใส่ NODE
    int n = str.length();
    string a="",b;
    for(int i =0; i<n; i++) {
        b=str[i];
        if(b != ",") {
            a=a+b;
        } else {
            data.push_back(a);
            a="";
        }
    }
    data.push_back(a);
    t=new NODE(data[0],data[1], data[2], data[3],stoi(data[4]),  stoi(data[5])) ;
    DATA.add_node(t);
    DATA.show_all();
    data.clear();
    for(int i=0; i<data.size(); i++) {
        cout << data[i]<<endl;
    }
}

void create(string data) { //สร้างไฟล์ตอนจบ
    fstream fout;
    fout.open("data_new.csv", ios::out | ios::app);
    fout << data<<endl;
    seperate(data);
}

void Build_LL() {   //เขียนไฟล์ใหม่หลังรับข้อมูล
    ifstream fin;
    string line;
    int j;
    fin.open("data.csv");
    while(!fin.eof()) {
        fin>>line;
        sizeofdata++;
    }
    fin.close();
    fin.open("data.csv");
    sizeofdata--;
    for(j=0; j<sizeofdata; j++) {
        fin>>line;
        cout<<line<<" "<<endl;
        create(line);
    }
    /* while(getline(fin,line)) {
         fin>>line;
         cout<<line<<" "<<endl;
         create(line);
     }*/
}
int main() {
    NODE *target;
    Build_LL();
    int check = 0;
    string input = "3";
    check =  DATA.Find_ID(input);
    if(check==1) {
        cout<<"Found data"<<endl;
        target = DATA.Find_node(input);
        target->edit_data("test10","test010","God",30000,0);
    } else {
        cout<<"NOT Found data"<<endl;
        cout<<"Create data id: "<<input<<endl;
        t=new NODE(input);
        DATA.add_node(t);
        //รับข้อมูลจากUI
        t->import_data("test09","test009","king",99999,0);
    }
    DATA.give_JobID();
    DATA.give_score();
    DATA.sort_data();
    DATA.show_all();
    DATA.write();
}
/*
int main() {
    read csv to build LL
    getID
    FindID
    if(found) {
        getdata
        choose edit or show_satate
    } else {
        inputdata
        show_state
    }
}*/
/*
int main() {
    LL A;
    NODE *t;
    t=new NODE("data test");
    A.add_node(t);
    Build_LL();
    for(int i =0;i<sizeofdata;i++){
            t=new NODE(data[i]);
        DATA.add_node(t);
    }
    system("PAUSE");
    return EXIT_SUCCESS;
    remove("data.csv");
    rename("data_new.csv", "data.csv");
     seperate("1,ron,siralak,6213133,1600");
}*/


