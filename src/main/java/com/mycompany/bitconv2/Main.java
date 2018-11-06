/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bitconv2;

import java.security.SecureRandom;
import java.util.Arrays;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

/**
 *
 * @author patrickmccaffrey
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Program BitConV2 start");
        
        String[] tempKeyPair =  keyGenerator();
        
        System.out.println("Values Returned: " + tempKeyPair[0] + " and " + tempKeyPair[1]);
        
        System.out.println("Program finished");
    }
    
    private static String[] keyGenerator() {
        System.out.println("keyGenerator starting");
        
        NetworkParameters params = MainNetParams.get();
        String filePrefix = "forwarding-service";
        
        SecureRandom random = new SecureRandom();
        ECKey key = new ECKey(random);
        
        String[] returnValue = new String[2];
        returnValue[0] = key.getPrivateKeyAsHex();
        returnValue[1] = "" + key.toAddress(params);
        
        System.out.println("Private Key: " + returnValue[0]);
        System.out.println("Adress: " + returnValue[1]);
        
        System.out.println("keyGenerator finished");
        return returnValue;
    }
}