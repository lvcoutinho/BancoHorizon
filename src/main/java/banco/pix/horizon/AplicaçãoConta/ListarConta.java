package banco.pix.horizon.AplicaçãoConta;

import banco.pix.horizon.dao.ContaDAO;
import banco.pix.horizon.model.Conta;

import java.util.List;

public class ListarConta {

    public static void main(String[] args) {

        ContaDAO dao = new ContaDAO();
        List<Conta> contas = dao.findAll();

        for (Conta conta : contas) {
            System.out.println("ID: " + conta.getId());
            System.out.println("PESSOA: " + conta.getNomePessoa());
            System.out.println("NUMERO: " + conta.getNumero());
            System.out.println("DIGITO: " + conta.getDigito());
            System.out.println("SALDO: " + conta.getSaldo());

            String tipoConta = conta.getTipo_conta();

            System.out.println("TIPO DE CONTA: " + tipoConta);
            System.out.println("====================================================");
        }
    }
}
