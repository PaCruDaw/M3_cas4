import java.time.*;

public class Sessio {
    private Obra representacio = new Obra();
    private Seients seient = new Seients();
    private LocalDate data;
    
    public Sessio (Obra repre, Seients asiento, LocalDate fecha) {
        this.representacio =repre;
        this.seient = asiento;
        this.data = fecha;
    }

    //SETTERS
    public void setObra (Obra repre) {
        this.representacio = repre;
    }

    public void setSeient (Seients asiento) {
        this.seient = asiento;
    }

    public void setLocalDate (LocalDate fecha) {
        this.data = fecha;
    }

    //GETTERS
    public Obra getObra () {
        return representacio;
    }

    public Seients getSeient () {
        return seient;
    }

    public LocalDate getData() {
        return data;
    }
}
