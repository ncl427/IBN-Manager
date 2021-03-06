# Project Title
**Intent Based Networking Manager (IBN Manager)**

![ALT text](/readme_Images/system_diagram.png "System Diagram")


## Introduction
Intent Based Networking Manager is a module for SD-ACCESS slicing on the KOREN SmartX Open Platform. 
IBN Manager can be used to send **Host to Host intent** to SDN(ONOS) Controller. 
IBN Manager also can be used to send **Point to Point Intent** to SDN(ONOS) Controller.

This application (remotely) will interact with ONOS controller via REST and install flows to handle real time traffic.

* Make intents according to their policies 
* Compile intents into json format 
* Send them to ONOS controller
* Maintain database for each intent
* Provide functionality to delete intent specific to mac address

## Host To Host intent ONOS
Host to Host intent provide  the simple connectivity intent which enables the connectivity between two hosts. 
Host to Host intent has been compiled to two path intents with the appropriate traffic selections and actions computed on your behalf.
Host to Host Intent is based on **Mac + VlanId**. For example **0E:6A:50:E7:45:89/-1**.
It identifies the shortest path b/w two endpoints and installs flows in the appropriate switches.
 
```
{ 
    "type": "HostToHostIntent", 
    "appId": "org.onosproject.cli",  
    "priority": 55, 
    "one": "FE:1D:3D:05:BB:71/-1",
    "two": "AB:16:24:91:CC:45/-1"
 }
```
 
![ALT text](/readme_Images/host_to_host_intent.png "Host To Host intent ONOS")


## Point To Point intent ONOS
In Point To Point Intent, we have to specify the device info as well where the flow should be installed.
Point to Point intent gives a fine grained control on the path for example: 
**forward all input pkts with specific Ip s/d to a predefined out port for all the switches on the path.**
```
{ 
    "type": "PointToPointIntent", 
    "appId": "org.onosproject.cli",  
    "priority": 100, 
    "selector": {
   	"criteria": [ 
	      {
		"type": "ETH_SRC",
		"mac": "FE:1D:3D:05:BB:71"
	      }
	]
     },
    "ingressPoint": { 
        "device": "of:0000000000000001", 
        "port": "3" 
    }, 
    "egressPoint":  {
        "device": "of:0000000000000001", 
        "port": "2" 
    } 
 }
 ```
 
![ALT text](/readme_Images/point_to_point_intent.png "Point To Point intent ONOS")

## Project Structure
This application is based on MVC development architecture using Grails 3 framework. Grails 3 framework is used Gradle as build automation system.

### grails-app
Most of the things that we will write will reside in the grails-app folder. This is where our Grails specific code will reside and it follows certain conventions.
 
 ![ALT text](/readme_Images/project_structure.png "Grails3 Project Structure")

1. **Assets folder**
    - This is where we will put most of our static files that we will refer in our HTML code. 
    - It will usually contain files for JavaScript, CSS, images, and others. 
    
2. **Conf folder**:
The conf folder is where the configuration files resides. 
    - **resources.groovy** - Grails sits on top of the Spring framework. If we wish to define additional Spring objects, we can put it here.
    - **application.yml** - This is the main configuration file and most of the settings goes here.
    - **logback.groovy** - This is the configuration file for logging
    
3. **Controllers folder** 
    - The controllers folder contains controller classes. If we need to create controllers, the corresponding classes generated will go here. 
    - Core business logic of each view is implement here.
    - By default, UrlMappings.groovy is also created in this folder. This is the file where we can control how requests, depending on url format, direct to which controller or resource.
    
4. **Views folder**
    - The views folder contains GSP files. This is the equivalent of Java's JSP files in Grails framework. 
    - This is where we put HTML related code to be rendered and shown to users. 
    - Each controller will have a corresponding folder with the same name (without the controller suffix and the first letter is not capitalized). 
    - There is a special folder named layouts where templates are placed. Templates are special GSP files that we can use to avoid repetitive code. This is where we will place our web design layout. The important thing is it should contain the `<g:layoutBody/>` tag.
    
5. **Domain folder**
    -  This is where we will put Groovy classes that corresponds to a table in a database.
      
6. **Services folder** 
    - Controllers are the entry point of interaction with users. But it is not good practice to put business code inside controllers. We usually place them in services, and we invoke those services inside controllers.
     
7. **Taglib folder**
    - Grails provide taglib is how easy it which is helpful for creating a custom tag easily. This folder is where we place code for custom tags.
     
8. **i8n folder**
    - This is the folder that contains resources for translation. By default, lookups to messages are searched in messages.properties. 
    - But when users of your application has chosen a different locale, then lookups will automatically search the appropriate file. E.g. messages_ja.properties for Japanese.
    
9. **Init folder**
    - This contains files related to when your application is launched. 
    - Application.groovy is a convenience class that we can use inside an IDE to launch our Grails 3 application. Because IDE's usually has a way to run a program that has a main static method. Hence this class is provided to launch our application via main method. 

### Source Folder
The source folder contains supporting code that we can use in our Grails project: 
* integration-test - this will contain integration test codes
* main/groovy - we can write any Groovy classes here that we can use inside controllers, etc. E.g. utility classes.
* test - this will contain unit test codes

## Build Process
Many modern web frameworks in the Java space are more complicated than needed and don’t embrace the Don’t Repeat Yourself (DRY) principles. 
Grails is a full stack framework and attempts to solve as many pieces of the web development puzzle through the core technology and its associated plugins.

### Installation Requirements
Before installing **Grails 3.3.6** you will need as a minimum a Java Development Kit (JDK) installed version **1.7 or above**. 
Download the appropriate **JDK** for your operating system, run the installer, and then set up an environment variable called **JAVA_HOME** pointing to the location of this installation.

```
JAVA_HOME=/path/to/Java/jdk
PATH=%JAVA_HOME/bin%
 ```

###  Downloading and Installing
The first step to getting up and running with Grails is to install the distribution. MySQL database is required as a external database. 

#### Download MySQL Installer
 * Download and install [mysql-installer-community-8.0.11.0](https://dev.mysql.com/downloads/mysql/) version in your developer machine. 
 
#### Grails Manual installation
* [Download](https://github.com/grails/grails-core/releases) a binary distribution of Grails and extract the resulting zip file to a location of your choice
* Set the **GRAILS_HOME** environment variable to the location where you extracted the zip

```
GRAILS_HOME=/path/to/grails
PATH=%GRAILS_HOME/bin%
```
 
If Grails is working correctly you should now be able to type `grails -version` in the terminal window and see output similar to this:

```
Grails version: 3.3.6
```

##  IDE
* **IntelliJ IDEA Ultimate** is a recommended IDE for development of Java based web application using Groovy on Grails framework.
* [Download](https://www.jetbrains.com/student/) free individual licenses for students and faculty members.
* [Getting Started with Grails 3 with IntelliJ IDEA](https://www.jetbrains.com/help/idea/getting-started-with-grails3.html)
* [Creating Grails Application from Existing Code](https://www.jetbrains.com/help/idea/creating-grails-application-from-existing-code.html)

## Download 
You can clone/download project from here.
https://github.com/ncl427/IBN-Manager
