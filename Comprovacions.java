public class Comprovacions {
    static final byte BFMAX = 10; //max files de butaques, hi han 10 files de butaques
    static final byte BCMAX = 20; //max  columnes de butaques, hi han 8 columnes de butaques
    static final byte GFMAX = 3; //max files de galeria, hi han 10 files de butaques
    static final byte GCMAX = 28; //max columnes de galeria, hi han 8 columnes de butaques
    static final byte PFMAX = 2; //maxim files per a les platees
    static final byte PCMAX = 2; //maxim columnes per a les platees
    static final byte NPLATEES = 6; //Platees -1 existens al teatre


    /**
     * 
     * @param data data for comparate
     * @param limitInferior low limit for data
     * @param limitSuperior up limit for data
     */
    public static boolean comprovarNombreInteger(int data, int limitInferior, int limitSuperior) {
        if ((data < limitInferior) || (data > limitSuperior) ) {
            System.out.println("El nombre introduit no es dins del rang especificat");
            return false;
        } else {
            return true;
        }
    }

    public static boolean comprovarNombreByte(byte data, byte limitInferior, byte limitSuperior) {
        if ((data < limitInferior) || (data > limitSuperior) ) {
            System.out.println("El nombre introduit no es dins del rang especificat");
            return false;
        } else {
            return true;
        }
    }

    public static boolean comprovarNombreShort(short data, short limitInferior, short limitSuperior) {
        if ((data < limitInferior) || (data > limitSuperior) ) {
            System.out.println("El nombre introduit no es dins del rang especificat");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 
     * @param x is the string for convert
     * @return is transformation is ok return num else return error -2
     */
    public static Integer cambiarStringInteger (String x) {
        try {
            int num = Integer.parseInt(x);
            return num;
        } catch (Exception e) {
            System.out.println("La data introduida no es un nombre");
            return -2;
        }
    }

    public static byte cambiarStringByte (String x) {
        try {
            byte num = Byte.parseByte(x);
            return num;
        } catch (Exception e) {
            System.out.println("La data introduida no es un nombre");
            return -2;
        }
    }

    public static short cambiarStringShort (String x) {
        try {
            short num = Short.parseShort(x);
            return num;
        } catch (Exception e) {
            System.out.println("La data introduida no es un nombre");
            return -2;
        }
    }
    
    public static float cambiarStringFloat (String x) {
        try {
            float num = Float.parseFloat(x);
            return num;
        } catch (Exception e) {
            System.out.println("La data introduida no es un nombre");
            float num = -2; 
            return num;
        }
    }

    /**
     * 
     * @param s
     * @return error return -2 else return num
     */
    public static byte comprovarFilaButaques (String s) {
        byte num = cambiarStringByte(s);
        if ((num!=-2) & (num > 1) & (num < BFMAX)) {
            return num--;
        } else {
            System.out.println("El nombre es fora del rang de les files.");
            return -2;
        }
    }

    public static byte comprovarColButaques (String s) {
        byte num = cambiarStringByte(s);
        if ((num!=-2) & (num > 1) & (num < BCMAX)) {
            num--;
            return num--;
        } else {
            System.out.println("El nombre es fora del rang numeric.");
            return -2;
        }
    }

    public static byte comprovarFilaGaleria (String s) {
        byte num = cambiarStringByte(s);
        if ((num!=-2) & (num > 1) & (num < GFMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang de les files.");
            return -2;
        }
    }

    public static byte comprovarColGaleria (String s) {
        byte num = cambiarStringByte(s);
        if ((num!=-2) & (num > 1) & (num < GCMAX)) {
            num--;
            return num;
        } else {
            System.out.println("El nombre es fora del rang numeric.");
            return -2;
        }
    }

    public static void main (String[] args) {
        byte x = comprovarFilaButaques("40");
        System.out.println(x);
    }
}
