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

        while (!escolhaValida) {
            enviarMensagem("Escolha a carta (1 a " + getMao().size() + " ou 0 para pedir truco): ");
            try {
                String resposta = in.readLine();
                escolha = Integer.parseInt(resposta);
                
                if (escolha == 0) {
                    return null;
                }
                if (escolha >= 1 && escolha <= getMao().size()) {
                    escolhaValida = true;
                } else {
                    enviarMensagem("Opção invalida. Tente novamente.");
                }
            } catch (Exception e) {
                enviarMensagem("Por favor, digite um numero valido.");
            }
        }

        Carta cartaJogada = removerCarta(escolha);
        enviarMensagem("Você jogou: " + cartaJogada);
        return cartaJogada;
    }
}
