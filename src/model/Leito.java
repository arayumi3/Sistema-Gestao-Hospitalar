package model;
import java.util.Objects;

public class Leito {
    private String id;
    private TipoLeito tipo;
    private boolean ocupado;

    public Leito(String id, TipoLeito tipo) {
        this.id = id;
        this.tipo = tipo;
        this.ocupado = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoLeito getTipo() {
        return tipo;
    }

    public void setTipo(TipoLeito tipo) {
        this.tipo = tipo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        return "Leito{" +
                "ID='" + id + '\'' +
                ", Tipo=" + tipo +
                ", Ocupado=" + (ocupado ? "Sim" : "Não") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leito leito = (Leito) o;
        return Objects.equals(id, leito.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}