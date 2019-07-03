//////////////////////////////////////////////
// Name(s): Felix Martinez & Liam May       //
// Assignment: Completing The Asteroid Game //
// Due Date: 03/31/19                       //
//////////////////////////////////////////////
package asteroidgame;

// Import our packages.
import blobz.BlobGUI;
import blobz.SandBox;
import java.util.Random;
import blobz.SandBoxMode;
import java.awt.Dimension;

// AsteroidGame must implement the BlobGUI interface
// so it can respond to presses of the "Start New Game" button.
public class AsteroidGame implements BlobGUI { 

	// AsteroidGame must have a one-line main() method that instantiates the class.
	// As you can see, the main method takes one (1) command line argument, interprets
	// it as an integer, and passes that integer to the constructor for the class.
	public static void main(String[] args) {
		new AsteroidGame( Integer.parseInt( args[0] ) );
		}
	
		// Save the input integer in a static variable
	    // (this number represents the number of asteroids that your program will need to create).
		static int input;
		
		// Create a sandbox object;
		SandBox sandbox;
		
	// & passes that integer to the constructor for the class.
	AsteroidGame( Integer n ) {
		
		// Save the input integer in a static variable (this number represents
		// the number of asteroids that your program will need to create).
		AsteroidGame.input = n;
		
		// Create a sandbox object.
        sandbox = new SandBox();
        
        // Set the sandbox to "flow" mode.
        sandbox.setSandBoxMode(SandBoxMode.FLOW);
        
        // Set the sandbox to run at 15 frames per second
        sandbox.setFrameRate(15);
        
        //  Initialize the sandbox by passing "this" (the AsteroidGame object)
        //  to the sandbox's init() method.
        sandbox.init( this ); 
        
	}
	
	// Add a "public void generate()" method that overrides the declaration in the BlobGUI interface.
	// This method will perform the following actions.
	@Override
	public void generate() {
		
		// Declare variables.
	    int i;
	    int xComponent, yComponent;
	    double rotation;
	    
	    // Instantiate your rocket and add it to the center of the sandbox.
    	Dimension dime = sandbox.getPanelBounds();
    	Rocket rocket = new Rocket(500,500,sandbox); 
        rocket = new Rocket(dime.width/2, dime.height/2, sandbox);
	    
        // Random Constructor call.
	    Random random = new Random();

	    // This for loop does a set number of asteroids when the assignment
	    // wants us to run this loop a number of times based on command 
	    // line input. 
	    for(i = 0; i < AsteroidGame.input; i++) {
	     
	    	// Each asteroid must be configured with independent random integer
	    	// x and y velocity components between -3 and +3.
	        xComponent = (random.nextInt(3) + 1) * (random.nextBoolean() ? -1: 1);
	        yComponent = (random.nextInt(3) + 1) * (random.nextBoolean() ? -1: 1);
	        
	        // It must randomly choose a rotation value of either -.1 or +.1 for each asteroid,
	        // with equal probability. Values in between -.1 and +.1 are not permitted.
	        rotation = random.nextBoolean() ? -.1: .1;
	      
	        // Instantiate and add to the sandbox as many asteroids as are specified by the number
	        // that was input to the constructor for the class.
	        sandbox.addBlob(new Asteroid( xComponent, yComponent, rotation));
	    }
	        
	        // Once the rocket is instantiated, the generate() method should then add it to the sandbox using
	        // your sandbox's addBlob() method.
	        sandbox.addBlob(rocket);
	}
}
