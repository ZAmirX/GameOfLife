//All these imports are needed for the different parts of the interface
//Read up in the Javadocs on each of these classes...
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Our class needs to inherit functionality from 'JPanel' and 'JActionListener'
public class GOLControl extends JPanel implements ActionListener 
{
	//All forms need a unique ID - ignore this!!!
    private static final long serialVersionUID = 1862962349L;
    //We are going to have four buttons
	private JButton startbutton,pausebutton,resetbutton,exitbutton;
	//We will have one label for the current iteration
	private JLabel iterationLabel;
	//The timer is for updating the iteration at a regular interval 
	private Timer timer;
	
	//Start the first iteration at 0
	int iterationCount = 0;
	
    public GOLControl() 
    {
    	//Generate a new random grid
    	GOLGrid.newRandom();
    	
    	//Set up the four buttons
    	
    	//Caption for first button
        startbutton = new JButton("Start");
        //Centre the text vertically
        startbutton.setVerticalTextPosition(AbstractButton.CENTER);
        //Centre the text horizontally
        startbutton.setHorizontalTextPosition(AbstractButton.CENTER);
        //Create the event/action 'Start' when clicked
        startbutton.setActionCommand("Start");

        //Caption for second button
        pausebutton = new JButton("Pause");
        //Centre the text vertically
        pausebutton.setVerticalTextPosition(AbstractButton.CENTER);
        //Centre the text horizontally
        pausebutton.setHorizontalTextPosition(AbstractButton.CENTER);
        //Create the event/action 'Stop' when clicked
        pausebutton.setActionCommand("Pause");
        //Initially the stop button is disabled
        pausebutton.setEnabled(false);

        //Caption for third button
        resetbutton = new JButton("Reset");
        //Centre the text vertically
        resetbutton.setVerticalTextPosition(AbstractButton.CENTER);
        //Centre the text horizontally
        resetbutton.setHorizontalTextPosition(AbstractButton.CENTER);
        //Create the event/action 'Reset' when clicked
        resetbutton.setActionCommand("Reset");
        
    	//Caption for fourth button
        exitbutton = new JButton("Exit");
        //Centre the text vertically
        exitbutton.setVerticalTextPosition(AbstractButton.CENTER);
        //Centre the text horizontally
        exitbutton.setHorizontalTextPosition(AbstractButton.CENTER);
        //Create the event/action 'Exit' when clicked
        exitbutton.setActionCommand("Exit");
        
        //Listen for actions from the four buttons
        //The current instance of 'this' class will process the actions for the buttons 
        startbutton.addActionListener(this);
        pausebutton.addActionListener(this);
        resetbutton.addActionListener(this);
        exitbutton.addActionListener(this);

        //This is the text that is displayed when we hover the mouse over the buttons
        startbutton.setToolTipText("Click this button to start the simulation");
        pausebutton.setToolTipText("Click this button to stop the simulation");
        resetbutton.setToolTipText("Click this button to reset the simulation with a new random arrangement");
        exitbutton.setToolTipText("This button is the only way you can exit this application...");
        
        //Create the iteration label
        iterationLabel = new JLabel("Current iteration: " + iterationCount);
        //Add a border to the label
        iterationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Add the Components to the Form
        //If they are not added then they will not appear!
        add(startbutton);
        add(pausebutton);
        add(resetbutton);
        add(exitbutton);
        add(iterationLabel);
        
        //Create a timer every 200 milliseconds
        //The current instance of 'this' class will process the actions for the timer
        timer = new Timer(200, this);
        //Create the event/action 'Timer' every 1/2 second
        timer.setActionCommand("Timer");
        //Start the timer straight away
        timer.setInitialDelay(0);
    }
    //The method must be implemented as we have inherited from 'JActionListener'
    //Every time a button is clicked or a timer event occurs this method is run
    //A total of four events are catered for here
    public void actionPerformed(ActionEvent e) 
    {
    	//Check for the 'Start' event
    	if (e.getActionCommand().equals("Start")) 
        {
    		//Log that something has happened
        	System.out.println("Started");
        	//Disable the 'Start' button
            startbutton.setEnabled(false);
            //Enable the 'Pause' button
            pausebutton.setEnabled(true);
    		//Log the current iteration number to the console
            System.out.println("Current Iteration: " + iterationCount);
            //Start the 'Timer'
            timer.start();
        }
    	//Check for the 'Pause' event
    	if (e.getActionCommand().equals("Pause"))
        {
    		//Log that something has happened
        	System.out.println("Paused");
        	//Enable the 'Start' button
            startbutton.setEnabled(true);
            //Disable the 'Pause' button
            pausebutton.setEnabled(false);
            //Stop the 'Timer'
            timer.stop();
        }
    	//Check for the 'Timer' event
    	if (e.getActionCommand().equals("Timer"))
        {
    		//Increment the current iteration counter by 1
    		iterationCount++;
    		//Log the current iteration number to the console
    		System.out.println("Current Iteration: " + iterationCount);
    		//Change the current iteration label to show the next number
    		iterationLabel.setText("Current iteration: " + iterationCount);
    		//Set up the grid's array for the next iteration
    		GOLGrid.nextIter();
        	//Redraw the window
        	repaint();
        }
    	//Check for the 'Reset' event
    	if (e.getActionCommand().equals("Reset"))
        {
    		//Log that something has happened
        	System.out.println("Reset");
        	//Generate a new random grid
        	GOLGrid.newRandom();
        	//Reset the iteration counter to 0
        	iterationCount = 0;
        	//Log the current iteration number to the console
    		System.out.println("Current Iteration: " + iterationCount);
    		//Change the current iteration label to show the correct number
    		iterationLabel.setText("Current iteration: " + iterationCount);
        }
    	//Test for the 'Exit' action
    	if (e.getActionCommand().equals("Exit"))
        {
    		//Log that something has happened
        	System.out.println("Exit");
        	//Stop the timer
        	timer.stop();
        	//Get the parent JFrame of this panel
        	JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        	//Hide the Window
        	parent.setVisible(false);
        	//Get rid of the Window
        	parent.dispose();
        }    	
    }
    //Override the super class paint method
    @Override
    public void paint(Graphics g) 
    {
    	//Call the super class paint method
    	//This is needed to make sure all the components are drawn correctly
    	super.paint(g);
    	
    	//Set the grid starting height and width
    	int height, width = 100;
    	//Set the grid width to some number + number of cells to account for gaps between cells
    	int gridWidth = 500 + GOLGrid.numCells;
    	//Set the height and width of each individual cell to width of whole grid/number of cells
    	int cellSize = gridWidth/GOLGrid.numCells;
    	
    	for (int i=0; i<GOLGrid.numCells; i++)
    	{
    		//Height at which the grid starts
    		height = 50;
    		for (int j=0; j<GOLGrid.numCells; j++)
        	{
        		if (GOLGrid.grid[i][j])
        		{
        			//If cell alive make it red
        			g.setColor(Color.RED);
        		}
        		else
        		{
        			//Otherwise make it white
        			g.setColor(Color.WHITE);
        		}
        		//Create the square representing a cell at the pixel coordinates of width and height
        		g.fillRect(width, height, cellSize, cellSize);
        		//Set the pixel coordinates for the next cell below it at current height + cellSize + 1 for cell gap
        		height += cellSize + 1;
        	}
    		//Set the pixel coordinates for next column of cells to the right + 1 for cell spacing
        	width += cellSize + 1;
    	}
    	repaint();
    }
 }