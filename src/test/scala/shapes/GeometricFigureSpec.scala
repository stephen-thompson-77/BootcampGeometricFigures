package shapes

import java.awt.Color

import bootcamp_fraction.MyFraction
import moodsdesign.cw.provd.FxFigureCanvasApp.canvas
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
    new Rectangle(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = None).draw(canvasMock)

  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillRectangle _).expects(4.0, 2.4, 4.8, 1.3)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineRectangle _).expects(4.0, 2.4, 4.8, 1.3)
    }
    new Rectangle(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Some(Color.RED))
      .draw(canvasMock)
  }

  it should "be equal to another rectangle if origin, width and height are the same" in {
    assert(
      new Rectangle(13, 4.5, (3.4, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN))
    )
  }

  it should "not be equal to another rectangle if origin, width and height are different" in {
    assert(
      !(new Rectangle(13, 5.9, (3.4, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
    )
    assert(
      !(new Rectangle(1, 4.5, (3.4, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
    )
    assert(
      !(new Rectangle(13, 4.5, (9.3, 4.5)) == new Rectangle(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
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
  }

  it should "have a factory method for width, height, origin, strokeColor and fillColor" in {
    val factoryMade = Rectangle(3.4, 5.6, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.width == 3.4)
    assert(factoryMade.height == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor.contains(Color.RED))
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
    new Square(5.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = None).draw(canvasMock)

  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillRectangle _).expects(4.0, 2.4, 5.3, 5.3)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineRectangle _).expects(4.0, 2.4, 5.3, 5.3)
    }
    new Square(5.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Some(Color.RED))
      .draw(canvasMock)

  }

  it should "be equal to another square if origin, length are the same" in {
    assert(
      new Square(13, (3.4, 4.5)) == new Square(13, (3.4, 4.5), Color.RED, Some(Color.CYAN))
    )
  }

  it should "not be equal to another square if origin, length are different" in {
    assert(
      !(new Square(17, (3.4, 4.5)) == new Square(13, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
    )
    assert(
      !(new Square(13, (6.5, 4.5)) == new Square(13, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
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
  }

  it should "have a factory method for length, origin, strokeColor and fillColor" in {
    val factoryMade = Square(3.4, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.length == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor.contains(Color.RED))
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
    new Ellipse(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = None).draw(canvasMock)
  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillEllipse _).expects(4.0, 2.4, 4.8, 1.3)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineEllipse _).expects(4.0, 2.4, 4.8, 1.3)
    }
    new Ellipse(4.8, 1.3, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Some(Color.RED))
      .draw(canvasMock)

  }

  it should "be equal to another rectangle if origin, hRadius and vRadius are the same" in {
    assert(
      new Ellipse(13, 4.5, (3.4, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN))
    )
  }

  it should "not be equal to another rectangle if origin, hRadius and vRadius are different" in {
    assert(
      !(new Ellipse(13, 5.9, (3.4, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
    )
    assert(
      !(new Ellipse(1, 4.5, (3.4, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
    )
    assert(
      !(new Ellipse(13, 4.5, (9.3, 4.5)) == new Ellipse(13, 4.5, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
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
  }

  it should "have a factory method for vRadius, hRadius, origin, strokeColor and fillColor" in {
    val factoryMade = Ellipse(3.4, 5.6, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.hRadius == 3.4)
    assert(factoryMade.vRadius == 5.6)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor.contains(Color.RED))
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
    new Circle(7.4, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = None).draw(canvasMock)

  }

  it should "be able to draw its outline with stroke colour and fill colour" in {
    val canvasMock = mock[FigureCanvas]
    inSequence {
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillEllipse _).expects(4.0, 2.4, 7.4, 7.4)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.outlineEllipse _).expects(4.0, 2.4, 7.4, 7.4)
    }
    new Circle(7.4, origin = (4.0, 2.4), strokeColor = Color.CYAN, fillColor = Some(Color.RED))
      .draw(canvasMock)

  }

  it should "be equal to another circle if origin, radius are the same" in {
    assert(
      new Circle(13, (3.4, 4.5)) == new Circle(13, (3.4, 4.5), Color.RED, Some(Color.CYAN))
    )
  }

  it should "not be equal to another circle if origin, radius are different" in {
    assert(
      !(new Circle(17, (3.4, 4.5)) == new Circle(13, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
    )
    assert(
      !(new Circle(13, (6.5, 4.5)) == new Circle(13, (3.4, 4.5), Color.RED, Some(Color.CYAN)))
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
  }

  it should "have a factory method for radius, origin, strokeColor and fillColor" in {
    val factoryMade = Circle(3.4, (2.3, 5.6), Color.CYAN, Color.RED)
    assert(factoryMade.radius == 3.4)
    assert(factoryMade.origin == (2.3, 5.6))
    assert(factoryMade.strokeColor == Color.CYAN)
    assert(factoryMade.fillColor.contains(Color.RED))
  }

  "Resizing a square" should "takes a fraction and apply a resize by the length" in {
    val sq = Square(60, (34.5, 12.4))
    val frac = MyFraction(1, 3)

    assert {
      val resized = sq.resize(frac)
      20 == resized.length
    }
  }

  it should "not change the origin point" in {
    val sq = Square(60, (34.5, 12.4))
    val frac = MyFraction(1, 3)

    val resized = sq.resize(frac)
    assertResult((34.5, 12.4))(resized.origin)
  }

  "Resizing a rectangle" should "take a rectangle and a fraction and apply a resize by the width and height" in {
    val rec = Rectangle(60, 52, (34.5, 12.4))
    val frac = MyFraction(2, 8)

    assert {
      val resized = rec.resize(frac)
      15 == resized.width
      13 == resized.height
    }
  }

  it should "not change the origin point" in {
    val rec = Rectangle(60, 52, (34.5, 12.4))
    val frac = MyFraction(1, 3)

    val resized = rec.resize(frac)
    assertResult((34.5, 12.4))(resized.origin)

  }

  "Resizing a ellipse" should "take a ellipse and a fraction and apply a resize by the vRadius and hRadius" in {
    val ell = Ellipse(60, 52, (34.5, 12.4))
    val frac = MyFraction(2, 8)

    assertResult(Ellipse(15, 13, (34.5, 12.4)))(ell.resize(frac))
  }

  it should "not change the origin point" in {
    val ell = Ellipse(60, 52, (34.5, 12.4))
    val frac = MyFraction(1, 3)

    val resized = ell.resize(frac)
    assertResult((34.5, 12.4))(resized.origin)

  }

  "Resizing a circle" should "take a ellipse and a fraction and apply a resize by the radius" in {
    val circ = Circle(60, (34.5, 12.4))
    val frac = MyFraction(2, 8)

    assertResult(Circle(15, (34.5, 12.4)))(circ.resize(frac))
  }

  it should "not change the origin point" in {
    val circ = Circle(60, (34.5, 12.4))
    val frac = MyFraction(1, 3)

    val resized = circ.resize(frac)
    assertResult((34.5, 12.4))(resized.origin)
  }

  "Linearisation of Traits" should "add border and background in expected order" in {

    val canvasMock = mock[FigureCanvas]
    inSequence {

      (canvasMock.setDrawingColor _).expects(Color.BLACK)
      (canvasMock.outlineRectangle _).expects(-124.4, -168.0, 256.8, 340.8)

      (canvasMock.setDrawingColor _).expects(Color.LIGHT_GRAY)
      (canvasMock.fillRectangle _).expects(-124.4, -168.0, 256.8, 340.8)

      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.fillRectangle _).expects(-124.4, -168.0, 256.8, 340.8)

      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.fillEllipse _).expects(4.0, 2.4, 103.4, 145.4)

      (canvasMock.setDrawingColor _).expects(Color.BLUE)
      (canvasMock.outlineEllipse _).expects(4.0, 2.4, 103.4, 145.4)
    }

    val ell: Ellipse = new Ellipse(103.4, 145.4, (4.0, 2.4), Color.BLUE, Some(Color.CYAN))
      with RedBackgroundedDrawable
      with LightGrayBackgroundedDrawable
      with BorderedDrawable

    ell.draw(canvasMock)
  }
}
