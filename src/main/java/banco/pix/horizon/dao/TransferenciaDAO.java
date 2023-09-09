package banco.pix.horizon.dao;

import banco.pix.horizon.model.Transferencia;

public class TransferenciaDAO implements ITransferenciaDAO{
    @Override
    public double consultarSaldo(Transferencia transferencia) {
        return 0;
    }

    @Override
    public boolean depositar(Transferencia transferencia) {
        return false;
    }

    @Override
    public boolean transferir(Transferencia transferencia) {
        return false;
    }

    @Override
    public boolean sacar(Transferencia transferencia) {
        return false;
    }
}
