package domain;

import java.util.Set;
import java.util.function.Predicate;

public class Filter {

    public static Predicate<Issue> author(String author) {
        return i -> i.matchesAuthor(author);
    }

    public static Predicate<Issue> assignee(String assignee) {
        return i -> i.matchesAssignee(assignee);
    }

    public static Predicate<Issue> labels(Set<String> labels) {
        return i -> i.matchesLabel(labels);
    }
}
