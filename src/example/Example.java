package example;

import cypher.DSSC;
import cypher.utility.BitArr;

import java.util.Random;

public class Example {


    public static void main(String[] args) {
        // Make vars for 256-bit key and nonce
        byte[] key = new byte[32];
        byte[] nonce = new byte[32];
        byte[] payload = new byte[32];

        // Populate vars (this is almost as secure as my algorithm, pun intended)
        Random rd = new Random();
        rd.nextBytes(key);
        rd.nextBytes(nonce);
        rd.nextBytes(payload);

        // Generate stuff
        DSSC dssc = new DSSC(BitArr.toBitArray(key), BitArr.toBitArray(nonce));

        // Print some stuff
        System.out.println("Key:          " + hex(key));
        System.out.println("Nonce:        " + hex(nonce));
        System.out.println("Payload:      " + hex(payload));
        System.out.println();

        // Going once, going twice!
        byte[] once = BitArr.toByteArray(dssc.pass(BitArr.toBitArray(payload)));
        byte[] twice = BitArr.toByteArray(dssc.pass(BitArr.toBitArray(payload)));
        System.out.println("Going once!   " + hex(once));
        System.out.println("Going twice!  " + hex(twice));
        System.out.println();

        // And actually decrypt it
        dssc = new DSSC(BitArr.toBitArray(key), BitArr.toBitArray(nonce));
        System.out.println("Decrypted:    " + hex(BitArr.toByteArray(dssc.pass(BitArr.toBitArray(once)))));
        System.out.println("Decrypted:    " + hex(BitArr.toByteArray(dssc.pass(BitArr.toBitArray(twice)))));
        System.out.println();
    }


    private static String hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            int decimal = (int) aByte & 0xff;
            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {
                hex = "0" + hex;
            }
            result.append(hex);
        }
        return result.toString();
    }


}
