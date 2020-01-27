# Type Inference

  Yue Shu  
  EECS293  
  Spring 2020  
  Prof. Libertore  

## Project Overview

This is the project of programming assignments 2-5 for EECS293, Type Inference, which is a type inference system similar to that of Java's.

## Usage

### Operating Environment

The project is built under a `maven` structure, thus to compile and run the project, maven is required to be installed in the operating environment. If maven is not installed in your system yet, follow the instructions below according to your operating system: [Windows](https://howtodoinjava.com/maven/how-to-install-maven-on-windows/), [MacOs](https://github.com/rajivkanaujia/alphaworks/wiki/Installing-Maven), or [Linux](https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html).

### Compile and Run

Once you have maven properly installed, go to the project directory `typeinference/` where a maven manifest `pom.xml` and other configration files come along with the project source code. To compile, type the following command in your command line:

``` bash
~/typeinference$ mvn compile -f pom.xml
```

You should see some output similar as below:

``` bash
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< edu.cwru.yxs626:typeinference >--------------------
[INFO] Building typeinference 1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ typeinference ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/gentsk77/GitHub/EECS293/Assignments/typeinference/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ typeinference ---
[INFO] Nothing to compile - all classes are up to date
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.582 s
[INFO] Finished at: 2020-01-27T02:55:55Z
[INFO] ------------------------------------------------------------------------
```

The project doesn't contain any `main` method in any of the classes by now, so to test the correctness of the project, unit tests are provided. Again, type the following command in your command line to run the provided unit tests:

``` bash
~/typeinference$ mvn test -f pom.xml
```

The output should be similar as below:

``` bash
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< edu.cwru.yxs626:typeinference >--------------------
[INFO] Building typeinference 1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ typeinference ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/gentsk77/GitHub/EECS293/Assignments/typeinference/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ typeinference ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ typeinference ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/gentsk77/GitHub/EECS293/Assignments/typeinference/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ typeinference ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ typeinference ---
[INFO] Surefire report directory: /home/gentsk77/GitHub/EECS293/Assignments/typeinference/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running edu.cwru.yxs626.typeinference.BasicTypeTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.046 sec
Running edu.cwru.yxs626.typeinference.TypeNameTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec

Results :

Tests run: 7, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.024 s
[INFO] Finished at: 2020-01-27T03:00:07Z
[INFO] ------------------------------------------------------------------------
```

You can also add customized unit tests under the test folder `src/test/java/edu/cwru/yxs626/typeinference/` to further validate the correctness of any components of the project.
