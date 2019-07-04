//////////////////////////////////////////////
// Name(s): Felix Martinez & Liam May       //
// Assignment: Completing The Asteroid Game //
// Due Date: 03/31/19                       //
//////////////////////////////////////////////
package asteroidgame;

// Import our packages.
import blobz.Blob;
import java.awt.Color;
import blobz.BlobProximity;

// Extend the Blob class (not PolyBlob) and also implement the BlobProximity interface.
public class Missile extends Blob implements BlobProximity {
	
	// The Missile CONSTRUCTOR should take three arguments:
	// The x & y locations of the missile's current location (both integers).
	// & the direction (angle) in which the missile is moving (a double).
	public Missile (int x, int y, double angle) {
		
		// Invoke Blob Superclass constructor. 
	    super(x, y, Color.yellow);
	
		// Use a "speed" value of 5 (which should be a double) for calculating velocity components.
	    final double speed = 5.0;
    
	    // Capturing theta
	    final double theta = angle;
	    
	    // Calculate velocity components 
	    int dx = (int) Math.round( speed * Math.cos( theta ) );
	    int dy = (int) Math.round( speed * Math.sin( theta ) );
	    
	    // Use setDelta(dx,dy) to set missile in motion this keyword prevents shadowing.
	    
	    this.setDelta(dx, dy);
	    
	}
}
