import java.util.LinkedList;
import java.time.*;
public class Teatre {
    //Array dinamic
    static LinkedList<Sessio> sessions = new LinkedList<Sessio>();
    
    
    /*public static Espectador crearEspectador() {
        try {
            System.out.print("Introduzca el nombre:");
            String nom = System.console().readLine();
            System.out.print("Introduzca el dia de nacimiento:");
            byte dia = Byte.parseByte(System.console().readLine());
            System.out.print("Introduzca el dia de nacimiento:");
            byte mes = Byte.parseByte(System.console().readLine());
            System.out.print("Introduzca el dia de nacimiento:");
            short any = Short.parseShort(System.console().readLine());
            LocalDate fecha = LocalDate.of(any,mes,dia);
            System.out.print("Introduzca la cantidad de dinero que tiene:");
            float diners = Float.parseFloat(System.console().readLine());
            Espectador viewer = new Espectador(nom,fecha,diners);
            return viewer;
        } catch (Exception e) {
            System.out.println("S'ha produit un error en la insercio de dades.");
            e.printStackTrace();
        }   
        return viewer;

    } */
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
        llistarSessions();
    }

    public static void guardarDades () {

    }

    public static void main (String[] args) {
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
