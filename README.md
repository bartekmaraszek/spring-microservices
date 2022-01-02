# Getting Started

### Udemy Course

This repository is based on working through Udemy course
'Master Microservices with Spring Boot and Spring Cloud'

### IntelliJ Setup

The project uses Maven to build. The project files are from IntelliJ.
In order to enable hot reload in IntelliJ:

* In `File --> Settings --> Advanced Settings`, enable build whne the application is running
* In `File --> Settings --> Build, Execution, Deployment --> Compiler`, enable auto-build
* The hot reload works **after** new build files are placed into the `/target` directory
* The difference between Build and Rebuild in IntelliJ is that Build is incremental and Rebuild makes the whole project
* You can bind a key shortcut to build action e.g. `ctrl + q`
* If running in WSL, make sure that the appropriate SDK and maven are selected
* If running in WSL, it's better to use IntelliJ `New Project` option to create the project. Otherwise it may not work.

### GitHub Setup

* It's best to use SSH key and set git remote using the SSH URI
  * https://linuxize.com/post/how-to-set-up-ssh-keys-on-ubuntu-20-04/
* If using the Authentication Token as a password, it may not be remembered by the operating system but the SSH key is always remembered correctly
* Adding and removing remotes:
  * `git remote add origin <URI>`
  * `git remote rm origin <URI>`
  * `origin` can be swapped for other remote name

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/#build-image)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

