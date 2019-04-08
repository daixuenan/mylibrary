package com.dai.gallery.network.base;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by dai on 2018/11/19.
 */

public class SSLSocketFactory extends org.apache.http.conn.ssl.SSLSocketFactory {
	/**
	 * @Fields sslContext
	 * @Description: 忽略证书验证
	 */
	SSLContext sslContext = SSLContext.getInstance("TLS");

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param truststore
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	public SSLSocketFactory(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
		super(truststore);
		TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		sslContext.init(null, new TrustManager[] { tm }, null);
	}

	/*
	 * (non-Javadoc) <p>Title: createSocket</p> <p>Description: </p>
	 * 
	 * @param socket
	 * 
	 * @param host
	 * 
	 * @param port
	 * 
	 * @param autoClose
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @throws UnknownHostException
	 * 
	 * @see
	 * org.apache.http.conn.ssl.SSLSocketFactory#createSocket(java.net.Socket,
	 * java.lang.String, int, boolean)
	 */
	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
			throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	/*
	 * (non-Javadoc) <p>Title: createSocket</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @see org.apache.http.conn.ssl.SSLSocketFactory#createSocket()
	 */
	@Override
	public Socket createSocket() throws IOException {
		return sslContext.getSocketFactory().createSocket();
	}
}