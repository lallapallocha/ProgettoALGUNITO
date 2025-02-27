#include "utils.h"
#include "sorting.h"


void swap(void **x, void **y)
{
    void *tmp = *x;
    *x = *y;
    *y = tmp;
}

int compare_int(const void *a, const void *b)
{
    Record r1= *(Record *)a;
    Record r2= *(Record *)b;

    if (r1.field2 < r2.field2)
        return -1;
    else if (r1.field2 > r2.field2)
        return 1;
    else
        return 0;
}

int compare_str(const void *a, const void *b)
{
    Record r1= *(Record *)a;
    Record r2= *(Record *)b;

    return strcmp(r1.field1, r2.field1);
}

int compare_float(const void *a, const void *b)
{
    Record r1= *(Record *)a;
    Record r2= *(Record *)b;

    if (r1.field3 < r2.field3)
        return -1;
    else if (r1.field3 > r2.field3)
        return 1;
    else
        return 0;
}

void csv_to_array(FILE *input, Record **Array) {
    if (input != NULL) {
        char *line = NULL;
        char *token;

        size_t pfile = 0;
        int i = 0;

        while ((getline(&line, &pfile, input)) != -1 && i < NITEMS) {
            Array[i] = malloc(sizeof(Record));
            if (Array[i] == NULL) {
                fprintf(stderr, "Memory allocation failed\n");
                exit(EXIT_FAILURE);
            }

            token = strtok(line, ",");
            Array[i]->id = atoi(token);

            token = strtok(NULL, ",");
            Array[i]->field1 = strdup(token); 
            if (Array[i]->field1 == NULL) {
                fprintf(stderr, "Memory allocation failed\n");
                exit(EXIT_FAILURE);
            }

            token = strtok(NULL, ",");
            Array[i]->field2 = atoi(token);

            token = strtok(NULL, ",");
            Array[i]->field3 = atof(token);

            i++;
        }
        free(line);
    }
}

void sort_records(FILE *infile, FILE *outfile, size_t field, size_t algo) {
    Record **Array = malloc(sizeof(Record *) * NITEMS);
    if (Array == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }

    csv_to_array(infile, Array);
    clock_t start = clock();
    switch (field)
    {
    case 1:
        if(algo == 1){
        quick_sort((void **)Array,NITEMS,compare_str);
        }
        else{
        merge_sort((void **)Array,NITEMS,compare_str);
        }
        break;
    case 2:
        if(algo == 1){
        quick_sort((void **)Array,NITEMS,compare_int);}
        else{
        merge_sort((void **)Array,NITEMS,compare_int);   
        }
        break;
    case 3:
        if(algo == 1){
        quick_sort((void **)Array,NITEMS,compare_float);}
        else{
        merge_sort((void **)Array,NITEMS,compare_float);   
        }
        break;
    default:
        break;
    }
    
   clock_t end = clock();
   double executionTime = (double)(end - start) / CLOCKS_PER_SEC;
   printf("Execution time: %f seconds", executionTime);
   for(int i = 0; i < NITEMS; i++){
        fprintf(outfile, "%d,%s,%d,%f\n", Array[i]->id, Array[i]->field1, Array[i]->field2, Array[i]->field3);
    }
    for (int i = 0; i < NITEMS && Array[i] != NULL; i++) {
        free(Array[i]->field1);
        free(Array[i]);
    }
    free(Array);
}

