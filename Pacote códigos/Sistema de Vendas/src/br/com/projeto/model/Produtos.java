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
public class Produtos {

    private int id;
    private String nome;
    private String tipo;
    private String ncm;
    private String cod_barras;
    private int quantidade;
    private String atualizacao;
    private double valor_compra;
    private double valor_venda;
    private String observacoes;
    private double margem_lucro;
    private double desconto_vista;
    private double valor_vista;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(String atualizacao) {
        this.atualizacao = atualizacao;
    }

    public double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(double valor_compra) {
        this.valor_compra = valor_compra;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public double getMargem_lucro() {
        return margem_lucro;
    }

    public void setMargem_lucro(double margem_lucro) {
        this.margem_lucro = margem_lucro;
    }

    public double getDesconto_vista() {
        return desconto_vista;
    }

    public void setDesconto_vista(double desconto_vista) {
        this.desconto_vista = desconto_vista;
    }

    public double getValor_vista() {
        return valor_vista;
    }

    public void setValor_vista(double valor_vista) {
        this.valor_vista = valor_vista;
    }

}
