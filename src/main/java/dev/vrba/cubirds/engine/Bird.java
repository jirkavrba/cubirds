package dev.vrba.cubirds.engine;

public enum Bird {
    PARROT(13, 4, 6, "Parrot"),
    REED_WARBLER(20, 6, 9, "Reed warbler"),
    ROBIN(20, 6, 9, "Robin"),
    DUCK(13, 4, 6, "Duck"),
    OWL(10, 3, 4, "Owl"),
    FLAMINGO(7, 2, 3, "Flamingo"),
    MAGPIE(17, 5, 7, "Magpie"),
    TOUCAN(10, 3, 4, "Toucan");

    public final int count;

    public final int smallFlock;

    public final int largeFlock;

    public final String name;

    Bird(int count, int smallFlock, int largeFlock, String name) {
        this.count = count;
        this.smallFlock = smallFlock;
        this.largeFlock = largeFlock;
        this.name = name;
    }
}
