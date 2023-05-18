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
}