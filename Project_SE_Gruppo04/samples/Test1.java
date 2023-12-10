import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Test {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Errore: Fornire il nome del file e due stringhe da scrivere");
            System.exit(1);
        }

        String filePath = args[0];

        try (FileWriter writer = new FileWriter(filePath)) {
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Scrivi le due stringhe nel file
            for (int i = 1; i <= 2; i++) {
                bufferedWriter.write(args[i]);
                bufferedWriter.newLine();
                
                System.out.println(args[i]);
            }

            bufferedWriter.close();
            System.out.println("Scrittura completata con successo.");
            System.exit(0);

        } catch (IOException e) {
            System.err.println("Errore durante la scrittura del file: " + e.getMessage());
            System.exit(1);
        }
    }
}


