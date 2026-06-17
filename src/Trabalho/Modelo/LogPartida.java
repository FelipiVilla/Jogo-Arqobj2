/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Felipi
 */
public class LogPartida {
    private final Queue<String> filaDeMensagens = new LinkedList<>();
    
    private boolean rodando = true; 
    
    private final Thread threadEscritora;

    public LogPartida() {
        threadEscritora = new Thread(() -> {
            try (FileWriter fw = new FileWriter("historico_truco.txt", true);
                 PrintWriter out = new PrintWriter(fw)) {
                 
                while (true) {
                    String mensagem = null;
                    
                    synchronized (this) {
                        while (rodando == true && filaDeMensagens.isEmpty() == true) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        

                        if (rodando == false && filaDeMensagens.isEmpty() == true) {
                            break; 
                        }
                        
                        mensagem = filaDeMensagens.poll();
                    }
                    
                    
                    if (mensagem != null) {
                        out.println(mensagem);
                        out.flush();
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao gravar log: " + e.getMessage());
            }
        });
        
        threadEscritora.start();
    }

    public synchronized void registrarPonto(Jogador jogador,int truco) {
        String msg = "";
        switch (truco) {
            case 0:
                {
                    msg = "[PONTO] O jogador " + jogador.getNome() + " ganhou: 1 ponto";
                    break;
                }
            case 1:
                {
                    msg = "[PONTO] O jogador " + jogador.getNome() + " ganhou: 3 pontos";
                    break;
                }
            case 2:
                {
                    msg = "[PONTO] O jogador " + jogador.getNome() + " ganhou: 6 pontos";
                    break;
                }
            case 3:
                {
                    msg = "[PONTO] O jogador " + jogador.getNome() + " ganhou: 9 pontos";
                    break;
                }
            case 4:
                {
                    msg = "[PONTO] O jogador " + jogador.getNome() + " ganhou: 12 pontos";
                    break;
                }
            default:
                break;
        }
        
        filaDeMensagens.add(msg);
        notify();
    }

    public synchronized void registrarFimPartida(Jogador vencedor) {
        String msg = "[FIM DE JOGO] O vencedor da partida foi: " + vencedor.getNome() + "\n";
        filaDeMensagens.add(msg);
        notify();
    }

    public synchronized void encerrar() {
        this.rodando = false;
        notify();
    }
}
