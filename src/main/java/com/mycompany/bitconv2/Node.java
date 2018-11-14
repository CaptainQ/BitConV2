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
    static Double[] parentLayerInputMultipliers;
    static Double bias;
    static Double output;
    static boolean newNode;
    
    public Node(boolean checkNewNode, Node[][] parentLayer) {
        parentLayerInputMultipliers = new Double[parentLayer.length * parentLayer[0].length];
        newNode = checkNewNode;
    }
    
    public Node(boolean checkNewNode, Node[] parentLayer) {
        parentLayerInputMultipliers = new Double[parentLayer.length];
        newNode = checkNewNode;
    }
    
    public Node(boolean checkNewNode) {
        parentLayerInputMultipliers = new Double[0];
        newNode = checkNewNode;
    }
    
    public static void initialiseNode() {
        if (newNode == true) {
            initialiseParentLayerDoubles();
            initialiseBias();
        }
        else {
            
        }
    }
    
    public static void initialiseParentLayerDoubles() {
        for (int i = 0; i < parentLayerInputMultipliers.length; i++) {
            parentLayerInputMultipliers[i] = 0.0; //create random value.
        }
    }
    
    public static void initialiseBias() {
        bias = 0.0; //create random value.
    }
}
