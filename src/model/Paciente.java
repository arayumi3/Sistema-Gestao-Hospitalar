package model;

import java.time.LocalDate;
import java.util.Objects;
public class Paciente extends Pessoa {

    private String planoSaude;
    private LocalDate dataNascimento;

    public Paciente(String nome, String cpf, String telefone, Endereco endereco, String planoSaude, LocalDate dataNascimento) {
        super(nome, cpf, telefone, endereco);
        this.planoSaude = planoSaude;
        this.dataNascimento = dataNascimento;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "Nome='" + getNome() + '\'' +
                ", CPF='" + getCpf() + '\'' +
                ", Telefone='" + getTelefone() + '\'' +
                ", Endereço=" + getEndereco() +
                ", Plano de Saúde='" + planoSaude + '\'' +
                ", Data de Nascimento=" + dataNascimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}