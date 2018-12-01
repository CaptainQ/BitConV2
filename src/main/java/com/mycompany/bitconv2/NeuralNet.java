/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bitconv2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author allkn
 */
public class NeuralNet {
    String privateKey;
    String publicAdress;
    
    
    Node[][] inputNodeArray = new Node[34][36];
    Node[][] outputNodeArray = new Node[64][16];
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
        
        System.out.print("How many nodes wide should the black box be? (1100 should be fine) ");
        blackBoxArrayWidth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        System.out.print("How many nodes deep should the black box be? (After 20 it starts using a lot of memory) ");
        blackBoxArrayDepth = Integer.parseInt(Main.user_input.next().toLowerCase());
        System.out.println("");
        
        blackBoxArray = new Node[blackBoxArrayDepth][blackBoxArrayWidth];
        
        //initialize all nodes with random values
        for (int character = 0; character < inputNodeArray.length; character++) {
            for (int letter = 0; letter < inputNodeArray[character].length; letter++) {
                inputNodeArray[character][letter] = new Node(true);
            }
        }
        
        for (int n = 0; n < blackBoxArray[0].length; n++) {
            blackBoxArray[0][n] = new Node(true, inputNodeArray, 0);
        }
        
        for (int layer = 1; layer < blackBoxArray.length; layer++) {
            for (int n = 0; n < blackBoxArray[layer].length; n++) {
                blackBoxArray[layer][n] = new Node(true, blackBoxArray[layer-1], layer);
            }
        }
        
