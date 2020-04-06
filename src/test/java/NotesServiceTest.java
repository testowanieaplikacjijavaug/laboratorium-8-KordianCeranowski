import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

class NotesServiceTest {

    private NotesService notesService;
    private Note note;

    @BeforeEach
    void setUp() {
        notesService = mock(MockType.NICE, NotesService.class);
        note = mock(MockType.NICE, Note.class);
    }

    @Test
    void addTest() {
        ArrayList<Note> notes = new ArrayList<>();

        notesService.add(note);
        EasyMock.expectLastCall().andAnswer(() -> {
            notes.add(note);
            return null;
        }).times(1);
        replay(notesService);

        notesService.add(note);

        assertThat(notes).contains(note);
        verify(notesService);
    }

    @Test
    void clearTest() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);

        notesService.clear();
        EasyMock.expectLastCall().andAnswer(() -> {
            notes.clear();
            return null;
        }).times(1);
        replay(notesService);

        notesService.clear();

        assertThat(notes).doesNotContain(note).isEmpty();
        verify(notesService);
    }

    @Test
    void averageOfTest() {

        notesService.averageOf("Bob");
        EasyMock.expectLastCall().andAnswer(EasyMock::anyFloat).times(1);
        replay(notesService);

        notesService.averageOf("Bob");
        verify(notesService);
    }

    @AfterEach
    void tearDown() {
        notesService = null;
    }
}