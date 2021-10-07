#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next; //¤¹¶Ñ´ä»
    Node () {
        next = NULL;
    }
    Node(int value) {
        data = value;
        next = NULL;
    }
};
class LinkedList {
public:
    Node *head;
    Node *tail;
    void addToTail(int value) {
        Node *newNode = new Node(value);

        if(head == NULL) {
            head = newNode;
            tail = newNode;

        } else {
            tail -> next = newNode;
            tail = newNode;
        }
    }

    void printAll() {
        Node *cur;
        cur = head;
        while(cur!=NULL) {
            cout << cur -> data << endl;
            cur = cur -> next;

        }
    }

    int findFromIndex(int index) {
        Node *cur;
        cur = head;
        int i=1;
        while(cur!=NULL) {
            if(i== index) {
                return cur ->data;

            }
            cur = cur -> next;
            i++;
        }
        return 0;
    }

    int findFromValue(int value) {
        Node *cur;
        cur = head;
        int index=1;
        while(cur!=NULL) {
            if(cur->data== value) {
                return index;

            }
            cur = cur -> next;
            index++;
        }
        return 0;
    }

    int countList() {
        int num =0;
        Node *cur = head;
        while(cur!=NULL) {
            cur = cur->next;
            num++;
        }
        return num;
    }

    void insertIndex(int index,int value) {
        Node *newNode = new Node(value);
        Node *cur = head;
        int listSize = countList();
        if(listSize+1>=index) {
            if(index==1) {
                if(head==NULL) {
                    addToTail(value);
                } else {
                    newNode->next = head;
                    head=newNode;
                }

            }

        } else if(listSize+1==index) {
            addToTail(value);
        } else {
            int i=2;
            while(cur!=NULL) {
                if(i==index) {
                    newNode->next = cur->next ;
                    cur->next = newNode;
                    break;
                }
                cur = cur->next;
                i++;

            }
        }
        if(listSize+1<index) {
            cout << "invalid, index size is "<< listSize<<endl;
        }

    }
};


int main() {
    LinkedList *myList = new LinkedList();
    myList -> addToTail(5);
    myList -> addToTail(6);
    myList -> addToTail(7);
    myList -> addToTail(8);



    //  cout << myList -> findFromIndex(3)<<endl;
    // cout << myList -> findFromValue(6)<<endl;

    myList->insertIndex(1,1);
    myList->printAll();
    myList->insertIndex(1,5555);
    myList->printAll();
    myList->insertIndex(3,9999);

    myList->printAll();
}
