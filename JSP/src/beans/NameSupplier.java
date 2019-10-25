package beans;

import java.util.Random;

public class NameSupplier {
    private static String[] names = {"Boris", "Obris", "Sorbi", "Robis", "Isorb", "Sirob"};

    public String next() {
        return names[new Random().nextInt(names.length)];
    }
}
