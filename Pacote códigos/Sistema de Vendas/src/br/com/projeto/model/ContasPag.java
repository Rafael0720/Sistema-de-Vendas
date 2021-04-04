/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Rafael Godoy
 */
public class ContasPag {

    private String id;
    private int contador;
    private int requisicao;
    private String sequencia;
    private String situacao;
    private String emissao;
    private String vencimento;
    private String baixa;
    private Fornecedores fornecedor;
    private Planos plano;
    private String origem;
    private double valor_compra;
    private double juros_porc;
    private double juros;
    private double desconto_porc;
    private double desconto;
    private double valor_final;
    private String observacoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(int requisicao) {
        this.requisicao = requisicao;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getEmissao() {
        return emissao;
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

    public String getBaixa() {
        return baixa;
    }

    public void setBaixa(String baixa) {
        this.baixa = baixa;
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Planos getPlano() {
        return plano;
    }

    public void setPlano(Planos plano) {
        this.plano = plano;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(double valor_compra) {
        this.valor_compra = valor_compra;
    }

    public double getJuros_porc() {
        return juros_porc;
    }

    public void setJuros_porc(double juros_porc) {
        this.juros_porc = juros_porc;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getDesconto_porc() {
        return desconto_porc;
    }

    public void setDesconto_porc(double desconto_porc) {
        this.desconto_porc = desconto_porc;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValor_final() {
        return valor_final;
    }

    public void setValor_final(double valor_final) {
        this.valor_final = valor_final;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}
