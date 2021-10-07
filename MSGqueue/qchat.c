#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include  <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/msg.h>

void sig_kill(int sig){
  kill(child,SIGKILL);
  wait(NULL);
  exit(EXIT_SUCCESS);
}

int child;
struct a_msg
 {
  long int msg_type;
  char data[BUFSIZ];
 };

int main(int argc, char *argv[])
{
  int  msgID;
  struct a_msg a_msg; 
  char buffer[BUFSIZ];

  if(argc != 2)
  {
    fprintf(stderr, "Usage: %s <[1, 2]>\n", *argv);
    exit(EXIT_FAILURE);
  }

  msgID = msgget((key_t) 6213133, 0666 |IPC_CREAT);
    if (msgID == -1)
    {
      fprintf(stderr, "msgget failed\n");
      exit(EXIT_FAILURE); 
    }

  argv++;

  if(strcmp(*argv, "1") == 0)
  { 
    child = fork();
    switch(child)
    {
      case -1 : perror("Forking failed"); 
                exit(EXIT_FAILURE);

      case  0 : while(strncmp(a_msg.data, "end chat",8))
                {
                  a_msg.msg_type = 2;
                  strcpy(a_msg.data, buffer);
                  if (msgrcv(msgID, (void *) &a_msg,BUFSIZ,2, 0) == -1)
                  {
                    fprintf(stderr, "msgget failed\n"); 
                    exit(EXIT_FAILURE);
                  }
                  write(1,"User2 : ",8);
                  printf("%s",a_msg.data);
                  
                }
                break; 
      default : while(strncmp(buffer, "end chat",8))
                {
                  signal(SIGUSR1,sig_kill);
                  fgets(buffer, BUFSIZ, stdin);
                  a_msg.msg_type = 1;
                  if(buffer[0]!='\n'){
                    strcpy(a_msg.data, buffer); 
                    if (msgsnd(msgID, (void *) &a_msg,BUFSIZ, 0) == -1)
                    {
                      fprintf(stderr, "msgsnd failed\n");
                      exit(EXIT_FAILURE); 
                    }
                  }
                }
    }
  }


  else if(strcmp(*argv, "2") == 0)
  {
    child = fork();
    switch(child)
    {
      case -1 : perror("Forking failed"); 
                exit(EXIT_FAILURE);

      case  0 : while(strncmp(a_msg.data, "end chat",8))
                {
                  a_msg.msg_type = 1;
                  strcpy(a_msg.data, buffer);
                  if (msgrcv(msgID, (void *) &a_msg,BUFSIZ,1, 0) == -1)
                  {
                    fprintf(stderr, "msgget failed\n"); 
                    exit(EXIT_FAILURE);
                  }
                  write(1,"User1 : ",8);
                  printf("%s",a_msg.data);
                  
                }  
                break;            
      default : while(strncmp(buffer, "end chat",8))
                {
                  signal(SIGUSR1,sig_kill);
                  fgets(buffer, BUFSIZ, stdin);
                  a_msg.msg_type = 2;
                  if(buffer[0]!='\n'){
                    strcpy(a_msg.data, buffer); 
                    if (msgsnd(msgID, (void *) &a_msg,BUFSIZ, 0) == -1)
                    {
                     fprintf(stderr, "msgsnd failed\n");
                     exit(EXIT_FAILURE); 
                    }
                  }
                }
    }
  }

  if(child == 0){
    kill(getppid(),SIGUSR1);
  }else if(child > 0){
    kill(child,SIGKILL);
    wait(NULL);
  }
  exit(EXIT_SUCCESS);
}