import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

class StringMatcher implements StringChecker {
  String match;

  public StringMatcher(String match) {
      this.match = match;
  }

  public boolean checkString(String s) {
      return s.contains(match);
  }
}

public class TestListExamples {
  @SuppressWarnings("unchecked")
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @SuppressWarnings("unchecked")
  @Test(timeout = 500)
  public void testFilterMoon() {
    StringChecker moonChecker = new IsMoon();
    List<String> strInput = new ArrayList();
    List<String> strInput2 = new ArrayList();
    strInput.add("moon");
    strInput.add("MOON");
    strInput.add("earth");
    strInput.add("MoOn");
    strInput.add("jupiter moon");

    strInput2.add("moon");
    strInput2.add("MOON");
    strInput2.add("MoOn");

    List<String> output = ListExamples.filter(strInput, moonChecker);
    assertEquals(strInput2, output);
  }

  @SuppressWarnings("unchecked")
  @Test(timeout = 500)
  public void testFilter() {
      StringChecker checker1 = new StringMatcher("l");
      StringChecker checker = new StringMatcher("he");
      List<String> strInput = new ArrayList<>();
      List<String> strInput2 = new ArrayList<>();
      List<String> strInput3 = new ArrayList<>();
      strInput.add("hello");
      strInput.add("world");
      strInput.add("helloo");
      strInput2.add("hello");
      strInput2.add("helloo");
      strInput3.add("hello");
      strInput3.add("world");
      strInput3.add("helloo");
      List<String> output1 = ListExamples.filter(strInput, checker1);
      List<String> output = ListExamples.filter(strInput, checker);
      assertEquals(output1, strInput3);
      assertEquals(output, strInput2);
  }

  @SuppressWarnings("unchecked")
  @Test(timeout = 500)
  public void testMerge() {
      List<String> strInput1 = new ArrayList<>();
      List<String> strInput2 = new ArrayList<>();

      strInput2.add("string!");

      assertEquals(strInput2, ListExamples.merge(strInput1, strInput2));
  }
}
