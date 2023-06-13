import java.time.*;

/**
 * @author Paula Cruzado Escura
 */
public class Sessio {
    static final byte BFMAX = 10; //max files de butaques, hi han 10 files de butaques
    static final byte BCMAX = 20; //max  columnes de butaques, hi han 8 columnes de butaques
    static final byte GFMAX = 3; //max files de galeria, hi han 10 files de butaques
    static final byte GCMAX = 28; //max columnes de galeria, hi han 8 columnes de butaques
    static final byte PFMAX = 2; //maxim files per a les platees
    static final byte PCMAX = 2; //maxim columnes per a les platees
    static final byte NPLATEES = 6; //Platees existens al teatre mantindre a la mitad del nombre de files de butaques
    static final byte NSPLATEES = 4; //nombre de seients disponibles a les platees       
    

    private Obra representacio = new Obra();
    private Seients seient = new Seients();
    private LocalDateTime data;
    private float preu;

        public class Seients {
            
            //Per a fer els colors funcione en linux i mac
            //Per Windows cal instal.ĺar una llibreria Open Source llamada JANSI
            /**
             * constants for screem colors
             */
            public static final String ANSI_RED = "\u001B[31m";
            public static final String ANSI_GREEN = "\u001B[32m";
            public static final String ANSI_WHITE = "\u001B[37m";
            public static final String ANSI_RESET = "\u001B[0m"; //valors per defecte


            //la quantitat de ubicacions diferens sera 
            //  numero de platees + butaques pati + butaques galeria 
            byte ubicacions = NPLATEES + 2; 

            Espectador[][][] butacas = new Espectador[ubicacions][BFMAX][GCMAX];

            //Constructor
            /**
             * Constructor for seats theather for session
             */
            public Seients () {
                iniciar();
            }

            /**
             * Method to create a theather localitation emply
             */
            public void iniciar () {
                //inicialitzem butaques
                int k = 0;
                for (int j = 0; j < BFMAX; j++) {
                    for (int i = 0; i < BCMAX ; i++ ) {
                        butacas[k][j][i]= new Espectador(-1);
                    }
                }

                //initcialitzem galeria
                k=1;
                for (int j = 0; j < GFMAX; j++) {
                    for (int i = 0; i < GCMAX ; i++ ) {
                        butacas[k][j][i]= new Espectador(-1);
                    }
                }

                //Init platees
                for (k = 2; k < ubicacions; k++) {
                    for (int j = 0; j < PFMAX; j++) {
                        for (int i = 0; i < PCMAX ; i++ ) {
                            butacas[k][j][i]= new Espectador(-1);
                        }
                    }
                }

            }

            //SETTERS
            /**
             * Method that assigns a spectator to a seat.
             * @param viewer //Is the viewer of sesion.
             * @param z  Location of seat.
             * @param y Row of seat o number Platea.
             * @param x Number of seat.
             */
            public void setEspectador (Espectador viewer, byte z, byte y, byte x ) {
                butacas[z][y][x].setNom(viewer.getNom());
                butacas[z][y][x].setDataNaixement(viewer.getDataNaixement());
                butacas[z][y][x].setDiners(viewer.getDiners());
            }

            /**
             * Method that makes a seat available again, emply seat.
             * @param z Location seat
             * @param y Row or Platea seat
             * @param x Number of seat
             */
            public void deleteEspectador (byte z, byte y, byte x) {
                butacas[z][y][x] = new Espectador(-1);
            }

            //GETTERS
            /**
             * Getter for Seient Espectador.
             * @param z Location seat.
             * @param y Row or Platea seat.
             * @param x Number of seat.
             * @return Object Espectador 
             */
            public Espectador getEspectador (byte z, byte y, byte x) {
                return butacas[z][y][x];
            }


            /**
             * 
             * @param z Location seat.
             * @param y Row or Platea seat.
             * @param x Number of seat.           
             * @return Date of born to Espectador
             */
            public LocalDate getBorn (byte z, byte y, byte x) {
                return butacas[z][y][x].getDataNaixement();
            }

            /**
             * Method to return cost of seat, if is positive seat is assigned and return the price pay for it,
             * if is error or emply seat.
             * @param z Location seat.
             * @param y Row or Patea seat.
             * @param x Number of seat.
             * @return Cost of seat, if is negative indicate error or emply seat.
             */
            public float getMoney (byte z, byte y, byte x) {
                return butacas[z][y][x].getDiners();
            }

