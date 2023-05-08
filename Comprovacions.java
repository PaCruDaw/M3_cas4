import java.time.*;
import java.time.format.DateTimeFormatter;

public class Comprovacions {
    
    /**
     * 
     * @param data data for comparate
     * @param limitInferior low limit for data
     * @param limitSuperior up limit for data
     */
    public static boolean betweenInteger(int data, int limitInferior, int limitSuperior) {
        if ((data < limitInferior) || (data > limitSuperior) ) {
            System.out.println("El nombre introduit no es dins del rang especificat");
            return false;
        } else {
            return true;
        }
    }

    public static boolean betweenByte(byte data, byte limitInferior, byte limitSuperior) {
        if ((data < limitInferior) || (data > limitSuperior) ) {
            System.out.println("El nombre introduit no es dins del rang especificat");
            return false;
        } else {
            return true;
        }
    }

    public static boolean betweenShort(short data, short limitInferior, short limitSuperior) {
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

    public static byte acotarMes (byte x) {
        if ((x <= 12) & (x >= 1)) {
            return x;
        } else {
            return -2;
        }
    }

    public static byte acotarDia (byte x) {
        if ((x <= 31) & (x >= 1)) {
            return x;
        } else {
            return -2;
        }
    }

    public static String dataHourString (LocalDateTime fecha) {
        LocalDateTime dataNoFormat = fecha;
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");  
        String data = dataNoFormat.format(formatData);  
        return data;
    }

    public static String dataString (LocalDate fecha) {
        LocalDate dataNoFormat = fecha;
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  
        String data = dataNoFormat.format(formatData);  
        return data;
    }

   /*  public static boolean comprovarDataFutura () {

    }
     */

    /* public static void main (String[] args) {
        byte x = comprovarFilaButaques("40");
        System.out.println(x);
    } */
}
