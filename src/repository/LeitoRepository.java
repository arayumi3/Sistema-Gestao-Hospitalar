package repository;

import model.Leito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class LeitoRepository {
    private List<Leito> leitos;


    public LeitoRepository() {
        this.leitos = new ArrayList<>();
    }


    public void add(Leito leito) {
        this.leitos.add(leito);
    }

    public Optional<Leito> findById(String id) {

        for (Leito l : leitos) {

            if (l.getId().equals(id)) {
                return Optional.of(l);
            }
        }
        return Optional.empty();
    }

    public List<Leito> findAll() {
        return new ArrayList<>(leitos);
    }


    public boolean remove(Leito leito) {
        return leitos.remove(leito);
    }
}