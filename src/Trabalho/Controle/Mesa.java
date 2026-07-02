/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Controle;

import Trabalho.Modelo.Carta;
import Trabalho.Modelo.Baralho;
import Trabalho.Modelo.CompareJogadas;
import Trabalho.Modelo.Jogador;
import Trabalho.Modelo.LogPartida;
import java.util.List;

/**
 *
 * @author Felipi
 */
public class Mesa {
    private LogPartida log;
    private Baralho baralho = new Baralho();
    private Carta vira;
    private Jogador jogador1;
    private Jogador jogador2;
    private Carta jogada1;
    private Carta jogada2;
    private int truco = 0; // 0 = sem truco, 1 = truco, 2 = 6, 3 = 9, 4 = 12
    private Jogador quemPodePedirTruco = null; // null sginifica que ambos podem pedir truco

    public Mesa(Jogador jogador1, Jogador jogador2, LogPartida log) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.log = log;
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
    

    public int rodada() {
        int resultado;
        jogador1.enviarMensagem("\nA carta Vira e: " + vira);
        jogada1 = jogador1.jogarCarta();
        
        if (jogada1 == null) {
            
            if (quemPodePedirTruco != null && quemPodePedirTruco != jogador1) {
                jogador1.enviarMensagem("Voce nao tem o direito de pedir aumento agora! Jogue uma carta.");

                // Força ele a jogar uma carta
                jogada1 = jogador1.jogarCarta();
                while (jogada1 == null) {
                    jogador1.enviarMensagem("Pedido invalido. Jogue uma carta.");
                    jogada1 = jogador1.jogarCarta();
                }
            }
            else{
                int valorTruco = negociarTruco(jogador1, jogador2);
                if (valorTruco % 2 == 0 && valorTruco >= 0){
                    return 99;//jogador 2 correu
                } else if(valorTruco >= 0){
                    return -99; // jogador 1 pediu truco inicialmente porem acabou correndo apos 6 ou 12
                }

                jogador1.enviarMensagem("Agora jogue sua carta de verdade:");
                jogador1.enviarMensagem("\nA carta Vira e: " + vira);
                jogada1 = jogador1.jogarCarta();
                while (jogada1 == null) {
                    jogador1.enviarMensagem("Limite maximo atingido ou pedido invalido. Jogue uma carta.");
                    jogada1 = jogador1.jogarCarta();
                }
            }
            
        }
        
        
        jogador2.enviarMensagem("\nA carta Vira e: " + vira);
        jogador2.enviarMensagem(jogador1.getNome() + " jogou a carta: " + jogada1);
        
        jogada2 = jogador2.jogarCarta();
        
        if (jogada2 == null) {
            
            if (quemPodePedirTruco != null && quemPodePedirTruco != jogador2) {
                jogador2.enviarMensagem("Voce nao tem o direito de pedir aumento agora! Jogue uma carta.");

                // Força ele a jogar uma carta
                jogada2 = jogador2.jogarCarta();
                while (jogada2 == null) {
                    jogador2.enviarMensagem("Pedido invalido. Jogue uma carta.");
                    jogada2 = jogador2.jogarCarta();
                }
            }
            else{
                int valorTruco = negociarTruco(jogador2, jogador1);
                if (valorTruco % 2 == 0 && valorTruco >= 0){
                    return -99;// jgoador 1 correu
                }else if(valorTruco >= 0){
                    return 99; // jogador 2 pediu truco inicialmente porem acabou correndo apos 6 ou 12
                }

                jogador2.enviarMensagem("Agora jogue sua carta de verdade:");
                jogador1.enviarMensagem("\nA carta Vira e: " + vira);
                jogada2 = jogador2.jogarCarta();
                while (jogada2 == null) {
                    jogador2.enviarMensagem("Limite maximo atingido ou pedido invalido. Jogue uma carta.");
                    jogada2 = jogador2.jogarCarta();
                }
            }
            
        }
        jogador1.enviarMensagem(jogador2.getNome() + " jogou a carta: " + jogada2);
        
        if(CompareJogadas.compareCartas(jogada1, jogada2, vira) == 0){
            resultado = (CompareJogadas.compareManilha(jogada1, jogada2) * 2);
        } else {
            resultado = CompareJogadas.compareCartas(jogada1, jogada2, vira);
        }
        
        if( resultado > 0){
            jogador1.enviarMensagem("\n" + jogador1.getNome() + "venceu essa rodada");
            jogador2.enviarMensagem("\n" + jogador1.getNome() + "venceu essa rodada");
        }
        else if(resultado < 0){
            
            jogador1.enviarMensagem("\n" + jogador2.getNome() + "venceu essa rodada");
            jogador2.enviarMensagem("\n" + jogador2.getNome() + "venceu essa rodada");
        
        }
        else{
            jogador1.enviarMensagem("essa rodada empatou");
            jogador2.enviarMensagem("essa rodada empatou");
        }
        
        return resultado;
    }
    
