package manager;

import domain.Filter;
import repository.IssueRepository;
import domain.Issue;

import java.util.*;

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
        return Filter.filterBy(repo.showAll(), author(author));
    }

    public Collection<Issue> filterByAssignee(String assignee) {
        return Filter.filterBy(repo.showAll(), assignee(assignee));
    }

    public Collection<Issue> filterByLabel(Set<String> labels) {
        return Filter.filterBy(repo.showAll(), labels(labels));
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
