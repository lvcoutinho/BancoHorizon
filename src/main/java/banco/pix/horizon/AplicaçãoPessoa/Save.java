package banco.pix.horizon.AplicaçãoPessoa;

import banco.pix.horizon.dao.PessoaDAO;
import banco.pix.horizon.model.Pessoa;


public class Save {

    public static void main(String[] args){


        PessoaDAO dao = new PessoaDAO();
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Momi");
        pessoa.setTelefone("(71)994738290");
        pessoa.setCpf("068.777.777-28");


        Pessoa pessoaSalva = dao.save(pessoa);

//        System.out.println("Pessoa salva com ID: " + pessoaSalva.getId());

//        Scanner sc = new Scanner(System.in);

//        System.out.println("Informe o nome: ");
//        String nome = sc.nextLine();
//        System.out.println("Informe o telefone: ");
//        String telefone = sc.nextLine();
//        System.out.println("Informe o cpf: ");
//        String cpf = sc.nextLine();


   }
}