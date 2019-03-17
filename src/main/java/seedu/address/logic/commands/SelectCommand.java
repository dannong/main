package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.subject.Subject;

/**
 * Selects a subject identified using it's displayed index from the address book.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the subject identified by the index number used in the displayed subject list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_SUBJECT_SUCCESS = "Selected Subject: %1$s";

    private final Index targetIndex;

    public SelectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Subject> filteredSubjectList = model.getFilteredSubjectList();

        if (targetIndex.getZeroBased() >= filteredSubjectList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SUBJECT_DISPLAYED_INDEX);
        }

        model.setSelectedSubject(filteredSubjectList.get(targetIndex.getZeroBased()));
        return new CommandResult(String.format(MESSAGE_SELECT_SUBJECT_SUCCESS, targetIndex.getOneBased()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                && targetIndex.equals(((SelectCommand) other).targetIndex)); // state check
    }
}
