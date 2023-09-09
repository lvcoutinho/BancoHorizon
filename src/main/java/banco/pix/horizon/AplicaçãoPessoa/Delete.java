package banco.pix.horizon.AplicaçãoPessoa;

import banco.pix.horizon.dao.PessoaDAO;

public class Delete {

    public static void main(String[] args){

    PessoaDAO dao = new PessoaDAO();
    dao.delete(5L);
}
}