class LL {
    NODE*head_LL;
    NODE*tail_LL;
    int size;
public:
    void add_node(NODE*&);
    NODE* Find_node(string);
    void show_all();
    void sort_data();
    void give_JobID();
    void give_score();
    void write();
    int selectID(string);
    int Find_ID(string);
    ~LL();
    LL();

};
