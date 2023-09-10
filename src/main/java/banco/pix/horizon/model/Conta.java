package banco.pix.horizon.model;

import java.math.BigDecimal;

public class Conta {

    private Long id;
    private Long pessoa_id;
    private String nomePessoa;
    private int numero;
    private int digito;
    private BigDecimal saldo;
    private String tipo_conta;


    public Conta(Long id, Long pessoa_id, String nomePessoa, int numero, int digito, BigDecimal saldo, String tipo_conta) {
        this.id = id;
        this.pessoa_id = pessoa_id;
        this.nomePessoa = nomePessoa;
        this.numero = numero;
        this.digito = digito;
        this.saldo = saldo;
        this.tipo_conta = tipo_conta;
    }

    public Conta() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPessoa_id() {
        return pessoa_id;
    }

    public void setPessoa_id(Long pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDigito() {
        return digito;
    }

    public void setDigito(int digito) {
        this.digito = digito;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }
}