            /**
             * Method to return the viewer name, if is "Anonim", seat is emply or viewer is not subcript.
             * @param z Location.
             * @param y Row or Number Platea.
             * @param x Number seat.
             * @return Name of viewer.
             */
            public String getViewerName (byte z, byte y, byte x) {
                return butacas[z][y][x].getNom();
            }

            /**
             * Draw a map of location seat, if the seat is emply is green, and if it is reserved color red red.
             */
            public void mapa_ocupacio () {
                int aux =  0; //per control d'ubicacions platees o butaques
                byte auxii =0; //control de files butaques
                for (int k = 2; k < ubicacions ; k++) {
                    k=2+aux;
                    byte p1 = 0; //platees numeracio control
                    byte p2 = 0; //platees numeracio control
                    for (int j = 0; j < PFMAX; j++) {    
                        System.out.print(ANSI_WHITE+"P" + (k-1) + " "+ANSI_WHITE);
                        for (int i = 0; i < PCMAX ; i++ ) {
                            if (butacas[k][j][i].getDiners() == -1) {
                                System.out.print(ANSI_GREEN+(++p1)+" "+ANSI_GREEN);
                            } else {
                                System.out.print(ANSI_RED+(++p1)+" "+ANSI_RED);
                            }          
                        }
                        //Dibuix butaques i platees
                        k = 0;
                        System.out.print(ANSI_WHITE+"\tfB" + (auxii+1) + "\t"+ANSI_WHITE);
                        for (int i = 0; i < BCMAX ; i+=2 ) {
                            if (butacas[k][auxii][i].getDiners() == -1) {
                                System.out.print(ANSI_GREEN+(i+1)+" "+ANSI_GREEN);
                            } else {
                                System.out.print(ANSI_RED+(i+1)+" "+ANSI_RED);
                            }          
                        }
                        System.out.print("\t");
                        for (int i = 1; i < BCMAX ; i+=2 ) {
                            if (butacas[k][auxii][i].getDiners() == -1) {
                                System.out.print(ANSI_GREEN+(i+1)+" "+ANSI_GREEN);
                            } else {
                                System.out.print(ANSI_RED+(i+1)+" "+ANSI_RED);
                            }          
                        }

                        //platea
                        auxii++;
                        k = k+ aux+3;
                        if (NPLATEES >= (k-1) ) {
                            System.out.print(ANSI_WHITE+"\tP" + (k-1) + " "+ANSI_WHITE);
                            for (int i = 0; i < PCMAX ; i++ ) {
                                if (butacas[k][j][i].getDiners() == -1) {
                                    System.out.print(ANSI_GREEN+(++p2)+" "+ANSI_GREEN);
                                } else {
                                    System.out.print(ANSI_RED+(++p2)+" "+ANSI_RED);
                                }          
                            }
                            
                        }
                        k = k - 1;
                        System.out.print("\n");
                    }  
                    k = k + 1;  
                    aux = aux + 2; 
                }

                //Segona part butaques k=0 ja que ubicació butaques
                for (int j = auxii; j < BFMAX; j++) {
                    System.out.print(ANSI_WHITE+"\tfB" + (j+1) + "\t"+ANSI_WHITE);
                    for (int i = 0; i < BCMAX ; i+=2 ) {
                        if (butacas[0][j][i].getDiners() == -1) {
                            System.out.print(ANSI_GREEN+(i+1)+" "+ANSI_GREEN);
                        } else {
                            System.out.print(ANSI_RED+(i+1)+" "+ANSI_RED);
                        }          
                    }
                    System.out.print("\t");
                    for (int i = 1; i < BCMAX ; i+=2 ) {
                        if (butacas[0][j][i].getDiners() == -1) {
                            System.out.print(ANSI_GREEN+(i+1)+" "+ANSI_GREEN);
                        } else {
                            System.out.print(ANSI_RED+(i+1)+" "+ANSI_RED);
                        }          
                    }
                    System.out.print("\n");
                }

                //Galeria
                System.out.print("\n");
                for (int j = 0; j < GFMAX; j++) {
                    System.out.print(ANSI_WHITE+"\t\b\bfG" + (j+1) + " "+ANSI_WHITE);
                    for (int i = 0; i < GCMAX ; i++ ) {
                        if (butacas[1][j][i].getDiners() == -1) {
                            System.out.print(ANSI_GREEN+(i+1)+" "+ANSI_GREEN);
                        } else {
                            System.out.print(ANSI_RED+(i+1)+" "+ANSI_RED);
                        }            
                    }
                    System.out.print(ANSI_RESET+"\n"+ANSI_RESET);
                }   
            }

        }      ////final classe anidada 

    

