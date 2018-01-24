package shapes

import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

class GeometricFigureSpec extends FlatSpec with MockFactory{

  "the rectangle" should "have an area of 6" in {
    assertResult(6.0)(new Rectangle(3, 2).area())
  }
  "the square" should "have an area of 4" in {
    assertResult(4.0)(new Square(2).area())
  }

  "the circle" should "have an area of 6" in {
    assertResult(6.0)(new Circle(3).area())
  }
  "the ellipse" should "have an area of 4" in {
    assertResult(4.0)(new Ellipse(2).area())
  }



}
