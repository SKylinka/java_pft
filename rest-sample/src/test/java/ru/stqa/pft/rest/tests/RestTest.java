package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTest extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(1968);
    Set<Issue> oldIssue = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssue.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssue);
  }

 }
