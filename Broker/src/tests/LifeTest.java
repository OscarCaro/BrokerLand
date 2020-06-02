package tests;

import model.life.Hunger;
import model.life.MentalHealth;
import org.junit.jupiter.api.Test;

class LifeTest extends TestCommons {

    @Test
    void statsTest(){
        Hunger h = new Hunger(50);
        h.add(30);
        assertEquals(h.getHungerIndex(), 80);
        h.add(-10);
        assertEquals(h.getHungerIndex(), 70);
        h.add(50);
        assertEquals(h.getHungerIndex(), 100); //Max is 100

        MentalHealth m = new MentalHealth(50);
        m.add(30);
        assertEquals(m.getMentalIndex(), 80);
        m.add(-10);
        assertEquals(m.getMentalIndex(), 70);
        m.add(50);
        assertEquals(m.getMentalIndex(), 100); //Max is 100
    }
    @Test
    void statsStringTest() {
        Hunger h = new Hunger(50);
        MentalHealth m = new MentalHealth(50);
        assertTrue(!m.toString().isEmpty() && !h.toString().isEmpty());
    }
}
