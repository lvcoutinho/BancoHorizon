package banco.pix.horizon.AplicaçãoConta;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CriarConta {
    public static void main(String[] args) {

        int quantidadeNumeros = 1;
        int limiteInferior = 10000;
        int limiteSuperior = 99999;

        Set<Integer> numerosUnicos = new HashSet<>();
        Random random = new Random();

        while (numerosUnicos.size() < quantidadeNumeros) {
            int numeroAleatorio = random.nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior;
            numerosUnicos.add(numeroAleatorio);
        }

        for (int numero : numerosUnicos) {
            System.out.println(numero);
        }

//        Conta novaConta = new Conta();
//        novaConta.setPessoa_id(8L);
//        novaConta.setNumero(35778);
//        novaConta.setDigito(6);
//        novaConta.setSaldo(BigDecimal.valueOf(2000.0));
//        novaConta.setTipo_conta(String.valueOf(TipoConta.POUPANÇA.ordinal()));
//        ContaDAO contaDAO = new ContaDAO();
//        Conta contaCriada = contaDAO.createConta(novaConta);
//        System.out.println("Conta criada com ID: " + contaCriada.getId());

    }
}
