package model;

import java.util.Objects;

public class Hospital {
    private String nome;
    private Endereco endereco;

    public Hospital(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "Nome='" + nome + '\'' +
                ", Endereço=" + endereco +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(nome, hospital.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}