#include <pthread.h>
#include <sys/time.h>
#include <stdio.h>
struct data {
    int p[10000];
    int start;
    int count;
};
void *compute_prime(void *arg) {
    struct data *p = (struct data *)arg;
    int candidate = p->start, n = candidate + 10000;
    p->count = 0;
    while (1) {
        int factor, is_prime = 1;
        for (factor = 2; factor < candidate; ++factor)
            if (candidate % factor == 0) {
                is_prime = 0;
                break;
            }

        if (is_prime && candidate >= 2) {
            p->p[p->count] = candidate;
            p->count++;
        }
        if (candidate == n)
            return (void *)p;
        ++candidate;
    }
    return NULL;
}
int main() {
    pthread_t pt[5];
    struct timeval begin, end;
    struct data t1,t2,t3,t4,t5;
    int start[] = {0,10000,20000,30000,40000};
    t1.start = start[0]; t2.start = start[1]; t3.start = start[2];
    t4.start = start[3]; t5.start = start[4];
    gettimeofday(&begin, 0);
    pthread_create (&pt[0], NULL, &compute_prime, &t1); pthread_create (&pt[1], NULL, &compute_prime, &t2);
    pthread_create (&pt[2], NULL, &compute_prime, &t3); pthread_create (&pt[3], NULL, &compute_prime, &t4);
    pthread_create (&pt[4], NULL, &compute_prime, &t5);
    for(int i = 0; i<5; i++) {
        pthread_join (pt[i],NULL);
    }
    gettimeofday(&end, 0);
    for (int i = 0; i < t1.count; i++) {
        printf("%d \n", t1.p[i]);
    }
    for (int i = 0; i < t2.count; i++) {
        printf("%d \n", t2.p[i]);
    }
    for (int i = 0; i < t3.count; i++) {
        printf("%d \n", t3.p[i]);
    }
    for (int i = 0; i < t4.count; i++) {
        printf("%d \n", t4.p[i]);
    }
    for (int i = 0; i < t5.count; i++) {
        printf("%d \n", t5.p[i]);
    }
    long seconds = end.tv_sec - begin.tv_sec;
    long microseconds = end.tv_usec - begin.tv_usec;
    double elapsed = seconds + microseconds * 1e-6;
    printf("\nTime: %.4f s\n", elapsed);
}
