package banco.pix.horizon.dao;

import banco.pix.horizon.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface IPessoaDAO {

    Pessoa save(Pessoa pessoa);
    Pessoa update(Pessoa pessoao);
    void delete(Long id);
    List<Pessoa> findAll();
    Optional<Pessoa> findById(Long id);
    Optional<Pessoa> findByNumeroConta(int numero);
}
