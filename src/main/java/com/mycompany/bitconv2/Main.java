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
        System.out.println("Program BitConV2 Start");
        
        NetworkParameters params = MainNetParams.get();
        String filePrefix = "forwarding-service";
        
        SecureRandom random = new SecureRandom();
        
        ECKey key = new ECKey(random);
        
        System.out.println("Private Key: " + key.getPrivateKeyAsHex());
        System.out.println("Adress: " + key.toAddress(params));
        System.out.println("Program Finished");
    }
}