package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOPIC;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Flashcard.
 */
public class FlashcardUtil {

    /**
     * Returns an add command string for adding the {@code flashcard}.
     */
    public static String getAddCommand(Flashcard flashcard) {
        return AddCommand.COMMAND_WORD + " " + getFlashcardDetails(flashcard);
    }

    /**
     * Returns the part of command string for the given {@code flashcard}'s details.
     */
    public static String getFlashcardDetails(Flashcard flashcard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TOPIC + flashcard.getTopic().fullTopic + " ");
        sb.append(PREFIX_DIFFICULTY + flashcard.getDifficulty().value + " ");
        sb.append(PREFIX_EMAIL + flashcard.getEmail().value + " ");
        sb.append(PREFIX_CONTENT + flashcard.getContent().value + " ");
        flashcard.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFlashcardDescriptor}'s details.
     */
    public static String getEditFlashcardDescriptorDetails(EditFlashcardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTopic().ifPresent(topic -> sb.append(PREFIX_TOPIC).append(topic.fullTopic).append(" "));
        descriptor.getDifficulty().ifPresent(difficulty ->
                sb.append(PREFIX_DIFFICULTY).append(difficulty.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getContent().ifPresent(content -> sb.append(PREFIX_CONTENT).append(content.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
