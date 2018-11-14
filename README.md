# Polynom

Author Omer Edut
Date: 1/11/2018

The system represents a polynom is the form of 𝑓(𝑥) = 𝑎(𝑥)* + ⋯ + 𝑎-𝑥). which is actually a collection of polynomials.
The system enables all kinds of logical, mathematical, and possible display on polynomials of this form.
There are a number of constructors which build the polynom.
A default constructor that creates an empty polynom. copy constructor, this constructor gets polynom, and creates a new polynom like it.
A constructor that gets a polynomial string, if the string is valid, the constructor creates the polynom,
otherwise it creates a default polynom.

Logic methods of polynom:
Polynom initialization.
testing if a polynom is a zero polynom. testing if two polynoms are the same. copying exist polynom.
Mathematics methods of polynom:
Addition, subtraction, multiplication, calculation f(x) by given x, derivative, calculation of area between two points,
finding root of polynom between two points.
There is also a method that returns a string to display the polynom.
In the polynom class has a helper function to initialize the polynomial to a default polynom.
For implement all the functions of the polynom in clearly and easily way, we build an object that represents a monom.
This monom is the form of 𝑎𝑥) so that b is natural.
This class does not allow direct access to the values of the monom in order to preserve the values - the incapsulation principle.
Monom also has a number of mathematical, logical, and monom functions that use to present the monom,
but also helps to implement the polynom in a simple, clear, and intuitive way.

There are a number of constructors for the monom:
Constructor that gets the base and power of the monom.
Empty constructor that creates a deafult monom with base 0 and power 0.
A copy constructor that creates a new monom exactly the same monom that he gets.
Logical methods in the monom:
Initialize monom by getting monom data (base and power).

Mathematical methods in monom:
Multiply.
Finding a monom value at a given point/
Finding a value of x when the function value is given at point x. Derived.
There is also a method that returns the monom in a string.
The monom class has several helper functions:
Initialized the monom to default monom.
Root finding.
Input values for the base variables and power of the monom.
