/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Controle;

import Trabalho.Modelo.Carta;
import Trabalho.Modelo.Baralho;
import Trabalho.Modelo.Bot;
import Trabalho.Modelo.CompareJogadas;
import Trabalho.Modelo.Jogador;
import Trabalho.Modelo.JogadorReal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Felipi
 */
public class Mesa {
    private Scanner scanner;
    private Baralho baralho = new Baralho();
    private Carta vira;
    private Jogador jogador1;
    private Jogador jogador2 = new Bot();
    private Carta jogada1;
    private Carta jogada2;
    private int truco = 0; // 0 = sem truco, 1 = truco, 2 = 6, 3 = 9, 4 = 12

    public Mesa(Scanner scanner) {
        this.scanner = scanner;
        jogador1  = new JogadorReal("EU", scanner);
    }
    
    public Jogador getJogador1(){
        return jogador1;
    }
    public Jogador getJogador2(){
        return jogador2;
    }
    public Carta getVira(){
        return vira;
    }
    
    public void entregarCartas(){
        
        List<Carta> deck = baralho.getBaralho();
        
        List<Carta> cartasJogador1 = deck.subList(0, 3);
        List<Carta> cartasJogador2 = deck.subList(3, 6);
        
        jogador1.receberMao(cartasJogador1);
        jogador2.receberMao(cartasJogador2);
        
        vira = deck.get(6);
        
    }
    

    public int rodada(){
        jogada1 = jogador1.jogarCarta();
        
        
        if (jogada1 == null) {
            System.out.println("\n!!! " + jogador1.getNome() + " PEDIU TRUCO !!!");
            
           
            System.out.println(jogador2.getNome() + " aceitou o Truco!"); 
            this.truco = 1; 
            
            System.out.println("Agora jogue sua carta de verdade:");
            jogada1 = jogador1.jogarCarta();
            
            while (jogada1 == null) {
                System.out.println("Voce ja pediu truco! Escolha uma carta valida.");
                jogada1 = jogador1.jogarCarta();
            }
        }
        
        
        jogada2 = jogador2.jogarCarta();
        if(CompareJogadas.compareCartas(jogada1,jogada2,vira) == 0){
            return (CompareJogadas.compareManilha(jogada1, jogada2) * 2);
        }else{
            return CompareJogadas.compareCartas(jogada1,jogada2,vira);
        }
        
        
        
    }
    
    public void tento(){
        int rodadasJogador1 = 0;
        int rodadasJogador2 = 0;
        int rodadaFinal = 0;
        int primeiraRodada = 0; // 1 para jogador 1 vencer -1 para jogador 2 vencer
        //rodada 1
        
        baralho.embraralhar();
        entregarCartas();
        
        System.out.println("\n--- NOVA MAO ---");
        System.out.println("A carta Vira e: " + vira);
        
        for (int i = 0; i < 2; i++) {
            switch (this.rodada()) {
                case 1: rodadasJogador1++;   break;
                case 2: 
                    rodadaFinal++;
                    break;
                
                case -1: rodadasJogador2++;   break;
                case -2: 
                    rodadaFinal++;
                    break;
                
            default:
                throw new AssertionError();
            }
        }
        
        if(rodadaFinal == 0){
            if(rodadasJogador2 == 2){
                jogador2.adicionarPontos(truco);     
                return;
            }
            if(rodadasJogador1 == 2){
                jogador1.adicionarPontos(truco);
                return;
            }
        }
        
        
        //rodada 3 caso necessario
        switch (this.rodada()) {
            case 1: 

                    jogador1.adicionarPontos(truco);

                
                
                break;
            case 2: 
                

                    jogador1.adicionarPontos(truco);

                
                break;
                
            case -1: 
                

                    jogador2.adicionarPontos(truco);     

                
                break;
            case -2: 

                    jogador2.adicionarPontos(truco);     

                
                break;
                
            default:
                throw new AssertionError();
        }
    }
}
