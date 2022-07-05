## Jason Means and Casey Pyburn
# RPN Calculator Project

Specifications:

https://docs.google.com/document/d/1fB7kKYAv-h9hrmlp-9BMIMw2yir8FcjO0mUKT7L80_o/edit#heading=h.js2mrlikwjc

To run the required size, clear, duplicate, and reverse functionality, execute "s", "c", "d", and "r", respectively.

# Functionality implemented beyond provided specs:

## Signed and Unsigned Operation

This calculator operates in either signed or unsigned mode

To switch between modes, execute the "m" command.

## Variables

This calculator allows the user to assign numerical values to variables for repeated reuse.

To assign a variable, use the syntax "->var", where "var" is the name of the variable you wish to assign.  This will remove the top value from the stack and associate it with the chosen variable name.  

## Running Unit Tests

Currently all the unit tests pass when each one is run individually in Eclipse. They can be run together if Maven is installed and has been added to the PATH variable using the following command:

mvn clean test

This, however, does not pass all the tests. We suspect there may be some issue with how it's running them and that it may be leaving artifacts from previous tests that are causing issues.