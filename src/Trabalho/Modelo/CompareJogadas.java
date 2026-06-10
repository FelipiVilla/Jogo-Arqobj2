/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho.Modelo;

/**
 *
 * @author Felipi
 */
public class CompareJogadas {
    
    
    
    private static boolean isManilha(Carta carta, Carta vira){
        
        if(carta.getValor() == ((vira.getValor() % 10) + 1)){
            return true;
        }else{
            return false;
        }
        
    }
    
    public static int compareCartas(Carta carta1, Carta carta2, Carta vira) {
        boolean isCarta1Manilha = isManilha(carta1, vira);
        boolean isCarta2Manilha = isManilha(carta2, vira);

        if (isCarta1Manilha && isCarta2Manilha) {
            return Integer.compare(carta1.getNaipe().getForca(), carta2.getNaipe().getForca());
        }

        if (isCarta1Manilha) return 1;
        if (isCarta2Manilha) return -1;

        return Integer.compare(carta1.getValor(), carta2.getValor());
    }
    
    public static int compareManilha(Carta carta1, Carta carta2){
        return Integer.compare(carta1.getNaipe().getForca(), carta2.getNaipe().getForca());
    }
}
