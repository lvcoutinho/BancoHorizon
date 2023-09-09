package banco.pix.horizon.AplicaçãoConta;

import banco.pix.horizon.dao.ContaDAO;

public class DeleteConta {

    public static void main(String[] args){

        ContaDAO contaDAO = new ContaDAO();
        Long contaParaExcluirId = 6L;
        contaDAO.deleteConta(contaParaExcluirId);
        System.out.println("Conta excluída com sucesso.");
    }
}
