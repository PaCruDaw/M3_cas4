public class Obra {

    private String titol;
    private byte peggi;
    private short durada;
    private String autor;

    //Constructor amb tots els parametres
    /**
     * Constuctor
     * @param titulo title of representation
     * @param autora author of representation
     * @param duracion duration of the performance
     * @param edadmin age min for viewer
     */
    public Obra (String titulo,String autora, short duracion, byte edadmin) {
        this.titol = titulo;
        this.autor = autora;
        this.durada = duracion;
        this.peggi = edadmin;
    }


    //Constructor vuit per poder intciar sessio
    /**
     * Constructor
     */
    public Obra () {

    }

    //SETTERS
    /**
     * setter for tittle
     * @param titulo title of representation
     */
    public void setTitol (String titulo) {
        this.titol = titulo;
    }

    /**
     * setter for peggi
     * @param edadMin age min for viewer
     */
    public void setPeggi (byte edadMin) {
        this.peggi = edadMin;
    }

    /**
     * setter for durada
     * @param duracion duration of the performance
     */
    public void setDurada (short duracion) {
        this.durada = duracion;
    }

    /**
     * setter for autor
     * @param autor author of representation
     */
    public void setAutor (String autor) {
        this.autor = autor;
    }


    //GETTERS
    /**
     * getter for title
     * @return title of representation
     */
    public String getTitul () {
        return titol;
    }

    /**
     * getter for peggi
     * @return age min for viewer
     */
    public byte getPeggi () {
        return peggi;
    }

    /**
     * getter for durada
     * @return duration of the performance
     */
    public short getDurada () {
        return durada;
    }

    /**
     * getter for autor
     * @return author of representation
     */
    public String getAutor () {
        return autor;
    }

}
