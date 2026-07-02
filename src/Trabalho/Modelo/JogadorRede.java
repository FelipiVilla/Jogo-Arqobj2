/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Felipi
 */
public class JogadorRede extends Jogador {
    private PrintWriter out;
    private BufferedReader in;

    public JogadorRede(String nome, Socket socket) throws IOException {
        super(nome);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void enviarMensagem(String mensagem) {
        out.println(mensagem);
    }

    @Override
    public Carta jogarCarta() {
        enviarMensagem("\n--- Vez de " + getNome() + " ---");
        enviarMensagem("Sua mao:");
        
        for (int i = 0; i < getMao().size(); i++) {
            enviarMensagem((i + 1) + " - " + getMao().get(i).toString());
        }

        int escolha = -1;
        boolean escolhaValida = false;

        while (escolhaValida == false) {
            enviarMensagem("Escolha a carta (1 a " + getMao().size() + " ou 0 para pedir truco): ");
            try {
                descartarEntradasForaDeHora();
                String resposta = in.readLine();
                escolha = Integer.parseInt(resposta);
                
                if (escolha == 0) {
                    return null;
                }
                if (escolha >= 1 && escolha <= getMao().size()) {
                    escolhaValida = true;
                } else {
                    enviarMensagem("Opçao invalida. Tente novamente.");
                }
            } catch (Exception e) {
                enviarMensagem("Por favor, digite um numero valido.");
            }
        }

        Carta cartaJogada = removerCarta(escolha);
        enviarMensagem("Voce jogou: " + cartaJogada);
        return cartaJogada;
    }
    
    @Override
    public int responderTruco() {
        int escolha = -1;
        boolean escolhaValida = false;

        while (escolhaValida == false) {
            try {
                descartarEntradasForaDeHora();
                String resposta = in.readLine();
                escolha = Integer.parseInt(resposta);
                
                if (escolha >= 1 && escolha <= 3) {
                    escolhaValida = true;
                } else {
                    enviarMensagem("Opcao invalida. Digite 1 (Aceitar), 2 (Correr) ou 3 (Aumentar).");
                }
            } catch (Exception e) {
                enviarMensagem("Por favor, digite um numero valido.");
            }
        }
        return escolha;
    }
    
    public boolean confirmarPronto() {
        enviarMensagem("\nDigite 1 quando estiver pronto para iniciar a partida:");
        while(true) {
            try {
                descartarEntradasForaDeHora();
                int resposta = Integer.parseInt(in.readLine());
                if(resposta == 1) return true;
                enviarMensagem("Comando invalido. Digite 1 para iniciar.");
            } catch (Exception e) {
                enviarMensagem("Erro. Digite 1 para iniciar.");
            }
        }
    }

    public boolean desejaReiniciar() {
        enviarMensagem("\nDeseja jogar novamente? (1 - Sim, 2 - Nao):");
        while(true) {
            try {
                descartarEntradasForaDeHora();
                int resposta = Integer.parseInt(in.readLine());
                if(resposta == 1) return true;
                if(resposta == 2) return false;
                enviarMensagem("Opcao invalida. Digite 1 (Sim) ou 2 (Nao).");
            } catch (Exception e) {
                enviarMensagem("Por favor, digite um numero valido.");
            }
        }
    }
    
    private void descartarEntradasForaDeHora() { // isso servre pra limpar os buffers antes de dar respostas
        try {
            
            while (in.ready()) {
                in.readLine();
            }
        } catch (IOException e) {

        }
    }
}
