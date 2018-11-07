/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bitconv2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

/**
 *
 * @author patrickmccaffrey
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Program BitConV2 start");
        Scanner user_input = new Scanner( System.in );
        
        System.out.print("Would you like to create more training data? (y): ");
        
        if ("y".equals(user_input.next().toLowerCase())) {
            generateTrainingData();
            System.out.println("end if statement");
        }
        else {
            System.out.println("end else statement");
        }
        
        System.out.println("Program finished");
    }
    
    private static void generateTrainingData() throws IOException {
        Scanner user_input = new Scanner( System.in );
        
        String[] tempKeyPair = new String[2];
        int trainingQuantity;
        
        System.out.println("How many more training pairs do you want to generate? (723,000 pairs per hour on surface)");
        trainingQuantity = Integer.parseInt(user_input.next());
        
        int loggingInt = 100;
        
        for (int i = 0; i < trainingQuantity; i++) {
            tempKeyPair = keyGenerator();
            saveKeyPair(tempKeyPair);
            if (i + 1 == loggingInt) {
                System.out.println((i + 1) + "th itteration.");
                loggingInt = loggingInt + 100;
            }
        }
    }
    
    private static String[] keyGenerator() {
        
        NetworkParameters params = MainNetParams.get();
        String filePrefix = "forwarding-service";
        
        SecureRandom random = new SecureRandom();
        ECKey key = new ECKey(random);
        
        String[] returnValue = new String[2];
        returnValue[0] = key.getPrivateKeyAsHex();
        returnValue[1] = "" + key.toAddress(params);
        
        return returnValue;
    }
    
    private static void saveKeyPair(String[] keyPair) throws FileNotFoundException, IOException {
        
        String wholeLine = keyPair[0] + " " + keyPair[1] + System.lineSeparator();
        
        Files.write(Paths.get("testKeyPair.txt"), wholeLine.getBytes(), StandardOpenOption.APPEND);
    }
}