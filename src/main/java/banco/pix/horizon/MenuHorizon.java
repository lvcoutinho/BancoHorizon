package banco.pix.horizon;

import banco.pix.horizon.dao.ContaDAO;
import banco.pix.horizon.dao.PessoaDAO;
import banco.pix.horizon.model.Conta;
import banco.pix.horizon.model.Pessoa;
import banco.pix.horizon.model.TipoConta;
import banco.pix.horizon.model.Transferencia;
import banco.pix.horizon.dao.TransferenciaDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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

        int opcaoMenuPrime = menu.nextInt();

        switch (opcaoMenuPrime) {
            case 1:
                System.out.println("Informe seu nome para cadastar ao banco: ");
                String nomecadastro = sc.nextLine();
                System.out.println("Informe seu numero de telefone: ");
                String telefonecadastrado = sc.nextLine();
                System.out.println("Informe seu CFP: ");
                String cpfcadastrado = sc.nextLine();


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
                    System.out.println("Conta criada com o digito: " + numero);
                }
                break;
            case 2:
                System.out.println("Informe o número da sua conta cadastrada: ");
                int numeroContaInformado = menu.nextInt();
                System.out.println("Informe o número do id da conta cadastrada: ");
                int numerodoId = menu.nextInt();

                PessoaDAO pessoaDAO = new PessoaDAO();
                Optional<Pessoa> pessoaEncontrada = pessoaDAO.findByNumeroConta(numeroContaInformado);

                if (pessoaEncontrada.isPresent()) {
                    Pessoa pessoaAssociada = pessoaEncontrada.get();
                    System.out.println("Pessoa associada à conta: " + pessoaAssociada.getNome());
                } else {
                    System.out.println("Nenhuma pessoa encontrada para a conta informada.");
                    break;
                }

                System.out.println("-----------------------------------------------------------");
                System.out.println("------------------ BEM VINDO " + pessoaEncontrada.get().getNome() + "-------------------------");
                System.out.println("-----------------------------------------------------------");
                System.out.println("*******SELECIONE UMA OPERAÇÃO QUE DESEJA REALIZAR**********");
                System.out.println("-----------------------------------------------------------");
                System.out.println("|      Opção 1 - Depositar              |");
                System.out.println("|      Opção 2 - Sacar                  |");
                System.out.println("|      Opção 3 - Tranferir              |");
                System.out.println("|      Opção 4 - Sair                   |");
                System.out.println("-----------------------------------------------------------");
                System.out.println("Digite uma opção: ");

                int opcaomenuSecundario = menu.nextInt();
                switch (opcaomenuSecundario) {

                    case 1:
                        System.out.println("Digite o valor que deseja depositar: ");
                        BigDecimal valorDeposito = new BigDecimal(menu.next());

                        if (valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
                            System.out.println("Valor de depósito inválido. O valor deve ser maior que zero.");
                        } else {

                            ContaDAO contaDAO = new ContaDAO();
                            Conta contaDaPessoa = contaDAO.findContaById(Long.valueOf(numerodoId));

                            if (contaDaPessoa != null) {

                                BigDecimal saldoAtual = contaDaPessoa.getSaldo();
                                BigDecimal novoSaldoconta = saldoAtual.add(valorDeposito);

                                contaDaPessoa.setSaldo(novoSaldoconta);
                                contaDAO.updateConta(contaDaPessoa);

                                System.out.println("Depósito de R$" + valorDeposito + " realizado com sucesso!");
                                System.out.println("Novo saldo: R$" + novoSaldoconta);
                            } else {
                                System.out.println("Conta não encontrada.");
                            }
                        }
                        break;

                    case 2:
                        System.out.println("Digite o valor que deseja sacar: ");
                        BigDecimal valorSaque = new BigDecimal(menu.next());

                        if (valorSaque.compareTo(BigDecimal.ZERO) <= 0) {
                            System.out.println("Valor de saque inválido. O valor deve ser maior que zero.");
                        } else {
                            ContaDAO contaDAO = new ContaDAO();
                            Conta contaDaPessoa = contaDAO.findContaById(Long.valueOf(numerodoId));

                            if (contaDaPessoa != null) {
                                BigDecimal saldoAtual = contaDaPessoa.getSaldo();

                                if (saldoAtual.compareTo(valorSaque) >= 0) {
                                    BigDecimal novoSaldoConta = saldoAtual.subtract(valorSaque);

                                    contaDaPessoa.setSaldo(novoSaldoConta);
                                    contaDAO.updateConta(contaDaPessoa);

                                    System.out.println("Saque de R$" + valorSaque + " realizado com sucesso!");
                                    System.out.println("Novo saldo: R$" + novoSaldoConta);
                                } else {
                                    System.out.println("Saldo insuficiente para o saque.");
                                }
                            } else {
                                System.out.println("Conta não encontrada.");
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Digite o número da conta de Destino da transferência: ");
                        int numeroContaDestino = menu.nextInt();
                        System.out.println("Digite o ID da conta de Destino da transferência: ");
                        int idContaDestino = menu.nextInt();

                        System.out.println("Informe o dia que deseja realizar a transferência:");
                        int diaTransferencia = menu.nextInt();

                        Optional<Pessoa> pessoaEncontradaDestino = pessoaDAO.findByNumeroConta(numeroContaDestino);

                        if (pessoaEncontradaDestino.isPresent()) {
                            Pessoa pessoaAssociada2 = pessoaEncontradaDestino.get();
                            System.out.println("Pessoa associada à conta de destino: " + pessoaAssociada2.getNome());
                        } else {
                            System.out.println("Nenhuma pessoa encontrada para a conta de destino informada.");
                            break;
                        }

                        System.out.println(pessoaEncontrada.get().getNome() + " está realizando uma transferência para " + pessoaEncontradaDestino.get().getNome());
                        System.out.println("Digite o valor a ser transferido: ");
                        BigDecimal valorTransferencia = sc.nextBigDecimal();

                        if (valorTransferencia.compareTo(BigDecimal.ZERO) <= 0) {
                            System.out.println("Valor de transferência inválido. O valor deve ser maior que zero.");
                        } else {
                            ContaDAO contaDAO = new ContaDAO();
                            Conta contaOrigem = contaDAO.findContaById(Long.valueOf(numerodoId));
                            Conta contaDestino = contaDAO.findContaById(Long.valueOf(idContaDestino));

                            if (contaOrigem == null || contaDestino == null) {
                                System.out.println("Conta de origem ou destino não encontrada.");
                            } else if (contaOrigem.getSaldo().compareTo(valorTransferencia) < 0) {
                                System.out.println("Saldo insuficiente para realizar a transferência.");
                            } else {
                                Transferencia transferencia = new Transferencia();
                                transferencia.setConta_origem_id(contaOrigem.getId());
                                transferencia.setConta_destino_id(contaDestino.getId());
                                transferencia.setValor(valorTransferencia);
                                transferencia.setData(LocalDate.of(2023,9,diaTransferencia));

                                TransferenciaDAO transferenciaDAO = new TransferenciaDAO();
                                transferenciaDAO.save(transferencia);

                                BigDecimal novoSaldoOrigem = contaOrigem.getSaldo().subtract(valorTransferencia);
                                BigDecimal novoSaldoDestino = contaDestino.getSaldo().add(valorTransferencia);

                                contaOrigem.setSaldo(novoSaldoOrigem);
                                contaDestino.setSaldo(novoSaldoDestino);

                                contaDAO.updateConta(contaOrigem);
                                contaDAO.updateConta(contaDestino);

                                System.out.println("Transferência de R$" + valorTransferencia + " realizada com sucesso!");
                                System.out.println("Novo saldo da conta de origem: R$" + novoSaldoOrigem);
                                System.out.println("Novo saldo da conta de destino: R$" + novoSaldoDestino);
                            }
                        }
                        break;

                    case 4:
                        System.out.print("\nAté logo!\n");
                        menu.close();
                        break;

                    default:
                        System.out.print("\nOpção Inválida!\n");
                        break;
                }
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




