class NODE{
      int data;
   /* int hp,potion;
    std::string name;*/
      NODE* next;
public:
        NODE(int);
      virtual void show_node() = 0 ;
        void insert(NODE*&);
        NODE* move_next();
       virtual ~NODE();
      };
