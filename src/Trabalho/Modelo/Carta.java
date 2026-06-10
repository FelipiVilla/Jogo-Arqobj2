/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

import Trabalho.Modelo.Naipe;

/**
 *
 * @author Felipi
 */
public class Carta {
    
    private Naipe naipe;
    private int valor; // o valor e feito de maneira crescence conforme forca da carta, a carta 4 = valor 1, 5 = valor 2... 3 = valor 10;

    public Carta(int valor, Naipe naipe){
        setValor(valor);
        setNaipe(naipe);
    }
    
    
    
    public Naipe getNaipe() {
        return naipe;
    }

    private void setNaipe(Naipe naipe) {
        if(naipe != null){
            this.naipe = naipe;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public int getValor() {
        return valor;
    }

    private void setValor(int valor) {
        if(valor >0 && valor <= 10){
            this.valor = valor;
        }else{
            throw new IllegalArgumentException("O valor inserido na carta nao existe");
        }
    }

    @Override
    public String toString() {
        String faceDaCarta;
        
        switch (this.valor) {
            case 1: faceDaCarta = "4"; break;
            case 2: faceDaCarta = "5"; break;
            case 3: faceDaCarta = "6"; break;
            case 4: faceDaCarta = "7"; break;
            case 5: faceDaCarta = "Q"; break; // Dama
            case 6: faceDaCarta = "J"; break; // Valete
            case 7: faceDaCarta = "K"; break; // Rei
            case 8: faceDaCarta = "A"; break; // Ás
            case 9: faceDaCarta = "2"; break;
            case 10: faceDaCarta = "3"; break;
            default: faceDaCarta = "???"; break;
        }
        
        return faceDaCarta + " de " + this.naipe;
    }
    
    
    
}
