package han.oefeningAdapter.adapter;

import han.oefeningAdapter.domain.KramersDictionary;
import org.springframework.stereotype.Component;

@Component
public class KramersAdapter implements IDictionaryAdapter {
    private KramersDictionary kramers;

    public KramersAdapter() {
        kramers = new KramersDictionary();
    }

    @Override
    public String translate(String word) {
        return kramers.find(word);
    }

    @Override
    public String getName() {
        return "Kramers";
    }
}
