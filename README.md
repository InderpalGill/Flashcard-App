# Flashcard Personal Project

## Project Description
A large process of learning involves committing learned material to memory, and being able to recall that material when
necessary. A common study device used by many students is Flashcards. Flashcards can be effective in helping promote
active recall in the brain. Additionally, flashcards can be created such that the material written is in the user's own
words, helping to create more meaningful connections to the learned content.

This application is a flashcard program, allowing users to create and modify flashcard decks, to which they can add 
flashcards. The flashcard decks can be used for studying and practice. This application is intended for students as a study and test 
preparation tool. The application includes the following features:
- The user of the application can create a **Flashcard Deck** that can hold flashcards for studying.
- The user can then create, add, and remove **Flashcards**, which have a question and an answer, to a Flashcard Deck.
- Users can edit flashcards in a flashcard deck, changing the questions and/or answers of any flashcard.
- Users can create multiple flashcard decks and can select a deck which they want to practice/study.
- During a practice/study session, the question portion of the flashcard will be visible to the user at first, then they
can select to view the answer.
- During a practice/study session the user can indicate if they got a particular flashcard correct or incorrect.
- At the end of each practice/study session, the user will receive feedback of what percent of flashcards they answered
correctly.
- All created flashcards and flashcard decks are saved.
- The user can modify and/or delete any flashcard deck.

This project is of interest to me because, as a student, I regularly use flashcards to help me study and prepare for 
examinations. I wish to create an application that would help me create and keep track of my study habits, as well as
provide this resource to other students and learners. 





## User Stories

- As a user, I want to be able to create a new Flashcard Deck, and be able to add Flashcards to it.
- As a user, I want to be able to view the list of Flashcards in any Flashcard Deck.
- As a user, I want to be able to select which Flashcard Deck I want to use.
- As a user, I want to be able to mark my response to a Flashcard as correct (or incorrect) when studying a Flashcard Deck
- As a user, I want to be able to delete a Flashcard from a Flashcard Deck.
- As a user, I want to be able to delete an entire Flashcard Deck.
- As a user, I want to be able to edit and modify any Flashcards in a Flashcard Deck.
- As a user, I want to be able to see statistics of how many Flashcards I answered correctly (or incorrectly) 
after studying a Flashcard Deck.
- As a user, I want to be able to save my Flashcard Decks. 
- As a user, when I quit the program, I want to be reminded to save my Flashcard Decks to file and have the option to do
so or not.
- As a user, I want to be able to load my Flashcard Decks from a previously saved file.


# Instructions for Grader

- You can generate the first required action related to the user story "adding multiples X's to a Y", in this case adding
multiple objects of FlashcardDeck class to FlashcardDecks class, by clicking on the button "Create a new Flashcard Deck"
in the main menu when the application starts up. This will let you enter the name of the new FlashcardDeck you want to
create. You can check that it has been created by clicking either on the button "Select an existing Flashcard Deck" or
the button "Delete a Flashcard Deck", as both will show you a list of current FlashcardDecks to choose from.
- You can generate the second required action related to the user story "adding multiples X's to a Y", in this case adding
multiple objects of Flashcard class to FlashcardDeck class, by first clicking on "Select an existing Flashcard Deck" in
the main menu once a FlashcardDeck object has been created, then in the next menu you can click on the button with the 
name of the Flashcard Deck you would like to select to add a Flashcard to. Finally, in the next menu, you can click on the
"Create a new Flashcard to add to this deck" button, which will ask you first to enter the question string of the Flashcard to
be created, then will ask you to enter the answer string for the Flashcard to be created. The application will then ask 
you to confirm if the entered text strings are correct before creating a new Flashcard and adding it to the current
FlashcardDeck selected earlier. You can check if the newly created Flashcard has been added to the FlashcardDeck by clicking
on the "Display a list of all Flashcards in this Deck to edit or delete" button in the same menu that the "Create a new
Flashcard to add to this deck" button was located.
- You can locate my visual component when you start the application. A splashscreen with an image saying "Flashcard App"
will appear in the middle of your screen for 2 seconds, then it will fade away and the main menu of the application will
appear.
- You can save the state of my application by clicking on the "Save Flashcard Deck to file" button in the main menu. If 
you are not in the main menu, clicking the pink colored buttons "Go back to previous menu" will eventually get you there.
Additionally, when you click on the "Quit the application" button in the main menu, you will be prompted with a popup message
box asking if you want to save any changes. The popup message has the options "Yes", "No", and "Cancel". Clicking "Yes" will
save the current state of the application to file, clicking "No" will not save the current state of the application to file
and will exit the application, clicking "Cancel" will cause the message box to disappear but will not exit the application.
- You can reload the state of my application by clicking on the "Load Flashcard Deck from file" button in the main menu.
If you are not in the main menu, clicking the pink colored buttons "Go back to previous menu" will eventually get you there.

