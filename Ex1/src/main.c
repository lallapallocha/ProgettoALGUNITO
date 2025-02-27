#include "utils.h"

int main(int argc, char *argv[]) {
    if(argc<4){
        printf("Error: Invalid number of arguments");
        return 1;
    }
    
    char *records = argv[1];
    char *result = argv[2];
    FILE *input = fopen(records, "r");
    FILE *output = fopen(result, "w");
    if (input == NULL || output == NULL) {
        printf("Could not open files\n");
        return 1;
    }

    
    sort_records(input, output, atoi(argv[3]), atoi(argv[4]));
    
    fclose(input);  
    fclose(output);
    return 0;
}
