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
    int netLayer; //Black Box starts at layer 1
    boolean newNode;
    
    public Node(boolean checkNewNode, Node[][] parentLayer, int layer) { //First layer of black box, reads from Neuralet.inputNodeArray
        parentLayerInputMultipliers = new Double[parentLayer.length * parentLayer[0].length];
        newNode = checkNewNode;
        netLayer = layer;
        initialiseNode();
    }
    
    public Node(boolean checkNewNode, Node[] parentLayer, int layer) { //Middle layers of black box
        parentLayerInputMultipliers = new Double[parentLayer.length];
        newNode = checkNewNode;
        netLayer = layer;
        initialiseNode();
    }
    
    public Node(boolean checkNewNode) { //Node with no parent layer: NeuralNet.inputNodeArray nodes
        parentLayerInputMultipliers = new Double[0];
        newNode = checkNewNode;
        netLayer = 0;
        initialiseNode();
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
    
    public void setBias(Double d) { //should only be used to set NeuralNet.inputNodeArray[x][y].bias values
        bias = d; //Should only be 1.0 or 0.0
    }
    
    public void GetOutput() {
        Node test = NeuralNet.getNeuralNet().blackBoxArray[1][1]; //test to see if i can grab a specific node from the active net
        if (netLayer == 0) {
            //Do nothing, this is handled by the first part of NeuralNet.RunNeuralNet() this should never be called on input layer nodes.
        }
        else {
            //read parent layer's node's output Double and do the node math
            //save as Double to output.
        }
    }
}
