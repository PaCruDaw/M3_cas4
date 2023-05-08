import java.util.LinkedList;
import java.time.*;
public class Teatre {
    //Array dinamic
    static LinkedList<Sessio> sessions = new LinkedList<Sessio>();
    static LinkedList<Espectador> abonats = new LinkedList<Espectador>();
    
    public static void menu() {
        System.out.print("Opcions de menu:\n" +
            "\ta) Llistat de sessions programades\n" +
            "\tb) Programar nova sessio\n" +
            "\tc) Introduir abonat\n" +
            "\td) Venta d'entrades\n" +
            "\te) Guardar dades\n" +
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
            dia = Comprovacions.acotarDia(dia);
            if (dia != -2) {
                System.out.print("Introdueix el mes:");
                byte mes = Byte.parseByte(System.console().readLine());
                mes = Comprovacions.acotarMes(mes);
                if (mes != -2) {
                    System.out.print("Introdueix l'any:");
                    short any = Short.parseShort(System.console().readLine());
                    System.out.print("Introdueix l'hora (exemple 18:00):");
                    String hora = System.console().readLine();
                    String[] hour = hora.split(":");
                    byte hSessio = Byte.parseByte(hour[0]);
                    byte minutos = Byte.parseByte(hour[1]);
                    LocalDateTime fecha = LocalDateTime.of(any,mes,dia,hSessio,minutos);
                    System.out.print("Preu de la entrada base:");
                    float preu = Comprovacions.cambiarStringFloat(System.console().readLine());
                    Sessio sesi = new Sessio(obra, fecha, preu);
                    sessions.add(sesi);
                } else {
                    System.out.println("El mes introduit no es valid.");
                }
            } else {
                System.out.println("El dia introduit no es valid.");
            }
        } catch (Exception e ) {
            System.out.println("S'ha produit un error en la insercio de dades.");
            e.printStackTrace();
        }        
    }

    public static void llistarSessions () {     
            for (int i =0; i < sessions.size(); i++) {
                System.out.print("\tCodi Sessio: "+ i + " Obra i horari: ");
                System.out.print(sessions.get(i).getObra().getTitul()+", "+ sessions.get(i).getObra().getAutor()+", "
                                + Comprovacions.dataHourString(sessions.get(i).getData())+ "\n");
                
            }
    }

    public static void crearAbonat () {
        System.out.print("Introdueix el nom del abonat:");
        String nom = System.console().readLine();

        LocalDate fecha;
        System.out.print("Introdueix el dia de naixement:");
        byte dia = Comprovacions.cambiarStringByte(System.console().readLine());
        dia = Comprovacions.acotarDia(dia);
        if (dia != -2) {
            System.out.print("Introdueix el mes de naixement:");    
            byte mes = Comprovacions.cambiarStringByte(System.console().readLine());
            mes =Comprovacions.acotarMes(mes);
            if (mes != -2) {
                System.out.print("Introdueix l'any de naixement:");
                byte any = Comprovacions.cambiarStringByte(System.console().readLine());
                fecha = LocalDate.of(any,mes,dia);        
                System.out.print("Introdueix la cantitat de diners disponible:");
                float diners = Comprovacions.cambiarStringFloat(System.console().readLine());
                Espectador viewer = new Espectador(nom,fecha,diners);
                abonats.add(viewer);
                System.out.println(abonats.get(0));
            } else {
                System.out.println("El mes introduit no es valid.");
            }            
        } else {
            System.out.println("El dia intoduit no es valid.");
        }
    }


    /**
     * 
     * @param i Is the number of session
     */
    public static void menuVentaEntrades(int nsessio) {
        String op;
        String MVENTA = "Que vol fer:\n" +
                        "\ta) Veure les localitats disponibles per a la sessio\n" +
                        "\tb) Reservar un seient\n" +
                        "\tc) Cancel.lar reserva\n" +
                        "\ts) Sortir del menú de venta\n" +
                        "Trie una opció:";
        do {
            System.out.print(MVENTA);
            op = System.console().readLine();
            switch (op) {
                case "a":
                    System.out.println("Ocupació dels seients per a la sessio escollida.");
                    sessions.get(nsessio).mapaAuditori();
                    break;
                case "b":
                    Espectador espectador = new Espectador(-1);
                    System.out.print("Reservar per abonat (y/n):");
                    op = System.console().readLine();
                    if ((op.equals("y")) || (op.equals("Y"))) {
                        espectador = assignarAbonat();
                    } else if  ((op.equals("n")) || (op.equals("N"))) {
                        espectador = new Espectador(sessions.get(nsessio).getPreu());
                    } else {
                        System.out.println("Opcio no valida.");
                    }
                    sessions.get(nsessio).reservarSeient(espectador);
                    break;
                case "c":
                    System.out.print("Indique la zona");
                    sessions.get(nsessio).cancelarReserva();
                    break;
                case "s":
                    op="s";
                    break;
                default:
                    System.out.println("Introdueixca una opció valida");
            }
        } while (op !="s"); 
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



    public static void guardarDades () {

    }

    public static Espectador assignarAbonat ( ) {
        Espectador espectador = new Espectador(-1);
        System.out.print("Introdueix el nombre d'abonat:");
        int nAbonat = Comprovacions.cambiarStringInteger(System.console().readLine());
        if ((nAbonat < abonats.size()) && (nAbonat >= 0)) {
            System.out.println(abonats.get(nAbonat).toString());
            System.out.print("Es correcte (y):");
            String op = System.console().readLine();
            if ((op.equals("y")) || (op.equals("Y"))) {
                espectador.igualar(abonats.get(nAbonat));
                return espectador;
            } else {
                return espectador = new Espectador(-1);
            }
        } else {
            return espectador = new Espectador(-1);
        }    
    }


    public static void main (String[] args) {
        System.out.print("\033[H\033[2J");  //Ens limpie la consola
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
                    crearAbonat();
                    break;
                case "d":
                    ventaEntrades();
                    break;
                case "e":
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
