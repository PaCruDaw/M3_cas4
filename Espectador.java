/**
 * @author Paula Cruzado Escura
 */

import java.time.*;;

public class Espectador {
    private String nom = "Anonim"; //Initzialitcem a anonim
    private LocalDate dataNaixement = LocalDate.of(1900,1,1); //Inicialitcem a data naixement a 1 de Gener de 1900
    private float diners; //Diners que te el espectador a un abonament per exemple

    //CONSTRUCTORS
    /**
     * Constructor of the viewer class with three parameters
     * @param nombre This is the viewer's name
     * @param fecha This is the viewer's date of birth
     * @param dinero This is the money that the viewer has
     */
    public Espectador (String nombre, LocalDate fecha, Float dinero) {
        this.nom = nombre;
        this.dataNaixement = fecha;
        this.diners = dinero;
    }

    /**
     * Constructor of the viewer class with two parameters
     * @param nombre This is the viewer's name
     * @param dinero This is the money that the viewer has
     */
    public Espectador (String nombre, float dinero) {
        this.nom = nombre;
        this.diners = dinero;
    }
    
    /**
     * Constructor of the viewer class with three parameters
     * @param dinero This is the money that the viewer has
     */
    public Espectador (float dinero) {
        this.diners = dinero;
    }
    
    //SETTERS
    /**
     * setter for nom attribute
     * @param nombre This is the viewer's name
     */
    public void setNom (String nombre) {
        this.nom = nombre;
    }

    /**
     * setter for dataNaixement attribute
     * @param fecha This is the viewer's date of birth
     */
    public void setDataNaixement (LocalDate fecha) {
        this.dataNaixement = fecha;
    }

    /**
     * setter for dinero attribute
     * @param dinero This is the money that the viewer has
     */
    public void setDiners (float dinero) {
        this.diners = dinero;
    }

    //GETTERS
    /**
     * getter for nom attribute
     * @return nombre This is the viewer's name
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter for dataNaixement attribute
     * @return fecha This is the viewer's date of birth
     */
     public LocalDate getDataNaixement () {
        return dataNaixement;
    }

    /**
     * getter for dinero attribute
     *  @return dinero This is the money that the viewer has
     */
    public float getDiners () {
        return diners;
    }

    /**
     * Method to print viewer data
     */
    public String toString () {
        String espectador = nom + " amb data de naixement " + Comprovacions.dataString(dataNaixement) + 
                            " te actualment " + diners + "€ al seu abonament";
        return espectador;
    }

    /**
     * Method for clone Espectador Object 
     * @param viewer Object Espectador
     */
    public void igualar(Espectador viewer) {
        this.nom = viewer.getNom();
        this.dataNaixement = viewer.getDataNaixement();
        this.diners = viewer.getDiners();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Metodes nous convenients per al meu criteri

    //en el enunciat original l'atribut es la edad un int, jo  tinc el atribut data
    /**
     * This method calcul age of espectador
     * @return age of espectador
     */
    public int calculEdat ( ) {
        LocalDate fn = this.dataNaixement; //data de naixement del abonat
        LocalDate fhoy = LocalDate.now();
        Period period = Period.between(fn, fhoy); //calcul del periode transcurrit
        int edatEsp = period.getYears(); //periode en anys
        return edatEsp;
    } 



    /**
     * Method to update the money that the spectator has, if the number is positive it is added to the amount, 
     * if it is negative it is subtracted
     * @param act_diners money to add or subtracted
     */
    public void actualitzarDiners ( float act_diners) {
        this.diners = this.diners + act_diners;
    }

    ///metodes nous solicitats Maria


    /**
     * This method compares the age of the viewer, 
     * with the age passed by parameter, if it is greater or equal,
     * it returns true, if it is less, false.
     * @param edat Age pased by parameter
     * @return false is ages less, true if is greater or equal. 
     */
    public boolean majorEdat(int edat) {
        int edatEsp = calculEdat();
        if (edatEsp >= edat) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method compares money of the viewer, with price passed by parameter, if it is greater or equal,
     * it returns true, if it is less, false.
     * @param preu price of admision
     * @return true if money is greater or equal, false if is less
     */
    public boolean teDiners ( float preu) {
        if (this.diners >= preu ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the money that the spectator has, if the number is positive it is added to the amount, 
     * if it is negative it is subtracted
     * @param preu money to add or subtracted
     * @return if operation ok return 0 if not return false
     */
    public boolean pagarEntrada ( float preu) {
        if (teDiners(preu)) {
            actualitzarDiners(-preu);
            return true;
        } else { //No te diners suficients
            System.out.println("No te diners suficients per accedir");
            return false;
        }   
    }



}