package application.controllers;

import application.core.JetStoreController;
import system.JetSetRequest;

@SuppressWarnings("unused")
public class DefaultController extends JetStoreController
{
	public DefaultController(JetSetRequest jsr, String methodParameter, int methodId, int methodTargetId)
	{
		super(jsr, methodParameter, methodId, methodTargetId);
	}

	public void index() throws Exception
	{
		jsView.assignData("welcome_message", "BOLI BOLI, era små pepparkorn!");
		jsView.assignData("SiteName", jsConfig.siteName);
		jsView.assignData("GetKey", jsInput.getInputGET("key"));
		jsView.assignData("Encrypted", jsEncryption.encrypt(jsInput.getInputGET("key")));
		jsView.assignData("Decrypted", jsEncryption.decrypt(jsEncryption.encrypt(jsInput.getInputGET("key"))));
		render("store", "index");
	}
}
