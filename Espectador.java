/**
 * @author Paula Cruzado Escura
 */

public class Espectador {
    private String nom = "Anonim"; //Initzialitcem a anonim
    private String dataNaixement = "No"; //Inicialitcem a data naixement desconeguda
    private float diners; //Diners que te el espectador

    //CONSTRUCTORS
    /**
     * Constructor of the viewer class with three parameters
     * @param nombre This is the viewer's name
     * @param fecha This is the viewer's date of birth
     * @param dinero This is the money that the viewer has
     */
    public Espectador (String nombre, String fecha, Float dinero) {
        this.nom = nombre;
        this.dataNaixement = fecha;
        this.diners = dinero;
    }

    /**
     * Constructor of the viewer class with two parameters
     * @param fecha This is the viewer's date of birth
     * @param dinero This is the money that the viewer has
     */
    public Espectador (String fecha, float dinero) {
        this.dataNaixement = fecha;
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
    public void setDataNaixement (String fecha) {
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
     * @param nombre This is the viewer's name
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter for dataNaixement attribute
     * @param fecha This is the viewer's date of birth
     */
    public String getDataNaixement () {
        return dataNaixement;
    }

    /**
     * getter for dinero attribute
     * @param dinero This is the money that the viewer has
     */
    public float getDiners () {
        return diners;
    }
}