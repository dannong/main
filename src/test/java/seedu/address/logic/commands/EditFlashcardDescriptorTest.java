package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIFFICULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOPIC_BOB;

import org.junit.Test;

import seedu.address.testutil.EditFlashcardDescriptorBuilder;

public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditFlashcardDescriptor descriptorWithSameValues =
                new EditCommand.EditFlashcardDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different topic -> returns false
        EditCommand.EditFlashcardDescriptor editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY)
            .withTopic(VALID_TOPIC_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different difficulty -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY).withDifficulty(VALID_DIFFICULTY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different content -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY).withContent(VALID_CONTENT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}