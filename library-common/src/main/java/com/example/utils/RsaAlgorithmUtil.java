package com.example.utils;

import com.example.constant.FileSystemConstant;
import com.example.constant.TimeFormatConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/26 20:02
 */
@Slf4j
public class RsaAlgorithmUtil {

    public static void generateRsaKey(String filename) throws NoSuchAlgorithmException, IOException {
        String priPath = FileSystemConstant.RSA_KEY_LOCATION+filename+ TimeFormatConstant.FILE_DATEFORMAT.format(new Date())+".pub";

        String pubPath = FileSystemConstant.RSA_KEY_LOCATION+filename+ TimeFormatConstant.FILE_DATEFORMAT.format(new Date())+".pri";

        OutputStream pubkey = new FileOutputStream(pubPath);
        OutputStream prikey = new FileOutputStream(priPath);

        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(2048);

        KeyPair keyPair = rsa.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();

        PublicKey aPublic = keyPair.getPublic();

        byte[] privateEncoded = aPrivate.getEncoded();

        byte[] publicEncoded = aPublic.getEncoded();

        pubkey.write(publicEncoded);
        prikey.write(privateEncoded);

        pubkey.close();
        prikey.close();

        log.info("save private key "+priPath+", public key "+pubPath+" successfully");
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        generateRsaKey("hello");
    }
}
