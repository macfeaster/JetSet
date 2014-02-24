/*
 * This file is part of JetSet, a lightweight Java Enterprise Web MVC framework.
 * Modified as of 2/24/14 4:04 PM
 *
 * JetSet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JetSet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JetSet.  If not, see <http://www.gnu.org/licenses/>.
 */

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
		if(plainText == null)
		{
			return null;
		}

		// Initialize variables
		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		BASE64Encoder base64Encoder = new BASE64Encoder();
		byte[] encrypted;

		// Initialize cipher engine
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

		// Perform encryption
		encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
		return base64Encoder.encodeBuffer(encrypted);
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
		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
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
			return new String(decrypted, "UTF-8");
		}
		catch (NullPointerException e)
		{
			return null;
		}
	}
}