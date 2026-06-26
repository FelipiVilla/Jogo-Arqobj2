/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Controle;

import Trabalho.Modelo.JogadorRede;
import Trabalho.Modelo.LogPartida;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Felipi
 */
public class Servidor {
    public static void main(String[] args) {
        
        int porta = 12345;
        int backlog = 2;
        
        try{
            // Define o IP exato onde o servidor vai hospedar o jogo
            //provavelmente precisa trocar quando for testar o jogod na classe de aula
            InetAddress endereco = InetAddress.getByName("192.168.0.48");
            
            try (ServerSocket servidor = new ServerSocket(porta, backlog, endereco)) {
                
                System.out.println("Servidor iniciado no IP " + endereco.getHostAddress() + " na porta " + porta);
                System.out.println("Aguardando jogadores...");

                Socket socketJ1 = servidor.accept();
                System.out.println("Jogador 1 conectado a partir do IP: " + socketJ1.getInetAddress().getHostAddress());
                JogadorRede j1 = new JogadorRede("Jogador 1", socketJ1);

                Socket socketJ2 = servidor.accept();
                System.out.println("Jogador 2 conectado a partir do IP: " + socketJ2.getInetAddress().getHostAddress());
                JogadorRede j2 = new JogadorRede("Jogador 2", socketJ2);

            LogPartida log = new LogPartida();
            Mesa mesa = new Mesa(j1, j2, log);

            j1.enviarMensagem("O jogo vai comecar");
            j2.enviarMensagem("O jogo vai comecar");

            while (j1.getPontos() < 12 && j2.getPontos() < 12) {
                mesa.tento();
            }
            
            log.encerrar();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
