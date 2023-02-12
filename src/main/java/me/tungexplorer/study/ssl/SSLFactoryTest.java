package me.tungexplorer.study.ssl;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.PemUtils;

public class SSLFactoryTest {

    public static void main(String[] args) throws Exception {
        // NoopHostnameVerifier.INSTANCE

        PrivateKey privateKey = PemUtils.loadPrivateKey(Paths.get("/home/tungtv/install_and_tool/EasyRSA-3.1.1/pki/private/jamesClient1.key"));
        List<X509Certificate> x509Certificates = PemUtils.loadCertificate(Paths.get("/home/tungtv/install_and_tool/EasyRSA-3.1.1/pki/issued/jamesClient1.crt"));

        SSLFactory sslFactory = SSLFactory.builder()
            .withIdentityMaterial(privateKey, "".toCharArray(), x509Certificates.toArray(new Certificate[0]))
            .withUnsafeHostnameVerifier()
            .withTrustingAllCertificatesWithoutValidation()
            .build();

        SSLContext sslContext = sslFactory.getSslContext();
        Socket socket = sslContext.getSocketFactory().createSocket();
        socket.connect(new InetSocketAddress("127.0.0.1", 33907));
        byte[] buffer = new byte[8193];
        socket.getInputStream().read(buffer);
        socket.getOutputStream().write(String.format("a0 LOGIN %s %s\r\n", "USER1", "USER_PASS").getBytes(StandardCharsets.UTF_8));

        System.out.println(123);

    }
}
