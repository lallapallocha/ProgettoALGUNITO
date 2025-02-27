#include <stdio.h>
#define NITEMS 20000000

void quick_sort(void **base, size_t nitems, int (*compar)(const void *, const void *));

size_t partition(void **base, size_t low, size_t high, int (*compar)(const void *, const void *));

void merge_sort(void **base, size_t nitems, int (*compar)(const void*, const void*));

void merge(void **base, size_t low, size_t high, int (*compar)(const void*, const void*));
