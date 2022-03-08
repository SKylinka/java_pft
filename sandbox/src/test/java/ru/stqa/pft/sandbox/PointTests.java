package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPointTrue1() {
    Point p1 = new Point(1, 0);
    Point p2 = new Point(2, 0);
    Assert.assertEquals(p1.distance(p2), 1.0);
  }
  @Test
  public void testPointTrue2() {
    Point p1 = new Point(1, 0);
    Point p2 = new Point(2, 0);
    Assert.assertNotEquals(p1.distance(p2), 2.0);
  }
  @Test
  public void testPointTrue3() {
    Point p1 = new Point(3, 3);
    Point p2 = new Point(4, 4);
    Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
  }
  @Test
  public void testPointFalse() {
    Point p1 = new Point(3, 3);
    Point p2 = new Point(4, 4);
    Assert.assertNotEquals(p1.distance(p2), 1.0);
  }
}
