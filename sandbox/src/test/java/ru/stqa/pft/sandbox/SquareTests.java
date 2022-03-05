package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

  @Test
  public void testPointTrue() {
    Point p1 = new Point(1, 0);
    Point p2 = new Point(2, 0);
    Assert.assertEquals(p1.distance(p2), 1.0);
  }
  @Test
  public void testPointFalse() {
    Point p1 = new Point(1, 0);
    Point p2 = new Point(2, 0);
    Assert.assertEquals(p1.distance(p2), 2.0);
  }
}
