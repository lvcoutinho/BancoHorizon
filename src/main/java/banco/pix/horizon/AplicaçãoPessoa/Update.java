package banco.pix.horizon.AplicaçãoPessoa;

import banco.pix.horizon.dao.PessoaDAO;
import banco.pix.horizon.model.Pessoa;

import java.util.Optional;

public class Update {

    public static void main(String[] args){

        PessoaDAO dao = new PessoaDAO();
        Optional<Pessoa> pessoaOptional = dao.findById(1L);

        Pessoa pessoa = pessoaOptional.get();

        pessoa.setNome("Guri");
        pessoa.setTelefone("(71)94843738");
        pessoa.setCpf("354.489.648-37");

        dao.update(pessoa);
    }
}
