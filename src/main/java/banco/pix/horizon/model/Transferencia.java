package banco.pix.horizon.model;

import java.time.LocalDate;

public class Transferencia {

    private Long id;
    private Long conta_origem_id;
    private Long conta_destino_id;
    private double valor;
    private LocalDate data;


public Transferencia(Long id, Long conta_origem_id, Long conta_destino_id, double valor, LocalDate data){
    this.id = id;
    this.conta_origem_id = conta_origem_id;
    this.conta_destino_id = conta_destino_id;
    this.valor = valor;
    this.data = data;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConta_origem_id() {
        return conta_origem_id;
    }

    public void setConta_origem_id(Long conta_origem_id) {
        this.conta_origem_id = conta_origem_id;
    }

    public Long getConta_destino_id() {
        return conta_destino_id;
    }

    public void setConta_destino_id(Long conta_destino_id) {
        this.conta_destino_id = conta_destino_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}