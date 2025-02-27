#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#define NITEMS 20000000

typedef struct record
{   int id;
    char *field1;
    int field2;
    float field3;
} Record;
void swap(void **x, void **y);

int compare_int(const void *a, const void *b);

int compare_str(const void *a, const void *b);

int compare_float(const void *a, const void *b);

void sort_records(FILE *infile, FILE *outfile, size_t field, size_t algo);

void csv_to_array(FILE *input, Record **Array);
