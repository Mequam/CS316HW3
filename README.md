# Homework 3 - CS 316 01 FA22

**Graph Algorithms**

This is a simple program to perform Dijkstras and Prims algorithm on a adjacency matrix represented graph.

The code is more designed to run then be efficient, but the ideas are there.

---

## Locations

the driver code for problems 2 and 3 can be found in the folder DAK/HW3

most of the code that actually runs the graph algorithms is located in the AdjGraph class in DAK/Graph/AdjGraph.java under the functions minSpanTree and dijkstras respectivly

the solution to problem 1 is located in the txtproblems folder under two different file formats for whatever is most convinenent.

- txtproblems/prob1.svg
- txtproblems/prob1.png

screenshots of program outputs from problems 2 and 3 are located in the outputScreenshots folder


## Compilation

to compile the program simply run the following commands on a gnu system with make and javac installed

```bash
make
```

the following commands will run problems 2 and 3 respectivly

```bash
java DAK/HW3/Prob2/Prob2
java DAK/HW3/Prob3/Prob3
```

When running either program you can specify an input file to the program as an argument on the command line

if no file is specified then the default graph examples provided with the homework (located in input.txt and problem_2_sample_input.txt respectivly) will be used.

> note that you are in the root folder of the project when you run these commands