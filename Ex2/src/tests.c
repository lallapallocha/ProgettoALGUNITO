#include "unity.h"
#include "edit_distance.h"

void setUp(void) {
    
}

void tearDown(void) {
    
}

void test_min(void) {
    TEST_ASSERT_EQUAL(1, min(1, 2, 7));
    TEST_ASSERT_EQUAL(2, min(3, 2, 8));
    TEST_ASSERT_EQUAL(5, min(5, 9, 7));
}

void test_edit_distance(void) {
    TEST_ASSERT_EQUAL(0, edit_distance("test", "test"));
    TEST_ASSERT_EQUAL(2, edit_distance("test", "tent"));
    TEST_ASSERT_EQUAL(5, edit_distance("kitten", "sitting"));
}

void test_edit_distance_dyn(void) {
    TEST_ASSERT_EQUAL(0, edit_distance_dyn("test", "test"));
    TEST_ASSERT_EQUAL(2, edit_distance_dyn("test", "tent"));
    TEST_ASSERT_EQUAL(5, edit_distance_dyn("kitten", "sitting"));
}

void test_edit_distance_edge_cases(void) {
    TEST_ASSERT_EQUAL(4, edit_distance("", "test"));
    TEST_ASSERT_EQUAL(4, edit_distance("test", ""));
    TEST_ASSERT_EQUAL(0, edit_distance("", ""));
    TEST_ASSERT_EQUAL(2, edit_distance("a", "b"));
    TEST_ASSERT_EQUAL(0, edit_distance("a", "a"));
    TEST_ASSERT_EQUAL(3, edit_distance("abc", "abcdef"));
    TEST_ASSERT_EQUAL(3, edit_distance("abcdef", "abc"));
    TEST_ASSERT_EQUAL(2, edit_distance("Test", "test"));
    TEST_ASSERT_EQUAL(1, edit_distance("aaaa", "aaa"));
    TEST_ASSERT_EQUAL(2, edit_distance("aaaa", "aaab"));
    TEST_ASSERT_EQUAL(11, edit_distance("longstringtest", "shorttest"));
    TEST_ASSERT_EQUAL(1, edit_distance("hello!", "hello"));
}

void test_edit_distance_dyn_edge_cases(void) {
    TEST_ASSERT_EQUAL(4, edit_distance_dyn("", "test"));
    TEST_ASSERT_EQUAL(4, edit_distance_dyn("test", ""));
    TEST_ASSERT_EQUAL(0, edit_distance_dyn("", ""));
    TEST_ASSERT_EQUAL(2, edit_distance_dyn("a", "b"));
    TEST_ASSERT_EQUAL(0, edit_distance_dyn("a", "a"));
    TEST_ASSERT_EQUAL(3, edit_distance_dyn("abc", "abcdef"));
    TEST_ASSERT_EQUAL(3, edit_distance_dyn("abcdef", "abc"));
    TEST_ASSERT_EQUAL(2, edit_distance_dyn("Test", "test"));
    TEST_ASSERT_EQUAL(1, edit_distance_dyn("aaaa", "aaa"));
    TEST_ASSERT_EQUAL(2, edit_distance_dyn("aaaa", "aaab"));
    TEST_ASSERT_EQUAL(11, edit_distance_dyn("longstringtest", "shorttest"));
    TEST_ASSERT_EQUAL(1, edit_distance_dyn("hello!", "hello"));
}

int main(void) {
    UNITY_BEGIN();
    
    RUN_TEST(test_min);
    RUN_TEST(test_edit_distance);
    RUN_TEST(test_edit_distance_dyn);
    
    RUN_TEST(test_edit_distance_edge_cases);
    RUN_TEST(test_edit_distance_dyn_edge_cases);
    
    return UNITY_END();
}
