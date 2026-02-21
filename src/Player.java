public class Player {
    private int energy;
    private int food;
    private int morale;
    public Player() {
        this.energy = 60;
        this.food = 50;
        this.morale = 55;
    }
    public int getEnergy() {
        return energy;
    }

    public int getFood() {
        return food;
    }

    public int getMorale() {
        return morale;
    }

    public void changeEnergy(int amount) {
        energy += amount;
        if (energy < 0) energy = 0;
    }

    public void changeFood(int amount) {
        food += amount;
        if (food < 0) food = 0;
    }

    public void changeMorale(int amount) {
        morale += amount;
        if (morale < 0) morale = 0;
    }

    public boolean isAlive() {
        return energy > 0 && food > 0 && morale > 0;
    }
}

