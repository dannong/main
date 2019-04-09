package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;

/**
 * Deletes a flashcard identified using it's displayed index from the flash book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_ALIAS = "d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the flashcard identified by the index number used in the displayed flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FLASHCARD_SUCCESS = "Deleted Flashcard: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToDelete = lastShownList.get(targetIndex.getZeroBased());

        /**
         * int count=0;
         * for (Flashcard f:) {
         *    if (f.getSubject() == flashcardToDelete.getSubject()) {
         *                 count++;
         *     }
         *  }
         *
         * FilteredList<Flashcard> remainingFlashcards = new FilteredList<>(model.getFilteredFlashcardList(),new
         *     TopicContainsSubjectPredicate(Arrays.asList(flashcardToDelete.getSubjectName())));
         *
         * if (count == 1) {
         *      model.deleteSubject(flashcardToDelete.getSubject());
         * }
         */


        model.deleteFlashcard(flashcardToDelete);
        model.commitFlashBook();
        model.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);
        model.getFilteredSubjectList();
        return new CommandResult(String.format(MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

}
