package repository;

import model.Medico;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicoRepository {
    private List<Medico> medicos;

    public MedicoRepository() {
        this.medicos = new ArrayList<>();
    }

    public void add(Medico medico) {
        this.medicos.add(medico);
    }


    public Optional<Medico> findByCrm(String crm) {
        for (Medico m : medicos) {
            if (m.getCrm().equals(crm)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }

    public List<Medico> findAll() {
        return new ArrayList<>(medicos);
    }

    public boolean remove(Medico medico) {
        return medicos.remove(medico);
    }
}