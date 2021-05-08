package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private boolean isOpen;
    private String author;
    private String assignee;
    private int date;
    private Set<String> labels;

    public boolean matchesAuthor(String search) {
        return this.getAuthor().equalsIgnoreCase(search);
    }

    public boolean matchesAssignee(String search) {
        return this.getAssignee().equalsIgnoreCase(search);
    }

    public boolean matchesLabel(Set<String> labels) {
        for (String label : labels) {
            if (this.labels.contains(label))
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Issue o) {
        return date - o.date;
    }
}
