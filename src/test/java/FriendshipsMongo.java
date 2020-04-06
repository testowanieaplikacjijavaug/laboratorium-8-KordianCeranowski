
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
        Person joe = new Person("Joe");
        //Zapisanie zachowania - co sie ma stac
        expect(friends.findByName("Joe")).andReturn(joe);
        //Odpalenie obiektu do sprawdzenia zachowania
        replay(friends);
        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends(){
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends(){
        List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
        Person joe = createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(expected);
        replay(friends);
        replay(joe);
        assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
    }

    @Test
    public void joeDoesHaveFriends(){
        assertThat(friendships.getFriendsList("Joe")).isEmpty();
    }

    @Test
    public void joeAndMaciejAreNotFriends(){
        assertThat(friendships.areFriends("Maciej", "Joe")).isFalse();
    }

    @Test
    public void adamAndDawidAreNotFriends(){
        assertThat(friendships.areFriends("Adam", "Dawid")).isFalse();
    }

    @Test
    public void addFiendTest(){
        Person joe = createMock(Person.class);
        List<String> expected = new ArrayList<>();

        joe.addFriend(anyString());

        expect(joe.getFriends()).andReturn(expected);
    }

    @Test
    public void setNameThrowsException() {
        Person joe = createMock(Person.class);
        joe.setName("");
        expectLastCall().andThrow(new RuntimeException());
    }

}
