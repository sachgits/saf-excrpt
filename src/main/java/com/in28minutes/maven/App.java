package com.in28minutes.maven;

import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
/**
 * Hello world!
 *
 */
public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getSimpleName());

    public static void main(String[] args) {
        App app = new App();
        String encrptedStr;
        try {
            encrptedStr = app.encryptString(app,"Hello World");
            System.out.println(encrptedStr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public int calculateSomething() {
		return 0;
    }
    
    public String encryptString(App app,String toEncryptStr) throws Exception{
        InputStream certInputStream = app.getClass().getClassLoader().getResourceAsStream("SandboxCertificate.cer");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate)certificateFactory.generateCertificate(certInputStream);
            PublicKey pk = certificate.getPublicKey();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            return Base64.getEncoder().encodeToString(cipher.doFinal(toEncryptStr.getBytes()));
    }
}
