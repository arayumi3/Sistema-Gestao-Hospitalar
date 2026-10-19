package model;

import java.util.Objects;

public class Administrativo extends Pessoa {

    private String cargo;

    public Administrativo(String nome, String cpf, String telefone, Endereco endereco, String cargo) {
        super(nome, cpf, telefone, endereco);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "Nome='" + getNome() + '\'' +
                ", CPF='" + getCpf() + '\'' +
                ", Telefone='" + getTelefone() + '\'' +
                ", Endereço=" + getEndereco() +
                ", Cargo='" + cargo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrativo that = (Administrativo) o;
        return Objects.equals(cargo, that.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo);
    }
}