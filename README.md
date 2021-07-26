# GameOfLife
This is a Java implementation of Conway's "Game Of Life" simulation.

# Description
Conway’s “Game of Life” (GOL) is an Artificial Life simulation. GOL is a type of “Cellular Automata” (CA), CAs in general are of interest since they have been shown to be decomposable to a Turing Machine, and as such can perform computation and evaluate logical expressions. CAs are naturally parallel which makes them of great interest for study; applications include image processing, medical modelling and oil slick simulations.

This Java application displays an interactable UI using the Java Swing library. The size of the grid can be changed within the code in `GOLGrid.java`. The starting iteration can also be changed in `GOLControl.java`. The buttons at the top of the GUI can be used to start, pause and reset (with a different random configuration of cells) the simulation, or exit the application.

The cells start off randomly and change to either "Dead" or "Alive" after every iteration based on the states of its neighbouring cells. The rules for each cell are as follows:
* Dead to Alive: Only if three of the eight neighbours are alive
* Alive to Dead: less than two neighbours alive or more than three

Eventually, the grid would reach a state of equilibrium and no more changes occur once none of the rules apply to any of the cells.

The GOL grid is meant as an infinite size, but as this is not possible due to resource limitation, the size has to be fixed and cell neighbours "wrap around" (bottom to top and vice versa, left to right and vice versa).
