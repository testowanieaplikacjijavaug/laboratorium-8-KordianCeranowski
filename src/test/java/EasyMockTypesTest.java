import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.easymock.MockType;
import org.junit.Test;

public class EasyMockTypesTest {

    @Test
    public void EasyMockStrictMockExampleTest() {
        ArrayList<Integer> mockList = mock(MockType.STRICT, ArrayList.class);
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.add(20)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        expect(mockList.get(0)).andReturn(10);
        replay(mockList);

        mockList.add(10);
        mockList.add(20);
        assertEquals(mockList.size(), 2);
        assertEquals(10, (int) mockList.get(0));

        verify(mockList);
    }

    @Test
    public void EasyMockNiceMockExampleTest() {
        ArrayList<Integer> mockList = mock(MockType.NICE, ArrayList.class);
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        expect(mockList.get(0)).andReturn(10);
        replay(mockList);

        mockList.add(10);
        // below will NOT throw exception because of nice mock
        boolean b = mockList.add(30);
        assertFalse(b);

        assertEquals(mockList.size(), 2);

        assertEquals(10, (int) mockList.get(0));
        //verify won't throw error for unexpected calls for nice mock
        verify(mockList);
    }

    @Test
    public void EasyMockDefaultMockExampleTest() {
        ArrayList<Integer> mockedList = mock(MockType.DEFAULT, ArrayList.class);

        expect(mockedList.add(20)).andReturn(true);
        expect(mockedList.size()).andReturn(2);
        expect(mockedList.get(1)).andReturn(30);
        replay(mockedList);

        mockedList.add(20);

        assertEquals(2, mockedList.size());
        assertEquals(30, (int) mockedList.get(1));

        verify(mockedList);
    }
}