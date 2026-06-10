/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

/**
 *
 * @author Felipi
 */
public enum Naipe {
    OUROS(1),
    ESPADAS(2),
    COPAS(3),
    PAUS(4);
    
    private final int forca;
    
    Naipe(int forca){
        this.forca = forca;
    }
    
    public int getForca(){
        return forca;
    }
}