    //Constructors classe
    /**
     * Constructor with tree parametres
     * @param repre Object Obra, representation.
     * @param fecha Date of representation.
     * @param cost Base price for session
     */
    public Sessio (Obra repre,LocalDateTime fecha, float cost) {
        this.representacio =repre;
        this.data = fecha;
        this.preu = cost;
    }

    public Sessio() {

    }

    //SETTERS
   /**
    * Setter of session representation.
    * @param repre Object representation
    */
    public void setObra (Obra repre) {
        this.representacio = repre;
    }

    /**
     * Setter of session date.
     * @param fecha date and hour of representation.
     */
    public void setLocalDate (LocalDateTime fecha) {
        this.data = fecha;
    }

    /**
     * Setter of session base price.
     * @param cost
     */
    public void setPreu (float cost) {
        this.preu = cost;
    }
    
    //GETTERS
    
    /**
     * Getter of representation
     * @return Objec representation.
     */
    public Obra getObra () {
        return representacio;
    }

    /**
     * Getter of session date
     * @return session date
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * getter Base Price session
     * @return  Base price of the session 
     */
    public float getPreu () {
        return this.preu;
    }

/**
     * Method to know the pay for a seat.
     * @param x Location.
     * @param y Number of row or Platea.
     * @param z Number of seat.
     * @return pay for seat.
     */
    public float sessionPagatPelSeient(byte z, byte y, byte x) {
        return seient.getMoney(z, y, x);
    }

    /**
     * Method to know the name of viewer.
     * @param x Location.
     * @param y Number of row or Platea.
     * @param z Number of seat.
     * @return name of viewer.
     */
    public String sessionNameViewer(byte z, byte y, byte x) {
        return seient.getViewerName(z, y, x);
    }

    /**
     * Method to find the amount of money paid for that seat,
     * If the amount is negative, it means that the seat is not occupied or that there has been a cancellation. 
     * @return  Amount of money for return or error.
     */
    public float cancelarSeient() {
        byte z = menuButaques();
        if (z == 0) {
            System.out.print("Indiqui la fila:");
            byte y = comprovarFilaButaques(System.console().readLine());
            System.out.print("Indiqui el nombre del seient:");
            byte x = comprovarColButaques(System.console().readLine());
            System.out.print("Esteu segurs de cancelar (y): ");
            String op1 = System.console().readLine();
            if  ((op1.equals("y")) || (op1.equals("Y"))) {
                float diners = seient.getMoney(z,y,x);
                if (diners == -1) {
                    System.out.println("El seient no te reserva revise les dades introduides.");
                    return -2;
                } else {
                    seient.deleteEspectador(z, y, x);
                    System.out.println("La cantitat a retornar es: " + diners);
                    return diners;
                }    
            }  else {
                System.out.println("Operació cancelada.");
                return -2;
            }  
        } else if (z==1) {
            System.out.print("Indiqui la fila:");
            byte y = comprovarFilaGaleria(System.console().readLine()); //y es la fila
            System.out.print("Indiqui el numero del seient:");
            byte x = comprovarColGaleria(System.console().readLine()); //x el nom del seient
            System.out.print("Esteu segurs cancelar (y): ");
            String op1 = System.console().readLine();
            if  ((op1.equals("y")) || (op1.equals("Y"))) {
                float diners = seient.getMoney(z,y,x);
                if (diners == -1) {
                    System.out.println("El seient no te reserva revise les dades introduides.");
                    return -1;
                } else {
                    seient.deleteEspectador(z, y, x);
                    return diners;
                }                      
            }  else {
                System.out.println("Operació cancelada.");
                return -2;
            }
        } else  if (z >= 2){           
            float diners = cancelarSeientPlatea();
            return diners;
        } else {
            System.out.println("S'ha produit un error al cancelar la reserva.");
            return -2;
        } 

    }

