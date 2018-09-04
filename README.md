# noodles
A recreation of the app 'Noodles!' by Lummox Labs | http://www.lummoxlabs.com/apps/

This program uses the javax.swing package to create an interactive version of the mobile app 'Noodles!' by Lummox Labs.  The goal of the game is to connect all of the noodles to the source by rotating each piece.  This can often be tricky due to the fact that all edges of every piece must be connected to another piece! 

------------------------------------------------------------------------------------------------------------------------------

//TODO

Game boards are stored as .csv files and the Imgtocsv class includes a method to create a .csv file from a screenshot of a board from the mobile app.  This feature currently only works for 5x5 boards and screenshots from the iPhone 6, but will be expanded in the future to accept any screenshot of any rectangular board

The Controller class is still a work in progress, the final version of will run a GUI that contains a level selector.  This would require the BaseFrame to contain other panels on the side of the GamePanel, one of which would show all of the available levels
