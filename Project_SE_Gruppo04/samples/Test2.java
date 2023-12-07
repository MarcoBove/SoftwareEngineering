import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;

public class Test2 {

    public static void main(String[] args) {
        // Controlla che siano stati forniti almeno due argomenti (il percorso del file e almeno una stringa da cercare)
        if (args.length < 2) {
            System.out.println("Usage: java -jar SearchStringInFile.jar <file_path> <search_string1> [search_string2 ...]");
            System.exit(1);
        }

        // Ottieni il percorso del file e le stringhe da cercare
        String filePath = args[0];
        String[] searchStrings = new String[args.length - 1];
        System.arraycopy(args, 1, searchStrings, 0, args.length - 1);

        try {
            // Leggi il contenuto del file
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Cerca le stringhe nel contenuto del file
            boolean found = false;
            for (String searchString : searchStrings) {
                if (fileContent.contains(searchString)) {
                    found = true;
                    break;
                }
            }

            // Restituisci 0 se almeno una delle stringhe è stata trovata, altrimenti 1
            System.exit(found ? 0 : 1);

        } catch (IOException e) {
            // Gestisci eventuali eccezioni durante la lettura del file
            e.printStackTrace();
            System.exit(1);
        }
    }
}
