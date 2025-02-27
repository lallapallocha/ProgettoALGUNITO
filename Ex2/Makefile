CC = gcc
CFLAGS = -g -Wvla -Wextra -Werror -Wall
DEPS = src/unity.h src/edit_distance.h
SRC_FILES = src/main.c src/edit_distance.c
TEST_FILES = src/tests.c src/unity.c
OBJ_FILES = $(SRC_FILES:%.c=%.o)
TEST_OBJ = $(TEST_FILES:%.c=%.o) src/edit_distance.o
TARGET = bin/main_ex2
TEST_TARGET = bin/test_ex2

all: $(TARGET) $(TEST_TARGET)

$(TARGET): $(OBJ_FILES)
	$(CC) $(CFLAGS) $^ -o $@

$(TEST_TARGET): $(TEST_OBJ)
	$(CC) $(CFLAGS) $^ -o $@

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

run: $(TARGET)
	./$(TARGET) src/dictionary.txt src/correctme.txt

test: $(TEST_TARGET)
	./$(TEST_TARGET)

clean:
	rm -f $(OBJ_FILES) $(TARGET) $(TEST_OBJ) $(TEST_TARGET)
