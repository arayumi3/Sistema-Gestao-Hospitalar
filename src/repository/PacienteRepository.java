package repository;

import model.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteRepository {
    private List<Paciente> pacientes;

    public PacienteRepository() {
        this.pacientes = new ArrayList<>();
    }

    public void add(Paciente paciente) {
        this.pacientes.add(paciente);
    }

    public Optional<Paciente> findByCpf(String cpf) {
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Paciente> findAll() {
        return new ArrayList<>(pacientes);
    }

    public boolean remove(Paciente paciente) {
        return pacientes.remove(paciente);
    }
}