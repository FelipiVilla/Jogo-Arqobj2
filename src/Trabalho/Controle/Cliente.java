/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Felipi
 */
public class Cliente {
    public static void main(String[] args) {
        try {
            int porta = 12345;
            
            //trocar o IP dessa linha pelo ip da maquina no dia de apresentar
            InetAddress endereco = InetAddress.getByName("192.168.0.48"); 
            
            Socket socket = new Socket(endereco, porta);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Conectado ao servidor de Truco!");
            Thread leitorServidor = new Thread(() -> {
                try {
                    String mensagem;
                    while ((mensagem = in.readLine()) != null) {
                        System.out.println(mensagem);
                    }
                } catch (IOException e) {
                    System.out.println("Conexao encerrada.");
                }
            });
            leitorServidor.start();

            while (true) {
                String comando = scanner.nextLine();
                out.println(comando);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
