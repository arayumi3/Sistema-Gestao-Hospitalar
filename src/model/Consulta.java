package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String diagnostico;
    private boolean realizada;

    public Consulta(String id, Paciente paciente, Medico medico, LocalDateTime dataHora) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.diagnostico = null;
        this.realizada = false;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "ID='" + id + '\'' +
                ", Paciente=" + paciente.getNome() +
                ", Médico=" + medico.getNome() +
                ", Data/Hora=" + dataHora +
                ", Diagnóstico='" + (diagnostico != null ? diagnostico : "N/A") + '\'' +
                ", Realizada=" + (realizada ? "Sim" : "Não") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(id, consulta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}