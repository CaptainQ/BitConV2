/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitconv2;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

/**
 *
 * @author allkn
 */
public class BitConV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        // TODO code application logic here
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec);
        
        KeyPair kp = keyGen.generateKeyPair();
        PublicKey pub = kp.getPublic();
        PrivateKey pvt = kp.getPrivate();
        
        ECPrivateKey epvt = (ECPrivateKey)pvt;
        String sepvt = adjustTo64(epvt.getS().toString(16)).toUpperCase();
        // System.out.println("s[" + sepvt.length() + "]: " + sepvt);
        
        ECPublicKey epub = (ECPublicKey)pub;
        ECPoint pt = epub.getW();
        String sx = adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
        String bcPub = "04" + sx + sy;
        // System.out.println("bcPub: " + bcPub);
        
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] s1 = sha.digest(bcPub.getBytes("UTF-8"));
        // System.out.println("  sha: " + bytesToHex(s1).toUpperCase());
        
        MessageDigest rmd = MessageDigest.getInstance("RipeMD160", "BC");
        byte[] r1 = rmd.digest(s1);
        
        byte[] r2 = new byte[r1.length + 1];
        r2[0] = 0;
        for (int i = 0 ; i < r1.length ; i++) r2[i+1] = r1[i];
        // System.out.println("  rmd: " + bytesToHex(r2).toUpperCase());
        
        byte[] s2 = sha.digest(r2);
        // System.out.println("  sha: " + bytesToHex(s2).toUpperCase());
        byte[] s3 = sha.digest(s2);
        // System.out.println("  sha: " + bytesToHex(s3).toUpperCase());
        
        byte[] a1 = new byte[25];
        for (int i = 0 ; i < r2.length ; i++) a1[i] = r2[i];
        for (int i = 0 ; i < 4 ; i++) a1[21 + i] = s3[i];
        
        // System.out.println("  adr: " + Base58.encode(a1));
    }
    
    static private String adjustTo64(String s) {
        switch(s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default: throw new IllegalArgumentException("not a valid key: " + s);
        }
    }
    
}
