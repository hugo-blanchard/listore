# These are notes to my self

#Concrete Routes :
## CREATE
### Simple registering of Users(persons) and Unit types(utypes) :
Simple casting of request body to instance of model, then save and return the new instance.

### Relational registering of Units and Actions :
- Units need a relation to a Unit type when registered
- Actions need a relation to a Person and to a Unit (in the future, to one or many units)

## READ
### Utype reads :
- When asking for multiple unit types, their relation to units should not be sent
- When getting a single unit type, their relation to units should not be sent but
the number of related units should be sent instead
### Person reads :
- when asking for multiple persons, actions relations should be excluded
- when asking for a specific person, every field should be included
### Action reads :
- route for multiple actions needs dates
- action routes always include everything

# Use of the api :
## 1 Setting up :
- Every user of the app needs to be registered in the DB with the use of personController @PostMapping
- Every type of units to be stored (e.g. Apples, Oranges ..) needs to be registered with the use of
  utypeController @PostMapping

## 2 The api can now be used :
- new units to be stored needs to be posted with the use of unitController @PostMapping (for now one by one)

## 3 Looking up data from the api :
### Need to look at how much is stored by unit type
