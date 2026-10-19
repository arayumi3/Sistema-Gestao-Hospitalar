package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prontuario {
    private String id;
    private Paciente paciente;
    private List<Atendimento> historicoAtendimentos;
    private List<Consulta> historicoConsultas;
    private List<Receita> historicoReceitas;
    private List<Exame> historicoExames;

    public Prontuario(String id, Paciente paciente) {
        this.id = id;
        this.paciente = paciente;
        this.historicoAtendimentos = new ArrayList<>();
        this.historicoConsultas = new ArrayList<>();
        this.historicoReceitas = new ArrayList<>();
        this.historicoExames = new ArrayList<>();
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

    public List<Atendimento> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        this.historicoAtendimentos.add(atendimento);
    }

    public List<Consulta> getHistoricoConsultas() {
        return historicoConsultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        this.historicoConsultas.add(consulta);
    }

    public List<Receita> getHistoricoReceitas() {
        return historicoReceitas;
    }

    public void adicionarReceita(Receita receita) {
        this.historicoReceitas.add(receita);
    }

    public List<Exame> getHistoricoExames() {
        return historicoExames;
    }

    public void adicionarExame(Exame exame) {
        this.historicoExames.add(exame);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prontuário do Paciente: ").append(paciente.getNome()).append(" (ID: ").append(id).append(")\n");
        sb.append("--- Histórico de Atendimentos ---\n");
        historicoAtendimentos.forEach(at -> sb.append(at).append("\n"));
        sb.append("--- Histórico de Consultas ---\n");
        historicoConsultas.forEach(c -> sb.append(c).append("\n"));
        sb.append("--- Histórico de Receitas ---\n");
        historicoReceitas.forEach(r -> sb.append(r).append("\n"));
        sb.append("--- Histórico de Exames ---\n");
        historicoExames.forEach(e -> sb.append(e).append("\n"));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prontuario that = (Prontuario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}