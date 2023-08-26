# Deal or No Deal

**Deadline: 9.Sept 2023 23:00**

Write a Java program that implements the logic of the TV show 'Deal or No Deal' as an interactive game.

The rules of the game can be found at: [Wikipedia](<https://en.wikipedia.org/wiki/Deal_or_No_Deal_(American_game_show)>)

On YouTube, there are numerous examples of the live show: [Youtube](https://www.youtube.com/watch?v=oSamPf4ckOo&t=253s&ab_channel=America%27sGameShows)

## Lifecycle of the game:

- First, the user is asked which suitcase he wants to pick for his prize.
- If the player doesn’t end the game before, 9 rounds are played in total where the player picks suitcases to eliminate and learns the contents of those suitcases one by one.
- At the end of each round the bank makes a monetary offer to the player to exit the game early based on which suitcases are still in play.
- If the player accepts the offer, he wins the offered amount, and the program exits.
- After 9 rounds only 2 suitcases remain in the game (the one the player picked and another one). The user is offered to switch his case he chose at the beginning of the game with the other one left in play.
- The player wins the content of the suitcase, and the program exits.

## Suitcases:

Each suitcase has a certain number (1-26) and a value (in $ or in ¢). For the game, the cases are mixed up and the player never knows the value of them. Analogous to the Wikipedia article, the values hidden in the 26 cases are:

```
$0.01

$1

$5

$10

$25

$50

$75

$100

$200

$300

$400

$500

$750

$1,000

$5,000

$10,000

$25,000

$50,000

$75,000

$100,000

$200,000

$300,000

$400,000

$500,000

$750,000

$1,000,000
```

For each round, the player can eliminate a certain number of cases:

| Round            | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| ---------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Eliminated Cases | 6   | 5   | 4   | 3   | 2   | 1   | 1   | 1   | 1   |

## Bank Offer Formula:

First, calculate the total value of the remaining suitcases, including the one picked by the player. Then divide this total value by the number of remaining suitcases to get an expected value. Lastly, multiply the expected value by the current round and then divide it by 10.

## Handling Error:

In case of a wrong input, the user should be notified and can retry.

- If the input is a number, the following errors should be handled: The user types in a number which is not in range, or which is not a number, or the number of the case was already taken.

- If the input is a string/character: The user types in an invalid character.

## Java Implementation:

For this assignment, there is no template you have to follow. You are free to use any class and data structure which works best for you. But don’t forget to separate the logic in different classes and methods and keep the main method small.

## Debugging:

At the beginning of the game, the user should be asked if the wants to start the game normally or if he prefers the debug mode. For this mode, the suitcases are not shuffled but the values go from 1¢ in case 1, to 1 000 000 in suitcase 26. Then it should be easier for you to spot any programming error.

As a guideline, I added two output examples: 
### Example 1: output in Debug Modus without errors and ending of user
![OutputExamples without errors_1](https://github.com/Amadeus1791/DealOrNoDealTemplate/blob/main/Examples/output%20in%20Debug%20Modus%20without%20errors%20and%20ending%20of%20user/Image%202.png)

![OutputExamples without errors_2](https://github.com/Amadeus1791/DealOrNoDealTemplate/blob/main/Examples/output%20in%20Debug%20Modus%20without%20errors%20and%20ending%20of%20user/Image%203.png)

### Example 2: output with errors
![OutputExamples with errors](https://github.com/Amadeus1791/DealOrNoDealTemplate/blob/main/Examples/output%20with%20errors/Image%204.png)
Your output doesn’t have to match exactly 100 percent, you can also be creative. If you have any questions, don’t hesitate to ask. I think it’s a great game to implement in Java. Have fun with programming and playing the game😊

### Examples as txt
* [OutputExamples without errors](https://github.com/Amadeus1791/DealOrNoDealTemplate/blob/main/Examples/output%20in%20Debug%20Modus%20without%20errors%20and%20ending%20of%20user/output%20in%20Debug%20Modus%20without%20errors.txt) 

* [OutputExamples with errors](https://github.com/Amadeus1791/DealOrNoDealTemplate/blob/main/Examples/output%20with%20errors/output%20with%20errors.txt)
 
