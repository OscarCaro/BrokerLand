package tests;

import model.life.Time;
import org.junit.jupiter.api.Test;

class TimeTestCase extends TestCommons {

    @Test
    void timeTest() {
        Time t1 = new Time();
        Time t2 = new Time();

        t2.addTime(100);

        assertEquals(t2.minus(t1), 100);
        assertTrue(t1.isBeforeThan(t2));
        assertTrue(t1.toString().contains("It's:"));
        assertTrue(t1.toString().contains(" of day "));
    }

}
