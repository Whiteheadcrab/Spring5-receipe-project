package whiteheadcrab.springframework.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import whiteheadcrab.springframework.commands.NotesCommand;
import whiteheadcrab.springframework.domain.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>
{
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes)
    {
        if (notes == null)
        {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setRecipeNotes(notes.getRecipeNotes());
        return notesCommand;
    }
}
