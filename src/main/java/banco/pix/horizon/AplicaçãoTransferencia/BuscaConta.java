package banco.pix.horizon.AplicaçãoTransferencia;

import java.util.HashMap;
import java.util.Map;

public class BuscaConta {

    private Map<String, Long> pessoaParaConta = new HashMap<>();

    public long encontrarNumeroConta(String pessoa){
        Long numeroconta =  pessoaParaConta.get(pessoa);
        if (numeroconta!= null){
            return numeroconta;
        }else{
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
    }
    public static void main(String[] args) {
        System.out.println("Informe o Numero da conta, para buscar:");


    }
}
