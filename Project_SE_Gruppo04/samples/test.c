#include <stdio.h>

#define FILENAME "test.txt"

int main(int argc, char *argv[]) {
    // Verifica se sono stati forniti almeno 2 argomenti (il programma stesso e almeno una stringa)
    if (argc < 3) {
        printf("Errore");
        return 1;
    }

    // Ottieni il percorso del file dalla linea di comando
    const char *filepath = argv[1];

    // Apri il file in modalità sovrascrittura (crea se non esiste)
    FILE *file = fopen(filepath, "w");
    if (file == NULL) {
        perror("Errore nell'apertura del file");
        return 1;
    }

    // Scrivi le stringhe nel file
    for (int i = 2; i < argc; i++) {
        fprintf(file, "%s\n", argv[i]);
    }

    // Chiudi il file
    fclose(file);

    return 0;
}
