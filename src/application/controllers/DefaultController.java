/*
 * This file is part of JetSet, a lightweight Java Enterprise Web MVC framework.
 * Modified as of 2/24/14 4:05 PM
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

package application.controllers;

import static application.config.JetSetConfig.SITE_NAME;

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
		this.loadEncryption();

		jsView.assignData("welcome_message", "BOLI BOLI, era små pepparkorn!");
		jsView.assignData("SiteName", SITE_NAME);
		jsView.assignData("GetKey", jsInput.getInputGET("key"));
		jsView.assignData("Encrypted", jsEncryption.encrypt(jsInput.getInputGET("key")));
		jsView.assignData("Decrypted", jsEncryption.decrypt(jsEncryption.encrypt(jsInput.getInputGET("key"))));
		render("store", "index");
	}
}
