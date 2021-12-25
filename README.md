# pokerhandcalculator
A tool to evaluate two poker hands and determine the better one. You can create your own hands or use the Dealer class to create random hands.


## SETUP
To run this project, you will need to clone this repository to your local machine.
Make sure you have the jdk 15 installed.
To run the tests you will need junit5

Example setup for Eclipse IDE:
1. run git clone https://github.com/Jo-bot86/pokerhandevaluator.git in a directory of your choice
2. start eclipse and goto File->Import->Git->Projects from Git (with smart import) and click next 
3. choose existing local repository and click next
4. now click on add and select the directory you have just cloned. It should contain the .git directory
5. now select the repository and click finish, next and finish again.
6. make a right click on the project folder and select Properties->Java Build Path->Libraries->Classpath and click Add Library
7. after selecting the junit library you will be asked to specify a version. Choose JUnit5, click finish and Apply and Close.

Thats it. Now you should be able to run this project and all tests on your local machine.

## Package structure
The folder structure of the project is as follows

```
project
│   README.md
│      
└───src
    │
    └───de.pokerhandevaluator
    │   │   Calculator.java
    │   │   Dealer.java
    │   │   Main.java
    │   │
    │   └───hand
    │       │   Hand.java
    │       │   HandRanking.java
    │       │
    │       └──card
    │          │   Card.java  
    │          │   CardSuit.java
    │          │   CardValue.java
    │          
    │          
    └───test  
        │   CalculatorTest.java
        │
        └───hand 
            │   HandTest.java      
```

The Main.java contains the main method and is therefore the entry point of the program.

The Calculator.java contains all methods to evaluate two hands with the same hand ranking.

The Dealer.java allows you to randomly create two hands

The Hand.java mainly contains methods to determine a hand ranking where the different hand rankings are described in the enum class HandRanking.java.

The class Card.java is a DTO and describes a poker card, while the enum classes CardSuit.java and CardValue.java are used to describe the suit and the value of the card.

CalculatorTest.java and HandTest.java contains test cases for Calculator.java and Hand.java.

## Run
To run the project from the console navigate to the src folder and set the classpath. 
Therefore you can simply run following commands to compile and run the project.
 
```cmd
javac -cp . de/pokerhandevaluator/Main.java
java de/pokerhandevaluator/Main.java
```

