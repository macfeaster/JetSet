package application.config;

/**
 * JetSet Configuration
 */
public class JetSetConfig
{
	public final String defaultController = "Default"; // "Controller" will be appended to this
    public final String defaultMethod = "index";
    public final String defaultMethodParameter = "process";

	public final String sqlServer = "localhost";
	public final int sqlServerPort = 3306;
	public final String sqlUsername = "root";
	public final String sqlPassword = "alpine";
	public final String sqlDatabase = "jetset";

	public final String siteName = "JetSet Site";

	/**
	 * Below is the encryption key used for your JetSet installation.
	 * Do NOT share it with anybody. The key HAS to be a base64
	 * encoded AES-256 encryption key, or encryption will fail.
	 *
	 * Keys can be generated on:
	 *  http://www.digitalsanctuary.com/aes-key-generator.php
	 */
	public final String encryptionKey = "MWr/AEsRhAw2j8JZfGq74+NmVNyd=opC";
}
