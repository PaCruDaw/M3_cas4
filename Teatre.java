import java.util.LinkedList;
import java.time.*;
public class Teatre {
    //Array dinamic
    static LinkedList<Sessio> sessions = new LinkedList<Sessio>();
    
    public static void menu() {
        System.out.print("Opcions de menu:\n" +
            "\ta) Llistat de sessions programades\n" +
            "\tb) Programar nova sessio\n" +
            "\tc) Venta d'entrades\n" +
            "\td) Guardar dades\n" +
            "\ts) Sortir del programa\n"+
            "Introdueix una opció de menú:");
    }

    public static void crearSession () {
        
        try {
            System.out.print("Introdueix el nom de l'obra:");
            String nom = System.console().readLine();
            System.out.print("Introdueix l'autor de l'obra:");
            String autor = System.console().readLine();
            System.out.print("Introdueix la durada de l'obra:");
            short durada = Short.parseShort(System.console().readLine());
            System.out.print("Introdueix el peggi de l'obra:");
            byte peggi = Byte.parseByte(System.console().readLine());
            Obra obra = new Obra(nom,autor,durada,peggi);

            System.out.print("Introdueix el dia:");
            byte dia = Byte.parseByte(System.console().readLine());
            System.out.print("Introdueix el mes:");
            byte mes = Byte.parseByte(System.console().readLine());
            System.out.print("Introdueix l'any:");
            short any = Short.parseShort(System.console().readLine());
            System.out.print("Introdueix l'hora (exemple 18:00):");
            String hora = System.console().readLine();
            String[] hour = hora.split(":");
            byte hSessio = Byte.parseByte(hour[0]);
            byte minutos = Byte.parseByte(hour[1]);
            LocalDateTime fecha = LocalDateTime.of(any,mes,dia,hSessio,minutos);

            Sessio sesi = new Sessio(obra,fecha);
            sessions.add(sesi);
        } catch (Exception e ) {
            System.out.println("S'ha produit un error en la insercio de dades.");
            e.printStackTrace();
        }        
    }

    public static void llistarSessions () {     
            for (int i =0; i < sessions.size(); i++) {
                System.out.print("\tCodi Sessio: "+ i + " Obra i horari: ");
                System.out.print(sessions.get(i).getObra().getTitul()+", "+ sessions.get(i).getObra().getAutor()+", "
                                + sessions.get(i).getDataString()+"\n");
                
            }
    }

    public static void ventaEntrades () {
        System.out.println("Sessions programades:");
        if (sessions.size() > 0) {
            llistarSessions();
            System.out.print("Trie la sessió per a la venta:");
            String op2 = System.console().readLine();
            try {
                int i = Integer.parseInt(op2);
                menuVentaEntrades(i);
            } catch (Exception e) {
                System.out.print("La session seleccionada no es valida.");
            } 
        } else {
            System.out.println("Actualment no i ha cap sessió programada.");
        }  
    }

    /**
     * 
     * @param i Is the number of session
     */
    public static void menuVentaEntrades(int i) {
        String op;
        String menuTest = "Que vol fer:\n" +
                            "\ta) Veure les localitats disponibles per a la sessio\n" +
                            "\tb) Reservar un seient\n" +
                            "\tc) Cancel.lar reserva\n" +
                            "\ts) Sortir del menú de venta\n" +
                            "Trie una opció:";
        do {
            System.out.print(menuTest);
            op = System.console().readLine();
            switch (op) {
                case "a":
                    System.out.println("Ocupació dels seients per a la sessio escollida.");
                    sessions.get(i).mapaAuditori();
                    break;
                case "b":
                    System.out.println("Introdueix les dades del espectador per a reservar el seient");
                    sessions.get(i).reservarSeient();
                    break;
                case "c":
                    System.out.print("Indique la zona");
                    sessions.get(i).cancelarReserva();
                    break;
                case "s":
                    op="s";
                    break;
                default:
                    System.out.println("Introdueixca una opció valida");
            }
        } while (op !="s"); 
    }

    public static void guardarDades () {

    }

    public static void main (String[] args) {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        String op;
        do {
            menu();
            op = System.console().readLine();
            switch (op) {
                case "a":
                    llistarSessions();
                    break;
                case "b":
                    crearSession();
                    break;
                case "c":
                    ventaEntrades();
                    break;
                case "d":
                    guardarDades();
                    break;
                case "s":
                    op="s";
                    break;
                default:
                    System.out.println("Introdueixca una opció valida");
            }
        } while (op !="s"); 
    }
}
