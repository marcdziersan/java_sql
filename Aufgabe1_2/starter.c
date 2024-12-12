#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_INPUT_LENGTH 256

int main(int argc, char *argv[]) {
    char userInput[MAX_INPUT_LENGTH] = "";
    char command[512];

    if (argc > 1) {
        for (int i = 1; i < argc; i++) {
            strcat(userInput, argv[i]);
            if (i < argc - 1) {
                strcat(userInput, " ");
            }
        }
    } else {
        printf("Bitte geben Sie den zusätzlichen Befehl ein: ");
        if (fgets(userInput, sizeof(userInput), stdin) == NULL) {
            fprintf(stderr, "Fehler beim Lesen der Eingabe.\n");
            return 1;
        }
        userInput[strcspn(userInput, "\n")] = 0;
    }
    snprintf(command, sizeof(command), "java -cp .;sqlite-jdbc-3.47.1.0.jar %s", userInput);
    int result = system(command);
    if (result == -1) {
        fprintf(stderr, "Fehler beim Ausführen des Befehls.\n");
        return 1;
    }
    return 0;
}