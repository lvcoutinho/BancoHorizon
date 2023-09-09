package banco.pix.horizon;

import banco.pix.horizon.dao.ContaDAO;
import banco.pix.horizon.dao.PessoaDAO;
import banco.pix.horizon.model.Conta;
import banco.pix.horizon.model.Pessoa;
import banco.pix.horizon.model.TipoConta;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuHorizon {

    public static void main(String[] args) {
        Scanner menu = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        PessoaDAO dao = new PessoaDAO();
        Pessoa pessoa = new Pessoa();
        Scanner menutipoConta = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------BEM VINDO A AGENCIA HORIZON-----------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("*******SELECIONE UMA OPERAÇÃO QUE DESEJA REALIZAR**********");
        System.out.println("-----------------------------------------------------------");
        System.out.println("|      Opção 1 - Criar Cadastro      |");
        System.out.println("|      Opção 2 - Já possuo Conta     |");
        System.out.println("|      Opção 3 - Sair                |");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Digite uma opção: ");

        int opcaoMenuPrime  = menu.nextInt();

        switch (opcaoMenuPrime) {
            case 1:
                System.out.println("Informe seu nome para cadastar ao banco: ");
                String nomecadastro = sc.nextLine();
                System.out.println("Informe seu numero de telefone: ");
                String telefonecadastrado = sc.nextLine();
                System.out.println("Informe seu CFP: ");
                String cpfcadastrado  = sc.nextLine();


                pessoa.setNome(nomecadastro);
                pessoa.setTelefone(telefonecadastrado);
                pessoa.setCpf(cpfcadastrado);


                Pessoa pessoaSalva = dao.save(pessoa);

                Conta novaConta = new Conta();
                novaConta.setPessoa_id(pessoaSalva.getId());

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
                novaConta.setNumero(numero);
                }
                System.out.println("Escolha o tipo da conta: ");
                System.out.println("Opção 1 - POUPANÇA");
                System.out.println("Opção 2 - CORRENTE");
                int tipodaconta = menutipoConta.nextInt();

                switch (tipodaconta) {
                    case 1:
                        novaConta.setTipo_conta(String.valueOf(TipoConta.POUPANÇA));
                        novaConta.setDigito(6);
                        break;
                    case 2:
                        novaConta.setTipo_conta(String.valueOf(TipoConta.CORRENTE));
                        novaConta.setDigito(7);
                        break;
                    default:
                }
                novaConta.setSaldo(BigDecimal.valueOf(0));
                ContaDAO contaDAO = new ContaDAO();
                Conta contaCriada = contaDAO.createConta(novaConta);
                System.out.println("Conta criada com ID: " + contaCriada.getId());

                break;
            case 2:

                break;
            case 3:
                System.out.print("\nAté logo!\n");
                menu.close();
                break;

            default:
                System.out.print("\nOpção Inválida!\n");
                break;
        }
    }
}


