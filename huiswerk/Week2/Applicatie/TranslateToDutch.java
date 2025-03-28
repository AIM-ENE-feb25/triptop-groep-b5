import java.util.Scanner;

public class TranslateToDutch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geef een Engels woord: ");
        String word = scanner.nextLine();

        IDictionaryAdapter adapter = DictionaryAdapterFactory.getDictionaryAdapter("koenen");
        String translation = adapter.translate(word);

        if (translation == null) {
            adapter = DictionaryAdapterFactory.getDictionaryAdapter("kramers");
            translation = adapter.translate(word);
        }

        if (translation == null) {
            System.out.println("Het woord '" + word + "' is niet gevonden.");
        } else {
            System.out.println("Vertaling: " + translation + " (bron: " + adapter.getName() + ")");
        }
    }
}
