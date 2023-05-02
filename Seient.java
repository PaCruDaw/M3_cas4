
public class Seient {
    static final byte BFMAX = 10; //max files de butaques, hi han 10 files de butaques
    static final byte BCMAX = 8; //max  columnes de butaques, hi han 8 columnes de butaques
    static final byte GFMAX = 3; //max files de galeria, hi han 10 files de butaques
    static final byte GCMAX = 12; //max columnes de galeria, hi han 8 columnes de butaques
    static final byte PFMAX = 2; //maxim files per a les platees
    static final byte PCMAX = 2; //maxim columnes per a les platees
    static final byte NPLATEES = 6; //Platees -1 existens al teatre

    //static final byte FMAX = BFMAX;
    //static final byte CMAX = GCMAX;


    //la quantitat de ubicacions diferens sera 
    //  numero de platees + butaques pati + butaques galeria + 1(per array inici 0)
    static int ubicacions = NPLATEES + 2; 

      
    
    static Espectador[][][] butacas = new Espectador[ubicacions][BFMAX][GCMAX];


    /**
     * Method to create a theather localitation
     * 
     */
    public static void inici_teatre () {
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

    public static void dibuixar_teatre () {
        //dibuixem ocupació butaques 
        int aux =  0;
        for (int k = 2; k < ubicacions ; k++) {
            k=2+aux;
            for (int j = 0; j < PFMAX; j++) {
               
                for (int i = 0; i < PCMAX ; i++ ) {
                    if (butacas[k][j][i].getDiners() == -1) {
                        //System.out.print("X");
                        System.out.print("("+k+","+j+","+i+")");
                    } else {
                        //System.out.print("O");
                        System.out.print("("+k+","+j+","+i+")");
                    }          
                }
                k = 0;
                System.out.print("\t");
                for (int i = 0; i < BCMAX ; i++ ) {
                    if (butacas[k][j][i].getDiners() == -1) {
                        //System.out.print("X");
                     System.out.print("("+k+","+j+","+i+")");
                    } else {
                        //System.out.print("O");
                        System.out.print("("+k+","+j+","+i+")");
                    }          
                }
                k = k+ aux+3;
                System.out.print("\t");
                for (int i = 0; i < PCMAX ; i++ ) {
                    if (butacas[k][j][i].getDiners() == -1) {
                        //System.out.print("X");
                        System.out.print("("+k+","+j+","+i+")");
                    } else {
                        //System.out.print("O");
                        System.out.print("("+k+","+j+","+i+")");
                    }          
                }
                k = k -1;
                System.out.print(k+"\n");
            }
            k = k + 1;  
            aux = aux + 2;      
        }
        // for (int j = 0; j < PFMAX; j++) {
        //     for (int i = 0; i < PCMAX ; i++ ) {
        //         if (butacas[k][j][i].getDiners() == -1) {
        //             //System.out.print("X");
        //             System.out.print("("+k+","+j+","+i+")");
        //         } else {
        //             //System.out.print("O");
        //             System.out.print("("+k+","+j+","+i+")");
        //         }          
        //     }
        //     k = k-2;
        //     System.out.print("\t");
        //     for (int i = 0; i < BCMAX ; i++ ) {
        //         if (butacas[k][j][i].getDiners() == -1) {
        //             //System.out.print("X");
        //          System.out.print("("+k+","+j+","+i+")");
        //         } else {
        //             //System.out.print("O");
        //             System.out.print("("+k+","+j+","+i+")");
        //         }          
        //     }
        //     k = k+3;
        //     System.out.print("\t");
        //     for (int i = 0; i < PCMAX ; i++ ) {
        //         if (butacas[k][j][i].getDiners() == -1) {
        //             //System.out.print("X");
        //             System.out.print("("+k+","+j+","+i+")");
        //         } else {
        //             //System.out.print("O");
        //             System.out.print("("+k+","+j+","+i+")");
        //         }          
        //     }
        //     k = k -1;
        //     System.out.print(k+"\n");
        // }    
       
    }
        
 /*
        k = 0;
        for (int j = 0; j < FMAX
            for (int i = 0; i < BCMAX ; i++ ) {
                if (butacas[k][j][i].getDiners() == -1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
                
            }
            System.out.print("/t");
        }

        int k = 1;
        for (int j = 0; j < PFMAX ; j++) {
            for (int i = 0; i < PCMAX ; i++ ) {
                if (butacas[k][j][i].getDiners() == -1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }          
            }
            System.out.print("/n");
        }

        //dibuixem ocupacio galeria
        System.out.println("");
        k=1;
        for (int j = 0; j < GFMAX; j++) {
            for (int i = 0; i < GCMAX ; i++ ) {
                if (butacas[k][j][i].getDiners() == -1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println("");

        }
        for (k = 2; k < ubicacions; k++) {
            for (int j = 0; j < PFMAX; j++) {
                for (int i = 0; i < PCMAX ; i++ ) {
                    if (butacas[k][j][i].getDiners() == -1) {
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }
                }
            }
            System.out.println("");

        }
*/
    
    
    public static void main (String[] args) {
        inici_teatre();
        butacas[0][0][2].setDiners(30);
        dibuixar_teatre();

    }
}
