import java.util.LinkedList;
import java.time.*;
public class Teatre {
    static final byte MAX_ANYS_RESER = 3; //Maxim d'anys per crear reserva
    static final int ANY_NAIXEMENT = 1900; //Any minim de naixement
    static final byte DES_ABONATS = 10; //
    static final byte DES_GALERIA = 10;
    static final byte BONI_PLATEA = 20;

    //Array dinamic
    /**
     * Scheduled sessions 
     */
    static LinkedList<Sessio> sessions = new LinkedList<Sessio>();

    /**
     * Subscribers 
     */
    static LinkedList<Espectador> abonats = new LinkedList<Espectador>();

    /**
     * Main menu.
     */
    public static void menu() {
        System.out.print("Opcions de menu:\n" +
            "\ta) Llistat de sessions programades\n" +
            "\tb) Programar nova sessio\n" +
            "\tc) Introduir abonat\n" +
            "\td) Venta d'entrades\n" +
            "\te) Llistar abonats\n" +
            "\tf) Calcular recaptació\n" +
            "\ts) Sortir del programa\n"+
            "Introdueix una opció de menú:");
    }

    /**
     * Method that allows us to create a new session. 
     * Session not create only to date after now and only with three years before
     */
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
                    if ((any >= year) && (any <= (year + MAX_ANYS_RESER))) { //les sessions no poden ser programades amb mes de tres anys d'antelació
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
                            throw new Exception("La data introduida es anterior a l'actual.");
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

    /**
     * Method for listing available sessions. 
     */
    public static void llistarSessions () {     
            for (int i =0; i < sessions.size(); i++) {
                System.out.print("\tCodi Sessio: "+ i + " Obra i horari: ");
                System.out.print(sessions.get(i).getObra().getTitul()+", "+ sessions.get(i).getObra().getAutor()+", "
                                + Comprovacions.dataHourString(sessions.get(i).getData())+ "\n");
                
            }
    }

    /**
     * Method for creating a subscriber. 
     */
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
                LocalDate now = LocalDate.now();
                if (fecha.isAfter(LocalDate.of(ANY_NAIXEMENT,1,1)) && (fecha.isBefore(now))) {
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
     * Method to choose the sales session, as well as the correct insertion of data by the user. 
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
                    System.out.println("No hi ha cap sessio ham aquest nombre.");
                }            
            } catch (Exception e) {
                System.out.print("La session seleccionada no es valida.");
            } 
        } else {
            System.out.println("Actualment no i ha cap sessió programada.");
        }  
    }

    /**
     * It generates the menu for the sale of tickets of the last session as a parameter.
     * @param nsessio Is the number of session
     */
    public static void menuVentaEntrades(int nsessio) {
        String op;
        do {
            System.out.print("Que vol fer:\n" +
                            "\ta) Veure les localitats disponibles per a la sessio\n" +
                            "\tb) Reservar un seient\n" +
                            "\tc) Cancel.lar reserva\n" +
                            "\td) Coneixer qui te la reserva\n" +
                            "\ts) Sortir del menú de venta\n" +
                            "Trie una opció:");
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
                    cancelarReserva(nsessio);
                    break;
                case "d":
                    saberNomEspectador(nsessio);
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
     * A method that returns a viewer object, copying values from a subscriber. 
     * @param nAbonat  Subscriber Number 
     * @return  Spectator object 
     */
    /* public static Espectador assignarAbonat (int nAbonat) {
        Espectador espectador = new Espectador(-1);
        if ((nAbonat < abonats.size()) && (nAbonat >= 0)) {
            System.out.println(abonats.get(nAbonat).toString());
            System.out.print("Es correcte (y):");
            String op = System.console().readLine();
            if ((op.equals("y")) || (op.equals("Y"))) {
                espectador.igualar(abonats.get(nAbonat));
                return espectador;
            } else if ((op.equals("n")) || (op.equals("N"))) {
                return espectador = new Espectador(-10);
            } else {
                
                return espectador = new Espectador(-10);
            }
        } else {
            return espectador = new Espectador(-10);
        }    
    } */

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SUSTITUIT A VOLUNTAT PROPIA
    /**
     * Menu to select theater locations.  
     */
    /*public static void menuButaques () {
        System.out.print("Ubicacions disponibles\n" +
                            "\t0 Butaques\n" +
                            "\t1 Galeria\n" +
                            "\t2 Platees\n"+
                            "\t3 Sortir d'aquest menú\n" +
                            "Seleccione una de les ubicacions:");   
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Metode antic sustituit per instruccions de Maria

    /* /**
     * Method for selling an entry from a session, given by the input parameter nsessio. It allows us to sell to both subscribers and non-subscribers, also the location of the seat, and the price applying discounts. 
     * @param nsessio Session number for cancellation of the reservation 
     *//*
    public static void ferVenta (int nsessio) {
        Espectador espectador = new Espectador(-1);
        System.out.print("Reservar per abonat (y/n):");
        String op = System.console().readLine();
        if ((op.equals("y")) || (op.equals("Y"))) { 
            //informacio sobre el preu
            float preuBase = sessions.get(nsessio).getPreu();
            float preuButaca = (float)preuBase*((float)1 - (float)DES_ABONATS/(float)100);
            System.out.println("El preu de les butaques es: " + String.format("%.2f",preuButaca));
            float preuGaleria = (float)preuBase*((float)1 - ((float)DES_ABONATS/(float)100) - ((float)DES_GALERIA/(float)100));
            System.out.println("El preu de la galeria es: " + String.format("%.2f",preuGaleria));
            float preuPlatea = (float)preuBase*((float)1 - ((float)DES_ABONATS/(float)100) + ((float)BONI_PLATEA/(float)100));
            System.out.println("El preu de les platees  es: " + String.format("%.2f",preuPlatea));

            System.out.print("Introdueix el nombre d'abonat:");
            int nAbonat = Comprovacions.cambiarStringInteger(System.console().readLine());
            if ((nAbonat < abonats.size()) && (nAbonat >= 0)) {
                System.out.println(abonats.get(nAbonat).toString());
                System.out.print("Es correcte (y):");
                String re = System.console().readLine();
                if ((re.equals("y")) || (re.equals("Y"))) {
                    espectador.igualar(abonats.get(nAbonat));
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
                                    float money = diners - preuButaca;
                                    abonats.get(nAbonat).setDiners(money);
                                    espectador.setDiners(preuButaca);
                                    sessions.get(nsessio).reservaSeient(espectador, z, y, x); 
                                } else {
                                    System.out.println("No te prou diners al abonament per la entrada");
                                } 
                                break;
                            case "1":
                                z = 1;
                                System.out.print("Indiqui la fila:");
                                y = sessions.get(nsessio).comprovarFilaGaleria(System.console().readLine());
                                System.out.print("Indiqui el numero del seient:");
                                x = sessions.get(nsessio).comprovarColGaleria(System.console().readLine());
                                if (diners >= preuGaleria) {
                                    float money = diners - preuGaleria;
                                    abonats.get(nAbonat).setDiners(money);
                                    espectador.setDiners(preuGaleria);
                                    sessions.get(nsessio).reservaSeient(espectador, z, y, x); //reservem seient amb reservarSeient a classe sessio   
                                } else {
                                    System.out.println("No te prou diners al abonament per la entrada");
                                } 
                                break;
                            case "2":
                                if (diners > preuPlatea) {
                                    float money = diners - preuPlatea;
                                    abonats.get(nAbonat).setDiners(money);
                                    espectador.setDiners(preuPlatea);
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
                } else {
                    System.out.println("La operació s'ha cancelat.");
                }
            } else {
                System.out.println("El nombre d'abonat no existeix");
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
                    System.out.print("Indiqui la fila: ");
                    byte y = sessions.get(nsessio).comprovarFilaButaques(System.console().readLine());
                    System.out.print("Indiqui el nombre del seient: ");
                    byte x = sessions.get(nsessio).comprovarColButaques(System.console().readLine());
                    espectador = new Espectador(preuBase); //creem un espectador amb el contructor de diners = preu de la entrada
                    sessions.get(nsessio).reservaSeient(espectador, z, y, x); //reservem seient amb reservarSeient a classe sessio   
                    break;
                case "1":
                    z = 1;
                    System.out.print("Indiqui la fila: ");
                    y = sessions.get(nsessio).comprovarFilaGaleria(System.console().readLine());
                    System.out.print("Indiqui el numero del seient: ");
                    x = sessions.get(nsessio).comprovarColGaleria(System.console().readLine());
                    espectador = new Espectador(preuGaleria); //creem un espectador amb el contructor de diners = preu de la entrada
                    sessions.get(nsessio).reservaSeient(espectador, z, y, x); //reservem seient amb reservarSeient a classe sessio   
                    break;
                case "2":
                        espectador = new Espectador(preuPlatea); //creem un espectador amb el contructor de diners = preu de la entrada
                        sessions.get(nsessio).seientPlatea(espectador);                      
                    break;
                default:
                    System.out.println("Opció no disponible.");    
            }
        } else {
            System.out.println("Opcio no valida.");
        }
    }
 */

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Method to cancel the reservation of a seat in a session determined by the input parameter. Distinguishes between returning a subscriber and a non-subscriber. 
     * @param nsessio  Session number for cancellation of the reservation 
     */
    public static void cancelarReserva (int nsessio) {
        System.out.print("Cancelar reserva per abonat (y/n):");
        String op = System.console().readLine();
        if  ((op.equals("y")) || (op.equals("Y"))) {
            System.out.print("Introdueix el nombre d'abonat:");
            int nAbonat = Comprovacions.cambiarStringInteger(System.console().readLine());
            System.out.println(abonats.get(nAbonat).toString());
            System.out.print("Son correctes les dades (y/n): ");
            String re = System.console().readLine();
            if  ((re.equals("y")) || (re.equals("Y"))) {
                float devolucio = sessions.get(nsessio).cancelarSeient();
                float dinersAbonat = abonats.get(nAbonat).getDiners();
                if (devolucio > 0 ) {
                    float diners = devolucio + dinersAbonat;
                    abonats.get(nAbonat).setDiners(diners);
                    System.out.println("La cantitat retornada al seu abonament es: " + devolucio);
                } else {
                    System.out.println("El seient es vuit.");
                }                
            } else if ((re.equals("n")) || (re.equals("N"))) {
                System.out.println("S'ha cancelat la anul.lació.");
            } else {
                System.out.println("La opció es invalida.");
            }           
        } else if ((op.equals("n")) || (op.equals("N"))) {
            float devolucio = sessions.get(nsessio).cancelarSeient();
            if (devolucio>0) {
                System.out.println("La cantitat a retornar es: " + devolucio);
            } 
            
        } else {
            System.out.println("La opció no es valida.");
        }
    }

    /**
     * Method for calculating the total collected in the selected session. 
     */
    public static void calcularRecaptacio () {
        System.out.println("Sessions programades:");
        if (sessions.size() > 0) {
            llistarSessions();
            System.out.print("Trie la sessió per a la venta:");
            String op2 = System.console().readLine();            
            try {
                int i = Integer.parseInt(op2);
                if (sessions.size() > i ) {
                    sessions.get(i).recaptatSessio();
                } else {
                    System.out.println("No hi ha cap sessio ham aquest nombre.");
                }            
            } catch (Exception e) {
                System.out.print("La session seleccionada no es valida.");
            } 
        }
    }

    /**
     * Method to know the name of the spectator who occupies a seat.
     * @param nsessio number of session
     */
    public static void saberNomEspectador (int nsessio) {
        System.out.println("Indiqui la locatitat per veure a qui esta assignada.");
        byte op = sessions.get(nsessio).menuButaques();
        if (op==0) {
            System.out.print("Insireix el nombre de fila: ");
                byte fila = sessions.get(nsessio).comprovarFilaButaques(System.console().readLine()); //comprovar fila butaca
                if (fila!=-2) {
                    System.out.print("Insireix el nombre del seient: ");
                    String num = System.console().readLine();
                    byte x = sessions.get(nsessio).comprovarColButaques(num); //Comprobar columna butaca
                    imprimirNom(nsessio, (byte)0, fila, x);
                } 
        } else if (op==1) {        
                System.out.print("Insireix el nombre de fila: ");
                byte fila =sessions.get(nsessio).comprovarFilaGaleria(System.console().readLine());
                if (fila!=-2) {
                    System.out.print("Insireix el nombre del seient: ");
                    String num = System.console().readLine();
                    byte x = sessions.get(nsessio).comprovarColGaleria(num);
                    imprimirNom(nsessio, (byte)1, fila, x);
                }
                
        } else if (op >= 2) {       
                    System.out.print("Insireix el nombre del seient: ");
                    String numse = System.console().readLine();
                    byte ns = sessions.get(nsessio).comprovarNumeroSeientPlatea(numse);
                    switch (ns) {
                        case 1:
                            byte y = 0;
                            byte x = 0;
                            imprimirNom(nsessio, op, y, x);
                            break;
                        case 2:
                            y = 0;
                            x = 1;
                            imprimirNom(nsessio, op, y, x);
                            break;               
                        case 3:
                            y = 1;
                            x = 0;
                            imprimirNom(nsessio, op, y, x);
                            break;               
                        case 4:
                            y = 1;
                            x = 1;
                            imprimirNom(nsessio, op, y, x);
                            break;
                        default:
                            System.out.println("Opció no valida.");     
                    }
        } 
           
    }

    /**
     * Method for print name of viewer.
     * @param nsessio number session.
     * @param z Location.
     * @param y Row or number of platea.
     * @param x Number of seat.
     */
    public static void imprimirNom (int nsessio, byte z, byte y, byte x) {
        if (sessions.get(nsessio).sessionPagatPelSeient(z, y, x) == -1) {
            System.out.println("El seient es vuit.");

        } else {           
            System.out.println("El seient esta ocupat per " + sessions.get(nsessio).sessionNameViewer(z,y,x));
        }  
    }
    

    /**
     * Method for print to screem the name of suscriptors.
     */
    public static void llistarAbonats() {
        for (int i = 0; i < abonats.size(); i++) {
            System.out.println(abonats.get(i).toString());
        }
    }
////////////////////////////////////////////////////////////////7
// Metode ferVenta solicitat per Maria

     /**
     * Method for selling an entry from a session, given by the input parameter nsessio. It allows us to sell to both subscribers and non-subscribers, also the location of the seat, and the price applying discounts. 
     * @param nsessio Session number for cancellation of the reservation 
     */
    public static void ferVenta (int nsessio) {
        byte y=-1; //donem un valor inicial, per evitar errada compilador  
        byte x=-1;
        float preu = sessions.get(nsessio).getPreu();
        byte z = sessions.get(nsessio).menuButaques();
        if (z==0){
            System.out.print("Indiqui la fila:");
            y = sessions.get(nsessio).comprovarFilaButaques(System.console().readLine());
            System.out.print("Indiqui el nombre del seient:");
            x = sessions.get(nsessio).comprovarColButaques(System.console().readLine());
        } else if (z==1) {
            System.out.print("Indiqui la fila:");
            y = sessions.get(nsessio).comprovarFilaGaleria(System.console().readLine());
            System.out.print("Indiqui el nombre del seient:");
            x = sessions.get(nsessio).comprovarColGaleria(System.console().readLine());
        } 
        System.out.print("Reservar per abonat (y/n):");
        String op = System.console().readLine();
        if ((op.equals("y")) || (op.equals("Y"))) { 
            //informacio sobre el preu
            System.out.print("Introdueix el nombre d'abonat:");
            int nAbonat = Comprovacions.cambiarStringInteger(System.console().readLine());
            if ((nAbonat < abonats.size()) && (nAbonat >= 0)) {
                System.out.println(abonats.get(nAbonat).toString());
                System.out.print("Es correcte (y):");
                String re = System.console().readLine();
                if ((re.equals("y")) || (re.equals("Y"))) {
                    if (abonats.get(nAbonat).majorEdat(sessions.get(nsessio).getObra().getPeggi())) {   
                        if (z==0) {
                            preu = preu*(1 - ((float)DES_ABONATS/100));
                        } else if (z==1) {
                            preu = preu*(1 - ((float)DES_ABONATS/100) - ((float)DES_GALERIA/100));
                        } else if (z >= 2) {
                            preu = preu*(1 - ((float)DES_ABONATS/100) + ((float)BONI_PLATEA/100));
                        }         
                        System.out.println("El preu de la entrada es: " + preu);                   
                        if (abonats.get(nAbonat).pagarEntrada(preu)) {
                            if ((z == 0) || (z == 1)) {
                                sessions.get(nsessio).reservaSeient(abonats.get(nAbonat), z, y, x); 
                            } else {
                                sessions.get(nsessio).seientPlatea(abonats.get(nAbonat), z);
                            }
                            System.out.println("S'ha realitzat la reserva.");
                        } else {
                            System.out.println("No s'ha pogut realitzar el pagament.");
                        }
                    } else { //No te prou edat per passar
                        System.out.println("No te edat suficient per entrar en aquesta sessió.");
                    } 
                }
            } else {
                System.out.println("El nombre d'abonat no existeix.");
            }
        } else if ((op.equals("n")) || (op.equals("N"))) { //no es abonat
            if (z==1) {
                preu = preu*(1 - ((float)DES_GALERIA/100));
            } else if (z >= 2) {
                preu = preu*(1 + ((float)BONI_PLATEA/100));
            }          
            System.out.println("la edat minima per entrar es " + sessions.get(nsessio).getObra().getPeggi());
            if (z==1) {
                preu = preu*(1 - ((float)DES_GALERIA/100));
            } else if (z >= 2) {
                preu = preu*(1 + ((float)BONI_PLATEA/100));
            }
            System.out.println("El preu de la entrada es: " + preu);                   
            Espectador espectador = new Espectador (preu);
            if ((z == 0) || (z == 1)) {
                sessions.get(nsessio).reservaSeient(espectador, z, y, x); 
            } else {
                sessions.get(nsessio).seientPlatea(espectador, z);
            }
            System.out.println("S'ha realitzat la reserva.");
        } else {
            System.out.println("La opció escollida no existeix.");
        }        
    }


////////////////////////////////////////////////////////////////////////////////////////////////
// Programa principal igual que estaba abans

    /**
     * Main program
     * @param args
     */
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
                    llistarAbonats();
                    break;
                case "f":
                    calcularRecaptacio();
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
