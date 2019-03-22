package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.flashcard.Address;
import seedu.address.model.flashcard.Deadline;
import seedu.address.model.flashcard.Email;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Phone;
import seedu.address.model.flashcard.Topic;

import seedu.address.model.tag.Subject;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Deadline EMPTY_DEADLINE = new Deadline("");
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[] {
            new Flashcard(new Topic("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_DEADLINE,
                getTagSet("friends")),
            new Flashcard(new Topic("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_DEADLINE,
                getTagSet("colleagues", "friends")),
            new Flashcard(new Topic("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_DEADLINE,
                getTagSet("neighbours")),
            new Flashcard(new Topic("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_DEADLINE,
                getTagSet("family")),
            new Flashcard(new Topic("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_DEADLINE,
                getTagSet("classmates")),
            new Flashcard(new Topic("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_DEADLINE,
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Subject> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Subject::new)
                .collect(Collectors.toSet());
    }

}
