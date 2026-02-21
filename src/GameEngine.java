import java.util.Random;
import java.util.Scanner;

public class GameEngine {

    private Random random = new Random();  
    public void startGame() throws Exception {

        System.out.println("===== STRANDED =====");
        System.out.println("The storm hit without warning.");
        Thread.sleep(1200);

        System.out.println("You wake up on a silent shore.");
        Thread.sleep(1500);

        System.out.println("The wreckage of your boat scattered across the sand.");
        Thread.sleep(2000);

        System.out.println("Your emergency radio is damaged... but salvageable.");
        Thread.sleep(1800);

        System.out.println("It needs 7 days of steady sunlight to charge.");
        Thread.sleep(2000);

        System.out.println("Survive until then.");
        Thread.sleep(2200);

        System.out.println("If any of your vital reserves fail... rescue won't matter.");
        Thread.sleep(3000);

        System.out.println();
        System.out.println("Press Enter to Start...");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // Game loop
        Player player = new Player();

        System.out.println("\n===== CURRENT STATUS =====");
        printStats(player);
        Thread.sleep(800);

        int day = 1;
        boolean alive = true;

        while (day <= 7 && alive) {

            System.out.println("\n===== Day " + day + " =====");
            Thread.sleep(800);

            // Daily decay
            player.changeEnergy(-5);
            player.changeFood(-7);
            player.changeMorale(-4);

            if (player.getFood() < 15) {
                player.changeEnergy(-3);
            }

            printStats(player);

            if (!player.isAlive()) {
                alive = false;
                break;
            }
            Thread.sleep(800);
            System.out.println("\nChoose your action:");

            Thread.sleep(400);
            System.out.println("1. Hunt");

            Thread.sleep(350);
            System.out.println("2. Rest");

            Thread.sleep(350);
            System.out.println("3. Explore");
            System.out.println();
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException nfe) {
                choice = -1;
            }

            if (choice == 1) {

                System.out.println("You go hunting...");
                Thread.sleep(1500);

                int foodGain;

                if (player.getEnergy() < 20) {
                    foodGain = 8;     // weak hunt
                } else {
                    foodGain = 15;    // strong hunt
                }

                player.changeFood(foodGain);
                player.changeEnergy(-10);

                int injuryChance;

                if (player.getEnergy() < 20) {
                    injuryChance = 30;
                } else {
                    injuryChance = 20;
                }

                int randomNumber = random.nextInt(100);

                if (randomNumber < injuryChance) {
                    System.out.println("You got injured during the hunt!");
                    Thread.sleep(1500);
                    player.changeEnergy(-10);
                }

            } else if (choice == 2) {

                System.out.println("You rest for the day...");
                Thread.sleep(1500);

                player.changeEnergy(15);
                player.changeFood(-5);
                player.changeMorale(-3);

            } else if (choice == 3) {

                System.out.println("You explore the island...");
                Thread.sleep(1500);

                int event = random.nextInt(4);

                if (event == 0) {
                    System.out.println("You found wild berries!");
                    player.changeFood(10);
                }
                else if (event == 1) {
                    System.out.println("You discover a beautiful cliff view.");
                    player.changeMorale(10);
                }
                else if (event == 2) {
                    System.out.println("You slipped on rough terrain.");
                    player.changeEnergy(-12);
                }
                else {
                    System.out.println("Nothing significant happens.");
                }

                Thread.sleep(1500);

            } else {

                System.out.println("Invalid choice. You hesitate and waste time.");
            }

            // Check vitals after action
            if (!player.isAlive()) {
                alive = false;
                break;
            }

            // Advance to next day
            day++;

        }

        // End of game with dramatic pauses
        System.out.println();
        Thread.sleep(1200);
        if (alive) {
            Thread.sleep(800);
            System.out.println("Seven days pass. The radio is charged. Rescue beacon sent!");
            Thread.sleep(1200);
            System.out.println("You survived. Congratulations.");
        } else {
            Thread.sleep(800);
            System.out.println("Your vitals failed. You did not survive.");
        }

        // Final status
        Thread.sleep(1000);
        System.out.println("\n===== FINAL STATUS =====");
        Thread.sleep(700);
        printStats(player);
        scanner.close();

    }

    private void printStats(Player player) {
        System.out.println("Energy : " + player.getEnergy());
        System.out.println("Food   : " + player.getFood());
        System.out.println("Morale : " + player.getMorale());
    }

}