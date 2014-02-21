JetSet
======

*Lightweight Java Enterprise MVC Web Application Framework*

Developed as a private hobby project, born from frustration with regular Java Web MVC frameworks, JetSet is a lightweight, no-bs alternative for Java developers who favor simplicity and performance. It contains only the absolute essentials of Java Enterprise required to run Java on the web, as well as a core set of useful features for web applications.

Features:
 * Built-in URL mapping to controllers and methods
 * XHTML view rendering using the [Thymeleaf template engine](http://www.thymeleaf.org/)
 * Data management using the [jOOQ SQL abstraction layer](http://www.jooq.org/) (currently only supporting MySQL)
 * Encryption using Java AES256 crypto libraries
 * Encrypted, database-validated session variables (work in progress)
 * Static HTML content minifier (work in progress)
 * A clear, detailed error handle page for easy debugging

The framework is currently in its very early stages of development and thus does not feature more than some core features. Feel free to post issues in the repo.

_Please note that JetSet has a very different approach to web Java than most frameworks. It does not feature any CLI interface, it does not support JSP/JSPX, Maven or any other technologies. Instead, it aims to provide a lightweight and easy-to-use framework for less-demanding projects._

Getting Started
---------------

JetSet comes packed with everything it needs bundled. To run it you would need the following:
* A servlet container to run JetSet in. Most should do thanks to Java EE's standardization. JetSet runs great with *Tomcat 7* or *Jetty 9*.
* JRE 7. For encryption to work, you need to replace security files with newer ones from Oracle.

For your IDE to understand JetSet, you need to do the following:
* Set up a project with a Java application server
* * Import the jOOQ JAR as a library to enable model autocompletion
* * Create a web artifact for your app server including all the necessary JetSet classes and libraries

Basic principles
----------------

JetSet operate much like most PHP web MVC frameworks even though it's implemented in Java.
* Controllers, models and views for your project are found in the application package.
* JetSet framework code is found in the system package. As your app server and IDE are both set up, you shouldn't have to touch this package at all.
* Controllers and models extend JetSet core controllers and models, granting use of the frameworks features.

Folder structure
----------------

- /application            Contains your application files
- - /core                 Classes extending the JetSet core
- - /controllers          MVC controllers invoked by JetSet
- - /models               MVC models fetching data using jOOQ
- /system                 The JetSet framework files
- /web/WEB-INF/           Java Enterprise directory, contains servlet configuration as well as the JetSet error template
- - /web/WEB-INF/views/     Application view HTML (Thymeleaf) files directory

Routing
-------

*All* requests to JetSet which are not referring to static content follow the exact same convention:

http://jetsetsite.com/controller/method/methodParameter/methodId/methodTargetId

Where:
* Controller represents the class in the controllers directory to load (e.g. /products -> ProductsController)
* Method represents the method name to be invoked by JetSet (/products/view -> view())
* MethodParameter is optional and gives the ability to pass additional action calls (e.g. ../posts/delete)
* MethodId is optional and specifies an action ID (e.g. /users/view/3412)
* MethodTargetId is optional and specifies an additional ID (e.g. /friends/add/3412/4216)

JetSet has no function to override this routing.

The Controller
--------------

JetSet gives you the option to either extend JetSetController directly to hook right into the system, *or* to extend a core controller which in its turn extends the JetSetController. Using your own core controller on top of the JetSet functionality may prove beneficial if you have much common functionality in your application.

Regardless, all of your controllers extending the JetSetController will feature the following component instances:
* jsr, an instance of the JetSetRequest, is a wrapper containing a HttpServletRequest, HttpServletResponse and a ServletContext
* jsView, an instance of the system/components/View class, grants access to view data assignment and rendering
* jsInput, an instance of system/components/Input, provides functions to retrieve GET and POST data
* jsConfig, an instance of application/config/JetSetConfig, provides system configuration variables
* jsEncryption, an instance of system/components/Encryption, provides encryption support (please see Encryption notes below)
* methodParameter, a String passed by the JetSetRouter, provides the optional methodParameter (see Routing section)
* methodId, int, also from the router, provides any ID specified (defaults to 0)
* methodTargetId, works like methodId but for the optional methodTargetId parameter (see Routing section)
