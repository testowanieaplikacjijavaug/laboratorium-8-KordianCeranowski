
import org.easymock.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(EasyMockExtension.class)
class FriendshipsMongoEasyMockTest {

    @TestSubject
    FriendshipsMongo friendships = new FriendshipsMongo();

    //A nice mock expects recorded calls in any order and returning null for other calls
    @Mock(type = MockType.NICE)
    FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected(){
        Person bob = new Person("Bob");
        //Zapisanie zachowania - co sie ma stac
        expect(friends.findByName("Bob")).andReturn(bob);
        //Odpalenie obiektu do sprawdzenia zachowania
        replay(friends);
        assertThat(friends.findByName("Bob")).isEqualTo(bob);
    }

    @Test
    public void alexDoesNotHaveFriends(){
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void bobHas5Friends(){
        List<String> expected = Arrays.asList("Karol","Dawid","Maciej","Tomek","Adam");
        Person bob = createMock(Person.class);
        expect(friends.findByName("Bob")).andReturn(bob);
        expect(bob.getFriends()).andReturn(expected);
        replay(friends);
        replay(bob);
        assertThat(friendships.getFriendsList("Bob")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
    }

    @Test
    public void bobDoesNotHaveFriends(){
        List<String> expected = new ArrayList<>();
        Person bob = createMock(Person.class);
        expect(friends.findByName("Bob")).andReturn(bob);
        expect(bob.getFriends()).andReturn(expected);
        replay(friends);
        replay(bob);
        assertThat(friendships.getFriendsList("Bob")).isEmpty();
    }

    @Test
    public void bobAndMaciejAreNotFriends(){
        assertThat(friendships.areFriends("Maciej", "Bob")).isFalse();
    }

    @Test
    public void adamAndDawidAreNotFriends(){
        assertThat(friendships.areFriends("Adam", "Dawid")).isFalse();
    }

    @Test
    public void addFiendTest(){
        Person bob = createMock(Person.class);
        List<String> expected = new ArrayList<>();

        bob.addFriend(anyString());

        expect(bob.getFriends()).andReturn(expected);
    }

    @Test
    public void setNameThrowsException() {
        Person bob = createMock(Person.class);
        bob.setName("");
        expectLastCall().andThrow(new RuntimeException());
    }

}
