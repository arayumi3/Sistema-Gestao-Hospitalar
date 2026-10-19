package model;

import java.time.LocalDate;
import java.util.Objects;

public class Exame {
    private String id;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private TipoExame tipoExame;
    private LocalDate dataSolicitacao;
    private LocalDate dataRealizacao;
    private String resultado;
    public Exame(String id, Paciente paciente, Medico medicoSolicitante, TipoExame tipoExame, LocalDate dataSolicitacao) {
        this.id = id;
        this.paciente = paciente;
        this.medicoSolicitante = medicoSolicitante;
        this.tipoExame = tipoExame;
        this.dataSolicitacao = dataSolicitacao;
        this.dataRealizacao = null;
        this.resultado = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedicoSolicitante() {
        return medicoSolicitante;
    }

    public void setMedicoSolicitante(Medico medicoSolicitante) {
        this.medicoSolicitante = medicoSolicitante;
    }

    public TipoExame getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(TipoExame tipoExame) {
        this.tipoExame = tipoExame;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "ID='" + id + '\'' +
                ", Paciente=" + paciente.getNome() +
                ", Médico Solicitante=" + medicoSolicitante.getNome() +
                ", Tipo de Exame=" + tipoExame.getNome() +
                ", Data de Solicitação=" + dataSolicitacao +
                ", Data de Realização=" + (dataRealizacao != null ? dataRealizacao : "Não realizada") +
                ", Resultado='" + (resultado != null ? resultado : "Pendente") + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exame exame = (Exame) o;
        return Objects.equals(id, exame.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}