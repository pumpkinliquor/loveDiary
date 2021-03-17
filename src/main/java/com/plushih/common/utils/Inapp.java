package com.plushih.common.utils;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.net.URL;

import java.net.URLConnection;

import java.security.InvalidKeyException;

import java.security.KeyFactory;

import java.security.NoSuchAlgorithmException;

import java.security.PublicKey;

import java.security.Signature;

import java.security.SignatureException;

import java.security.spec.InvalidKeySpecException;

import java.security.spec.X509EncodedKeySpec;


import org.apache.commons.codec.binary.Base64;

import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;

import org.codehaus.jackson.JsonNode;

import org.codehaus.jackson.map.ObjectMapper;


/**
 * Apple IAP and Google Play In-app Billing Verification Class
 */

public class Inapp {

    static Logger logger = Logger.getLogger(Inapp.class);

    public static final String VERIFICATION_URL_REAL = "https://buy.itunes.apple.com/verifyReceipt";

    public static final String VERIFICATION_URL_SANDBOX = "https://sandbox.itunes.apple.com/verifyReceipt";

    /**
     * @param receipt
     * @param isTest
     * @return 0: fail, 1: success, -1 : urus hacking
     * @throws IOException
     */

    public static int verifyApplePurchase(String receipt, boolean isTest) throws IOException {

        String returnedString;

        if (isTest) {

            returnedString = verifyAppleReceiptData(VERIFICATION_URL_SANDBOX, receipt);

        } else {

            returnedString = verifyAppleReceiptData(VERIFICATION_URL_REAL, receipt);

        }


        ObjectMapper mapper = new ObjectMapper();

        JsonNode resultObject = mapper.readTree(returnedString);

        int resultStatus = Integer.parseInt(resultObject.get("status").toString());


        if (resultStatus != 0) {

            // urus hacking check.

            try {

                String decodedText = new String(Base64.decodeBase64(receipt

                        .getBytes()), "UTF-8");

                if (decodedText != null && decodedText.startsWith("com.urus")) {

                    return -1;

                }

            } catch (Exception base64DecodeException) {

                logger.error(base64DecodeException.getMessage());

            }

            return 0;


        }

        return 1;

    }

    /**
     * @param urladdress
     * @param receiptData
     * @return result string
     * @throws IOException
     * @author Kim Seong Su
     */

    public static String verifyAppleReceiptData(String urladdress, String receiptData) throws IOException {

        URL url = null;

        URLConnection conn = null;

        OutputStreamWriter osw = null;

        BufferedReader br = null;

        StringBuffer sb = new StringBuffer();


        try {

            String jsonData = "{" +

                    "\"receipt-data\" : \"" + receiptData + "\"," +

                    "}";

            url = new URL(urladdress);

            conn = url.openConnection();

            conn.setDoOutput(true);

            osw = new OutputStreamWriter(conn.getOutputStream());

            osw.write(jsonData);

            osw.flush();


            // Get the response

            br = new BufferedReader(

                    new InputStreamReader(conn.getInputStream()));

            String line;

            sb = new StringBuffer();

            while ((line = br.readLine()) != null) {

// Process line...

                sb.append(line);

            }

        } finally {

            if (osw != null)

                osw.close();

            if (br != null)

                br.close();

        }


        return sb.toString();

    }


    // --------------------- For Android --------------------------//


    private static final String KEY_FACTORY_ALGORITHM = "RSA";

    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";


    /**
     * Verifies that the data was signed with the given signature, and returns
     * <p>
     * the verified purchase. The data is in JSON format and signed
     * <p>
     * with a private key. The data also contains the {@link PurchaseState}
     * <p>
     * and product ID of the purchase.
     *
     * @param base64PublicKey the base64-encoded public key to use for verifying.
     * @param signedData      the signed JSON string (signed, not encrypted)
     * @param signature       the signature for the data, signed with the private key
     */

    public static boolean verifyAndroidPurchase(String base64PublicKey, String signedData, String signature) {

        if (signedData == null) {

            return false;

        }


        boolean verified = false;

        if (!StringUtils.isEmpty(signature)) {

            PublicKey key = Inapp.generatePublicKey(base64PublicKey);

            verified = Inapp.verifyAndroidSignedData(key, signedData, signature);

            if (!verified) {

                return false;

            }

        }

        return true;

    }


    /**
     * Generates a PublicKey instance from a string containing the
     * <p>
     * Base64-encoded public key.
     *
     * @param encodedPublicKey Base64-encoded public key
     * @throws IllegalArgumentException if encodedPublicKey is invalid
     */

    public static PublicKey generatePublicKey(String encodedPublicKey) {

        try {

            byte[] decodedKey = Base64.decodeBase64(encodedPublicKey);

            KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);

            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);

        } catch (InvalidKeySpecException e) {

            throw new IllegalArgumentException(e);

        }

    }


    /**
     * Verifies that the signature from the server matches the computed
     * <p>
     * signature on the data.  Returns true if the data is correctly signed.
     *
     * @param publicKey  public key associated with the developer account
     * @param signedData signed data from server
     * @param signature  server signature
     * @return true if the data and signature match
     */

    public static boolean verifyAndroidSignedData(PublicKey publicKey, String signedData, String signature) {

        Signature sig;

        try {

            sig = Signature.getInstance(SIGNATURE_ALGORITHM);

            sig.initVerify(publicKey);

            sig.update(signedData.getBytes());

            if (!sig.verify(Base64.decodeBase64(signature))) {

                return false;

            }

            return true;

        } catch (NoSuchAlgorithmException e) {

            logger.error(e.getMessage());

        } catch (InvalidKeyException e) {

            logger.error(e.getMessage());

        } catch (SignatureException e) {

            logger.error(e.getMessage());

        }

        return false;

    }

}



