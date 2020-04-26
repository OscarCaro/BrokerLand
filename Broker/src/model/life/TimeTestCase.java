package model.life;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeTestCase {

	@Test
	void testComparisonFunc() {
		Time t1 = new Time();
		Time t2 = new Time();
		
		t2.addTime(100);
		
		assertEquals(t2.minus(t1), 100);
		
		assertTrue(t1.isBeforeThan(t2));
	}

}
