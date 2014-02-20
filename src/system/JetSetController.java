package system;

import application.config.JetSetConfig;
import system.components.Encryption;
import system.components.Input;
import system.components.View;

public class JetSetController
{
	protected JetSetRequest jsr;
	protected View jsView;
	protected Input jsInput;
	protected JetSetConfig jsConfig;
	protected Encryption jsEncryption;

	protected String methodParameter;
	protected int methodId;
	protected int methodTargetId;

	/**
	 * Super controller constructor
	 * @param jsr JetSetRequest for the controller
	 * @param methodParameter Method parameter (defaults to JetSetConfig.defaultMethodParameter)
	 * @param methodId Method ID (defaults to 0)
	 * @param methodTargetId Method target ID (defaults to 0)
	 */
	protected JetSetController(JetSetRequest jsr, String methodParameter, int methodId, int methodTargetId)
	{
		// Set up variables
		this.jsr = jsr;
		this.methodParameter = methodParameter;
		this.methodId = methodId;
		this.methodTargetId = methodTargetId;

		// Initialize components
		this.jsInput = new Input(jsr.request);
		this.jsView = new View(jsr);
		this.jsConfig = new JetSetConfig();
		this.jsEncryption = new Encryption(jsConfig.encryptionKey);

		// Send HTTP headers
		jsr.response.setStatus(200);
		jsr.response.setHeader("Content-Type", "text/html;charset=utf8");
		jsr.response.setHeader("Cache-Control", "no-cache");
	}

}