public class KramersDictionary {
    private String[][] list = {
            {"aeroplane", "vliegtuig"}, {"bicycle", "fiets"}, {"bird", "vogel"}, {"boat", "boot"},
            {"car", "auto"}, {"cat", "kat"}, {"chicken", "kip"}, {"cow", "koe"}, {"dog", "hond"},
            {"donkey", "ezel"}, {"elephant", "olifant"}, {"fish", "vis"}, {"fly", "vlieg"}, {"fox", "vos"},
            {"goat", "geit"}, {"horse", "paard"}, {"lion", "leeuw"}, {"rabbit", "konijn"},
            {"monkey", "aap"}, {"mouse", "muis"}, {"pig", "varken"}, {"sheep", "schaap"},
            {"snake", "slang"}, {"submarine", "onderzeeboot"}, {"tiger", "tijger"}, {"train", "trein"}
    };

    public KramersDictionary() {}

    public String find(String english) {
        for (int i = 0; i < list.length; i++) {
            if (english.equals(list[i][0])) {
                return list[i][1];
            }
        }
        return null;
    }
}
