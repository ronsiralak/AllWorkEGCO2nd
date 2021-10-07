using namespace std;
class NODE {
    string id,name,last_name,job;
    int salary,job_id,tax,score;
    NODE* next;
    //sort score->salary->tax->job_id
public:
    NODE(string);
    NODE(string,string,string,string,int,int );
    void insert(NODE*&);
    NODE* move_next();
    void edit_data(string,string,string,int,int);
    void show_node();
    void import_data(string,string,string,int,int) ;
    int calculate_score();
    string get_ID();
    string get_job();
    void set_jobID(int);
    int get_score();
    void swap_NODE(NODE*&,NODE*&,NODE*&);
    string create_data();
    ~NODE();
};
