package repository;

import model.Enfermeiro;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnfermeiroRepository {
    private List<Enfermeiro> enfermeiros;

    public EnfermeiroRepository() {
        this.enfermeiros = new ArrayList<>();
    }

    public void add(Enfermeiro enfermeiro) {
        this.enfermeiros.add(enfermeiro);
    }

    public Optional<Enfermeiro> findByCoren(String coren) {
        for (Enfermeiro e : enfermeiros) {
            if (e.getCoren().equals(coren)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    public Optional<Enfermeiro> findByCpf(String cpf) {
        for (Enfermeiro e : enfermeiros) {
            if (e.getCpf().equals(cpf)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    public List<Enfermeiro> findAll() {
        return new ArrayList<>(enfermeiros);
    }

    public boolean remove(Enfermeiro enfermeiro) {
        return enfermeiros.remove(enfermeiro);
    }
}