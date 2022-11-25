package org.monjasa.lab04.crypto.cipher.engine;

import org.junit.jupiter.api.Test;
import org.monjasa.lab04.crypto.cipher.exception.InvalidCipherTextException;
import org.monjasa.lab04.crypto.cipher.parameters.impl.RSAKeyParameters;
import org.monjasa.lab04.crypto.encoder.base.Hex;
import org.monjasa.lab04.crypto.key.RsaKeyFactory;
import org.monjasa.lab04.crypto.key.impl.RsaKeyFactoryImpl;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RsaTest {

    static BigInteger mod = new BigInteger("b259d2d6e627a768c94be36164c2d9fc79d97aab9253140e5bf17751197731d6f7540d2509e7b9ffee0a70a6e26d56e92d2edd7f85aba85600b69089f35f6bdbf3c298e05842535d9f064e6b0391cb7d306e0a2d20c4dfb4e7b49a9640bdea26c10ad69c3f05007ce2513cee44cfe01998e62b6c3637d3fc0391079b26ee36d5", 16);
    static BigInteger pubExp = new BigInteger("11", 16);
    static BigInteger privExp = new BigInteger("92e08f83cc9920746989ca5034dcb384a094fb9c5a6288fcc4304424ab8f56388f72652d8fafc65a4b9020896f2cde297080f2a540e7b7ce5af0b3446e1258d1dd7f245cf54124b4c6e17da21b90a0ebd22605e6f45c9f136d7a13eaac1c0f7487de8bd6d924972408ebb58af71e76fd7b012a8d0e165f3ae2e5077a8648e619", 16);

    static String input = "4e6f77206973207468652074696d6520666f7220616c6c20676f6f64206d656e";

    @Test
    void test() throws InvalidCipherTextException, IOException {


        RsaKeyFactory keyFactory = new RsaKeyFactoryImpl();

        RSAPublicKey publicKey = keyFactory.decodePublicKey(Files.readAllBytes(Path.of("/Users/monjasa/nulp-local/IV/first/BPD/source/id_rsa.pub")));
        RSAPrivateKey privateKey = keyFactory.decodePrivateKey(Files.readAllBytes(Path.of("/Users/monjasa/nulp-local/IV/first/BPD/source/id_rsa")));

        mod = publicKey.getModulus();
        pubExp = publicKey.getPublicExponent();
        privExp = privateKey.getPrivateExponent();

        RSAKeyParameters pubParameters = new RSAKeyParameters(false, mod, pubExp);
        RSAKeyParameters privParameters = new RSAKeyParameters(true, mod, privExp);

        //
        // RAW
        //
        AsymmetricBlockCipher eng = new RsaCipherEngine();

        byte[] data = Hex.decode(input);

        eng.init(true, pubParameters);

        data = eng.processBlock(data, 0, data.length);

        eng.init(false, privParameters);

            data = eng.processBlock(data, 0, data.length);

        if (!input.equals(new String(Hex.encode(data))))
        {
            throw new RuntimeException("failed RAW Test");
        }
    }
}
