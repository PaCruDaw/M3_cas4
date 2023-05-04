import java.time.*;
import java.time.format.DateTimeFormatter; 

public class Sessio {

    public class Seients {
        static final byte BFMAX = 10; //max files de butaques, hi han 10 files de butaques
        static final byte BCMAX = 20; //max  columnes de butaques, hi han 8 columnes de butaques
        static final byte GFMAX = 3; //max files de galeria, hi han 10 files de butaques
        static final byte GCMAX = 28; //max columnes de galeria, hi han 8 columnes de butaques
        static final byte PFMAX = 2; //maxim files per a les platees
        static final byte PCMAX = 2; //maxim columnes per a les platees
        static final byte NPLATEES = 6; //Platees -1 existens al teatre

        //static final byte FMAX = BFMAX;
        //static final byte CMAX = GCMAX;


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
        public void setEspectador (Espectador viewer, int z, int y, int x) {
            butacas[z][y][x] = viewer;
        }

        public void setViewerDelete (byte z, byte y, byte x) {
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



        public void mapa_ocupacio () {
            //dibuixem ocupació butaques 
            int aux =  0;
            int auxii =0;
            for (int k = 2; k < ubicacions ; k++) {
                k=2+aux;
                for (int j = 0; j < PFMAX; j++) {              
                    for (int i = 0; i < PCMAX ; i++ ) {
                        if (butacas[k][j][i].getDiners() == -1) {
                            System.out.print("X");
                        } else {
                            System.out.print("O");
                        }          
                    }
                    //Dibuix butaques
                    k = 0;
                    System.out.print("\t");
                    for (int i = 1; i < BCMAX ; i+=2 ) {
                        if (butacas[k][auxii][i].getDiners() == -1) {
                            System.out.print("X");
                        } else {
                            System.out.print("O");
                        }          
                    }
                    System.out.print("\t");
                    for (int i = 0; i < BCMAX ; i+=2 ) {
                        if (butacas[k][auxii][i].getDiners() == -1) {
                            System.out.print("X");
                        } else {
                            System.out.print("O");
                        }          
                    }

                    auxii++;
                    k = k+ aux+3;
                    System.out.print("\t");
                    for (int i = 0; i < PCMAX ; i++ ) {
                        if (butacas[k][j][i].getDiners() == -1) {
                            System.out.print("X");
                        } else {
                            System.out.print("O");
                        }          
                    }
                    k = k -1;
                    System.out.print("\n");
                }
                k = k + 1;  
                aux = aux + 2;      
            }

            //Segona part butaques
            for (int j = auxii; j < BFMAX; j++) {
                System.out.print("\t");
                for (int i = 1; i < BCMAX ; i+=2 ) {
                    if (butacas[0][j][i].getDiners() == -1) {
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }          
                }
                System.out.print("\t");
                for (int i = 0; i < BCMAX ; i+=2 ) {
                    if (butacas[0][j][i].getDiners() == -1) {
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }          
                }
                System.out.print("\n");

            }

            //Galeria
            System.out.print("\n");
            for (int j = 0; j < GFMAX; j++) {
                System.out.print("       ");
                for (int i = 0; i < GCMAX ; i++ ) {
                    if (butacas[1][j][i].getDiners() == -1) {
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }            
                }
                System.out.print("\n");
            }

        }
        
    }


    private Obra representacio = new Obra();
    private Seients seient = new Seients();
    private LocalDateTime data;
    //Constructor
    public Sessio (Obra repre,LocalDateTime fecha) {
        this.representacio =repre;
        this.data = fecha;
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

    public String getDataString () {
        LocalDateTime dataNoFormat = this.data;
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");  
        String data = dataNoFormat.format(formatData);  
        return data;
    }

}
