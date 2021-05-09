package manager;

import repository.IssueRepository;
import domain.Issue;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static domain.Filter.*;

public class IssueManager {
    private IssueRepository repo = new IssueRepository();

    public void add(Issue issue) {
        repo.add(issue);
    }

    public Collection<Issue> showAll() {
        return repo.showAll();
    }

    public Collection<Issue> showOpenIssues() {
        return repo.showOpenIssues();
    }

    public Collection<Issue> showClosedIssues() {
        return repo.showClosedIssues();
    }

    public Collection<Issue> filterByAuthor(String author) {
        return filterBy(author(author));
    }

    public Collection<Issue> filterByAssignee(String assignee) {
        return filterBy(assignee(assignee));
    }

    public Collection<Issue> filterByLabel(Set<String> labels) {
        return filterBy(labels(labels));
    }

    public Collection<Issue> sortNewerFirst(Comparator<Issue> comparator) {
        ArrayList<Issue> result = new ArrayList<>(repo.showAll());
        result.sort(comparator.reversed());
        return result;
    }

    public Collection<Issue> sortOlderFirst(Comparator<Issue> comparator) {
        ArrayList<Issue> result = new ArrayList<>(repo.showAll());
        result.sort(comparator);
        return result;
    }

    public void flipIssueStatus(int id) {
        Collection<Issue> tmp = repo.showAll();
        for (Issue issue : tmp) {
            if (issue.getId() == id) {
                issue.setOpen(!issue.isOpen());
            }
        }
    }

    public void removeAll() {
        repo.removeAll();
    }

    public Collection<Issue> filterBy(Predicate<Issue> predicate) {
        return repo.showAll().stream()
                .filter(predicate)
                .collect(Collectors.<Issue>toList());
    }
}
