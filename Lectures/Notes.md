# EECS293 Lecture Notes

> Software Craftsmanship Spring 2020
> Yue Shu

## Week One

- [EECS293 Lecture Notes](#eecs293-lecture-notes)
  - [Week One](#week-one)
    - [Iterative vs Sequential development](#iterative-vs-sequential-development)
      - [Sequential](#sequential)
      - [Iterative](#iterative)
    - [Problem Definition](#problem-definition)
    - [Requirement Development](#requirement-development)
    - [Software Architecture](#software-architecture)
  - [Week Two](#week-two)
    - [Straight Line Code](#straight-line-code)
    - [Strings](#strings)
    - [Floating Point Calculation](#floating-point-calculation)
    - [Prefer Lists to Arrays](#prefer-lists-to-arrays)
    - [Boolean Expressions](#boolean-expressions)
    - [Conditional Statements (if, else, switch)](#conditional-statements-if-else-switch)
  - [Week Three](#week-three)
    - [Recitation](#recitation)
    - [Loops](#loops)
      - [Entering the Loop](#entering-the-loop)
      - [Middle of Loop](#middle-of-loop)
      - [Exiting the Loop](#exiting-the-loop)
      - [Break/Early Return](#breakearly-return)
    - [Structure Code](#structure-code)
      - [Three Types of Structure Code](#three-types-of-structure-code)
    - [Complexity](#complexity)
      - [Complexity Measurement by McCabe](#complexity-measurement-by-mccabe)
      - [Assignment Complexity Requirement](#assignment-complexity-requirement)
      - [Fix Complexity](#fix-complexity)
  - [Week Four](#week-four)
    - [Recitation](#recitation-1)
      - [Review](#review)
      - [Code Demo](#code-demo)
    - [Design](#design)
      - [Accidental Difficulties](#accidental-difficulties)
      - [Essential Difficulties](#essential-difficulties)
    - [Objective of Software Design: Manage Complexity](#objective-of-software-design-manage-complexity)
      - [Abstractions](#abstractions)
      - [Encapsulation](#encapsulation)
      - [Information Hiding](#information-hiding)
    - [Coupling](#coupling)
  - [Week Five](#week-five)
    - [Recitation](#recitation-2)
    - [Routines (methods)](#routines-methods)
    - [Cohesiveness](#cohesiveness)
    - [Routine Names](#routine-names)
      - [Prelim](#prelim)
      - [Rules](#rules)
      - [Routine Parameters](#routine-parameters)

### Iterative vs Sequential development 

#### Sequential

- could somehow jump directly to a solution
- major steps: requirement development -> construction planning -> software architecture -> unit testing -> integration testing etc. 

#### Iterative

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

## Week Two

### Straight Line Code

- sequence of instructions without branching (if/for/etc.)
- shud organize the logical order of the straight line codes when there are dependencies/order of executions required
- highlight the logic orders in your code: 
  - insert parameters in each invocation of the methods to indicate modification on the data

### Strings

- not the best data type to use 
- for example

  ```Java

  String lastName;
  String age;
  String option;
  ```

- use int instead of String for `age`
- use emum instead of String for `option`
- inappropriate use of string as aggregate type

  ```Java

  String compoundKey = className + "#" + i.next();
  ```

- in the exmaple above you have to parse the string every time, declare a new class to contain the compound information 


### Floating Point Calculation

- for anything that require precise number (such as in monetary calculation), it's inappropriate to use floating point numbers
- instead of using double, use int (aka make the unit cent instead of dollar); could also use long to avoid overflow
- when the data is too large, use `BigInteger`, a built-in type supported by Java, allows infinitely large numbers until the memory of computer overflows

### Prefer Lists to Arrays

- uable to declare generic arrays

### Boolean Expressions

- should write code for better readibility rather than for your own convenience
- never compare with true or false using `if (Error == true)`, do `if (error)` and `if (!error)` instead

  ``` Java

  boolean areSufficientlyLarge(int x, int y) {
    if (x > 3 && y > 4) {
      return true;
    }
    else {
      return false;
    }
  }
  ```

- change the code block above to one line of `return x > 3 && y > 4`
- use short-circuit evaluation (logical AND) in most cases
- try to avoid the nested if block below

  ``` Java

  if (x == 0) {
    if (y > 0) {
      ...
    }
  }
  ```

- to express $ 0 < x <= 13$, write `0 < x && x <= 13` instead of `x > 0 && x <= 13`
- `==` compares the references instead of the actual equivalence
- `.equals()` compares the actual content inside (aka the equivalence), pre-defined in most object classes

### Conditional Statements (if, else, switch)

- use else clause even if it's just a comment -- put helpful comments to indicate you've thought about the condition (eg: the case shouldn't happen, etc.
- **Avoiding Deep Nesting**
  1. exit the clause before entering the nested conditional statement (make the checking at the beginning)
  2. cut blocks of nested if statements, make it into a method to segment different sections of conditions and responsibilities
  3. create a table for input to map the outputs instead of creating blocks of conditional statements as the example below

    ``` Java

    charType = charTypeTable[input char];
    ```

- **Avoiding Multiple Switches**

  ``` Java

  switch (X) {
    case (a) {
      ...
    }

    case (b) {
      ...
    }

    case (c) {
      ...
    }
  }
  ```

  - instead of using multiple cases in switch as above, create classes `A`, `B`, `C` implementing interface `X`
  - override proper version of methods in each of `A`, `B`, `C` from `X` method stub

## Week Three

### Recitation

- Use the one-line code below to require a parameter to be not null.

  ``` Java

  Objects.requireNonNull(identifier);
  ```

- structure: `user` - `interface` (the API) - `core`
- we use builder to allow the users to interact in the interface, and the builder then invoke the constructor
- in the case of `typeinference::TypeName`, the constructor is in the core package, and `of` is the builder, in the interface.
- always make the content in the lambda expression `final` for stream

  ``` Java
  if (!allidentifiersadd(Objects.requireNotNull(identifier))) {
    throw new IllegalStateException();
  }
  ```

- helper method > comment
- have an individual class as the database instead of using static field
- make nested class for testing mock (have non-static field for testing, not for actual usge)
- getter below constructor
- builder, constructor, getter
- override at very bottom
- put helper closer to the method
- overloading `BasicType::of`
- just assume using basic type 

### Loops

- `for`, `while`, `do ... while`, `for-each`

  ``` Java

  /* argument: list of widgets, return total weight of red widgets */
  int redWeight(List<Widget> widgets) {
  var sum = 0;
  for (var i = 0; i < widgets.size(); i++) {
    var widget = widget.get(i);
    if (widget.getColor() == RED) {
      sum += widget.getWeight();
    }
  }
  return sum;
  }
  ```

- problems with the code above:
  - go back to the primitive data structure of array, abandoned the nice features of List
  - the list could potentially be a LinkedList, which requires $O(n^2)$ time to finish the entire program
- use for-each loop instead:

  ``` Java

  int redWeight(List<Widget> widgets) {
  var sum = 0;
  for (Widget widget: widgets) {
    if (widget.getColor() == RED) {
      sum += widget.getWeight();
    }
  }
  return sum;
  }
  ```

- always use for-each unless there's a very compelling reason to use for
- could also apply Stream to replace loops and simplify the code

#### Entering the Loop

(refer to the code in the previous section)

- initialization code

	``` Java

	var sum = 0;
	```

- should be kept as close to the beginning of the loop as possible
- use appropriate for loop header so that the initializer of the loop, the check termination, and the make progress part fit in logically
- use while instead of for loop when appropriate
- use for each loop when the object is `Iterable`
- `while (true)` vs `for (i = 0; i < LARGENUMBER; i++>)`
  - in general, use the former one when an infinite loop is needed
  - could conscioulsy use a safe limit as the latter one instead of infinite loop for error handling

#### Middle of Loop

- keep house keeping statement all together (eg: at the bottom)

#### Exiting the Loop

- do not alter the increment value to confuse loop exit
  - use a break statement instead when needed
- do not put a lot of break statements in your code
  - confuses when/where to exit the loop
- use break to avoid code repetition
- always label the break statements when you use one

``` Java

do {
	...
	switch
		...
		CALL_CENTER_DOWN:
		if () {
			...
			break CALL_CENTER_DOWN;
			...
		}
		...
} while (...);
}
```

#### Break/Early Return

- avoid duplicate code in the loops
- changing a while loop to a do while loop might not solve the problem
- sometime we might need a break statement or an early return as the solution

### Structure Code

#### Three Types of Structure Code

1. sequential code
2. conditional code
3. loop statements

### Complexity

- inherent, you cannot remove it, yet should manage it
- cyclamatic complexity is related to the number of **branches** in the code

#### Complexity Measurement by McCabe

- start from 0
- add 1 for each of the following key words:
  - `if`, `while`, `do-while`, `for`
  - `&&`, `||` (branching points with short-circuiting)
    - `if(a && b)` is equally complexed as `if(a) { if (b)}`
  - every single `case`
- add 0 for the following:
  - `switch`, `else`, `default`, `!`
  - `try-catch()-finally`, `throw`

#### Assignment Complexity Requirement

- 0 - 4: possibly fine
- \> 4: automatic C for the assignment
- in general, the smaller the better

#### Fix Complexity

- break into routines
- OO approach (object factories)
- apply table-driven methods (hashtable, etc.)

## Week Four

### Recitation

#### Review

- enter the loop from one location only
  - don't enter in the middle (eg: counter from 4/10)
- put initialization code directly before the loop
  - put variables as close to their use as possible
- don't use for loop when while loop is better
- use while(true) for infinite loops
- consider using break statements rather than boolean flags in a while loop (labeled if available)
- prefer for each loops to traditional for loops
- optimize judiciously

#### Code Demo

- use assert in the code for testing to notify error cases that should never happen
  - after testing, comment out the assertion
- for the `CompoundTypeEntry::toString` method, use stream as below instead:

``` Java

type.toString() + subtypes.Stream().map(subtype -> substring.apply(subtype)).collect(Collectors.joining(",", "<", ">"))
```

- in java, clone only provides a shallow copy
- avoid overhead clone in the code, save copy if appropriate
- try to use stream to reduce complexity

### Design

- wicked problem
- non-deterministic
  - could exist various solutions
- iterative process
- restrictions
  - trade off of your design

#### Accidental Difficulties

- brand new library used

#### Essential Difficulties

- inherient to the process of designing software
- complexity

### Objective of Software Design: Manage Complexity

#### Abstractions

- form convincing abstractions to manage complexity
- Use `interface` instead of `implementation`
  - the implementation could break the abstraction of the object

#### Encapsulation

- encapsulate the details of the software

#### Information Hiding

- for example, the identifier could reveal the sequence of admission?
  - create a class Identifier and create methods as needed
- how to use the abstraction to properly ensure encapsulation

### Coupling

- loose vs tight
  - USB is a loose coupling
  - soldering is a tight coupling
  - should provide standard interface, refer to the interface as form of interaction (plugin/out)
  - tight coupling: **semantic coupling**

## Week Five

### Recitation

- refer to objects by their interface
  - more flexible
  - always use least specific class
- for consistent abstractions
  - consistenly ignore the same irrelevant details to save complexity
    - eg: class: Animal (move method), subclass: Bird, Fox
    - bird: move fly, fox.movefrontleftleg movefrontrightleg
    - abstract out move from fox and bird and put into animal
    - as a result, both method of the detailed implementation should be `move`
    - encapsulate the detailed movements of Fox and Bird, make them private
- keep coupling loose
  - work with info hiding and encapsulation
    - eg: `TypeSystem::of`: even though TypeName checked null input, we should still make TypeSystem check for null input
  - instead of have `GameButton` and `ExitButton`, `PlayButton` extending the game button,
    - have interface `Button` and method stubs
  - prefer interfaces to extension
  - semantic coupling:
    - you know smething happens somewhere else, so you do something because you already know
    - passing around data internally
    - only pass part of the object when you can, try to avoid passing the entire object
- class structure sequence:  public - default - private
  - fields
  - constructor, builder
  - getter
  - helpers
  - methods w/ helpers
  - @Override

### Routines (methods)

- avoid repeated code
- manage complexity
- introduce abstractoins
  - using abstractions to reduce repeated code and split the routines
  - sometimes have abstraction in spite of complexity to allow for future refining/improvement in performance
- support inheritance
  - having a routine support the overriding in subclasses/implementation
- improve portability
  - could create routine to check the compatibility of systems so we don't need to check it everytime
- performance
  - have external overhead execution instead of inline execution

### Cohesiveness

- functional cohesiveness
  - a routine should do one and only one thing
  - primary
  - eg: `Math.sin(double x)`, `eraseFile()`, `computeTotals()`
  - for the examples above, a bad example would be `computeTotalAndEraseFile()`
    - not functionally cohesive
    - name is too long
- sequential cohesiveness (?)
  - less important
  - birthdate --> age --> time to retirement
  - could pack the routines due to sequential
  - purely due to laziness, just split the routines!!
- communication cohesiveness (?)
  - birthdate --> age
  - birthdate --> time to retire
  - pack two routines into the same because both parameters are the same
  - this is purely due to laziness!
- temperal cohesiveness

  ``` Java

  void shutDown() {
    ... 100 lines to save totals
    ... 100 lines to erase file
  }
  ```

  - just replace the lines above with `saveTotals()` and `eraseFile()`
  - it's okay to have a temperal point that has meaningful functionality
- logical cohesive (???)

  - example:

  ``` Java

    void foo() {
      // not mutually cohesive
      case(...) {
        // should become its own method
        ... 100 lines to save totals
      }
      // same below
      case(...) {
        ... 100 lines to save totals
      }
      case(...) {
        ... 100 lines to save totals
      }
      default {
        ... 50 lines
      }
    }
  ```

### Routine Names

- primary reason to create routine is to control complexity by creating meaningful **abstraction**
- reader of your code unaware of your meaningful abstraction without a clear and intuitive name

#### Prelim

1. name everything at a high level
   - `printReportAndEraseFile` is a good name for a bad routine cuz it reveal its not cohesive natur
2. avoid meaningless verbs or numbers
   - `processInput`, `handleOutput`
   - `foo`, `bar`
   - `print1`, `print2`, `print3`

#### Rules

- a routine is either a function or a procedure
- function
  - primary purpose is to **return a value**
  - eg: `Math.sin(x)`
  - *should be named by the value it returns*
  - special cases:
    - getter methods: `get[variablename]`
    - boolean value: phrased as question
    - conversions: `[a]2[b]`
- procedure
  - primary purpose is to p**erform an operation**
  - eg: `eraseFile()`
  - should be named as `[verb][object]`

#### Routine Parameters

1. number of arguments <= 7
   - eg: if too many parameters in the routine, the parameters can actually relate to each other
   - `foo(x1, x2, x3, ..., x100)`, first three related to RouteNode, the rest to Airport
   - create meaningful abstractions(classes) for the class above, change the code to `foo(RouteName, Airport)`
2.  consistency with the order of arguments