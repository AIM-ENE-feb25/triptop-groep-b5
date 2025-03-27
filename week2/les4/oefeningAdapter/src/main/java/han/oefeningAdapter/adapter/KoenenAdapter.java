package han.oefeningAdapter.adapter;

import han.oefeningAdapter.domain.KoenenDictionary;
import org.springframework.stereotype.Component;

@Component
public class KoenenAdapter implements IDictionaryAdapter {
    private KoenenDictionary koenen;

    public KoenenAdapter() {
        koenen = new KoenenDictionary();
        koenen.openEnglishDutch();
    }

    @Override
    public String translate(String word) {
        return koenen.lookUp(word);
    }

    @Override
    public String getName() {
        return "Koenen";
    }
}