        for (int character = 0; character < outputNodeArray.length; character++) {
            for (int letter = 0; letter < outputNodeArray[character].length; letter++) {
                outputNodeArray[character][letter] = new Node(true, blackBoxArray[blackBoxArray.length-1], blackBoxArray.length);
            }
        }
    }
    
    public void saveNeuralNet() throws IOException {
        String saveBuffer;
        
        saveBuffer = blackBoxArrayDepth + "x" + blackBoxArrayWidth + System.lineSeparator();
        Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
        
        //save layer 0 parent input multipliers
        for (int i = 0; i < blackBoxArray[0].length; i++) {
            saveBuffer = blackBoxArray[0][i].netLayer + "L";
            Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            
            saveBuffer = i + "N";
            Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            
            saveBuffer = blackBoxArray[0][i].bias + "B";
            Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            
            for (int j = 0; j < blackBoxArray[0][i].parentLayerInputMultipliers.length; j++) {
                saveBuffer = blackBoxArray[0][i].parentLayerInputMultipliers[j] + "M";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            }
            
            saveBuffer = System.lineSeparator();
            Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
        }
        
        //save layer 1-bottom parent input multipliers
        for (int i = 1; i < blackBoxArray.length; i++) {
            for (int j = 0; j < blackBoxArray[i].length; j++) {
                saveBuffer = blackBoxArray[i][j].netLayer + "L";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            
                saveBuffer = j + "N";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            
                saveBuffer = blackBoxArray[i][j].bias + "B";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
                
                for (int k = 0; k < blackBoxArray[i][j].parentLayerInputMultipliers.length; k++) {
                    saveBuffer = blackBoxArray[i][j].parentLayerInputMultipliers[k] + "M";
                    Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
                }
            
                saveBuffer = System.lineSeparator();
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            }
        }
        
        //save layer bottom+1 parent input multipliers
        for (int i = 0; i < outputNodeArray.length; i++) {
            for (int j = 0; j < outputNodeArray[i].length; j++) {
                saveBuffer = outputNodeArray[i][j].netLayer + "L";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
                
                saveBuffer = i + "C";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            
                saveBuffer = j + "N";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            
                saveBuffer = outputNodeArray[i][j].bias + "B";
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
                
                for (int k = 0; k < outputNodeArray[i][j].parentLayerInputMultipliers.length; k++) {
                    saveBuffer = outputNodeArray[i][j].parentLayerInputMultipliers[k] + "M";
                    Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
                }
            
                saveBuffer = System.lineSeparator();
                Files.write(Paths.get("savedNet.txt"), saveBuffer.getBytes(), StandardOpenOption.APPEND);
            }
        }
    }
    
    public void recoverNeuralNet() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("savedNet.txt"));
        String curentLine;
        String buffer;
        curentLine = reader.readLine();
        blackBoxArrayDepth = 
    }
    
    public String runNeuralNet(String pub) {
        for (int i = 0; i < inputNodeArray.length; i++) { //set all to zero, just to be safe
            for (int j = 0; j < inputNodeArray[i].length; j++) {
                inputNodeArray[i][j].setBias(0.0);
                inputNodeArray[i][j].setOutput(0.0);
            }
        }
        
        publicAdress = pub;
        //use key to set inputNodeArray[x][y] biases
        for (int i = 0; i < 34; i++) {
            switch (publicAdress.toLowerCase().charAt(i)) {
                case '0': inputNodeArray[i][0].setOutput(1.0);
                        break;
                case '1': inputNodeArray[i][1].setOutput(1.0);
                        break;
                case '2': inputNodeArray[i][2].setOutput(1.0);
                        break;
                case '3': inputNodeArray[i][3].setOutput(1.0);
                        break;
                case '4': inputNodeArray[i][4].setOutput(1.0);
                        break;
                case '5': inputNodeArray[i][5].setOutput(1.0);
                        break;
                case '6': inputNodeArray[i][6].setOutput(1.0);
                        break;
                case '7': inputNodeArray[i][7].setOutput(1.0);
                        break;
                case '8': inputNodeArray[i][8].setOutput(1.0);
                        break;
                case '9': inputNodeArray[i][9].setOutput(1.0);
                        break;
                case 'a': inputNodeArray[i][10].setOutput(1.0);
                        break;
                case 'b': inputNodeArray[i][11].setOutput(1.0);
                        break;
                case 'c': inputNodeArray[i][12].setOutput(1.0);
                        break;
                case 'd': inputNodeArray[i][13].setOutput(1.0);
                        break;
                case 'e': inputNodeArray[i][14].setOutput(1.0);
                        break;
                case 'f': inputNodeArray[i][15].setOutput(1.0);
                        break;
                case 'g': inputNodeArray[i][16].setOutput(1.0);
                        break;
                case 'h': inputNodeArray[i][17].setOutput(1.0);
                        break;
                case 'i': inputNodeArray[i][18].setOutput(1.0);
                        break;
                case 'j': inputNodeArray[i][19].setOutput(1.0);
                        break;
                case 'k': inputNodeArray[i][20].setOutput(1.0);
                        break;
                case 'l': inputNodeArray[i][21].setOutput(1.0);
                        break;
                case 'm': inputNodeArray[i][22].setOutput(1.0);
                        break;
                case 'n': inputNodeArray[i][23].setOutput(1.0);
                        break;
                case 'o': inputNodeArray[i][24].setOutput(1.0);
                        break;
                case 'p': inputNodeArray[i][25].setOutput(1.0);
                        break;
                case 'q': inputNodeArray[i][26].setOutput(1.0);
                        break;
                case 'r': inputNodeArray[i][27].setOutput(1.0);
                        break;
                case 's': inputNodeArray[i][28].setOutput(1.0);
                        break;
                case 't': inputNodeArray[i][29].setOutput(1.0);
                        break;
                case 'u': inputNodeArray[i][30].setOutput(1.0);
                        break;
                case 'v': inputNodeArray[i][31].setOutput(1.0);
                        break;
                case 'w': inputNodeArray[i][32].setOutput(1.0);
                        break;
                case 'x': inputNodeArray[i][33].setOutput(1.0);
                        break;
                case 'y': inputNodeArray[i][34].setOutput(1.0);
                        break;
                case 'z': inputNodeArray[i][35].setOutput(1.0);
                        break;
            }
        }
        //run the net
        for (int bBLayer = 0; bBLayer < blackBoxArray.length; bBLayer++) {
            for (int n = 0; n < blackBoxArray[bBLayer].length; n++) {
                blackBoxArray[bBLayer][n].calculateOutput();
            }
        }
        for (int outputCharacter = 0; outputCharacter < outputNodeArray.length; outputCharacter++) {
            for (int characterOption = 0; characterOption < outputNodeArray[outputCharacter].length; characterOption++) {
                outputNodeArray[outputCharacter][characterOption].calculateOutput();
            }
        }
        //Build code that checks all outputNodeArray nodes and finds the character option for each character slot with the highest output double. Convert these to single string and save it to private key
        int[] privateKeyArray = new int[64];
        
        for (int outputCharacter = 0; outputCharacter < outputNodeArray.length; outputCharacter++) {
            int characterOptionSlot = 0; //should end up between 0 and 15 (inclusive)
            for (int characterOption = 1; characterOption < outputNodeArray[outputCharacter].length; characterOption++) {
                if (outputNodeArray[outputCharacter][characterOption].output > outputNodeArray[outputCharacter][characterOption-1].output) {
                    characterOptionSlot = characterOption;
                }
            }
            privateKeyArray[outputCharacter] = characterOptionSlot; //sets output character index to value between 0 and 15 (inclusive)
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