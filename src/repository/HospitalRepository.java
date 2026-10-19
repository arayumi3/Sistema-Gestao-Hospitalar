package repository;

import model.Hospital;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HospitalRepository {
    private List<Hospital> hospitais;

    public HospitalRepository() {
        this.hospitais = new ArrayList<>();
    }

    public void add(Hospital hospital) {
        this.hospitais.add(hospital);
    }

    public Optional<Hospital> findByNome(String nome) {
        for (Hospital h : hospitais) {
            if (h.getNome().equalsIgnoreCase(nome)) {
                return Optional.of(h);
            }
        }
        return Optional.empty();
    }

    public List<Hospital> findAll() {
        return new ArrayList<>(hospitais);
    }

    public boolean remove(Hospital hospital) {
        return hospitais.remove(hospital);
    }
}