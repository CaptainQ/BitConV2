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
        
        //initialize all nodes with random values
        for (int character = 0; character < 64; character++) {
            for (int letter = 0; letter < 16; letter++) {
                inputNodeArray[letter][character] = new Node(true);
            }
        }
        
        for (int n = 0; n < blackBoxArray[0].length; n++) {
            blackBoxArray[n][0] = new Node(true, inputNodeArray, 1);
        }
        
        for (int layer = 1; layer < blackBoxArray.length; layer++) {
            for (int n = 0; n < blackBoxArray[layer].length; n++) {
                blackBoxArray[n][layer] = new Node(true, blackBoxArray[layer-1], layer+1);
            }
        }
        
        for (int character = 0; character < 34; character++) {
            for (int letter = 0; letter < 36; letter++) {
                outputNodeArray[letter][character] = new Node(true, blackBoxArray[blackBoxArray.length-1], blackBoxArray.length+1);
            }
        }
    }
    
    public void RecoverNeuralNet() {
        
    }
    
    public String RunNeuralNet(String pub) {
        publicAdress = pub;
        //use key to set inputNodeArray[x][y] biases
        for (int i = 0; i < 64; i++) {
            switch (publicAdress.toLowerCase().charAt(i)) {
                case '0': inputNodeArray[0][i].setBias(1.0);
                        break;
                case '1': inputNodeArray[1][i].setBias(1.0);
                        break;
                case '2': inputNodeArray[2][i].setBias(1.0);
                        break;
                case '3': inputNodeArray[3][i].setBias(1.0);
                        break;
                case '4': inputNodeArray[4][i].setBias(1.0);
                        break;
                case '5': inputNodeArray[5][i].setBias(1.0);
                        break;
                case '6': inputNodeArray[6][i].setBias(1.0);
                        break;
                case '7': inputNodeArray[7][i].setBias(1.0);
                        break;
                case '8': inputNodeArray[8][i].setBias(1.0);
                        break;
                case '9': inputNodeArray[9][i].setBias(1.0);
                        break;
                case 'a': inputNodeArray[10][i].setBias(1.0);
                        break;
                case 'b': inputNodeArray[11][i].setBias(1.0);
                        break;
                case 'c': inputNodeArray[12][i].setBias(1.0);
                        break;
                case 'd': inputNodeArray[13][i].setBias(1.0);
                        break;
                case 'e': inputNodeArray[14][i].setBias(1.0);
                        break;
                case 'f': inputNodeArray[15][i].setBias(1.0);
                        break;
            }
        }
        //run the net
        for (int bBLayer = 0; bBLayer < blackBoxArray.length; bBLayer++) {
            for (int n = 0; n < blackBoxArray[bBLayer].length; n++) {
                blackBoxArray[n][bBLayer].GetOutput();
            }
        }
        for (int outputCharacter = 0; outputCharacter < outputNodeArray.length; outputCharacter++) {
            for (int characterOption = 0; characterOption < outputNodeArray[outputCharacter].length; characterOption++) {
                outputNodeArray[characterOption][outputCharacter].GetOutput();
            }
        }
        //Build code that checks all outputNodeArray nodes and finds the character option for each character slot with the highest output double. Convert these to single string and save it to private key
        //set privateKey = net results
        return privateKey;
    }
}