package shapes

import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

class GeometricFigureSpec extends FlatSpec with MockFactory{

  "the rectangle" should "have an area of 6" in {
    assertResult(6.0)(new Rectangle(3, 2).area)
  }
  "the square" should "have an area of 4" in {
    assertResult(4.0)(new Square(2).area)
  }

  "the circle" should "have an area of 78.53981633974483" in {
    assertResult(78.53981633974483)(new Circle(5).area)
  }
  "the ellipse" should "have an area of 100.53096491487338" in {
    assertResult(100.53096491487338)(new Ellipse(4, 8).area)
  }

  "the rectangle" should "have a perimeter of 12" in {
    assertResult(12.0)(new Rectangle(4, 2).perimeter)
  }
  "the square" should "have a perimeter of 8" in {
    assertResult(8.0)(new Square(2).perimeter)
  }

  "the circle" should "have a perimeter of " in {
    assertResult(31.41592653589793)(new Circle(5).perimeter)
  }
  "the ellipse" should "have a perimeter of 39.738353063184405" in {
    assertResult(39.738353063184405)(new Ellipse(4, 8).perimeter)
  }

  "the rectangle" should "not allow a negative value" in {
    assertThrows[IllegalArgumentException] {
      new Rectangle(-5, -4)
    }
  }
  "the square" should "not allow a negative value" in {
    assertThrows[IllegalArgumentException] {
      new Square(-5)
    }
  }

  "the circle" should "not allow a negative radii" in {
    assertThrows[IllegalArgumentException] {
      new Circle(-5)
    }
  }
  "the ellipse" should "not allow a negative radii" in {
    assertThrows[IllegalArgumentException] {
      new Ellipse(-5, -4)
    }
  }





}
