#include <pthread.h>
#include <sys/time.h>
#include <stdio.h>
struct data {
    int p[50000];
    int start;
    int count;
};
void *compute_prime(void *arg) {
    struct data *p = (struct data *)arg;
    int candidate = p->start, n = candidate + 50000;
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
int main () {
    pthread_t thread;
    int start = 0;
    struct timeval begin, end;

    struct data t;
    t.start =start;

    gettimeofday(&begin, 0);

    pthread_create (&thread, NULL, &compute_prime, &t);
    pthread_join (thread, NULL);
    gettimeofday(&end, 0);
    for (int i = 0; i < t.count; i++)
        printf("%d \n", t.p[i]);
    long seconds = end.tv_sec - begin.tv_sec;
    long microseconds = end.tv_usec - begin.tv_usec;
    double elapsed = seconds + microseconds * 1e-6;

    printf("\nTime: %.4f s\n", elapsed);
}
