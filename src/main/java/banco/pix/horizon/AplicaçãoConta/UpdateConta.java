package banco.pix.horizon.AplicaçãoConta;

import banco.pix.horizon.dao.ContaDAO;
import banco.pix.horizon.model.Conta;

import java.math.BigDecimal;

public class UpdateConta {

    public static void main(String[] args){

        ContaDAO contaDAO = new ContaDAO();
        Conta novaConta = new Conta();
        Conta contaCriada = contaDAO.createConta(novaConta);
        Conta contaExistente = contaDAO.findContaById(contaCriada.getId());
        contaExistente.setSaldo(BigDecimal.valueOf(1500.0));
        contaDAO.updateConta(contaExistente);
        System.out.println("Conta atualizada com sucesso.");
    }
}
