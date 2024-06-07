import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {

	GradeBook subjectA;
	GradeBook subjectB;
	
	@BeforeEach
	void setUp() throws Exception {
		subjectA = new GradeBook(5);
		subjectB = new GradeBook(5);
		
		subjectA.addScore(70.5);
		subjectA.addScore(83.0);
		
		subjectB.addScore(59.4);
		subjectB.addScore(90.1);
		subjectB.addScore(75.0);
	}

	@AfterEach
	void tearDown() throws Exception {
		subjectA = null;
		subjectB = null;
	}

	@Test
	void testAddScore() {
		assertTrue(subjectA.addScore(60.0));
		assertTrue(subjectA.addScore(60.5));
		assertTrue(subjectA.addScore(61.0));
		assertFalse(subjectA.addScore(61.5)); //should fail!
		
		assertTrue(subjectB.addScore(60.0));
		assertTrue(subjectB.addScore(60.5));
		assertFalse(subjectB.addScore(61.0)); //fails after only 2 scores b/c we added 3 scores in setup
	}
	
	@Test
	void testSum() {
		assertEquals(subjectA.sum(), 153.5, 0.01);
		assertEquals(subjectB.sum(), 224.5, 0.01);
	}
	
	@Test
	void testMinimum() {
		assertEquals(subjectA.minimum(), 70.5, 0.01);
		assertEquals(subjectB.minimum(), 59.4, 0.01);
	}
	
	@Test
	void testFinalScore() {
		assertEquals(subjectA.finalScore(), 83.0, 0.01);
		assertEquals(subjectB.finalScore(), 165.1, 0.01);
	}

}
