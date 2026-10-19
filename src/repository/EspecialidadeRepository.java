package repository;

import model.Especialidade;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EspecialidadeRepository {
    private List<Especialidade> especialidades;

    public EspecialidadeRepository() {
        this.especialidades = new ArrayList<>();
    }

    public void add(Especialidade especialidade) {
        this.especialidades.add(especialidade);
    }

    public Optional<Especialidade> findByNome(String nome) {
        for (Especialidade e : especialidades) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    public List<Especialidade> findAll() {
        return new ArrayList<>(especialidades);
    }

    public boolean remove(Especialidade especialidade) {
        return especialidades.remove(especialidade);
    }
}