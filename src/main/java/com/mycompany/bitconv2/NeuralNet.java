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
    
    
    Node[][] inputNodeArray = new Node[36][34];
    Node[][] outputNodeArray = new Node[16][64];
    String outputRef = "";
    
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
    
    public void generateNewNeuralNet() {
        System.out.println("Building a new Neural Net.");
        
        System.out.print("How many nodes wide should the black box be? ");
        blackBoxArrayWidth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        System.out.print("How many nodes deep should the black box be? ");
        blackBoxArrayDepth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        blackBoxArray = new Node[blackBoxArrayWidth][blackBoxArrayDepth];
        
        //initialize all nodes with random values
        for (int character = 0; character < 34; character++) {
            for (int letter = 0; letter < 36; letter++) {
                inputNodeArray[letter][character] = new Node(true);
            }
        }
        
        for (int n = 0; n < blackBoxArray[0].length; n++) {
            blackBoxArray[n][0] = new Node(true, inputNodeArray, 0);
        }
        
        for (int layer = 1; layer < blackBoxArray.length; layer++) {
            for (int n = 0; n < blackBoxArray[layer].length; n++) {
                blackBoxArray[n][layer] = new Node(true, blackBoxArray[layer-1], layer);
            }
        }
        
        for (int character = 0; character < 64; character++) {
            for (int letter = 0; letter < 16; letter++) {
                outputNodeArray[letter][character] = new Node(true, blackBoxArray[blackBoxArray.length-1], blackBoxArray.length);
            }
        }
    }
    
    public void recoverNeuralNet() {
        
    }
    
    public String runNeuralNet(String pub) {
        for (int i = 0; i < 34; i++) { //set all to zero, just to be safe
            for (int j = 0; j < 36; j++) {
                inputNodeArray[j][i].setBias(0.0);
                inputNodeArray[j][i].setOutput(0.0);
            }
        }
        
        publicAdress = pub;
        //use key to set inputNodeArray[x][y] biases
        for (int i = 0; i < 34; i++) {
            switch (publicAdress.toLowerCase().charAt(i)) {
                case '0': inputNodeArray[0][i].setOutput(1.0);
                        break;
                case '1': inputNodeArray[1][i].setOutput(1.0);
                        break;
                case '2': inputNodeArray[2][i].setOutput(1.0);
                        break;
                case '3': inputNodeArray[3][i].setOutput(1.0);
                        break;
                case '4': inputNodeArray[4][i].setOutput(1.0);
                        break;
                case '5': inputNodeArray[5][i].setOutput(1.0);
                        break;
                case '6': inputNodeArray[6][i].setOutput(1.0);
                        break;
                case '7': inputNodeArray[7][i].setOutput(1.0);
                        break;
                case '8': inputNodeArray[8][i].setOutput(1.0);
                        break;
                case '9': inputNodeArray[9][i].setOutput(1.0);
                        break;
                case 'a': inputNodeArray[10][i].setOutput(1.0);
                        break;
                case 'b': inputNodeArray[11][i].setOutput(1.0);
                        break;
                case 'c': inputNodeArray[12][i].setOutput(1.0);
                        break;
                case 'd': inputNodeArray[13][i].setOutput(1.0);
                        break;
                case 'e': inputNodeArray[14][i].setOutput(1.0);
                        break;
                case 'f': inputNodeArray[15][i].setOutput(1.0);
                        break;
                case 'g': inputNodeArray[16][i].setOutput(1.0);
                        break;
                case 'h': inputNodeArray[17][i].setOutput(1.0);
                        break;
                case 'i': inputNodeArray[18][i].setOutput(1.0);
                        break;
                case 'j': inputNodeArray[19][i].setOutput(1.0);
                        break;
                case 'k': inputNodeArray[20][i].setOutput(1.0);
                        break;
                case 'l': inputNodeArray[21][i].setOutput(1.0);
                        break;
                case 'm': inputNodeArray[22][i].setOutput(1.0);
                        break;
                case 'n': inputNodeArray[23][i].setOutput(1.0);
                        break;
                case 'o': inputNodeArray[24][i].setOutput(1.0);
                        break;
                case 'p': inputNodeArray[25][i].setOutput(1.0);
                        break;
                case 'q': inputNodeArray[26][i].setOutput(1.0);
                        break;
                case 'r': inputNodeArray[27][i].setOutput(1.0);
                        break;
                case 's': inputNodeArray[28][i].setOutput(1.0);
                        break;
                case 't': inputNodeArray[29][i].setOutput(1.0);
                        break;
                case 'u': inputNodeArray[30][i].setOutput(1.0);
                        break;
                case 'v': inputNodeArray[31][i].setOutput(1.0);
                        break;
                case 'w': inputNodeArray[32][i].setOutput(1.0);
                        break;
                case 'x': inputNodeArray[33][i].setOutput(1.0);
                        break;
                case 'y': inputNodeArray[34][i].setOutput(1.0);
                        break;
                case 'z': inputNodeArray[35][i].setOutput(1.0);
                        break;
            }
        }
        //run the net
        for (int bBLayer = 0; bBLayer < blackBoxArray.length; bBLayer++) {
            for (int n = 0; n < blackBoxArray[bBLayer].length; n++) {
                blackBoxArray[n][bBLayer].calculateOutput();
            }
        }
        for (int outputCharacter = 0; outputCharacter < outputNodeArray.length; outputCharacter++) {
            for (int characterOption = 0; characterOption < outputNodeArray[outputCharacter].length; characterOption++) {
                outputNodeArray[characterOption][outputCharacter].calculateOutput();
            }
        }
        //Build code that checks all outputNodeArray nodes and finds the character option for each character slot with the highest output double. Convert these to single string and save it to private key
        int[] privateKeyArray = new int[64];
        
        for (int outputCharacter = 0; outputCharacter < outputNodeArray.length; outputCharacter++) {
            int characterOptionSlot = 0; //should end up between 0 and 15 (inclusive)
            for (int characterOption = 1; characterOption < outputNodeArray[outputCharacter].length; characterOption++) {
                if (outputNodeArray[characterOption][outputCharacter].output > outputNodeArray[characterOption-1][outputCharacter].output) {
                    characterOptionSlot = characterOption;
                }
            }
            privateKeyArray[outputCharacter] = characterOptionSlot;
        }
        //set privateKey = net results
        privateKey = "";
        for (int i = 0; i < privateKeyArray.length; i++) {
            switch (privateKeyArray[i]) {
                case 0: privateKey = privateKey+"0";
                        break;
                case 1: privateKey = privateKey+"1";
                        break;
                case 2: privateKey = privateKey+"2";
                        break;
                case 3: privateKey = privateKey+"3";
                        break;
                case 4: privateKey = privateKey+"4";
                        break;
                case 5: privateKey = privateKey+"5";
                        break;
                case 6: privateKey = privateKey+"6";
                        break;
                case 7: privateKey = privateKey+"7";
                        break;
                case 8: privateKey = privateKey+"8";
                        break;
                case 9: privateKey = privateKey+"9";
                        break;
                case 10: privateKey = privateKey+"a";
                        break;
                case 11: privateKey = privateKey+"b";
                        break;
                case 12: privateKey = privateKey+"c";
                        break;
                case 13: privateKey = privateKey+"d";
                        break;
                case 14: privateKey = privateKey+"e";
                        break;
                case 15: privateKey = privateKey+"f";
                        break;
            }
        }
        return privateKey;
    }
}