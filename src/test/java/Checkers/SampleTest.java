
/* 
package Checkers;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import javax.lang.model.type.NullType;

public class SampleTest {

    // Test when the user presses a key, then the tank should start moving
    @Test
    public void testTankMovement() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
		
		//app.keyPressed(new KeyEvent(null, 0, 0, 0, ... ' ', 37)) // all that matters is keyCode if this is all you check in your code
		//check velocity of tank is as expected
		
        //assertEquals(expected, app.getCurrentPlayer().getVelocity());
    }
	
	// Test when the user presses a key, then the powerup activates
    @Test
    public void testPowerup() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        //check powerup had an effect
		
		//assert scoreboard value decreased as expected
    }

    @Test
    public void testProjectile() {

        //perform key press action - space bar
        int initialframes = app.frameCount;
        Thread.sleep(1000); //1 second passes, check game state was changed in expected way
        //projectile moves through the air

        int endframes = app.frameCount;
        //do we expect the projectile to have hit the target? - if time is too short, then assert projectile still exists
        
        //if expected projectile to have hit terrain - assert terrain is destroyed

        //however, the user has to wait 1 second for the testcase to complete
        // - to avoid this - use app.noLoop()

        //simulate 3 second of time in the game
        for (int i = 0; i < App.FPS*3; i++) {
            app.draw(); //simulate frame
            //or if a seperate method is used for game logic tick, eg. app.tick()
        }
    }

}

// gradle run						Run the program
// gradle test						Run the testcases

// Please ensure you leave a comments in your testcases explaining what the testcase is testing.
// Your mark will be based off the average of branches and instructions code coverage.
// To run the testcases and generate the jacoco code coverage report: 
// gradle test jacocoTestReport

*/
package Checkers;


import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    public void simpleTest() {
        
    }
}