## Phase 4, task 2
- Please find below a sample of the events logged and printed to the console when the program runs.
- Tue Apr 02 16:25:16 PDT 2024\
Created Flashcard Deck: Math Cards
- Tue Apr 02 16:25:16 PDT 2024\
  Added Flashcard Deck: Math Cards, to my Flashcard Decks
- Tue Apr 02 16:25:47 PDT 2024\
  Created Flashcard, Question: What is Pythagoras's Theorem?\
  Answer: a^2 + b^2 = c^2
- Tue Apr 02 16:25:47 PDT 2024\
  Added Flashcard, Question: What is Pythagoras's Theorem?\
  Answer: a^2 + b^2 = c^2\
  to Flashcard Deck: Math Cards
- Tue Apr 02 16:26:59 PDT 2024\
  Created Flashcard, Question: How do you find the length of the opposite side of a triangle?\
  Answer: Hypotneus * cos(angle)
- Tue Apr 02 16:26:59 PDT 2024\
  Added Flashcard, Question: How do you find the length of the opposite side of a triangle?\
  Answer: Hypotneus * cos(angle)\
  to Flashcard Deck: Math Cards
- Tue Apr 02 16:27:22 PDT 2024\
  Flashcard Answer: Hypotneus * cos(angle)\
  has been changed to: Hypoteneus * sin(angle)
- Tue Apr 02 16:27:47 PDT 2024\
  Removed Flashcard: Question: How do you find the length of the opposite side of a triangle?\
  Answer: Hypoteneus * sin(angle)\
  from Flashcard Deck Math Cards
- Tue Apr 02 16:28:02 PDT 2024\
  Removed Flashcard Deck: Math Cards, from my Flashcard Decks


## Phase 4, task 3
If I had more time to work on the project, some of the design changes that I would make compared to the current program
would be to utilize the Composite Design Pattern when making the GUI. As I was creating the GUI and all the different Menu
options, I ended up replicating a lot of the same code, particularly each Menu had a similar constructor, and a similar
placeMessage(), and similar placeButtons() method. These could have been included in an abstract class or an interface.
In the UML diagram provided, there are 7 arrows that leave from FlashcardProgramUI and go to the different menus. As such,
that would mean if there is a change to the program, all 7 menus would have to be changed one by one to make sure that there
are no compiler errors. The composite design pattern could work by having an interface with the constructor, placeMessage(), and placeButtons() 
methods, and then a MainMenu component and sub Menu leaves. This would help reduce redundant code, and make making changes
to the existing code easier. Additionally, the UML diagram would be a lot more simplified with fewer arrows leaving from
FlashcardProgramUI.

Another way I could refactor the current design is to reduce coupling between the FlashcardProgramUI class and all other Menu classes.
Currently, FlashcardProgramUI has fields for FlashcardDecks, FlashcardDeck(the current Deck object of the program), and 
Flashcard(the current Card object of the program). Each Menu when created has a final field called "controller" that is of type 
FlashcardProgramUI. The purpose of this field is so that each menu can access the FlashcardDecks, FlashcardDeck, and Flashcard
fields of FlashcardProgramUI, therefore every menu class is coupled with the FlashcardProgramUI class. Reducing coupling between
FlashcardProgramUI and the other Menu classes would help make the program more robust, as currently any changes to FlashcardProgramUI
could result in compiler errors in all the other Menu classes.