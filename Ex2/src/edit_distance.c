#include "edit_distance.h"

int memo[MAX_WORD_SIZE][MAX_WORD_SIZE];

/**
 * min - Returns the minimum of three integers.
 * @x: The first integer.
 * @y: The second integer.
 * @z: The third integer.
 *
 * This function compares three integers and returns the smallest one.
 */
int min(int x, int y, int z) {
    return x < y ? (x < z ? x : z) : (y < z ? y : z);
}

/**
 * edit_distance - Computes the edit distance between two strings.
 * @s1: The first string.
 * @s2: The second string.
 *
 * This function calculates the minimum number of operations required to
 * transform one string into another using recursion. The allowed operations
 * are insertion, deletion, and substitution.
 *
 * Returns the edit distance between the two strings.
 */

int edit_distance(const char *s1, const char *s2) {
    if (strlen(s1) == 0)
        return strlen(s2);
    else if (strlen(s2) == 0)
        return strlen(s1);
    else {
        int d_noop = s1[0] == s2[0] ? edit_distance(s1 + 1, s2 + 1) : INT_MAX;
        int d_canc = 1 + edit_distance(s1, s2 + 1);
        int d_ins = 1 + edit_distance(s1 + 1, s2);
        return min(d_noop, d_canc, d_ins);
    }
}

/**
 * edit_distance_dyn_recursive - Computes the edit distance using dynamic programming.
 * @s1: The first string.
 * @s2: The second string.
 * @len1: The length of the first string.
 * @len2: The length of the second string.
 *
 * This function calculates the minimum number of operations required to
 * transform one string into another using recursion and memoization to
 * store intermediate results. The allowed operations are insertion, deletion,
 * and substitution.
 *
 * Returns the edit distance between the two strings.
 */
int edit_distance_dyn_recursive(const char *s1, const char *s2, int len1, int len2) {
    if (len1 == 0) return len2;
    if (len2 == 0) return len1;
    if (memo[len1][len2] != -1) return memo[len1][len2];
    
    int d_no_op = (s1[0] == s2[0]) ? edit_distance_dyn_recursive(s1 + 1, s2 + 1, len1 - 1, len2 - 1) : INT_MAX;
    int d_canc = 1 + edit_distance_dyn_recursive(s1, s2 + 1, len1, len2 - 1);
    int d_ins = 1 + edit_distance_dyn_recursive(s1 + 1, s2, len1 - 1, len2);
    
    memo[len1][len2] = min(d_no_op, d_canc, d_ins);
    return memo[len1][len2];
}

/**
 * edit_distance_dyn - Computes the edit distance between two strings using dynamic programming.
 * @s1: The first string.
 * @s2: The second string.
 *
 * This function calculates the minimum number of operations required to
 * transform one string into another using dynamic programming. The allowed
 * operations are insertion, deletion, and substitution.
 *
 * Returns the edit distance between the two strings.
 */
int edit_distance_dyn(const char *s1, const char* s2) {
    memset(memo, -1, sizeof(memo)); 
    int len1 = strlen(s1);
    int len2 = strlen(s2);
    return edit_distance_dyn_recursive(s1, s2, len1, len2);
}
