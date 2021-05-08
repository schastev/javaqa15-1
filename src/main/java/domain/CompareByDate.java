package domain;

import java.util.Comparator;

public class CompareByDate implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getDate() - o2.getDate();
    }
}
