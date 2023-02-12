package me.tungexplorer.study.ssl;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import jakarta.xml.bind.DatatypeConverter;

public class Thumbprint {

    public static void main(String[] args) throws Exception {
        X509Certificate certObject = getCertObject("/home/tungtv/workplace/2_JAMES/james-gcs-sara/src/test/resources/client_cert/jamesClient1.crt");
        System.out.println(getThumbprint(certObject));
    }

    public static X509Certificate getCertObject(String filePath)
        throws IOException, CertificateException {
        try (FileInputStream is = new FileInputStream(filePath)) {
            CertificateFactory certificateFactory = CertificateFactory
                .getInstance("X.509");
            return (X509Certificate) certificateFactory.generateCertificate(is);
        }
    }

    private static String getThumbprint(X509Certificate cert)
        throws NoSuchAlgorithmException, CertificateEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(cert.getEncoded());
        return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
    }
}
