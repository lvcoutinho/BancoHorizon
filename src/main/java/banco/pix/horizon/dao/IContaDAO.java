package banco.pix.horizon.dao;

import banco.pix.horizon.model.Conta;

import java.util.List;

public interface IContaDAO {

    Conta createConta(Conta conta);
    Conta updateConta(Conta conta);
    void deleteConta(Long id);
    List<Conta> findAll();
    Conta findContaById(Long id);
    Conta findContaByNumero(int numero);
}