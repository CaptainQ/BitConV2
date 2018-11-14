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
public class Node {
    Double[] parentLayerInputMultipliers;
    Double bias;
    Double output;
    int netLayer;
    boolean newNode;
    
    public Node(boolean checkNewNode, Node[][] parentLayer, int layer) {
        parentLayerInputMultipliers = new Double[parentLayer.length * parentLayer[0].length];
        newNode = checkNewNode;
        netLayer = layer;
    }
    
    public Node(boolean checkNewNode, Node[] parentLayer, int layer) {
        parentLayerInputMultipliers = new Double[parentLayer.length];
        newNode = checkNewNode;
        netLayer = layer;
    }
    
    public Node(boolean checkNewNode) {
        parentLayerInputMultipliers = new Double[0];
        newNode = checkNewNode;
    }
    
    public void initialiseNode() {
        if (newNode == true) {
            initialiseParentLayerDoubles();
            initialiseBias();
        }
        else {
            
        }
    }
    
    public void initialiseParentLayerDoubles() {
        for (int i = 0; i < parentLayerInputMultipliers.length; i++) {
            parentLayerInputMultipliers[i] = 0.0; //create random value.
        }
    }
    
    public void initialiseBias() {
        bias = 0.0; //create random value.
    }
    
    public void GetOutput() {
        Node test = NeuralNet.getNeuralNet().blackBoxArray[1][1]; //test to see if i can grab a specific node from the active net
        if (netLayer == 0) {
            //Read from file
        }
        else {
            
        }
    }
}
