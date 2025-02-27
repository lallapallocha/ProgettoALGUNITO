#include "edit_distance.h"

int main(int argc, char **argv) {
    if(argc < 3){
        printf("Invalid number of arguments");
    }    
    char* dictionary_char = argv[1];
    char* correctme_char = argv[2];
    FILE *dictionary_file = fopen(dictionary_char, "r");
    FILE *correctme_file = fopen(correctme_char, "r");

    if (dictionary_file == NULL || correctme_file == NULL) {
        printf("Unable to open the files.\n");
        return 1;
    }

    char *dictionary[MAX_DICTIONARY_SIZE];
    int dictionary_size = 0;
    char buffer[MAX_WORD_SIZE];
    while (fgets(buffer, MAX_WORD_SIZE, dictionary_file) != NULL) {
        buffer[strcspn(buffer, "\n")] = '\0';
        dictionary[dictionary_size] = strdup(buffer);
        dictionary_size++;
    }
    fclose(dictionary_file);


    char line[MAX_WORD_SIZE];
    while (fgets(line, MAX_WORD_SIZE, correctme_file) != NULL) {
        char *token = strtok(line, " ,.;:!?\n");
        while (token != NULL) {
            printf("Word to correct: %s\n", token);
            int min_distance = INT_MAX;
            for (int i = 0; i < dictionary_size; i++) {
                int distance = edit_distance_dyn(token, dictionary[i]);
                if (distance < min_distance) {
                    min_distance = distance;
                }
            }
            printf("Minimum edit distance: %i\n",min_distance);
            printf("Dictionary words with minimum edit distance:\n");
            for (int i = 0; i < dictionary_size; i++) {
                int distance = edit_distance_dyn(token, dictionary[i]);
                if (distance == min_distance) {
                    printf("%s\n", dictionary[i]);
                }
            }
            printf("\n");

            token = strtok(NULL, " ,.;:!?\n");
        }
    }
    fclose(correctme_file);

    for (int i = 0; i < dictionary_size; i++) {
        free(dictionary[i]);
    }

    return 0;
}
