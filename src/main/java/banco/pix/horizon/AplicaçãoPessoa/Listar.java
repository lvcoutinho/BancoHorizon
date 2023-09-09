package banco.pix.horizon.AplicaçãoPessoa;

import banco.pix.horizon.dao.PessoaDAO;
import banco.pix.horizon.model.Pessoa;

import java.util.List;

public class Listar {

    public static void main(String[] args){

        PessoaDAO dao =  new PessoaDAO();
        List<Pessoa> pessoas = dao.findAll();

        for (Pessoa pessoa : pessoas){
            System.out.println("ID: " + pessoa.getId());
            System.out.println("NOME: " + pessoa.getNome());
            System.out.println("TELEFONE: " + pessoa.getTelefone());
            System.out.println("CPF: " + pessoa.getCpf());
            System.out.println("====================================================");

        }

    }
}


