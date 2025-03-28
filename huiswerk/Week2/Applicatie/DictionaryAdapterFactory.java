public class DictionaryAdapterFactory {
    public static IDictionaryAdapter getDictionaryAdapter(String dictionaryName) {
        if (dictionaryName.equalsIgnoreCase("koenen")) {
            return new KoenenAdapter();
        } else if (dictionaryName.equalsIgnoreCase("kramers")) {
            return new KramersAdapter();
        } else {
            throw new IllegalArgumentException("Onbekende dictionary: " + dictionaryName);
        }
    }
}
