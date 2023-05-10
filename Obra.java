public class Obra {

    private String titol;
    private byte peggi;
    private short durada;
    private String autor;

    //Constructor amb tots els parametres
    public Obra (String titulo,String autora, short duracion, byte edadmin) {
        this.titol = titulo;
        this.autor = autora;
        this.durada = duracion;
        this.peggi = edadmin;
    }

    public Obra (String titulo,String autora, short duracion) {
        this.titol = titulo;
        this.autor = autora;
        this.durada = duracion;
    }

    //Constructor vuit per poder intciar sessio
    public Obra () {

    }

   /*  public Obra (Obra repre) {
        this.titol = repre.titol;
        this.peggi = repre.peggi;
        this.durada = repre.durada;
        this.autor = repre.autor;
    } */

    //SETTERS
    public void setTitol (String titulo) {
        this.titol = titulo;
    }

    public void setPeggi (byte edadMin) {
        this.peggi = edadMin;
    }

    public void setDurada (short duracion) {
        this.durada = duracion;
    }

    public void setAutor (String autorp) {
        this.autor = autorp;
    }


    //GETTERS
    public String getTitul () {
        return titol;
    }

    public byte getPeggi () {
        return peggi;
    }

    public short getDurada () {
        return durada;
    }

    public String getAutor () {
        return autor;
    }

}
