package model;

import java.util.Objects;

public class Enfermeiro extends Pessoa {

    private String coren;

    public Enfermeiro(String nome, String cpf, String telefone, Endereco endereco, String coren) {
        super(nome, cpf, telefone, endereco);
        this.coren = coren;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }

    @Override
    public String toString() {
        return "Enfermeiro{" +
                "Nome='" + getNome() + '\'' +
                ", CPF='" + getCpf() + '\'' +
                ", Telefone='" + getTelefone() + '\'' +
                ", Endereço=" + getEndereco() +
                ", COREN='" + coren + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Enfermeiro enfermeiro = (Enfermeiro) o;
        return Objects.equals(coren, enfermeiro.coren);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coren);
    }
}