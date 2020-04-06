import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

class NotesStorageTest {

    private NotesStorage notesStorage;
    private Note note;

    @BeforeEach
    void setUp() {
        notesStorage = mock(MockType.NICE, NotesStorage.class);
        note = mock(MockType.NICE, Note.class);
    }

    @Test
    void addTest() {
        ArrayList<Note> notes = new ArrayList<>();

        notesStorage.add(note);
        EasyMock.expectLastCall().andAnswer(() -> {
            notes.add(note);
            return null;
        }).times(1);
        replay(notesStorage);

        notesStorage.add(note);

        verify(notesStorage);
    }

    @Test
    void clearTest() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);

        notesStorage.clear();
        EasyMock.expectLastCall().andAnswer(() -> {
            notes.clear();
            return null;
        }).times(1);
        replay(notesStorage);

        notesStorage.clear();

        verify(notesStorage);
    }

    @Test
    void getAllNotesOfTest() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);

        notesStorage.getAllNotesOf("Bob");
        EasyMock.expectLastCall().andAnswer(() -> notes).times(1);
        replay(notesStorage);

        notesStorage.getAllNotesOf("Bob");

        verify(notesStorage);
    }

    @AfterEach
     void tearDown() {
        notesStorage = null;
    }


}