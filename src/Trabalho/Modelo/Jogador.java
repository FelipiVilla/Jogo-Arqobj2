/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Felipi
 */
public abstract class Jogador {
    private String nome;
    private int pontos;
    private ArrayList<Carta> mao;
    
    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<>();
        this.pontos = 0;
    }
    
    public abstract void enviarMensagem(String mensagem);
    
    public List<Carta> getMao(){
        return Collections.unmodifiableList(mao);
    }
    
    public void receberMao(List<Carta> entregue){
        
        mao.clear();
        
        mao.addAll(entregue);

    }
    
    public void limparMao() {
        this.mao.clear();
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPontos(int truco) {
        if(truco == 0){
            this.pontos += 1;
        }
        else if(truco > 0 && truco < 5){
            this.pontos += (truco * 3);
        }
        else{
            throw new IllegalArgumentException("O valor nao pode ser abaixo de 0");
        }
    }
    
    public abstract Carta jogarCarta();
    
    protected Carta removerCarta(int x){
        return mao.remove(x - 1);
    }
}
