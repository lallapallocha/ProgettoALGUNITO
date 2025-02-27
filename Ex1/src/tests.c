#include "unity.h"
#include "sorting.h"
#include "utils.h"

Record record1, record2;
const Record *array[2];
void setUp(void) {
    record1.id = 1;
    record1.field1 = "apple";
    record1.field2 = 10;
    record1.field3 = 5.6;

    record2.id = 2;
    record2.field1 = "zebra";
    record2.field2 = 5;
    record2.field3 = 8.9;

    array[0] = &record1;
    array[1] = &record2;
}

void tearDown(void) {
    
   
}

// Test functions
void test_compare_str_lt(void) {
    TEST_ASSERT_TRUE(compare_str(&record1, &record2) < 0);
}

void test_compare_str_gt(void) {
    TEST_ASSERT_TRUE(compare_str(&record2, &record1) > 0);
}

void test_compare_str_eq(void) {
    TEST_ASSERT_TRUE(compare_str(&record1, &record1) == 0);
}
void test_compare_int_lt(void) {
    TEST_ASSERT_TRUE(compare_int(&record2, &record1) < 0);
}

void test_compare_int_gt(void) {
    TEST_ASSERT_TRUE(compare_int(&record1, &record2) > 0);
}

void test_compare_int_eq(void) {
    TEST_ASSERT_TRUE(compare_int(&record1, &record1) == 0);
}

void test_compare_float_lt(void) {
    TEST_ASSERT_TRUE(compare_float(&record1, &record2) < 0);
}

void test_compare_float_gt(void) {
    TEST_ASSERT_TRUE(compare_float(&record2, &record1) > 0);
}

void test_compare_float_eq(void) {
    TEST_ASSERT_TRUE(compare_float(&record1, &record1) == 0);
}

void test_quick_sort_strings(void) {
    const Record *expected[] = {&record1, &record2};

    quick_sort((void **)array, 2, compare_str);

    for (size_t i = 0; i < 2; ++i) {
        TEST_ASSERT_EQUAL_STRING(expected[i]->field1, array[i]->field1);
    }
}

void test_quick_sort_int(void) {
    const Record *expected[] = {&record2,&record1};

    quick_sort((void **)array, 2, compare_int);

    for (size_t i = 0; i < 2; ++i) {
        TEST_ASSERT_EQUAL_INT(expected[i]->field2, array[i]->field2);
    }
}

void test_quick_sort_floats(void) {
    const Record *expected[] = {&record1, &record2};

    quick_sort((void **)array, 2, compare_float);

    for (size_t i = 0; i < 2; ++i) {
        TEST_ASSERT_EQUAL_FLOAT(expected[i]->field3, array[i]->field3);
    }
}

void test_merge_sort_strings(void) {
    const Record *expected[] = {&record1, &record2};

    merge_sort((void **)array, 2, compare_str);

    for (size_t i = 0; i < 2; ++i) {
        TEST_ASSERT_EQUAL_STRING(expected[i]->field1, array[i]->field1);
    }
}
void test_merge_sort_int(void) {
    const Record *expected[] = {&record2,&record1};

    merge_sort((void **)array, 2, compare_int);

    for (size_t i = 0; i < 2; ++i) {
        TEST_ASSERT_EQUAL_INT(expected[i]->field3, array[i]->field3);
    }
}

void test_merge_sort_floats(void) {
    const Record *expected[] = {&record1, &record2};

    merge_sort((void **)array, 2, compare_float);

    for (size_t i = 0; i < 2; ++i) {
        TEST_ASSERT_EQUAL_FLOAT(expected[i]->field2, array[i]->field2);
    }
}


int main(void)
{
    UNITY_BEGIN();
    RUN_TEST(test_compare_str_lt);
    RUN_TEST(test_compare_str_gt);
    RUN_TEST(test_compare_str_eq);
    RUN_TEST(test_compare_int_lt);
    RUN_TEST(test_compare_int_gt);
    RUN_TEST(test_compare_int_eq);
    RUN_TEST(test_compare_float_lt);
    RUN_TEST(test_compare_float_gt);
    RUN_TEST(test_compare_float_eq);
    RUN_TEST(test_quick_sort_strings);
    RUN_TEST(test_quick_sort_int);
    RUN_TEST(test_quick_sort_floats);
    RUN_TEST(test_merge_sort_strings);
    RUN_TEST(test_merge_sort_int);
    RUN_TEST(test_merge_sort_floats);
    return UNITY_END();
}
