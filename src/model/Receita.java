package model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Receita {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private LocalDate dataEmissao;
    private List<String> medicamentos;
    private String observacoes;

    public Receita(String id, Paciente paciente, Medico medico, LocalDate dataEmissao) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataEmissao = dataEmissao;
        this.medicamentos = new ArrayList<>();
        this.observacoes = null;
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

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public List<String> getMedicamentos() {
        return medicamentos;
    }

    // Método para adicionar um medicamento à receita
    public void adicionarMedicamento(String medicamento) {
        this.medicamentos.add(medicamento);
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "ID='" + id + '\'' +
                ", Paciente=" + paciente.getNome() +
                ", Médico=" + medico.getNome() +
                ", Data de Emissão=" + dataEmissao +
                ", Medicamentos=" + medicamentos +
                ", Observações='" + (observacoes != null ? observacoes : "N/A") + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return Objects.equals(id, receita.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}