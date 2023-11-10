import java.util.Random;

class Stazione {

    // Variabili che rappresentano lo stato della stazione ferroviaria
    private static boolean trenoInArrivo = false;
    private static boolean sbarraAbbassata = false;
    private static boolean semaforoRosso = false;

    // PROCESSO A
    // Il seguente metodo simula l'arrivo del treno e la gestione della sbarra e del semaforo.
    // Un thread blocca l'accesso ad altri thread, quando setta sbarraAbbassata e semaforoRosso a true.
    // Il thread attende finché trenoInArrivo è true, poi alza la sbarra e il semaforo.
    public static void gestisciTreno() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(5000) + 1000); // Simula il passaggio casuale del treno
                trenoInArrivo = true;
                System.out.println("Il treno è in arrivo!");
                synchronized (Stazione.class) {
                    sbarraAbbassata = true;
                    semaforoRosso = true;
                    System.out.println("La sbarra è abbassata, il semaforo è rosso.");
                }
                while (trenoInArrivo) {
                    Thread.sleep(1000); // Tempo di attesa fino al passaggio del treno
                }
                synchronized (Stazione.class) {
                    sbarraAbbassata = false;
                    semaforoRosso = false;
                    System.out.println("Il treno è passato, la sbarra è alzata, il semaforo è verde.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // PROCESSO B
    // Il seguente metodo gestisce l'abbassamento e l'alzamento della sbarra quando è richiesto.
    // Un thread entra nella sezione critica quando sbarraAbbassata è true e attende finché la sbarra non è abbassata.
    // Dopodiché, simula il tempo di attraversamento del treno, imposta trenoInArrivo a false e simula il tempo di rialzo della sbarra.
    public static void gestisciSbarra() {
        while (true) {
            try {
                while (!sbarraAbbassata) {
                    Thread.sleep(1000); // Simula il tempo di abbassamento della sbarra
                }
                Thread.sleep(2000); // Simula il tempo di attraversamento del treno
                synchronized (Stazione.class) {
                    trenoInArrivo = false;
                }
                Thread.sleep(1000); // Simula il tempo di rialzo della sbarra
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
