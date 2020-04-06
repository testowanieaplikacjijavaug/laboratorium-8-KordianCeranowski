
import org.easymock.EasyMock;
import org.easymock.*;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class CarEasyTest {
    private Car myFerrari = EasyMock.createMock(Car.class);

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needs_fuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_stubbing_mock(){
        expect(myFerrari.needsFuel()).andReturn(true);
        replay(myFerrari);
        assertTrue(myFerrari.needsFuel());

        verify(myFerrari);
    }

    @Test
    public void test_exception(){
        expect(myFerrari.needsFuel()).andThrow(new RuntimeException());
        replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });

        verify(myFerrari);
    }

    @Test
    public void test_enginetemp() {
        expect(myFerrari.getEngineTemperature()).andReturn(100.0);
        replay(myFerrari);

        double expected = myFerrari.getEngineTemperature();
        assertThat(expected).isEqualTo(100.0);

        verify(myFerrari);
    }

    @Test
    public void testGetProductionYearMock() {
        expect(myFerrari.getYear()).andReturn(1943);
        replay(myFerrari);

        int year = myFerrari.getYear();

        assertThat(year).isEqualTo(1943);

        verify(myFerrari);
    }

    @Test
    public void testGetProductionYearMockTest() {
        expect(myFerrari.getYear()).andReturn(1927);
        replay(myFerrari);

        int year = myFerrari.getYear();

        assertThat(year).isNotEqualTo(1923);

        verify(myFerrari);
    }

    @Test
    public void needsFuelTest() {
        expect(myFerrari.needsFuel()).andReturn(true);
        replay(myFerrari);
        assertTrue(myFerrari.needsFuel());

        verify(myFerrari);
    }

    @Test
    public void needsFuelFalseTest() {
        expect(myFerrari.needsFuel()).andReturn(false);
        replay(myFerrari);
        assertFalse(myFerrari.needsFuel());

        verify(myFerrari);
    }

    @Test
    public void needsFuelIsNot42Test() {
        expect(myFerrari.needsFuel()).andReturn(false);
        replay(myFerrari);
        assertNotEquals(myFerrari.needsFuel(), 42);

        verify(myFerrari);
    }




}