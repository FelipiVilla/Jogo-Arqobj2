/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

import Trabalho.Modelo.Naipe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Felipi
 */
public class Baralho {
    
    private ArrayList<Carta> cartas;

    public Baralho() {
        criarBaralho();
        embraralhar();
    }
    
    public void embraralhar(){
        Collections.shuffle(cartas);
    }
    
    public List<Carta> getBaralho(){
        return Collections.unmodifiableList(cartas);
    }
    
    private void criarBaralho(){
        cartas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            cartas.add(new Carta(i,Naipe.ESPADAS));
        }
        for (int i = 1; i <= 10; i++) {
            cartas.add(new Carta(i,Naipe.COPAS));
        }
        for (int i = 1; i <= 10; i++) {
            cartas.add(new Carta(i,Naipe.PAUS));
        }
        for (int i = 1; i <= 10; i++) {
            cartas.add(new Carta(i,Naipe.OUROS));
        }
    }
    
    
    
}
