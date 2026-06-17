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
        System.out.println("\n--- Vez de " + getNome() + " ---");
        System.out.println(getNome() + " esta pensando na proxima jogada...");
        
        try {
            Thread.sleep(1500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("A jogada do bot foi interrompida.");
        }

        Carta cartaJogada = removerCarta(1);
        System.out.println(getNome() + " jogou: " + cartaJogada);
        return cartaJogada;
    }
    
}
