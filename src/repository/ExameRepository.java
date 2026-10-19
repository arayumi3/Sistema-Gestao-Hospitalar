package repository; // Define que esta classe pertence ao pacote 'repository'

import model.Exame;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ExameRepository {
    private List<Exame> exames;


    public ExameRepository() {
        this.exames = new ArrayList<>();
    }

    public void add(Exame exame) {
        this.exames.add(exame);
    }

    public Optional<Exame> findById(String id) {
        for (Exame e : exames) {
            if (e.getId().equals(id)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }


    public List<Exame> findAll() {
        return new ArrayList<>(exames);
    }

    public boolean remove(Exame exame) {
        return exames.remove(exame);
    }
}