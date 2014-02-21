JetSet
======

*Lightweight Java Enterprise MVC Web Application Framework*

Developed as a private hobby project, born from frustration with regular Java Web MVC frameworks, JetSet is a lightweight, no-bs alternative for Java developers who favor simplicity and performance. It contains only the absolute essentials of Java Enterprise required to run Java on the web.

Features:
 * Built-in URL mapping to controllers and methods
 * XHTML view rendering using the [Thymeleaf template engine](http://www.thymeleaf.org/)
 * Data management using the [jOOQ SQL abstraction layer](http://www.jooq.org/) (currently only supporting MySQL)
 * Encryption using Java AES256 crypto libraries
 * Encrypted, database-validated session variables (work in progress)
 * Static HTML content minifier (work in progress)

The framework is currently in its very early stages of development and thus does not feature more than some core features. Feel free to post issues in the repo.

_Please note that JetSet has a very different approach to web Java than most frameworks. It does not feature any CLI interface, it does not support JSP/JSPX, Maven or any other technologies. Instead, it aims to provide a lightweight and easy-to-use framework for less-demanding projects._

Getting Started
---------------

JetSet comes packed with everything it needs bundled. To run it you would need the following:
* A servlet container to run JetSet in. Most should do thanks to Java EE's standardization. JetSet runs great with *Tomcat 7* or *Jetty 9*.
* JRE 7. For encryption to work, you need to replace security files with newer ones from Oracle.

For your IDE to understand JetSet, you need to do the following:
* (Todo)
