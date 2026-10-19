package repository;

import model.Atendimento;
import model.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AtendimentoRepository {
    private List<Atendimento> atendimentos;

    public AtendimentoRepository() {
        this.atendimentos = new ArrayList<>();
    }

    public void add(Atendimento atendimento) {
        this.atendimentos.add(atendimento);
    }

    public Optional<Atendimento> findById(String id) {
        for (Atendimento at : atendimentos) {
            if (at.getId().equals(id)) {
                return Optional.of(at);
            }
        }
        return Optional.empty();
    }

    public List<Atendimento> findByPaciente(Paciente paciente) {
        List<Atendimento> atendimentosDoPaciente = new ArrayList<>();
        for (Atendimento at : atendimentos) {
            if (at.getPaciente().equals(paciente)) {
                atendimentosDoPaciente.add(at);
            }
        }
        return atendimentosDoPaciente;
    }

    public List<Atendimento> findAll() {
        return new ArrayList<>(atendimentos);
    }

    public boolean remove(Atendimento atendimento) {
        return atendimentos.remove(atendimento);
    }
}