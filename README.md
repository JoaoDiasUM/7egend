# 7egend

[Screen_recording_20240716_221650.webm](https://github.com/user-attachments/assets/4da3ee6e-d7c7-41f4-b342-c5a022a4ae3d)

## Repository Description

This repository was created with the aim to build a sample messaging app for the 7egend technical challenge.
This app was made with the goal to read, write and send messages.

## Prompt description analysis

#### All the messages and users info need to be loaded when the app’s launched and locally stored.
For this I considered Room or Realm DB and considered when and how should the contents be stored.
Messages should be updated every time the app is opened in case there are new messages coming from the API and updated when an user send or would receive a message but should take care to only do the DB operations when needed and in the most efficient way.

#### You can use any third party libraries or frameworks, as long as you can properly justify why you’re using them.
I considered using a library that would help with creating a chat but i both found that a lot of them are paid kits and that i wanted to try my more direct approach to it. For third party libraries i used some like Turbine to facilitate some processes like in testing kotlin Flows.

#### We recommend you to use the MVP or MVVM architecture, but feel free to use the architecture you feel more comfortable with.
The architecture used was MMVM and Clean architectures to best separate business and UI logic while being more modular, reusable and easier to maintain.
S.O.L.I.D principles were also considered.

#### You need to assume that we are one of the existing users in the API, which means that the messages with the id of the chosen user need to have a different design from the others because they will be our messages.
For this purpose a single user, Erwin Howell, was chosen so all messages related to him are treated as our own, same with attachments.

#### All the message attachments must be displayed.
For this although I initially considered adding more kinds of attachment than the url's provided, i decided it would take too much time.

## Implementation Notes


## Limitations
For the users, their avatar images are not able to be retrieved because the API endpoint shows as outdated.
For the attachments, sometimes the links for the images become unresponsive so only placeholders are shown.

## Considerations
Considered the user actually adding attachments but it didnt feel it would work well with the data and the challenge time restraint.
Considered a way to first generate a response message after a message was sent and then periodically send more to simulate a more realistic chatting experience.
Considered adding a loading effect to some transitions.
