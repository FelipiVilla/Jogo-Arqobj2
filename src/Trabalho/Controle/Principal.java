/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Controle;

import java.util.Scanner;

/**
 *
 * @author Felipi
 */
public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Mesa mesa = new Mesa(scanner);

        System.out.println("=== BEM VINDO AO JOGO DE TRUCO ===");

        while (mesa.getJogador1().getPontos() < 12 && mesa.getJogador2().getPontos() < 12) {
            
            System.out.println("\n==================================");
            System.out.println("PLACAR: " + mesa.getJogador1().getNome() + " [" + mesa.getJogador1().getPontos() + "] x [" 
                               + mesa.getJogador2().getPontos() + "] " + mesa.getJogador2().getNome());
            System.out.println("==================================");
            
            mesa.tento(); 
        }

        System.out.println("\n=== FIM DE JOGO ===");
        if (mesa.getJogador1().getPontos() >= 12) {
            System.out.println("PARABENS! " + mesa.getJogador1().getNome() + " venceu a partida!");
        } else {
            System.out.println("O " + mesa.getJogador2().getNome() + " venceu a partida. Tente novamente!");
        }

        scanner.close();
    }
}
