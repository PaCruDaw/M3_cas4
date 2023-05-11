import java.util.LinkedList;
import java.time.*;
public class Teatre {
    static final byte MAX_ANYS_RESER = 3; //Maxim d'anys per crear reserva
    static final int ANY_NAIXEMENT = 1900; //Any minim de naixement
    static final byte DES_ABONATS = 10; //
    static final byte DES_GALERIA = 10;
    static final byte BONI_PLATEA = 20;

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
            System.out.print("Introdueix la edad mínima per al espectador:");
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
                    int any = Short.parseShort(System.console().readLine());
                    LocalDateTime actual = LocalDateTime.now(); //Comprovarem que l'any sigui valid
                    int year = actual.getYear();
                    if ((any >= year) && (any <= (year + MAX_ANYS_RESER))) { 
                        System.out.print("Introdueix l'hora (exemple 18:00):");
                        String hora = System.console().readLine();
                        String[] hour = hora.split(":");
                        byte hSessio = Byte.parseByte(hour[0]);
                        byte minutos = Byte.parseByte(hour[1]);
                        //Comprovem que la data introduida es posterior a la actual
                        LocalDateTime fecha = LocalDateTime.of(any,mes,dia,hSessio,minutos);
                        LocalDateTime now = LocalDateTime.now();
                        if (fecha.isAfter(now)) {
                            System.out.print("Preu de la entrada base:");
                            float preu = Comprovacions.cambiarStringFloat(System.console().readLine());
                            Sessio sesi = new Sessio(obra, fecha, preu);
                            sessions.add(sesi);
                        } else {
                            throw new Exception("S'ha produit un error durant la insercio de la data.");
                        }

                    } else {
                        throw new Exception("S'ha produit un error durant la insercio del any.");
                    }                
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
                short any = Comprovacions.cambiarStringShort(System.console().readLine());
                fecha = LocalDate.of(any,mes,dia);   
                if (fecha.isAfter(LocalDate.of(ANY_NAIXEMENT,1,1))) {
                    System.out.print("Introdueix la cantitat de diners disponible:");
                    float diners = Comprovacions.cambiarStringFloat(System.console().readLine());
                    Espectador viewer = new Espectador(nom,fecha,diners);
                    abonats.add(viewer);
                } else {
                    System.out.println("T'has passat amb l'edat.");
                }                
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
                    ferVenta(nsessio);
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


    /**
     * 
     */
    public static void ventaEntrades () {
        System.out.println("Sessions programades:");
        if (sessions.size() > 0) {
            llistarSessions();
            System.out.print("Trie la sessió per a la venta:");
            String op2 = System.console().readLine();            
            try {
                int i = Integer.parseInt(op2);
                if (sessions.size() > i ) {
                    menuVentaEntrades(i);
                } else {
                    System.out.println("No ho ha cap sessio ham aquest nombre.");
                }            
            } catch (Exception e) {
                System.out.print("La session seleccionada no es valida.");
            } 
        } else {
            System.out.println("Actualment no i ha cap sessió programada.");
        }  
    }




    public static void guardarDades () {

    }

