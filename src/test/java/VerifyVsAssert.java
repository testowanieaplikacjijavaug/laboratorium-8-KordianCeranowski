import org.easymock.EasyMock;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.verify;

class VerifyVsAssert {
    @Disabled
    @Test
    void verifyTestThrowsErrorIfNoMethodWasUsed(){
        Person person = EasyMock.createMock(Person.class);

        EasyMock.expect(person.getName()).andReturn("Hi");
        EasyMock.replay(person);

        verify(person);
    }

    @Disabled
    @Test
    void verifyTestDoesNotThrowsErrorIfMethodWasUsed(){
        Person person = EasyMock.createMock(Person.class);

        EasyMock.expect(person.getName()).andReturn("Hi");
        EasyMock.replay(person);
        person.getName();

        verify(person);
    }
}