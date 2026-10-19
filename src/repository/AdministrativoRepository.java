package repository;

import model.Administrativo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdministrativoRepository {
    private List<Administrativo> administrativos;

    public AdministrativoRepository() {
        this.administrativos = new ArrayList<>();
    }

    public void add(Administrativo administrativo) {
        this.administrativos.add(administrativo);
    }

    public Optional<Administrativo> findByCpf(String cpf) {
        for (Administrativo a : administrativos) {
            if (a.getCpf().equals(cpf)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public List<Administrativo> findAll() {
        return new ArrayList<>(administrativos);
    }

    public boolean remove(Administrativo administrativo) {
        return administrativos.remove(administrativo);
    }
}