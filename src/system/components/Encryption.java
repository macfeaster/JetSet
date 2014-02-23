package system.components;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("unused")
public class Encryption
{
	private String encryptionKey;

	/**
	 * Class constructor demanding encryption key
	 * @param encryptionKey Key passed from jsConfig
	 */
	public Encryption(String encryptionKey)
	{
		this.encryptionKey = encryptionKey;
	}

	/**
	 * AES-256 encryption method
	 * @param plainText String to encrypt
	 * @return The encrypted bytes as a base64 encoded string
	 * @throws Exception
	 */
	public String encrypt(String plainText) throws Exception
	{
		// Perform parameter validation
		assert (plainText.getBytes().length == 0) : "Cannot encrypt null";

		// Initialize variables
		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		BASE64Encoder base64Encoder = new BASE64Encoder();
		byte[] encrypted;

		// Initialize cipher engine
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

		// Perform encryption
		try
		{
			encrypted = cipher.doFinal(plainText.getBytes());
			return base64Encoder.encodeBuffer(encrypted);
		}
		catch (NullPointerException e)
		{
			return null;
		}
	}

	/**
	 * AES-256 decryption method
	 * @param encrypted Base64 encoded bytes to decrypt
	 * @return Decrypted plaintext string
	 * @throws Exception
	 */
	public String decrypt(String encrypted) throws Exception
	{
		// Initialize variables
		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] decryptBytes;
		byte[] decrypted;

		// Perform decryption
		try
		{
			decryptBytes = base64Decoder.decodeBuffer(encrypted);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			decrypted = cipher.doFinal(decryptBytes);

			// Return result
			return new String(decrypted);
		}
		catch (NullPointerException e)
		{
			return null;
		}
	}
}