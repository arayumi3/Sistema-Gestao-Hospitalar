package model;

import java.util.Objects;

public class TipoLeito {
    private String descricao;

    public TipoLeito(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Tipo de Leito: " + descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoLeito tipoLeito = (TipoLeito) o;
        return Objects.equals(descricao, tipoLeito.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao);
    }
}