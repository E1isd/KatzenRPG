package main.java.com.katzenrpg;

public class Player {
    private int level = 1;
    private int xp = 0;
    private int xpToLevel = 50;

    private int atc = 100;
    private int def = 100;
    private int mag = 100;

    public Player() {
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getXpToLevel() {
        return xpToLevel;
    }

    public int getAtc() {
        return atc;
    }

    public int getDef() {
        return def;
    }

    public int getMag() {
        return mag;
    }

    public void addXp(int amount) {
        if (amount <= 0) {
            return;
        }
        xp += amount;
        while (xp >= xpToLevel) {
            xp -= xpToLevel;
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        atc = (int) Math.round(atc * 1.1);
        def = (int) Math.round(def * 1.1);
        mag = (int) Math.round(mag * 1.1);
        xpToLevel = (int) Math.round(xpToLevel * 1.15);
    }
}
