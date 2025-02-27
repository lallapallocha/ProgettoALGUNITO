#include <stdio.h>
#include <limits.h>
#include <string.h>
#include <stdlib.h>

#define MAX_WORD_SIZE 50
#define MAX_DICTIONARY_SIZE 800000

int min(int x, int y, int z);
int edit_distance(const char *s1, const char *s2);
int edit_distance_dyn_recursive(const char *s1, const char *s2, int len1, int len2);
int edit_distance_dyn(const char *s1, const char* s2);

