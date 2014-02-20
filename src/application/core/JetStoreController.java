package application.core;

/**
 * JetStoreController.java
 * Crafting your own core modules, you can extend JetSet to bring
 * common functionality to all of your controllers without messing
 * with JetSet system files.
 *
 * Using this way of coding would make the hierarchy as follows:
 *    JetSetController -> CoreController -> PageController
 */

import system.JetSetController;
import system.JetSetRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class JetStoreController extends JetSetController
{
	List<CssObject> cssList = new ArrayList<>();
	List<String> jsList = new ArrayList<>();
	List<MetaObject> metaList = new ArrayList<>();
	Map<String, String> ogList = new HashMap<>();

	public JetStoreController(JetSetRequest jsr, String methodParameter, int methodId, int methodTargetId)
	{
		super(jsr, methodParameter, methodId, methodTargetId);
	}

	public void render(String viewDir, String viewName) throws IOException
	{
		// Set up data
		jsView.assignData("SiteName", jsConfig.siteName);
		jsView.assignData("CssList", this.cssList);
		jsView.assignData("JsList", this.jsList);
		jsView.assignData("MetaList", this.metaList);
		jsView.assignData("OgList", this.ogList);

		// Load the appropriate view
		jsView.assignData("ViewName", viewName);
		jsView.templateResolver.setPrefix("/WEB-INF/views/" + viewDir + "/");
		jsView.loadView("master");
	}

	public void addCss(String cssHref)
	{
		CssObject cssObject = new CssObject(cssHref, "text/css", "stylesheet", "all");
		cssList.add(cssObject);
	}

	public void addCss(String cssHref, String cssType, String cssRel, String cssMedia)
	{
		CssObject cssObject = new CssObject(cssHref, cssType, cssRel, cssMedia);
		cssList.add(cssObject);
	}

	public void addJs(String jsURI)
	{
		jsList.add(jsURI);
	}

	public void addMeta(boolean httpEquiv, String httpEquivName, String metaContent)
	{
		MetaObject metaObject = new MetaObject(httpEquiv, httpEquivName, metaContent);
		metaList.add(metaObject);
	}

	public void addOg(String ogProperty, String ogContent)
	{
		ogList.put(ogProperty, ogContent);
	}
}

/**
 * CssObject
 * Data type defining the required attributes for a CSS link tag
 */
class CssObject
{
	String cssHref;
	String cssType;
	String cssRel;
	String cssMedia;

	CssObject(String cssHref, String cssType, String cssRel, String cssMedia)
	{
		this.cssHref = cssHref;
		this.cssType = cssType;
		this.cssRel = cssRel;
		this.cssMedia = cssMedia;
	}
}

class MetaObject
{
	boolean httpEquiv;
	String httpEquivName;
	String metaContent;

	MetaObject(boolean httpEquiv, String httpEquivName, String metaContent)
	{
		this.httpEquiv = httpEquiv;
		this.httpEquivName = httpEquivName;
		this.metaContent = metaContent;
	}
}