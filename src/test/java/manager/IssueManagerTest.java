package manager;

import domain.CompareByDate;
import domain.Issue;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueManager repo = new IssueManager();
    private final Issue one = new Issue(0, true, "A", "B", 20210301, new HashSet<>(Arrays.asList("engine", "critical")));
    private final Issue two = new Issue(1, false, "B", "B", 20210219, new HashSet<>(Arrays.asList("engine", "minor")));
    private final Issue three = new Issue(2, true, "A", "A", 20200420, new HashSet<>(Arrays.asList("engine", "critical")));
    private final Issue four = new Issue(3, false, "C", "C", 20200510, new HashSet<>(Arrays.asList("UI", "critical")));
    private final Issue five = new Issue(4, true, "A", "A", 20210310, new HashSet<>(Arrays.asList("UI", "major")));

    @Nested
    public class Empty {
        @BeforeEach
        void setUp() {
            repo.add(one);
            repo.add(two);
            repo.add(three);
            repo.add(four);
            repo.add(five);
        }

        @AfterEach
        void cleanUp() {
            repo.removeAll();
        }

        @Test
        void showOpenIssuesTest() {
            for (Issue issue : repo.showAll()) {
                if (issue.isOpen()) repo.flipIssueStatus(issue.getId());
            }
            Collection<Issue> actual = repo.showOpenIssues();
            assert actual.isEmpty();
        }

        @Test
        void showClosedIssues() {
            for (Issue issue : repo.showAll()) {
                if (!issue.isOpen()) repo.flipIssueStatus(issue.getId());
            }
            Collection<Issue> actual = repo.showClosedIssues();
            assert actual.isEmpty();
        }

        @Test
        void filterByAuthorTest() {
            Collection<Issue> actual = repo.filterByAuthor("D");
            assert actual.isEmpty();
        }

        @Test
        void filterByAssigneeTest() {
            Collection<Issue> actual = repo.filterByAssignee("D");
            assert actual.isEmpty();
        }

        @Test
        void filterByLabelTest() {
            Collection<Issue> actual = repo.filterByLabel(new HashSet<>(Collections.singletonList("123")));
            assert actual.isEmpty();
        }
    }

    @Nested
    public class SingleItem {
        @BeforeEach
        void setUp() {
            repo.add(one);
            repo.add(two);
            repo.add(three);
            repo.add(four);
            repo.add(five);
        }

        @AfterEach
        void cleanUp() {
            repo.removeAll();
        }

        @Test
        void showOpenIssuesTest() {
            Collection<Issue> expected = List.of(one, three, five);
            Collection<Issue> actual = repo.showOpenIssues();
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void showClosedIssues() {
            Collection<Issue> expected = List.of(two, four);
            Collection<Issue> actual = repo.showClosedIssues();
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void filterByAuthorTest() {
            Collection<Issue> expected = List.of(four);
            Collection<Issue> actual = repo.filterByAuthor("C");
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void filterByAssigneeTest() {
            Collection<Issue> expected = List.of(four);
            Collection<Issue> actual = repo.filterByAssignee("C");
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void filterByLabelTest() {
            Collection<Issue> expected = List.of(five);
            Collection<Issue> actual = repo.filterByLabel(new HashSet<>(Collections.singletonList("major")));
            assertArrayEquals(expected.toArray(), actual.toArray());
        }
    }

    @Nested
    public class MultipleItems {
        @BeforeEach
        void setUp() {
            repo.add(one);
            repo.add(two);
            repo.add(three);
            repo.add(four);
            repo.add(five);
        }

        @AfterEach
        void cleanUp() {
            repo.removeAll();
        }

        @Test
        void showAllTest() {
            Collection<Issue> expected = List.of(one, two, three, four, five);
            Collection<Issue> actual = repo.showAll();
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void showOpenIssuesTest() {
            Collection<Issue> expected = List.of(one, three, five);
            Collection<Issue> actual = repo.showOpenIssues();
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void showClosedIssues() {
            Collection<Issue> expected = List.of(two, four);
            Collection<Issue> actual = repo.showClosedIssues();
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void filterByAuthorTest() {
            Collection<Issue> expected = List.of(one, three, five);
            Collection<Issue> actual = repo.filterByAuthor("A");
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void filterByAssigneeTest() {
            Collection<Issue> expected = List.of(three, five);
            Collection<Issue> actual = repo.filterByAssignee("A");
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void filterByLabelTest() {
            Collection<Issue> expected = List.of(one, three, four);
            Collection<Issue> actual = repo.filterByLabel(new HashSet<>(Collections.singletonList("critical")));
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void sortNewerFirstTest() {
            Collection<Issue> expected = List.of(five, one, two, four, three);
            Collection<Issue> actual = repo.sortNewerFirst(new CompareByDate());
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void sortOlderFirstTest() {
            Collection<Issue> expected = List.of(three, four, two, one, five);
            Collection<Issue> actual = repo.sortOlderFirst(new CompareByDate());
            assertArrayEquals(expected.toArray(), actual.toArray());
        }

        @Test
        void flipIssueStatusToTrue() {
            Collection<Issue> expected = List.of(
                    one,
                    two,
                    three,
                    four,
                    new Issue(4, false, "A", "A", 20210310, new HashSet<>(Arrays.asList("UI", "major"))));
            repo.flipIssueStatus(4);
            assertArrayEquals(expected.toArray(), repo.showAll().toArray());
        }

        @Test
        void flipIssueStatusToFalse() {
            Collection<Issue> expected = List.of(
                    one,
                    two,
                    three,
                    new Issue(3, true, "C", "C", 20200510, new HashSet<>(Arrays.asList("UI", "critical"))),
                    five);
            repo.flipIssueStatus(3);
            assertArrayEquals(expected.toArray(), repo.showAll().toArray());
        }
    }
}
