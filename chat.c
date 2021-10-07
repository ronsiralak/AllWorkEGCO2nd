#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include<signal.h>
#include <sys/wait.h>
#define FIFO_1 "./fifo1to2"
#define FIFO_2 "./fifo2to1"
#define MAX_BUF 80
int FIFO_FD1, FIFO_FD2;
int child;
static void sig_FIN(int);
int main(int argc,char *argv[]){
    int StrIn;
    char Buf[MAX_BUF]  = "";
    if(argc != 2){
        fprintf(stderr,"Usage :%s c[1,2]>\n",*argv);
        exit(EXIT_FAILURE);
    } 
    if(access(FIFO_1,F_OK) == -1){
        FIFO_FD1 = mkfifo(FIFO_1,0777);
        if(FIFO_FD1){
            fprintf(stderr,"Could not create fifo %s\n",FIFO_1);
        exit(EXIT_FAILURE);
        }
    }
    if(access(FIFO_2,F_OK) == -1){
        FIFO_FD2 = mkfifo(FIFO_2,0777);
        if(FIFO_FD2){
            fprintf(stderr,"Could not create fifo %s\n",FIFO_2);
        exit(EXIT_FAILURE);
        }
    }
    signal(SIGALRM,sig_FIN);
    if(strcmp(argv[1], "1") == 0){
        FIFO_FD1 = open(FIFO_1,O_WRONLY);
        FIFO_FD2 = open(FIFO_2,O_RDONLY);
      child = fork();
       switch (child)
        {
        case -1 : perror("Forking failed"); exit((EXIT_FAILURE));
            break;
        case 0 : while (strncmp(Buf,"end chat",8))
        {     
              StrIn = read(FIFO_FD2,Buf, MAX_BUF);
              write(1,Buf,StrIn);
        }
        break;
        default: while (strncmp(Buf,"end chat",8))
        {   
            StrIn = read(0,Buf, MAX_BUF);
            write(FIFO_FD1,Buf, StrIn); 
        }
            break;
    }
    }else if(strcmp(argv[1], "2") == 0){
        FIFO_FD1 = open(FIFO_1,O_RDONLY);
        FIFO_FD2 = open(FIFO_2,O_WRONLY);
              child = fork();
        switch (child)
        {
        case -1 : perror("Forking failed"); exit((EXIT_FAILURE));
            break;
        case 0 : while (strncmp(Buf,"end chat",8))
        {       
                StrIn = read(FIFO_FD1,Buf, MAX_BUF);
                write(1,Buf,StrIn);              
        }
        break;
        default: while (strncmp(Buf,"end chat",8))
        {
            StrIn = read(0,Buf, MAX_BUF);
            write(FIFO_FD2,Buf, StrIn);        
        }
            break;
    }
    }
    if(FIFO_FD1 != -1) close(FIFO_FD1);
    if(FIFO_FD2 != -1) close(FIFO_FD2);
    if(child){
      kill(child, SIGKILL);
      wait(NULL); 
      exit(EXIT_SUCCESS);
    }else if(child == 0){

    kill(getppid(), SIGALRM);
    }
}
static void sig_FIN(int sig){
  kill(child, SIGKILL);
  wait(NULL);
  exit(EXIT_SUCCESS);
}
