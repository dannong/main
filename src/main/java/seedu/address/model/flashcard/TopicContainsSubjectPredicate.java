package seedu.address.model.flashcard;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Topic} matches any of the keywords given.
 */
public class TopicContainsSubjectPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public TopicContainsSubjectPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(flashcard.getSubject().subjectName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TopicContainsSubjectPredicate // instanceof handles nulls
                && keywords.equals(((TopicContainsSubjectPredicate) other).keywords)); // state check
    }

}
