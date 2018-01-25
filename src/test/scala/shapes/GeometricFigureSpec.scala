package shapes

import java.awt.Color

import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

class GeometricFigureSpec extends FlatSpec with MockFactory {


  "the rectangle" should "have an area of 6" in {
    assertResult(6.0)(new Rectangle(3, 2).area)
  }

  it should "have a perimeter of 12" in {
    assertResult(12.0)(new Rectangle(4, 2).perimeter)
  }

  it should "not allow a negative value" in {
    assertThrows[IllegalArgumentException] {
      new Rectangle(-5, -4)
    }
  }

  it should "throw exception if origin contains negative value" in {
    assertThrows[IllegalArgumentException] { new Rectangle(3, 5, origin = (-1, 5)) }
    assertThrows[IllegalArgumentException] { new Rectangle(3, 5, origin = (1, -5)) }
  }

  it should "be able to draw its outline with colour (no fill)" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineRectangle _).expects(4.0, 2.4, 4.8, 1.3)
    }
    new Rectangle(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, filled = false).draw(canvasMock)

  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillRectangle _).expects(4.0, 2.4, 4.8, 1.3)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineRectangle _).expects(4.0, 2.4, 4.8, 1.3)
    }
    new Rectangle(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Color.RED, filled = true)
      .draw(canvasMock)
  }

  it should "be equal to another rectangle if origin, width and height are the same" in {
    assert(
      new Rectangle(13, 4.5, (3.4, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true)
    )
  }

  it should "not be equal to another rectangle if origin, width and height are different" in {
    assert(
      !(new Rectangle(13, 5.9, (3.4, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
    assert(
      !(new Rectangle(1, 4.5, (3.4, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
    assert(
      !(new Rectangle(13, 4.5, (9.3, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
  }

  it should "be equal to a square if origin, width and height are the same, and width and height are equals" in {
    assert(
      new Rectangle(4.5, 4.5, (3.4, 4.5)) == new Square(4.5, (3.4, 4.5))
    )
  }

  it should "have a factory method for width, height and origin" in {
    val factoryMade = Rectangle(3.4, 5.6, (2.3, 5.6))
    assert(factoryMade.width == 3.4)
    assert(factoryMade.height == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
  }

  it should "have a factory method for width, height, origin and strokeColor" in {
    val factoryMade = Rectangle(3.4, 5.6, (2.3, 5.6), Color.CYAN)
    assert(factoryMade.width == 3.4)
    assert(factoryMade.height == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(!factoryMade.filled)
  }

  it should "have a factory method for width, height, origin, strokeColor and fillColor" in {
    val factoryMade = Rectangle(3.4, 5.6, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.width == 3.4)
    assert(factoryMade.height == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor == Color.RED)
    assert(factoryMade.filled)
  }

  "the square" should "have an area of 4" in {
    assertResult(4.0)(new Square(2).area)
  }

  it should "have a perimeter of 8" in {
    assertResult(8.0)(new Square(2).perimeter)
  }

  it should "not allow a negative value" in {
    assertThrows[IllegalArgumentException] {
      new Square(-5)
    }
  }

  it should "throw exception if origin contains negative value" in {
    assertThrows[IllegalArgumentException] { new Square(3, origin = (-2, 3)) }
    assertThrows[IllegalArgumentException] { new Square(3, origin = (3, -4)) }
  }

  it should "be able to draw its outline with colour (no fill)" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineRectangle _).expects(4.0, 2.4, 5.3, 5.3)
    }
    new Square(5.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, filled = false).draw(canvasMock)

  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillRectangle _).expects(4.0, 2.4, 5.3, 5.3)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineRectangle _).expects(4.0, 2.4, 5.3, 5.3)
    }
    new Square(5.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Color.RED, filled = true)
      .draw(canvasMock)

  }

  it should "be equal to another square if origin, length are the same" in {
    assert(
      new Square(13, (3.4, 4.5)) == new Square(13, (3.4, 4.5), Color.RED, Color.CYAN, true)
    )
  }

  it should "not be equal to another square if origin, length are different" in {
    assert(
      !(new Square(17, (3.4, 4.5)) == new Square(13, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
    assert(
      !(new Square(13, (6.5, 4.5)) == new Square(13, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
  }

  it should "be equal to a rectangle if origin, length are the same" in {
    assert(
      new Square(4.5, (3.4, 4.5)) == new Rectangle(4.5, 4.5, (3.4, 4.5))
    )
  }

  it should "have a factory method for length and origin" in {
    val factoryMade = Square(3.4, (2.3, 5.6))
    assert(factoryMade.length == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
  }

  it should "have a factory method for length, origin and strokeColor" in {
    val factoryMade = Square(3.4, (2.3, 5.6), Color.CYAN)
    assert(factoryMade.length == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(!factoryMade.filled)
  }

  it should "have a factory method for length, origin, strokeColor and fillColor" in {
    val factoryMade = Square(3.4, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.length == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor == Color.RED)
    assert(factoryMade.filled)
  }

  "the ellipse" should "have an area of 100.53096491487338" in {
    assertResult(100.53096491487338)(new Ellipse(4, 8).area)
  }
  it should "have a perimeter of 39.738353063184405" in {
    assertResult(39.738353063184405)(new Ellipse(4, 8).perimeter)
  }
  it should "not allow a negative radii" in {
    assertThrows[IllegalArgumentException] {
      new Ellipse(-5, -4)
    }
  }
  it  should "throw exception if origin contains negative value" in {
    assertThrows[IllegalArgumentException] { new Ellipse(3, 5, origin = (-2, 3)) }
    assertThrows[IllegalArgumentException] { new Ellipse(3, 5, origin = (3, -4)) }
  }

  it should "be able to draw its outline with colour (no fill)" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineEllipse _).expects(4.0, 2.4, 4.8, 1.3)
    }
    new Ellipse(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, filled = false).draw(canvasMock)
  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillEllipse _).expects(4.0, 2.4, 4.8, 1.3)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineEllipse _).expects(4.0, 2.4, 4.8, 1.3)
    }
    new Ellipse(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Color.RED, filled = true)
      .draw(canvasMock)

  }

  it should "be equal to another rectangle if origin, hRadius and vRadius are the same" in {
    assert(
      new Ellipse(13, 4.5, (3.4, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true)
    )
  }

  it should "not be equal to another rectangle if origin, hRadius and vRadius are different" in {
    assert(
      !(new Ellipse(13, 5.9, (3.4, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
    assert(
      !(new Ellipse(1, 4.5, (3.4, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
    assert(
      !(new Ellipse(13, 4.5, (9.3, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
  }

  it should "be equal to a square if origin, hRadius and vRadius are the same and hRadius and vRadius are equals" in {
    assert(
      new Ellipse(4.5, 4.5, (3.4, 4.5)) == new Circle(4.5, (3.4, 4.5))
    )
  }

  it should "have a factory method for vRadius, hRadius and origin" in {
    val factoryMade = Ellipse(3.4, 5.6, (2.3, 5.6))
    assert(factoryMade.hRadius == 3.4)
    assert(factoryMade.vRadius == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
  }

  it should "have a factory method for vRadius, hRadius, origin and strokeColor" in {
    val factoryMade = Ellipse(3.4, 5.6, (2.3, 5.6), Color.CYAN)
    assert(factoryMade.hRadius == 3.4)
    assert(factoryMade.vRadius == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(!factoryMade.filled)
  }

  it should "have a factory method for vRadius, hRadius, origin, strokeColor and fillColor" in {
    val factoryMade = Ellipse(3.4, 5.6, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.hRadius == 3.4)
    assert(factoryMade.vRadius == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor == Color.RED)
    assert(factoryMade.filled)
  }


  "the circle" should "have an area of 78.53981633974483" in {
    assertResult(78.53981633974483)(new Circle(5).area)
  }

  it should "have a perimeter of " in {
    assertResult(31.41592653589793)(new Circle(5).perimeter)
  }

  it should "not allow a negative radii" in {
    assertThrows[IllegalArgumentException] {
      new Circle(-5)
    }
  }

  it should "throw exception if origin contains negative value" in {
    assertThrows[IllegalArgumentException] { new Circle(3, origin = (-2, 3)) }
    assertThrows[IllegalArgumentException] { new Circle(3, origin = (3, -4)) }
  }

  it should "be able to draw its outline with colour (no fill)" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineEllipse _).expects(4.0, 2.4, 7.4, 7.4)
    }
    new Circle(7.4, origin = (4.0, 2.4), strokeColor = Color.CYAN, filled = false).draw(canvasMock)

  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillEllipse _).expects(4.0, 2.4, 7.4, 7.4)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineEllipse _).expects(4.0, 2.4, 7.4, 7.4)
    }
    new Circle(7.4, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Color.RED, filled = true)
      .draw(canvasMock)

  }

  it should "be equal to another circle if origin, radius are the same" in {
    assert(
      new Circle(13, (3.4, 4.5)) == new Circle(13, (3.4, 4.5), Color.RED, Color.CYAN, true)
    )
  }

  it should "not be equal to another circle if origin, radius are different" in {
    assert(
      !(new Circle(17, (3.4, 4.5)) == new Circle(13, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
    assert(
      !(new Circle(13, (6.5, 4.5)) == new Circle(13, (3.4, 4.5), Color.RED, Color.CYAN, true))
    )
  }

  it should "be equal to an ellipse if origin, radius are the same" in {
    assert(
      new Circle(4.5, (3.4, 4.5)) == new Ellipse(4.5, 4.5, (3.4, 4.5))
    )
  }

  it should "have a factory method for radius and origin" in {
    val factoryMade = Circle(3.4, (2.3, 5.6))
    assert(factoryMade.radius == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
  }

  it should "have a factory method for radius, origin and strokeColor" in {
    val factoryMade = Circle(3.4,(2.3, 5.6), Color.CYAN)
    assert(factoryMade.radius == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(!factoryMade.filled)
  }

  it should "have a factory method for radius, origin, strokeColor and fillColor" in {
    val factoryMade = Circle(3.4, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.radius == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor == Color.RED)
    assert(factoryMade.filled)
  }
}
