package domain;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static Collection<Issue> filterBy(Collection<Issue> list, Predicate<Issue> predicate) {
        return list.stream()
                .filter(predicate)
                .collect(Collectors.<Issue>toList());
    }
}
