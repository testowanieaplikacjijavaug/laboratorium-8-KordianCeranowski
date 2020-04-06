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
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);

        notesService.averageOf(anyString());
        EasyMock.expectLastCall().andAnswer(() -> notes).times(2);
        replay(notesService);

        assertThat(notesService.averageOf(anyString())).isEqualTo(notes);
        assertThat(notesService.averageOf(anyString())).isEqualTo(notes);
        verify(notesService);
    }

    @AfterEach
    void tearDown() {
        notesService = null;
    }
}