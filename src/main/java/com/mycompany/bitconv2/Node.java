/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bitconv2;

import java.util.Random;

/**
 *
 * @author allkn
 */
public class Node {
    Double[] parentLayerInputMultipliers;
    Double bias = 0.0;
    Double output = 0.0;
    int netLayer; //all nodes check blackBox layer above them even if they are outputNodes. Exception is inputNodes who have layer -1 and no parent layer.
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
        netLayer = -1;
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
            Random r = new Random();
            parentLayerInputMultipliers[i] = r.nextDouble();
        }
    }
    
    public void initialiseBias() {
        Random r = new Random();
        bias = r.nextDouble();
    }
    
    public void setBias(Double d) { //should only be used to set NeuralNet.inputNodeArray[x][y].bias values
        bias = d; //Should only be 1.0 or 0.0
    }
    
    public void setOutput(Double d) { //should only be used to set NeuralNet.inputNodeArray[x][y].bias values
        output = d; //Should only be 1.0 or 0.0
    }
    
    public Double returnOutput() {
        return output;
    }
    
    public double compressToSigmoid(double value) {
        return 1 / (1 + (Math.pow(1.5, value)));
    }
    
    public void calculateOutput() {
        Double temp = 0.0;
        int count = 0;
        NeuralNet net = NeuralNet.getNeuralNet();
        
        if (netLayer == -1) {
            //Do nothing, this is handled by the first part of NeuralNet.RunNeuralNet() this should never be called on input layer nodes.
        }
        else {
            if (netLayer == 0) {
                //read parent layer's node's output Double and do the node math
                //save as Double to output.
                for (int character = 0; character < net.inputNodeArray.length; character++) {
                    for (int letter = 0; letter < net.inputNodeArray[character].length; letter++) {
                        temp = temp + (net.inputNodeArray[letter][character].returnOutput() * parentLayerInputMultipliers[count]);
                        count++;
                    }
                }
            }
            else {
                for (int node = 0; node < net.blackBoxArray[netLayer-1].length; node++) {
                    temp = temp + (net.blackBoxArray[node][netLayer-1].returnOutput() * parentLayerInputMultipliers[node]);
                }
            }
        }
        temp = temp + bias;
        output = compressToSigmoid(temp);
    }
}
