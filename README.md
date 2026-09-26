University Student Record and Campus Route Management System
CIT300 - Data Structures and Algorithms
Graded Practical Assignment 1 (Week 10)

PROJECT DESCRIPTION
--------------------
A Java console application that manages university student records
and represents campus locations and their connections. The system
demonstrates the use of linked lists, stacks, queues, trees, hashing,
and graphs.

GROUP MEMBERS
--------------------
1. Name: J.A.F. Rushana      Student ID: 23DA2-0500
   Responsibility: Linked list implementation and student-record
   management (add, update, delete, search, display).

2. Name: A.R.S. Farwin       Student ID: 23DA2-0510
   Responsibility: Stack (recent actions/undo) and Queue
   (service requests) implementation.

3. Name: H.M. Aasath         Student ID: 23DA2-1094
   Responsibility: BST implementation and hashing/search
   functionality for Student ID lookup.

4. Name: N.M. Mufeer         Student ID: 23DA2-1045
   Responsibility: Graph implementation - campus locations,
   connections, and BFS/DFS traversal.

INDIVIDUAL CONTRIBUTIONS
--------------------
J.A.F. Rushana: Implemented Student class and linked list; wrote
add/update/delete/display functions; tested edge cases for duplicate
and missing student IDs.

A.R.S. Farwin: Implemented Stack class for undo/recent actions;
implemented Queue class for service requests; wired both into the
main menu.

H.M. Aasath: Implemented BST for storing students; implemented
hash table for fast Student ID search; tested search performance.

N.M. Mufeer: Implemented Graph class using adjacency list;
implemented add/remove location and connection; implemented BFS/DFS
traversal and display of campus network.

ALL MEMBERS: Integration of all components into Main.java, input
validation, testing, debugging, and documentation.

HOW TO RUN
--------------------
1. Open the project folder in an IDE (IntelliJ / Eclipse / VS Code).
2. Compile: javac src/*.java
3. Run: java -cp src Main
4. Follow the on-screen menu (1-16) to use the system.

TECHNOLOGIES USED
--------------------
- Java
- Git & GitHub for version control and collaboration

FILES
--------------------
src/Student.java            - Student data model
src/StudentLinkedList.java  - Linked list: add/update/delete/search/display students
src/ActionStack.java        - Stack: recent actions / undo history
src/ServiceQueue.java       - Queue: student service requests
src/StudentBST.java         - Binary search tree: students organized by ID
src/StudentHashTable.java   - Hash table: fast student ID lookup
src/CampusGraph.java        - Graph: campus locations and connections, BFS/DFS
src/Main.java                - Menu-driven console interface tying everything together