    /**
     * 
     * @return 
     */
    public static Espectador assignarAbonat (int nAbonat) {
        Espectador espectador = new Espectador(-1);
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


    public static void ferVenta (int nsessio) {
        Espectador espectador = new Espectador(-1);
        System.out.print("Reservar per abonat (y/n):");
        String op = System.console().readLine();
        if ((op.equals("y")) || (op.equals("Y"))) { 
            float preu = sessions.get(nsessio).getPreu();
            float preuBase = sessions.get(nsessio).getPreu();
            float preuButaca = (float)preuBase*((float)1 - (float)DES_ABONATS/(float)100);
            System.out.println("El preu de les butaques es: " + String.format("%.2f",preuButaca));
            float preuGaleria = (float)preuBase*((float)1 - ((float)DES_ABONATS/(float)100) - ((float)DES_GALERIA/(float)100));
            System.out.println("El preu de la galeria es: " + String.format("%.2f",preuGaleria));
            float preuPlatea = (float)preuBase*((float)1 - ((float)DES_ABONATS/(float)100) + ((float)BONI_PLATEA/(float)100));
            System.out.println("El preu de les platees  es: " + String.format("%.2f",preuPlatea));

            System.out.print("Introdueix el nombre d'abonat:");
            int nAbonat = Comprovacions.cambiarStringInteger(System.console().readLine());
            espectador.igualar(assignarAbonat(nAbonat)); //el espectador es un abonat 
            float diners = abonats.get(nAbonat).getDiners(); //guardem els diners que te el abonat
            int edatMinima = sessions.get(nsessio).getObra().getPeggi(); //el peggi de la obra
            LocalDate fn = abonats.get(nAbonat).getDataNaixement(); //data de naixement del abonat
            LocalDate fhoy = LocalDate.now();
            Period period = Period.between(fn, fhoy);
            int edatEsp = period.getYears();
            if (edatMinima < edatEsp) {  
                menuButaques();
                op = System.console().readLine();
                switch (op) {
                    case "0":
                        byte z = 0;
                        System.out.print("Indiqui la fila:");
                        byte y = sessions.get(nsessio).comprovarFilaButaques(System.console().readLine());
                        System.out.print("Indiqui el nombre del seient:");
                        byte x = sessions.get(nsessio).comprovarColButaques(System.console().readLine());
                        if (diners >= preuButaca) {
                            sessions.get(nsessio).reservaSeient(espectador, z, y, x); //reservem seient amb reservarSeient a classe sessio   
                        } else {
                            System.out.println("No te prou diners al abonament per la entrada");
                        } 
                        //sessions.get(nsessio).reservaSeient(espectador, z, y, x); //assignem espectador si no hi ha reserva previa
                        break;
                    case "1":
                        z = 1;
                        System.out.print("Indiqui la fila:");
                        y = sessions.get(nsessio).comprovarFilaGaleria(System.console().readLine());
                        System.out.print("Indiqui el numero del seient:");
                        x = sessions.get(nsessio).comprovarColGaleria(System.console().readLine());
                        if (diners >= preuGaleria) {
                            sessions.get(nsessio).reservaSeient(espectador, z, y, x); //reservem seient amb reservarSeient a classe sessio   
                        } else {
                            System.out.println("No te prou diners al abonament per la entrada");
                        } 
                        //sessions.get(nsessio).reservaSeient(espectador, z, y, x); //assignem espectador si no hi ha reserva previa
                        break;
                    case "2":
                        if (diners > preuPlatea) {
                            sessions.get(nsessio).seientPlatea(espectador);
                        } else {
                            System.out.println("No te prou diners al abonament per la entrada");
                        }
                        
                        break;
                    case "3":
                        break;
                    default:
                        System.out.println("Opció no disponible.");    
                }
            } else {
                System.out.println("No te la edat suficient per a accedir.");
            }     
        } else if  ((op.equals("n")) || (op.equals("N"))) {
            float preuBase = sessions.get(nsessio).getPreu();
            System.out.println("El preu de les butaques es: " + String.format("%.2f",preuBase));
            float preuGaleria = ((float)preuBase*((float)1 - ((float)DES_GALERIA/(float)100.0)));
            System.out.println("El preu de la galeria es: " + String.format("%.2f",preuGaleria));
            float preuPlatea = (float)(preuBase*((float)1 + (float)(BONI_PLATEA/(float)100)));
            System.out.println("El preu de les platees  es: " + String.format("%.2f",preuPlatea));
            if (sessions.get(nsessio).getObra().getPeggi() != 0) {
                 System.out.println("La edat minima per accedir aquesta obra es: " + sessions.get(nsessio).getObra().getPeggi());
            }    
            menuButaques();
            op = System.console().readLine();
            switch (op) {
                case "0":
                    byte z = 0;
                    System.out.print("Indiqui la fila:");
                    byte y = sessions.get(nsessio).comprovarFilaButaques(System.console().readLine());
                    System.out.print("Indiqui el nombre del seient:");
                    byte x = sessions.get(nsessio).comprovarColButaques(System.console().readLine());
                    x = sessions.get(nsessio).comprovarColGaleria(System.console().readLine());
                    espectador = new Espectador(preuBase); //creem un espectador amb el contructor de diners = preu de la entrada
                    sessions.get(nsessio).reservaSeient(espectador, z, y, x); //reservem seient amb reservarSeient a classe sessio   
                    break;
                case "1":
                    z = 1;
                    System.out.print("Indiqui la fila:");
                    y = sessions.get(nsessio).comprovarFilaGaleria(System.console().readLine());
                    System.out.print("Indiqui el numero del seient:");
                    x = sessions.get(nsessio).comprovarColGaleria(System.console().readLine());
                    espectador = new Espectador(preuGaleria); //creem un espectador amb el contructor de diners = preu de la entrada
                    sessions.get(nsessio).reservaSeient(espectador, z, y, x); //reservem seient amb reservarSeient a classe sessio   
                    break;
                case "2":
                        espectador = new Espectador(preuPlatea); //creem un espectador amb el contructor de diners = preu de la entrada
                        sessions.get(nsessio).seientPlatea(espectador);                      
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Opció no disponible.");    
            }
        } else {
            System.out.println("Opcio no valida.");
        }
    }


    public static void menuButaques () {
        System.out.print("Ubicació disponibles\n" +
                            "\t0 Butaques\n" +
                            "\t1 Galeria\n" +
                            "\t2 Platees\n"+
                            "\t3 Sortir d'aquest menú\n" +
                            "Seleccione una de les ubicacions:");   
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
