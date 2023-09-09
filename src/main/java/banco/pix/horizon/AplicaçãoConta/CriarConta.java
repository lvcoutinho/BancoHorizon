package banco.pix.horizon.AplicaçãoConta;

import banco.pix.horizon.dao.ContaDAO;
import banco.pix.horizon.model.Conta;
import banco.pix.horizon.model.TipoConta;

import java.math.BigDecimal;

public class CriarConta {
    public static void main(String[] args) {

        Conta novaConta = new Conta();
        novaConta.setPessoa_id(8L);
        novaConta.setNumero(35778);
        novaConta.setDigito(6);
        novaConta.setSaldo(BigDecimal.valueOf(2000.0));
        novaConta.setTipo_conta(String.valueOf(TipoConta.POUPANÇA.ordinal()));
        ContaDAO contaDAO = new ContaDAO();
        Conta contaCriada = contaDAO.createConta(novaConta);
        System.out.println("Conta criada com ID: " + contaCriada.getId());


    }
}
