package han.oefeningAdapter;

import han.oefeningAdapter.adapter.IDictionaryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TranslateToDutch {

	@Autowired
	private List<IDictionaryAdapter> dictionaries;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TranslateToDutch.class, args);
		TranslateToDutch app = context.getBean(TranslateToDutch.class);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("-----------------------------");
			System.out.print("Vertaal: ");
			String wordToTranslate = scanner.nextLine();
			app.translate(wordToTranslate);
		}
	}

	public void translate(String wordToTranslate) {
		String translation = null;
		String source = null;

		for (IDictionaryAdapter dictionary : dictionaries) {
			translation = dictionary.translate(wordToTranslate);
			if (translation != null && !translation.isEmpty()) {
				source = dictionary.getName();
				break;
			}
		}

		if (translation == null || translation.isEmpty()) {
			System.out.println(wordToTranslate + " niet gevonden");
		} else {
			System.out.println(wordToTranslate + " : " + translation);
			System.out.println("Dictionary: " + source);
		}
	}
}