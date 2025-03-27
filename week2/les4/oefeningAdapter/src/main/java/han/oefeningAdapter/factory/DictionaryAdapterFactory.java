package han.oefeningAdapter.factory;

import han.oefeningAdapter.adapter.IDictionaryAdapter;
import han.oefeningAdapter.adapter.KoenenAdapter;
import han.oefeningAdapter.adapter.KramersAdapter;

public class DictionaryAdapterFactory {
    public IDictionaryAdapter getDictionaryAdapter(String directoryname) {
        if (directoryname.equals("Kramers")) {
            return new KramersAdapter();
        } else if (directoryname.equals("Koenen")) {
            return new KoenenAdapter();
        } else {
            throw new IllegalArgumentException("Unknown dictionary adapter: " + directoryname);
        }
    }
}
