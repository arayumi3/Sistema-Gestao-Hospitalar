package model;

import java.util.Objects;

public class TipoExame {
    private String nome;
    private String descricao;

    public TipoExame(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Tipo de Exame: " + nome + " (" + descricao + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoExame tipoExame = (TipoExame) o;
        return Objects.equals(nome, tipoExame.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}