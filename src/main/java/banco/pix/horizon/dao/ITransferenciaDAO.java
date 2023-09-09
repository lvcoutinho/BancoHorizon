package banco.pix.horizon.dao;

import banco.pix.horizon.model.Transferencia;

public interface ITransferenciaDAO {

    double consultarSaldo(Transferencia transferencia);
    boolean depositar(Transferencia transferencia);
    boolean transferir(Transferencia transferencia);
    boolean sacar(Transferencia transferencia);
}
