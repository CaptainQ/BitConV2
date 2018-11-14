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
    
    static Node[][] inputNodeArray = new Node[16][64];
    static Node[][] outputNodeArray = new Node[36][34];
    
    static int blackBoxArrayWidth;
    static int blackBoxArrayDepth;
    static Node[][] blackBoxArray;
    
    public NeuralNet() {
        
    }
    
    public static void NewNeuralNet() {
        System.out.println("Building a new Neural Net.");
        
        System.out.print("How many nodes wide should the black box be? ");
        blackBoxArrayWidth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        System.out.print("How many nodes deep should the black box be? ");
        blackBoxArrayDepth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        blackBoxArray = new Node[blackBoxArrayWidth][blackBoxArrayDepth];
    }
    
    public static void RecoverNeuralNet() {
        
    }
}