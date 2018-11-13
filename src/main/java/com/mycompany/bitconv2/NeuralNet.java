/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bitconv2;

/**
 *
 * @author allkn
 */
public class NeuralNet {
    String privateKey;
    String publicAdress;
    
    String[] inputNodeArray;
    String[] outputNodeArray;
    String[][] blackBoxArray;
    
    static int blackBoxArraywidth;
    static int blackBoxArraydepth;
    
    public NeuralNet() {
        
    }
    
    public static void NewNeuralNet() {
        System.out.println("Building a new Neural Net.");
        
        System.out.print("How many nodes wide should the black box be? ");
        blackBoxArraywidth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        System.out.print("How many nodes deep should the black box be? ");
        blackBoxArraydepth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        
    }
    
    public static void ReconstructNeuralNet() {
        
    }
}
