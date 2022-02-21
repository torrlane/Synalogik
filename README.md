# Synalogik
Code for the Synalogik coding test

## How to run
Pass the full path of the text file you wish to use as the first parameter to the jar e.g.
```
java -jar synalogik_test.jar bible_daily.txt 
```
The result will be outputted to the console.
Optionally, instantiating the WordCounter class with a the File object and calling its print() method will output the result to the console.

## Assumptions
- Words are bits of text seperated by white space, commas, periods, etc are excluded.
- Words containing characters like apostrophes and dashes, and other non-letter characters, only the letter characters are counted (e.g. "can't" is 4 letters long)
- If a "word" contains a digit then it is a number and all characters are counted (e.g. 1,345.78 is 8 characters long)
- The file will be UTF-8 encoded
- The text will only use latin characters - this has not been tested with Cyrillic, Greek, Chinese etc character sets
