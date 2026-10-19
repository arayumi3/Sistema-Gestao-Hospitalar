package repository;

import model.Prontuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProntuarioRepository {
    private List<Prontuario> prontuarios;

    public ProntuarioRepository() {
        this.prontuarios = new ArrayList<>();
    }

    public void add(Prontuario prontuario) {
        this.prontuarios.add(prontuario);
    }

    public Optional<Prontuario> findById(String id) {
        for (Prontuario p : prontuarios) {
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Optional<Prontuario> findByPacienteCpf(String cpfPaciente) {
        for (Prontuario p : prontuarios) {
            if (p.getPaciente().getCpf().equals(cpfPaciente)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Prontuario> findAll() {
        return new ArrayList<>(prontuarios);
    }

    public boolean remove(Prontuario prontuario) {
        return prontuarios.remove(prontuario);
    }
}