# My Notes App API

@author stevelyall

RESTful web service backend for simple notes app, built with Spring. 

No data is persisted between server restarts, but some initial mock data is included.

Unit tests are written for the controller and services.

#### Features:
* View a list of all notes
* Create new notes, with a title and content
* Simple request validation

No authentication has been implemented.

#### Validation rules:
* Title has a max length of 255 characters
* Note content has a max length of 1024 characters

## Build

Run `mvn clean install` to build.

## Running unit tests

`mvn surefire:test` to run the unit tests.


## Development server

Run `mvn spring-boot:run` for a local dev server.

The API will be available at http://localhost:8080/.


## API Documentation

### /notes
##### GET

Retrieve all stored notes.


Example request:
```
GET http://localhost:8080/notes
```

Example Response:
```
[
    {
      "noteId": "0c5376bb-3527-460f-b0fd-a89b339d3882",
      "title": "Example Note Title",
      "content": "This is an example of a note's content."
    },
    {
      "noteId": "0df1498b-a88c-4508-a5ac-a03a0640168a",
      "title": "Another Example Note Title",
      "content": "This is another example of a note's content."
    }
 ]
```



##### POST 

Create a note.

Example request:
```
GET http://localhost:8080/notes
```



```
{
    "title" : "New Note Title",
    "content" : "This is a new note's content."
 }
```

Example Response:
```
{
    "noteId": "95e4952a-70e9-46fc-975f-edf2064ea698",
    "title" : "New Note Title",
    "content" : "This is a new note's content."
}
```