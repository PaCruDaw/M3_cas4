import java.time.*;

/**
 * @autor Paula Cruzado Escura
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
            public static final String ANSI_RED = "\u001B[31m";
            public static final String ANSI_GREEN = "\u001B[32m";
            public static final String ANSI_WHITE = "\u001B[37m";
            public static final String ANSI_RESET = "\u001B[0m"; //valors per defecte


            //la quantitat de ubicacions diferens sera 
            //  numero de platees + butaques pati + butaques galeria 
            byte ubicacions = NPLATEES + 2; 

            Espectador[][][] butacas = new Espectador[ubicacions][BFMAX][GCMAX];

            //Constructor
            public Seients () {
                iniciar();
            }

            /**
             * Method to create a theather localitation
             * 
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
             * 
             * @param viewer //Is the viewer of sesion
             * @param z  
             * @param y
             * @param x
             */
            public void setEspectador (Espectador viewer, byte z, byte y, byte x ) {
                butacas[z][y][x].setNom(viewer.getNom());
                butacas[z][y][x].setDataNaixement(viewer.getDataNaixement());
                butacas[z][y][x].setDiners(viewer.getDiners());
            }

            public void deleteEspectador (byte z, byte y, byte x) {
                butacas[z][y][x] = new Espectador(-1);
            }

            //GETTERS
            public Espectador getEspectador (byte z, byte y, byte x) {
                return butacas[z][y][x];
            }

            public LocalDate getBorn (byte z, byte y, byte x) {
                return butacas[z][y][x].getDataNaixement();
            }

            public float getMoney (byte z, byte y, byte x) {
                return butacas[z][y][x].getDiners();
            }

            public String getViewerName (byte z, byte y, byte x) {
                return butacas[z][y][x].getNom();
            }

            /**
             * 
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
        }
    ////final classe anidada

    //Constructors classe
    public Sessio (Obra repre,LocalDateTime fecha, float cost) {
        this.representacio =repre;
        this.data = fecha;
        this.preu = cost;
    }

    public Sessio() {

    }

    //SETTERS
    public void setObra (Obra repre) {
        this.representacio = repre;
    }

    public void setSeient (Seients asiento) {
        this.seient = asiento;
    }

    public void setLocalDate (LocalDateTime fecha) {
        this.data = fecha;
    }

    public void setPreu (float cost) {
        this.preu = cost;
    }
    
    //GETTERS
    public Obra getObra () {
        return representacio;
    }

    public Seients getSeient () {
        return seient;
    }

    public LocalDateTime getData() {
        return data;
    }


    public float getPreu () {
        return this.preu;
    }

    
    public void cancelarReserva( ) {
        Teatre.menuButaques();
        String op = System.console().readLine();
        switch (op) {
            case "0":
                byte z = 0;
                System.out.print("Indiqui la fila:");
                byte y = comprovarFilaButaques(System.console().readLine());
                System.out.print("Indiqui el nombre del seient:");
                byte x = comprovarColButaques(System.console().readLine());
                seient.deleteEspectador(z, y, x);                
                break;
            case "1":
                z = 1;
                System.out.print("Indiqui la fila:");
                y = comprovarFilaGaleria(System.console().readLine());
                System.out.print("Indiqui el numero del seient:");
                x = comprovarColGaleria(System.console().readLine());
                seient.deleteEspectador(z, y, x);                
                break;
            case "2":
                deleteSeientPlatea();
                break;           
            default:
                System.out.println("Opció no valida.");    
        }
    }

 /**
     * 
     * @param s
     * @return error return -2 else return num
     */
    public byte comprovarFilaButaques (String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if ((num!=-2) & (num >= 1) & (num <= BFMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang de les files.");
            return -2;
        }
    }

    /**
     * 
     * @param s
     * @return
     */
    public byte comprovarColButaques (String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if ((num!=-2) & (num >= 1) & (num <= BCMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang numeric.");
            return -2;
        }
    }

    /**
     * 
     * @param s
     * @return
     */
    public byte comprovarFilaGaleria (String s) {
        byte num =  Comprovacions.cambiarStringByte(s);
        if ((num!=-2) & (num >= 1) & (num <= GFMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang de les files.");
            return -2;
        }
    }

    /**
     * 
     * @param s
     * @return
     */
    public byte comprovarColGaleria (String s) {
        byte num = Comprovacions.cambiarStringByte(s);
        if ((num!=-2) & (num >= 1) & (num <= GCMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang numeric.");
            return -2;
        }
    }

    public void seientPlatea (Espectador viewer) {
        System.out.print("Indiqui el nombre de la platea:");
        byte p = Comprovacions.cambiarStringByte(System.console().readLine());
        if ((p > 0) & (p <= NPLATEES)) {
            p++;
            System.out.print("Indiqui el nombre del seient:");
            byte num = Comprovacions.cambiarStringByte(System.console().readLine());
            if ((num!=-2) & (num >= 1) & (num <= NSPLATEES)) {
                switch (num) {
                    case 1:
                        byte y = 0;
                        byte x = 0;
                        reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                        break;
                    case 2:
                        y = 0;
                        x = 1;
                        reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                        break;               
                    case 3:
                        y = 1;
                        x = 0;
                        reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                        break;               
                    case 4:
                        y = 1;
                        x = 1;
                        reservaSeient(viewer, p, y, x); //assignem espectador si no hi ha reserva previa
                }
            } else {
                System.out.println("El seient indicat no existeix.");
            }
        } else {
            System.out.println("La platea no existeix.");
        }        
    }


    public void deleteSeientPlatea () {
        System.out.print("Indiqui el nombre de la platea:");
        byte p = Comprovacions.cambiarStringByte(System.console().readLine());
        if ((p > 0) & (p <= NPLATEES)) {
            p++;
            System.out.print("Indiqui el nombre del seirnt:");
            byte num = Comprovacions.cambiarStringByte(System.console().readLine());
            if ((num!=-2) & (num >= 1) & (num <= NSPLATEES)) {
                switch (num) {
                    case 1:
                        byte y = 0;
                        byte x = 0;
                        seient.deleteEspectador(p, y, x);
                        break;
                    case 2:
                        y = 0;
                        x = 1;
                        seient.deleteEspectador(p, y, x);
                        break;               
                    case 3:
                        y = 1;
                        x = 0;
                        seient.deleteEspectador(p, y, x);
                        break;               
                    case 4:
                        y = 1;
                        x = 1;
                        seient.deleteEspectador(p, y, x);
                }
            } else {
                System.out.println("El seient indicat no existeix.");
            }
        } else {
            System.out.println("La platea no existeix.");
        }        
    }

    public void reservaSeient (Espectador viewer, byte z, byte y, byte x) {
        if (seient.getMoney(z,y,x) == -1) { //si es -1 no hi ha espectador ja esta assignat
            seient.setEspectador(viewer,z,y,x); //assignem espectador
        } else {
            System.out.println("El seint ja es reservat.");
        } 
    }

    public void mapaAuditori() {
        seient.mapa_ocupacio();
    }
}
