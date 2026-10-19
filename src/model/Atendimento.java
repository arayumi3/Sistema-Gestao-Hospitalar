package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Atendimento {
    private String id;
    private Paciente paciente;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String motivo;
    private Leito leito;
    private Medico medicoResponsavel;

    public Atendimento(String id, Paciente paciente, LocalDateTime dataEntrada, String motivo, Medico medicoResponsavel) {
        this.id = id;
        this.paciente = paciente;
        this.dataEntrada = dataEntrada;
        this.motivo = motivo;
        this.medicoResponsavel = medicoResponsavel;
        this.dataSaida = null;
        this.leito = null;
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

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Leito getLeito() {
        return leito;
    }

    public void setLeito(Leito leito) {
        this.leito = leito;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public void setMedicoResponsavel(Medico medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "ID='" + id + '\'' +
                ", Paciente=" + paciente.getNome() +
                ", Data de Entrada=" + dataEntrada +
                ", Data de Saída=" + (dataSaida != null ? dataSaida : "Em andamento") +
                ", Motivo='" + motivo + '\'' +
                ", Leito=" + (leito != null ? leito.getId() : "N/A") +
                ", Médico Responsável=" + medicoResponsavel.getNome() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendimento that = (Atendimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}