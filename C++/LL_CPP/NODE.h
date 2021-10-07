using namespace std;
class NODE{
      string name;
      int hp,potion;
      NODE* next;
public:
        NODE(string = "no name",int=100,int=10);
        void show_node();
        void insert(NODE*&);
        NODE* move_next();
        ~NODE();
      };
