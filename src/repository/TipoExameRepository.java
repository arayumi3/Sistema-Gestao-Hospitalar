package repository;

import model.TipoExame;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TipoExameRepository {
    private List<TipoExame> tiposExame;

    public TipoExameRepository() {
        this.tiposExame = new ArrayList<>();
    }

    public void add(TipoExame tipoExame) {
        this.tiposExame.add(tipoExame);
    }

    public Optional<TipoExame> findByNome(String nome) {
        for (TipoExame te : tiposExame) {
            if (te.getNome().equalsIgnoreCase(nome)) {
                return Optional.of(te);
            }
        }
        return Optional.empty();
    }

    public List<TipoExame> findAll() {
        return new ArrayList<>(tiposExame);
    }

    public boolean remove(TipoExame tipoExame) {
        return tiposExame.remove(tipoExame);
    }
}