package repository;

import domain.Issue;

import java.util.ArrayList;
import java.util.Collection;

public class IssueRepository {

    private Collection<Issue> repo = new ArrayList<>();


    public void add(Issue issue) {
        repo.add(issue);
    }

    public Collection<Issue> showAll() {
        return repo;
    }

    public Collection<Issue> showOpenIssues() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue issue : repo) {
            if (issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public Collection<Issue> showClosedIssues() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue issue : repo) {
            if (!issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public void removeAll() {
        repo.clear();
    }
    
}
