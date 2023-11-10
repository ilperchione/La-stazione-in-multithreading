import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Thread processoA = new Thread(Stazione::gestisciTreno);
        Thread processoB = new Thread(Stazione::gestisciSbarra);

        processoA.start();
        processoB.start();

        // Premere il tasto Invio per fermare i processi
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // Interruzione dei processi
        processoA.interrupt();
        processoB.interrupt();

        // Chiusura del programma
        System.out.println("Programma chiuso.");
        System.exit(0);
    }
}
