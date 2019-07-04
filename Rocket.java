//////////////////////////////////////////////
// Name(s): Felix Martinez & Liam May       //
// Assignment: Completing The Asteroid Game //
// Due Date: 03/31/19                       //
//////////////////////////////////////////////
package asteroidgame;

// Import our packages.
import blobz.SandBox;
import blobz.PolyBlob;
import java.awt.Point;
import blobz.BlobUtils;
import blobz.BlobAction;
import blobz.BlobProximity;
import java.awt.event.KeyEvent;

// The Rocket class should now also implement the BlobProximity interface in addition
// to implementing the BlobAction interface and extending the PolyBlob class.
public class Rocket extends PolyBlob implements BlobAction, BlobProximity {
	
	// Add a third input argument for the Rocket constructor. This argument will be of type SandBox.
	// This value will need to be saved as a static variable. You will need this reference in order
	// to be able to launch missiles by adding them to the program's sandbox.
	public static SandBox sbox;
	
	public int radius;
 
	// Instance Variables angle, delta, speed:
	// To allow us to set the direction of the rocket to the right of the screen, This variable keeps track of the orientation
	// of our rocket.
    private double angle = 0.0;
    
    // Set the motion vector for this blob, This will allow us to move left/right.
    private final double delta = 0.15;
    
    // Speed of our rocket.
    private final double speed = 5.0;
    
    // Rocket class constructor.
    Rocket( int xCoordinate, int yCoordinate, SandBox sandbox ) {
    	
    	// Run super class constructor, This will create a stationary PolyBlob in x,y location.
        super( xCoordinate, yCoordinate, 0 );
        
        // Set the location for this blob.
        super.setLoc( xCoordinate, yCoordinate );
    	
    	// saving sandbox value that was passed as a static variable
    	sbox = sandbox; 
    	
        // Size of rocket, Pass these points to our ARRAY OF POINTS to populate them.
        Point [] myPoints = {
          
		        // 4 vertices for our rocket shape, if we change our vertices we change the shape of our rocket, We only want our
		        // rocket to have 4 intersecting points for a "simple" shape (A.K.A Arrow).
		        // (Vertices cannot be < -10 || > +10) 
                new Point(10, 0),  //Head  (Vert)
                new Point(-8, 8),  //Left  (Vert)
                new Point(-1, 0),  //Tail  (Vert)
                new Point(-8, -8), //Right (Vert)
        };
        
        // Create rocket shape using setPolygon.
        // wait what would the return type be?
        setPolygon( myPoints );
    }
    
    // (KeyAction should have separate processing blocks for handling key codes: Left, Up, Right Keys)
    // Each time a key is pressed, we must determine what the NEW angle should be, and then we should update 
    // the rockets orientation.
    public void keyAction( KeyEvent e ) {
     
     
    	// BLOCK 1: "Left Motion".
        // (Left Arrow: 37) Generates a new angle W/Key Action Listener.
        if ( e.getKeyCode() == 37 ) {
         
        	// Turning to the left will involve (SUBTRACTING) "delta" from the (CURRENT ANGLE -> angle - delta)
            if ( angle - delta < 6.28318530718 ) {
             
            	// Adding 2*Pie keeps the current angle between (0 - 2*pie == 360 degrees in radians).
                angle = angle - delta + (6.28318530718);
                
                // if occurs: update the rockets orientation w/setAngle method.
                super.setAngle(angle);
            }
            else {
              angle = angle - delta;
                
                 // else occurs: update the rockets orientation w/setAngle method.
                 super.setAngle(angle);
            }
        }
        
        // BLOCK 2: "Forward Motion".
        // (Up Arrow: 38) Updates the x and y location.
        else if ( e.getKeyCode() == 38 ) {
         
        	// Call on Array points Constructor of our superclass which allows us to retrieve the CURRENT (x,y) Coordinates
            Point myPoints = super.getLoc();
            
            // Here we are updating our locations using the speed configuration parameter.This affects the way out rocket moves in the forward motion,
            // The result of our trigonometric function Math.cos & Math.sin returns the trigonometric result of our angle, We cast that as an integer
            // (Floor Function) because it returns a double. 
            myPoints.x = (int)(myPoints.x + (speed * Math.cos(angle)));
            myPoints.y = (int)(myPoints.y + (speed * Math.sin(angle)));
            
            // Update the the location of our rocket.
            super.setLoc(myPoints.x, myPoints.y);
        }
  
        // BLOCK 3: "Right Motion".
        // (Right Arrow: 39) Generates a new angle W/Key Action Listener.
        else if ( e.getKeyCode() == 39 ) {
         
        	// Turning to the right will involve (ADDING) "delta" to the (CURRENT ANGLE -> angle + delta).
            if ( angle + delta > 6.28318530718) {
             
                angle = angle + delta - (6.28318530718);
                
                // if occurs: update the rockets orientation w/setAngle method.
                super.setAngle(angle);
                
            }
            else {
             
            	// (CURRENT ANGLE -> angle + delta).
                angle = angle + delta;
                
                // else occurs: update the rockets orientation w/setAngle method..
                super.setAngle(angle);
            }   
        }
        // Block 4: "spacebar" will launch missile.
        // Add another block of code in the keyAction() method for processing spacebar key events (key code 32).
        // This block should run the new "launch" instance method.
        else if ( e.getKeyCode() == 32 ) {
        	
        	// Invoke the new launch method using saved sandbox instance variable (sbox)
        	launch(sbox); 
        
        }
    }
    // Add a new "launch(Sandbox sb)" method to launch the missile. This method is called from the keyAction() method as described above.
    // The launch() method will calculate the start point for the missile to be 5 pixels ahead of the rocket and have it move in the same
    // direction as the current rocket orientation. The method will then instantiate the Missile and add it to the sandbox, just as we do
    // for asteroids and the rocket.
	public void launch(SandBox sb) {
		
		// Capturing current position, +5 for starting point.
		int x = getLoc().x + BlobUtils.rotatePoint(((getSize()/2) + 5), angle).x;
		int y = getLoc().y + BlobUtils.rotatePoint(((getSize()/2) + 5), angle).y;
			
		// Add a missile to the sandbox.
		sb.addBlob(new Missile(x, y, angle));
	
		}
}
