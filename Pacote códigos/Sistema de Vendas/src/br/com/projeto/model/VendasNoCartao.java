/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

/**
 *
 * @author Rafael Godoy
 */
public class VendasNoCartao {

    private int id;
    private int id_requisicao;
    private String emissao;
    private String vencimento;
    private String data_baixa;
    private Planos plano;
    private String situacao;
    private double valorVenda;
    private double desconto;
    private double valorFinal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmissao() {
        return emissao;
    }

    public int getId_requisicao() {
        return id_requisicao;
    }

    public void setId_requisicao(int id_requisicao) {
        this.id_requisicao = id_requisicao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getData_baixa() {
        return data_baixa;
    }

    public void setData_baixa(String data_baixa) {
        this.data_baixa = data_baixa;
    }

    public Planos getPlano() {
        return plano;
    }

    public void setPlano(Planos plano) {
        this.plano = plano;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

}
