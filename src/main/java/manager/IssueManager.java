package manager;

import repository.IssueRepository;
import domain.Issue;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repo = new IssueRepository();
    Set<String> labels = new HashSet<>();

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
        Collection<Issue> tmp = repo.showAll();
        Collection<Issue> result = new ArrayList<>();
        for (Issue issue : tmp) {
            if (issue.matchesAuthor(author)) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> filterByAssignee(String assignee) {
        Collection<Issue> tmp = repo.showAll();
        Collection<Issue> result = new ArrayList<>();
        for (Issue issue : tmp) {
            if (issue.matchesAssignee(assignee)) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> filterByLabel(Set<String> labels) {
        Collection<Issue> tmp = repo.showAll();
        Collection<Issue> result = new ArrayList<>();
        for (Issue issue : tmp) {
            if (issue.matchesLabel(labels)) {
                result.add(issue);
            }
        }
        return result;
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
}
