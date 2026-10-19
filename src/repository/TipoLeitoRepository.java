package repository;

import model.TipoLeito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TipoLeitoRepository {
    private List<TipoLeito> tiposLeito;

    public TipoLeitoRepository() {
        this.tiposLeito = new ArrayList<>();
    }

    public void add(TipoLeito tipoLeito) {
        this.tiposLeito.add(tipoLeito);
    }

    public Optional<TipoLeito> findByDescricao(String descricao) {
        for (TipoLeito tl : tiposLeito) {
            if (tl.getDescricao().equalsIgnoreCase(descricao)) {
                return Optional.of(tl);
            }
        }
        return Optional.empty();
    }

    public List<TipoLeito> findAll() {
        return new ArrayList<>(tiposLeito);
    }

    public boolean remove(TipoLeito tipoLeito) {
        return tiposLeito.remove(tipoLeito);
    }
}