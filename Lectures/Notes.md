# EECS293 Lecture Notes

## Week One

### Iterative vs Sequential development 

**Sequential** 
- could somehow jump directly to a solution
- major steps: requirement development -> construction planning -> software architecture -> unit testing -> integration testing etc. 

**Iterative**
- more flexible way 
- define problem first
- resent prototype that demo only parts of the features
- eg: airport prototypes w/o sbux
- major stpes: similar to pure sequential, though in iterative mannar 

Makes adjustments in timely mannar 

### Problem Definition

A short description of what **problem** your software will solve. 

Differentiate: when it is delivered, it seems to be a concise and simple statement of your solution to the problem. 

eg: when asked about a problem, people tends to describe possible solutions rather than the prolbem itself. Transportation vs Automobiles 

### Requirement Development

**User stories**: for collecting requirements 

- eg: "As a customer, I want to enter my credit card information so that I can pay for my purchase"
- can put various test cases according to the user stories 
- should prioritize the user stories (eg: according to significance/workload/phase of release etc.)

### Software Architecture


**Software organization**: the following decisions to make

- the organization of the software in terms of packages, components, classes, etc. 
- and how the pieces of software listed above interact with each other
- figure 5-5 on CC
  - application level classes: the core of this software
  - diff between UI (fields, text boxes etc.) and Graphics (reflect the changes users made in UI)
  - data storage (databases?)
  - adapting enterprise-level tools to leverage existing libraries and packages
  - separating business rules form application level classes to reduce ouhexing(. i.e. changing rules without affecting application level classes

**Error Processing**

- how to catch the error
- how to notify the users of the errors
- where to catch the error: in every method? w/ an individual error class? package?

**Fault Tolerance**

due to computer/server failure
- fig. example 
  - eg: with multiple servers, you can reassign the users to other servers with lower buffer
- single point failure: one single server in the system, should be avoided if possible
  - should be avoided in large scale distributed system

**Build vs Buy**

Buy and adopt open-sourced softwares available ones online or build it?

- buying the software could be less expensive than building it
- less flexible buying the software, unable to change
- buying/reusing the software is generally the better decision: no need to pay for developing, testing, etc.
- could also reuse open-sourced libraries/packages 
  - could have potential problems:
  - cutting edged libraries could be under-tested
  - u need to both figuring out how to use the package and buying it, producing extra cost

**Choice of Programming Language**

Java vs Python vs ...

- team expertise, desires, 
- mission critical 
- expressiveness of the language
  - fig Table4-1

**Programming IN / INTO a language**

- In: 
  - use java as the language
  - do everything in the ways of Java
- Into: 
  - come from a different language
  - borrow concepts from other languages and force them into Java
  - should be avoided in some cases without considering the conventions of the target language

eg: 
- Into: program classes from Java in C
- in `myClass.c`:
``` c
int x; // static

void foo(int y) {
    ...
}

struct myData; // use as argument 
```

- in `Ruby` no need to use `return`