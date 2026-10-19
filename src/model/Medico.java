package model;
import java.util.Objects;

public class Medico extends Pessoa {

    private String crm;
    private Especialidade especialidade;

    public Medico(String nome, String cpf, String telefone, Endereco endereco, String crm, Especialidade especialidade) {
        super(nome, cpf, telefone, endereco);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "Nome='" + getNome() + '\'' +
                ", CPF='" + getCpf() + '\'' +
                ", Telefone='" + getTelefone() + '\'' +
                ", Endereço=" + getEndereco() +
                ", CRM='" + crm + '\'' +
                ", Especialidade=" + especialidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medico medico = (Medico) o;
        return Objects.equals(crm, medico.crm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crm);
    }
}