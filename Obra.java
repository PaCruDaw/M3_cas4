public class Obra {
    private enum PEGGI {TRES,SET,DOTZE,SETZE,DIVUIT}

    private String titol;
    private PEGGI peggi;
    private short durada;
    private String autor;

    //Constructor amb tots els parametres
    public Obra (String titulo,String autora, short duracion, PEGGI edadmin) {
        this.titol = titulo;
        this.autor = autora;
        this.durada = duracion;
        this.peggi = edadmin;
    }

    //Constructor vuit per poder intciar sessio
    public Obra () {

    }

    //SETTERS
    public void setTitol (String titulo) {
        this.titol = titulo;
    }

    public void setPeggi (PEGGI edadMin) {
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

    public PEGGI getPeggi () {
        return peggi;
    }

    public short getDurada () {
        return durada;
    }

    public String getAutor () {
        return autor;
    }

}