    public void tento(){
        this.truco = 0;
        this.quemPodePedirTruco = null;
        int rodadasJogador1 = 0;
        int rodadasJogador2 = 0;
        int rodadaFinal = 0;
        int primeiraRodada = 0; // 1 para jogador 1 vencer -1 para jogador 2 vencer
        //rodada 1
        
        baralho.embraralhar();
        entregarCartas();
        

        
        for (int i = 0; i < 2; i++) {
            int resultadoRodada = this.rodada();
            
            if (resultadoRodada == 99) { // Jogador 2 correu
                jogador1.adicionarPontos(this.truco);   
                log.registrarPonto(jogador1, this.truco);
                exibirPlacar();
                return;
            } else if (resultadoRodada == -99) { // Jogador 1 correu
                jogador2.adicionarPontos(this.truco);   
                log.registrarPonto(jogador2, this.truco);
                exibirPlacar();
                return; 
            }

            switch (resultadoRodada) {
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
        
        
        // checa se um jogador ganhou as 2 primeiras rodadas
        
        if(rodadaFinal == 0){
            if(rodadasJogador2 == 2){
                jogador2.adicionarPontos(truco);   
                log.registrarPonto(jogador2,truco);
                exibirPlacar();
                return;
            }
            else if(rodadasJogador1 == 2){
                jogador1.adicionarPontos(truco);
                log.registrarPonto(jogador1, truco);
                exibirPlacar();
                return;
            }
        }
        
        
        
        //rodada 3 caso necessario
        
        
        
        int resultadoRodada = this.rodada();
        
        if (resultadoRodada == 99) { // Jogador 2 correu
                jogador1.adicionarPontos(this.truco);   
                log.registrarPonto(jogador1, this.truco);
                exibirPlacar();
                return;
            } else if (resultadoRodada == -99) { // Jogador 1 correu
                jogador2.adicionarPontos(this.truco);   
                log.registrarPonto(jogador2, this.truco);
                exibirPlacar();
                return; 
            }
        
        switch (resultadoRodada) {
            case 1: 

                    jogador1.adicionarPontos(truco);
                    log.registrarPonto(jogador1, truco);
                    exibirPlacar();
                
                
                break;
            case 2: 
                

                    jogador1.adicionarPontos(truco);
                    log.registrarPonto(jogador1, truco);
                    exibirPlacar();
                break;
                
            case -1: 
                

                    jogador2.adicionarPontos(truco);     
                    log.registrarPonto(jogador2, truco);
                    exibirPlacar();
                break;
            case -2: 

                    jogador2.adicionarPontos(truco);     
                    log.registrarPonto(jogador2, truco);
                    exibirPlacar();
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    
    public int negociarTruco(Jogador pedinte, Jogador oponente) {
        String[] nomesTruco = {"", "TRUCO", "SEIS", "NOVE", "DOZE"};
        int valor = 0;
        while (this.truco < 4) {
            int proximoNivel = this.truco + 1;
            
            pedinte.enviarMensagem("\nVoce pediu " + nomesTruco[proximoNivel]);
            oponente.enviarMensagem("\n" + pedinte.getNome() + " pediu " + nomesTruco[proximoNivel]);
            oponente.enviarMensagem("O que voce deseja fazer");
            oponente.enviarMensagem("1 - Aceitar");
            oponente.enviarMensagem("2 - Correr");
            
            if (proximoNivel < 4) {
                oponente.enviarMensagem("3 - Pedir " + nomesTruco[proximoNivel + 1]);
            }
            
            int resposta = oponente.responderTruco(); 
            
            if (resposta == 1) { // aceitou
                
                this.truco = proximoNivel;
                pedinte.enviarMensagem(oponente.getNome() + " aceitou");
                oponente.enviarMensagem("Voce aceitou");
                this.quemPodePedirTruco = oponente;
                return -1;
                
            } else if (resposta == 2) { // crreu
                
                pedinte.enviarMensagem(oponente.getNome() + " correu");
                oponente.enviarMensagem("Voce correu");
                return valor;
                
            } else if (resposta == 3 && proximoNivel < 4) {  // aumentou
               
                this.truco = proximoNivel;
                Jogador temp = pedinte;
                pedinte = oponente;
                oponente = temp;
                valor++;
                
            } else {
                oponente.enviarMensagem("Opcao invalida.");
            }
        }
        return -1;
    }
    
    private void exibirPlacar() {
        String placar = "     PLACAR ATUAL     \n"+
                        jogador1.getNome() + ": " + jogador1.getPontos() + " pontos\n" +
                        jogador2.getNome() + ": " + jogador2.getPontos() + " pontos\n\n";

        
        jogador1.enviarMensagem(placar);
        jogador2.enviarMensagem(placar);
    }
}
