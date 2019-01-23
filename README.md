# paint-app
Part 1: Geometric Shapes Data Model Description:
Geometric shapes belong to different groups (ex: Elliptical Shapes, Polygons, Sectors…etc). Members of these different groups are related to each other in the sense that they share common properties. In order to be able to implement an efficient and object oriented drawing application. It is essential to design a model that takes these relations into consideration. So our design creates a super class called "Our Shape" that implements shape interface and contains the common properties and all shapes classes extend these super class. Functionalities that controls drawing in canvas (ex : add shapes, refresh, delete, save, load…etc) on all shape are the same so they are all in the same class that implements "DrawingEngine" interface which used in GUI classes.

Part 2: Drawing and Painting Application Description:
Design and implement a GUI that allows the following functionalities for the user on all the shapes defined in part 1: Draw, Color, Resize, Move, and Delete which can be done by using cursor to select a drawn shape to show its properties and allow him to change what he wants. Also the application would allow the user to undo or redo any action performed. The GUI design consists of:
1- Main frame contains 3 parts:
(1) Menu bar contains:
1. Save icon
2. Redo icon
3. Undo icon
4. File list
(2) Panel contains list of available shapes
(3) Drawing area (canvas)
2- Properties panel which opens when a drawn shape is clicked or when creating a new shape and contains:
(1) The properties of the shape
(2) Delete button
(3) Clone button
(4) Choose colors button
3- Choose color panel opens when clicking in its button in properties panel
4- Clone panel is used to enter the new position of the shape.

Part 3: Save and load Description:
One of the main features in any paint application is saving user’s drawings in a file and modifying it later.
So our Application provide an option in GUI to save the drawing in XML and JSON file which can be loaded later to modify it. There is shortcut of save icon in menu bar or user can find it in file list in the menu bar. Load icon exists in file list in the menu bar.
