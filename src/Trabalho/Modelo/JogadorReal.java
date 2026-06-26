/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

import java.util.Scanner;

/**
 *
 * @author Felipi
 */
public class JogadorReal extends Jogador {

    private Scanner scanner;
    
    public JogadorReal(String nome, Scanner scanner) {
        super(nome);
        this.scanner = scanner;
    }

    @Override
    public void enviarMensagem(String mensagem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    @Override
    public Carta jogarCarta() {
    System.out.println("\n--- Vez de " + getNome() + " ---");
        System.out.println("Sua mao:");
        
        for (int i = 0; i < getMao().size(); i++) {
            System.out.println((i + 1) + " - " + getMao().get(i).toString());
        }

        int escolha = -1;
        boolean escolhaValida = false;

        
        while (escolhaValida == false) {
            System.out.print("Escolha a carta (1 a " + getMao().size() + " ou 0 para pedir truco): ");
            try {
                escolha = Integer.parseInt(scanner.nextLine());
                if(escolha == 0){
                    return null;
                }
                if (escolha >= 1 && escolha <= getMao().size()) {
                    escolhaValida = true;
                } else {
                    System.out.println("Opçao invalida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido.");
            }
        }

        Carta cartaJogada = removerCarta(escolha);
        System.out.println(getNome() + " jogou: " + cartaJogada);
        return cartaJogada;
    }
    
}
