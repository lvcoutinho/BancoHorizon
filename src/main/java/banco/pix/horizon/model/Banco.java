package banco.pix.horizon.model;

public class Banco {

    private Conta Conta;
    private Pessoa Pessoa;
    private Transferencia Transferencia;

    public Banco(Conta Conta, Pessoa Pessoa,Transferencia Transferencia){
        this.Conta = Conta;
        this.Pessoa = Pessoa;
        this.Transferencia = Transferencia;
    }

    public banco.pix.horizon.model.Conta getConta() {
        return Conta;
    }

    public void setConta(banco.pix.horizon.model.Conta conta) {
        Conta = conta;
    }

    public banco.pix.horizon.model.Pessoa getPessoa() {
        return Pessoa;
    }

    public void setPessoa(banco.pix.horizon.model.Pessoa pessoa) {
        Pessoa = pessoa;
    }

    public banco.pix.horizon.model.Transferencia getTransferencia() {
        return Transferencia;
    }

    public void setTransferencia(banco.pix.horizon.model.Transferencia transferencia) {
        Transferencia = transferencia;
    }
}
