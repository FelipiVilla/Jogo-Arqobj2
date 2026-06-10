/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Felipi
 */
public class Bot extends Jogador {

    
    
    public Bot() {
        super("bot");
    }

    

    
    
    @Override
    public Carta jogarCarta() {
        Carta cartaJogada = removerCarta(1);
        System.out.println(getNome() + " jogou: " + cartaJogada);
        return cartaJogada;
    }
    
}
