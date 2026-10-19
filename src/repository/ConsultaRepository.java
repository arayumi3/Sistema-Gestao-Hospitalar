package repository;

import model.Consulta;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ConsultaRepository {
    private List<Consulta> consultas;

    public ConsultaRepository() {
        this.consultas = new ArrayList<>();
    }


    public void add(Consulta consulta) {
        this.consultas.add(consulta);
    }


    public Optional<Consulta> findById(String id) {

        for (Consulta c : consultas) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public List<Consulta> findAll() {
        return new ArrayList<>(consultas);
    }


    public boolean remove(Consulta consulta) {
        return consultas.remove(consulta);
    }
}