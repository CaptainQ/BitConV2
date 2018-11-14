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
    
    Node[][] inputNodeArray = new Node[16][64];
    Node[][] outputNodeArray = new Node[36][34];
    
    int blackBoxArrayWidth;
    int blackBoxArrayDepth;
    Node[][] blackBoxArray;
    
    private NeuralNet() {
        
    }
    
    private static NeuralNet _net; //names single instance of the class
    
    public static NeuralNet getNeuralNet() //public method that returns the single instance of the Neural Net so that other objects can grab nodes
    {
        if (_net == null)
            _net = new NeuralNet();
        return _net;
    }
    
    public void NewNeuralNet() {
        System.out.println("Building a new Neural Net.");
        
        System.out.print("How many nodes wide should the black box be? ");
        blackBoxArrayWidth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        System.out.print("How many nodes deep should the black box be? ");
        blackBoxArrayDepth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        blackBoxArray = new Node[blackBoxArrayWidth][blackBoxArrayDepth];
    }
    
    public void RecoverNeuralNet() {
        
    }
}