    /**
     * Method to transform to numeric format, and check the range of the Seats(butaques) rows number,
     * returns a negative number if there is an error.
     * @param s number row of  in String format
     * @return number row in type byte
     */
    public byte comprovarFilaButaques (String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if ((num >= 1) && (num <= BFMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang de les files.");
            return -2;
        }
    }

    /**
     * Method to transform to numeric format, and check the range of the Seats(butaques) seat number,
     * returns a negative number if there is an error.
     * @param s number seat of  in String format
     * @return number seat in type byte
     */
    public byte comprovarColButaques (String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if ((num >= 1) && (num <= BCMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang numeric.");
            return -2;
        }
    }

    /**
     * Method to transform to numeric format, and check the range of the Galery seat rows,
     * returns a negative number if there is an error.
     * @param s number row of  in String format
     * @return number row in type byte
     */
    public byte comprovarFilaGaleria (String s) {
        byte num =  Comprovacions.cambiarStringByte(s);
        if ((num >= 1) && (num <= GFMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang de les files.");
            return -2;
        }
    }

    /**
     *  Method to transform to numeric format, and check the range of the gallery seat number,
     *  returns a negative number if there is an error..
     * @param s number seat of Galery in String format
     * @return number seat in type byte
     */
    public byte comprovarColGaleria (String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if ((num >= 1) && (num <= GCMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang numeric.");
            return -2;
        }
    }

    /**
     * Method to check if the string is a valid stall number, if it is valid 
     * it returns the number otherwise it returns a negative number
     * @param s the string to find out
     * @return a number of stall or number negative
     */
    public byte comprovarNumeroPlatea(String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if (( num >= 1  ) && (num <= NPLATEES)) {
            return num;
        } else {
            System.out.println("El nombre no es valid.");
            return -2;
        }
    }

    /**
     * Method to check if the string is a valid seat number, if it is valid 
     * it returns the number otherwise it returns a negative number
     * @param s the string to find out
     * @return a seat number
     */
    public byte comprovarNumeroSeientPlatea(String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if (( num >= 1 ) && (num <= NSPLATEES)) {
            return num;
        } else {
            System.out.println("El nombre no es valid.");
            return -2;
        }
    }

    /**
     *  Method to reserve a seat in the stalls 
     */
    public void seientPlatea (Espectador viewer, byte p) {
        System.out.print("Introdueix el nombre del seient: ");
        String ns = System.console().readLine();
            switch (ns) {
                case "1":
                    byte y = 0;
                    byte x = 0;
                    reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                    break;
                case "2":
                    y = 0;
                    x = 1;
                    reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                    break;               
                case "3":
                     y = 1;
                     x = 0;
                    reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                    break;               
                case "4":
                    y = 1;
                    x = 1;
                    reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                    break;
                default:
                    System.out.println("No hi ha cap seient amb aquest nombre.");
            }
      
    }

    /**
     * Method to calculate the amount to be returned  in case of cancellation,
     * returns the amount if there is a reservation but returns a negative value if not reservation.
     * @return  Cost of the ticket for your return 
     */
    public float cancelarSeientPlatea () {
        System.out.print("Indiqui el nombre de la platea:");
        byte p = Comprovacions.cambiarStringByte(System.console().readLine());
        if ((p > 0) & (p <= NPLATEES)) {
            p++;
            System.out.print("Indiqui el nombre del seient:");
            byte num = Comprovacions.cambiarStringByte(System.console().readLine());
            if ((num >= 1) & (num <= NSPLATEES)) {
                switch (num) {
                    case 1:
                        byte y = 0;
                        byte x = 0;
                        System.out.print("Esteu segurs cancelar (y): ");
                        String op = System.console().readLine();
                        if  ((op.equals("y")) || (op.equals("Y"))) {
                            float diners = seient.getMoney(p,y,x);
                            seient.deleteEspectador(p, y, x);   
                            return diners;
                        }  else {
                            System.out.println("Operació cancelada.");
                            return -2;
                        }           
                    case 2:
                        y = 0;
                        x = 1;
                        System.out.print("Esteu segurs cancelar (y): ");
                        op = System.console().readLine();
                        if  ((op.equals("y")) || (op.equals("Y"))) {
                            float diners = seient.getMoney(p,y,x);
                            seient.deleteEspectador(p, y, x);   
                            //System.out.println("La cantitat a retornar es: " + diners);
                            return diners;
                        }  else {
                            System.out.println("Operació cancelada.");
                            return -2;
                        }             
                    case 3:
                        y = 1;
                        x = 0;
                        System.out.print("Esteu segurs cancelar (y): ");
                        op = System.console().readLine();
                        if  ((op.equals("y")) || (op.equals("Y"))) {
                            float diners = seient.getMoney(p,y,x);
                            seient.deleteEspectador(p, y, x);  
                            return diners; 
                        }  else {
                            System.out.println("Operació cancelada.");
                            return -2;
                        }             
                    case 4:
                        y = 1;
                        x = 1;
                        System.out.print("Esteu segurs cancelar (y): ");
                        op = System.console().readLine();
                        if  ((op.equals("y")) || (op.equals("Y"))) {
                            float diners = seient.getMoney(p,y,x);
                            //System.out.println("La cantitat a retornar es: " + diners);
                            seient.deleteEspectador(p, y, x);  
                            return diners; 
                        }  else {
                            System.out.println("Operació cancelada.");
                            return -2;
                        }   
                    default:
                        return -2;
                }
            } else {
                System.out.println("El seient indicat no existeix.");
                return -2;
            }
        } else {
            System.out.println("La platea no existeix.");
            return -2;
        }        
    }

    /**
     *  Method to check if a seat is previously reserved.
     * @param viewer Object 
     * @param z Location
     * @param y row
     * @param x  seat number
     */
    public void reservaSeient (Espectador viewer, byte z, byte y, byte x) {
        if (seient.getMoney(z,y,x) == -1) { //si es -1 no hi ha espectador ja esta assignat
            seient.setEspectador(viewer,z,y,x); //assignem espectador
        } else {
            System.out.println("El seint ja es reservat anteriorment.");
        } 
    }


    /**
     * Method to see the distribution of spectators in the theather.
     */
    public void mapaAuditori() {
        seient.mapa_ocupacio();
    }

    
    /**
     *  Method for calculating the total collected in a particular session 
     */
    public void recaptatSessio () {
        byte k = 0;
        float total = 0;
        for (byte j = 0; j < BFMAX; j++) {
            for (byte i = 0; i < BCMAX ; i++ ) {
                if (seient.getMoney(k,j,i) != -1) {
                    total = total + seient.getMoney(k,j,i);
                }            
            }
        }

        //initcialitzem galeria
        k=1;
        for (byte j = 0; j < GFMAX; j++) {
            for (byte i = 0; i < GCMAX ; i++ ) {
                if (seient.getMoney(k,j,i) != -1) {
                    total = total + seient.getMoney(k,j,i);
                }            }
        }

        //Init platees
        byte ubicacions = NPLATEES + 2; 
        for (k = 2; k < ubicacions; k++) {
            for (byte j = 0; j < PFMAX; j++) {
                for (byte i = 0; i < PCMAX ; i++ ) {
                    if (seient.getMoney(k,j,i) != -1) {
                        total = total + seient.getMoney(k,j,i);
                    }
                }
            }
        }
        System.out.println("El total recaptat en aquesta sessio es: " + total);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
//Nous

    public byte menuButaques () {
        System.out.print("Ubicacions disponibles\n" +
                "\t0 Butaques\n" +
                "\t1 Galeria\n");
                for (int i = 2; i < (NPLATEES + 2); i++) {
                    System.out.print("\t"+ i + " Platea " + (i-1) + "\n");
                }
            System.out.print(
                "\t" + (NPLATEES + 2 ) + " Sortir d'aquest menú\n" +
                "Seleccione una de les ubicacions: "); 
        String op = System.console().readLine();
        byte ubi = Comprovacions.cambiarStringByte(op);
        if (ubi != -2) {
            if ((ubi >= 0) && (ubi < NPLATEES + 3)) {
                return ubi;
            } else {
                return -1;
            }       
        } else {
            System.out.println("S'ha produit un error al introduir la ubicació");
            return -1;
        }
        
    }

}

