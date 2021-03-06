package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOPIC;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.SubjectTag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TOPIC, PREFIX_DIFFICULTY, PREFIX_CONTENT, PREFIX_SUBJECT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditFlashcardDescriptor editFlashcardDescriptor = new EditCommand.EditFlashcardDescriptor();
        if (argMultimap.getValue(PREFIX_TOPIC).isPresent()) {
            editFlashcardDescriptor.setTopic(ParserUtil.parseTopic(argMultimap.getValue(PREFIX_TOPIC).get()));
        }
        if (argMultimap.getValue(PREFIX_DIFFICULTY).isPresent()) {
            editFlashcardDescriptor.setDifficulty(ParserUtil
                    .parseDifficulty(argMultimap.getValue(PREFIX_DIFFICULTY).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTENT).isPresent()) {
            editFlashcardDescriptor.setContent(ParserUtil.parseContent(argMultimap.getValue(PREFIX_CONTENT).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_SUBJECT)).ifPresent(editFlashcardDescriptor::setTags);

        if (!editFlashcardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editFlashcardDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<SubjectTag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<SubjectTag>} containing zero tags.
     */
    private Optional<Set<SubjectTag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
