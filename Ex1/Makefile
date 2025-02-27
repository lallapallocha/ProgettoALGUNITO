CC = gcc
CFLAGS = -g -Wvla -Wextra -Werror -Wall
DEPS = src/sorting.h src/utils.h src/unity.h
SRC_FILES = src/main.c src/utils.c src/sorting.c
TEST_FILES = src/tests.c src/unity.c src/utils.c src/sorting.c
OBJ_FILES = $(SRC_FILES:%.c=%.o)
TEST_OBJ = $(TEST_FILES:%.c=%.o)
UNITY_SRC = src/unity.c
TARGET = bin/main_ex1
TEST_TARGET = bin/test_ex1

all: $(TARGET) $(TEST_TARGET)

$(TARGET): $(OBJ_FILES)
	$(CC) $(CFLAGS) $^ -o $@

$(TEST_TARGET): $(TEST_OBJ)
	$(CC) $(CFLAGS) $^ -o $@

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

run: $(TARGET)
	./$(TARGET) src/records.csv src/result.csv  2 2

test: $(TEST_TARGET)
	./$(TEST_TARGET)

clean:
	rm -f $(OBJ_FILES) $(TARGET) $(TEST_OBJ) $(TEST_TARGET)
