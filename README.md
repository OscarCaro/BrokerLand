Welcome to the Broker Studios final project!

If you want to gain some insight on the project, here is some useful information.

## Project Structure
The repository is divided into 2 projects. The **Broker** folder contains out project,  and the **WastelandMud** is an external project we took references from.

## Feedback
If you notice any issue while running the program, or want to give some feedback, please refer to the [Issues Page](https://github.com/UCM-FDI-IS2-2020/se2-project-broker-studios/issues).

## Planning
We are following the agile methodologies. To have a look at our schedule and management, visit the [Projects Tab](https://github.com/UCM-FDI-IS2-2020/se2-project-broker-studios/projects).

## Code insights
All the detailed information for developers is kept on the [Wiki](https://github.com/UCM-FDI-IS2-2020/se2-project-broker-studios/wiki). For specific topics, here are some direct links:
### User Stories
The user stories are recopilated under the [User Stories](https://github.com/UCM-FDI-IS2-2020/se2-project-broker-studios/wiki/1.-User-Stories) tab on the wiki.
### Architecture
Find a specific explanation on how the code is organized at [Architecture](https://github.com/UCM-FDI-IS2-2020/se2-project-broker-studios/wiki/2.-Architecture)
### Java Patterns + UML Diagrams
This project contains several standard **Java Patterns**. Find all the documentation and diagrams in the [Java Patterns](https://github.com/UCM-FDI-IS2-2020/se2-project-broker-studios/wiki/3.-Java-Patterns) tab on the wiki.
If you directly want to take a look at the **UML Diagrams**, go to the diagrams folder inside the Broker folder.
### Testing
The development of the project has been done with testing techniques. To get more information about it, visit the [Testing](https://github.com/UCM-FDI-IS2-2020/se2-project-broker-studios/wiki/4.-JUnit-Tests) tab. Also you can check out the tests folder inside the src folder in the project to take a look at the tests and run them yourself.
## Jar File
The jar file can be run as any other jar file without the input of any argument (with command 'java -jar [path]\Broker.jar' in the console terminal, or any other way of your preference). This will create a game with the "NORMAL" premade difficulty. If you input the argument -h (java -jar [path]\Broker.jar -h). You will get the following text: 

usage: controller.main [-1 <arg>] [-2 <arg>] [-3 <arg>] [-4 <arg>] [-5<arg>] [-a <arg>] [-b <arg>] [-c] [-d <arg>] [-h] [-m <arg>] [-s<arg>] [-v <arg>]
              
-1,--type1 <arg>               Ratio between 0 and 1 of AGGRESSIVE brokerbots (Only needed if -c is enabled).
-2,--type2 <arg>               Ratio between 0 and 1 of DUMBASS broker bots (Only needed if -c is enabled).
-3,--type3 <arg>               Ratio between 0 and 1 of GREEDY broker bots (Only needed if -c is enabled).
-4,--type4 <arg>               Ratio between 0 and 1 of KNOWLEDGEABLE broker bots (Only needed if -c is enabled).
-5,--type5 <arg>               Ratio between 0 and 1 of RANDOM broker bots (Only needed if -c is enabled).
-a,--adaptability <arg>        Adaptability to adversity ratio of the bots (Only needed if -c is enabled).
-b,--bots <arg>                Number of bots that will accompany you.
-c,--custom                    Custom play mode, to play this you will need to input options 1, 2, 3, 4, 5, a, s, m, b.
-d,--premadeDifficulty <arg>   Set premadeDifficulty, values are: Easy, Normal, Difficult, WorldTradeCenter.
-h,--help                      Print this message.
-m,--minNum <arg>              Minimum number of assets on the market (Only needed if -c is enabled).
-s,--startingNum <arg>         Starting number of assets on the market (Only needed if -c is enabled).
-v,--volatility <arg>          Ratio between 0 and 1 of market volatility (Only needed if -c is enabled).
        
        
 Though it is all self-explanatory, it is notable that there are two ways to run the program:
 
 -Through a premade difficulty using argument -d. 
 -Through a custom difficulty using arguments -c -1 -2 -3 -4 -5 -b -a -m -s -v. 
 
 These would be some examples of the usages explained above:
 
 - java -jar [path]\Broker.jar -d WORLDTRADECENTER (This creates a new game in WORLDTRADECENTER difficulty).
 - java -jar [path]\Broker.jar -d easy (This creates a new game in EASY difficulty).
 - java -jar [path]\Broker.jar -d easy (This creates a new game in EASY difficulty).
 - java -jar [path]\Broker.jar -c -1 0.1 -2 0.2 -3 0.3 -4 0.2 -5 0.3 -a 0.7 -s 3 -m 2 -b 30 -v 0.3 (This creates a new game in a custom difficulty with: 30 bots, 0.1 * 30 aggressive brokers, 0.2 * 30 dumbass brokers, 0.3 * 30 greedy brokers, 0.2 * 30 knowledgeable brokers, 0.3 * 30 random brokers, 0.7/1 adaptability ratio for every bot's behaviour, 3 starting assets, 2 minimum assets in the market allowed, and 0.3/1 market volatility).

It is also to be noted that if one uses -c but does not add all custom game parameters an according exception will stop the execution of the code. Also if one inputs a complete custom difficulty but also uses the -d argument the program will ignore the premade difficulty argument and enter the custom difficulty the user created.

