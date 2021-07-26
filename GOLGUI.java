import javax.swing.JFrame;

//This class creates our Game Of Life Control Form!
public class GOLGUI implements Runnable 
{
	//Since the class 'Runnable' is an implementation we have to define this method
	public void run() 
	{
	    //Create and set up the Window
        JFrame frame = new JFrame("Conway's Game of Life");
        //Disable the exit 'X' and close menu
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Create and set up the content Pane based on our FinchControl class
        GOLControl newContentPane = new GOLControl();
        //All content Panes must be opaque apparently...
        newContentPane.setOpaque(true);
        //Put the Pane in the Window!
        frame.setContentPane(newContentPane);
        //Move to point 100,100 (offset from top left) and size to 800,800 pixels
        frame.setBounds(100,100,800,800);
        //Disable the window resize feature
        frame.setResizable(false);
        //Display the frame.
        frame.setVisible(true);
    }
}