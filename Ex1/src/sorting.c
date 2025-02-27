#include <stdio.h>
#include <string.h>
#include "sorting.h"
#include "utils.h"

/**
 * quick_sort - Sorts an array using the quicksort algorithm.
 * @base: The array to be sorted.
 * @nitems: The number of elements in the array.
 * @compar: A pointer to the comparison function.
 *
 * This function sorts the array by recursively partitioning it into
 * smaller sub-arrays and sorting those sub-arrays.
 */
void quick_sort(void **base, size_t nitems, int (*compar)(const void *, const void *))
{
    
    if (nitems > 1)
    {
        size_t index = partition(base, 0, nitems - 1, compar);

        if (index > 1)
            quick_sort(base, index, compar);
        if (index < nitems - 1)
            quick_sort(base + index + 1, nitems - index - 1, compar);
    }
}

/**
 * partition - Partitions the array for quicksort.
 * @base: The array to be partitioned.
 * @low: The starting index of the partition.
 * @high: The ending index of the partition.
 * @compar: A pointer to the comparison function.
 *
 * This function selects a pivot element and partitions the array
 * such that elements less than the pivot are on the left, and elements
 * greater than the pivot are on the right.
 *
 * Returns the index of the pivot element after partitioning.
 */
size_t partition(void **base, size_t low, size_t high, int (*compar)(const void *, const void *))
{

    void **pivot_value = base;
    size_t i = low;
    size_t j = high;


    while (i < j)
    {
        while (i < j && compar(base[i], *pivot_value) <= 0)
            i++;

        while (compar(base[j], *pivot_value) > 0)
            j--;

        if (i < j)
        {
            swap(&base[i], &base[j]);
        }
    }

    swap(&base[low], &base[j]);

    return j;
}

/**
 * merge_sort - Sorts an array using the merge sort algorithm.
 * @base: The array to be sorted.
 * @nitems: The number of elements in the array.
 * @compar: A pointer to the comparison function.
 *
 * This function sorts the array by recursively dividing it into
 * smaller sub-arrays, sorting those sub-arrays, and then merging
 * them back together.
 */
void merge_sort(void **base, size_t nitems, int (*compar)(const void*, const void*)) {
    if (nitems > 1) {
        size_t mid = nitems / 2;
        
        merge_sort(base, mid, compar);
        merge_sort(base + mid, nitems - mid, compar);
        merge(base, mid, nitems - mid, compar);
    }
}

/**
 * merge - Merges two sorted sub-arrays into a single sorted array.
 * @base: The array containing the sub-arrays to be merged.
 * @low: The starting index of the first sub-array.
 * @high: The ending index of the second sub-array.
 * @compar: A pointer to the comparison function.
 *
 * This function takes two sorted sub-arrays and merges them into a single
 * sorted array. The sub-arrays are defined by the indices `low` and `high`.
 */
void merge(void **base, size_t low, size_t high, int (*compar)(const void*, const void*)) {
    size_t i, j, k;
    size_t left = low;
    size_t right = high;
    
    
    void **left_array = malloc(sizeof(void*) * left);
    void **right_array = malloc(sizeof(void*) * right);
    
    
    for (i = 0; i < left; i++) {
        left_array[i] = base[i];
    }
    for (j = 0; j < right; j++) {
        right_array[j] = base[low + j];
    }
    
    i = 0;
    j = 0;
    k = 0;
    while (i < left && j < right) {

        if (compar(left_array[i], right_array[j]) <= 0) {
            base[k++] = left_array[i++];
        }   else {
            base[k++] = right_array[j++];
        }
    }
    
    while (i < left) {
        base[k++] = left_array[i++];
    }
    
    while (j < right) {
        base[k++] = right_array[j++];
    }
    
    free(left_array);
    free(right_array);
}
