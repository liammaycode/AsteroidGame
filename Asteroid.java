//////////////////////////////////////////////
// Name(s): Felix Martinez & Liam May       //
// Assignment: Completing The Asteroid Game //
// Due Date: 03/31/19                       //
//////////////////////////////////////////////
package asteroidgame;

// Import our packages.
import java.awt.Point;
import java.util.Random;

// Asteroid class extends our blobz.PolyBlob Class.
public class Asteroid extends blobz.PolyBlob {
	
	// Asteroid constructor.
    public Asteroid(int x, int y, double rotation) {
    	
    	// Refers to Polyblob sets asteroid to start @ off screen location
    	// PolyBlob creates default diamond-shaped PolyBlob.
        super(-100, -100, rotation); 
        
        // (this) keyword prevents shadowing so this line makes a distinct setDelta
        // with the x and y given to Asteroid setDelta comes from all the way up in
        // Blob and sets the motion vector for this blob.
        this.setDelta(x, y);
      
        // Constructor for Random called random.
        Random random = new Random();
        
        // Asteroid has between 5 and 9 sides and is between 10 and 30 pixels in diameter.
        int vertices = random.nextInt(5) + 5;
        int rad = random.nextInt(11);
        
        // Creates arrays of x and y values the size of variable value vertices.
        int[] xComponent = new int[vertices];
        int[] yComponent = new int[vertices];
        
        double arc = 360/vertices;
        
        // Type Array of points Constructs and initializes a point at the specified location
        // in the coordinate space.
        Point[] points = new Point[vertices];
        
        // Loop runs for whatever amount of sides we have.
        for(int i = 0; i < vertices; i++) {
         
            // Get angle for point.
            double angle = (arc * i) + (random.nextDouble() * arc );
           
            // Get radius for angle.
            double arcRad = (random.nextInt(rad + 1) + 5);
            angle = Math.toRadians(angle);
           
            xComponent[i] = (int)Math.round(Math.cos(angle) * arcRad);
            yComponent[i] = (int)Math.round(Math.sin(angle) * arcRad);
        }
      
        // This populates our array of pointers.
        for(int i = 0; i < vertices; i++) {
        	
        	points[i] = new Point(xComponent[i], yComponent[i]);
        	
        }
        
        // Method from PolyBlob superclass sets the relative polygon for the polyblob so that it
        // takes all of the points at the end of the vertices. This shape is what we want to create,
        // and setPolygon actually makes the shape.
        this.setPolygon(points);      
    }
}
