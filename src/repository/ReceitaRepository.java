package repository;

import model.Receita;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceitaRepository {
    private List<Receita> receitas;
    public ReceitaRepository() {
        this.receitas = new ArrayList<>();
    }


    public void add(Receita receita) {
        this.receitas.add(receita);
    }

    public Optional<Receita> findById(String id) {

        for (Receita r : receitas) {
            if (r.getId().equals(id)) {
                return Optional.of(r);
            }
        }
        return Optional.empty();
    }

    public List<Receita> findAll() {
        return new ArrayList<>(receitas);
    }

    public boolean remove(Receita receita) {
        return receitas.remove(receita);
    }
}