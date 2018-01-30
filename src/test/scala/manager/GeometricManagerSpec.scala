package manager

import java.awt.Color

import bootcamp_fraction.MyFraction
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import shapes._

class GeometricManagerSpec extends FlatSpec with MockFactory {

  private val baseCanvasMock = mock[FigureCanvas]

  "add" should "add a shape to a fresh geometry manager" in {
    val ell = Ellipse(45.7, 34.9, (25,25))
    val gm = new GeometricManager(baseCanvasMock)
    gm.add(ell)
    assertResult(ell) {
      gm.list.head
    }
  }

  it should "add a shape to an existing geometry manager, at the beginning of the list" in {
    val ell = Ellipse(45.7, 34.9, (25,25))
    val gm = new GeometricManager(baseCanvasMock)
    gm.add(Circle(4.5, (32,32)))
    gm.add(Square(4.5, (32,32)))


    gm.add(ell)
    assert(gm.list.size == 3)
    assertResult(ell) {
      gm.list.head
    }
  }

  "remove" should "remove a shape (based on size and position only)" in {
    val gm = new GeometricManager(baseCanvasMock)

    val toRemove = Square(4.5, (32,32), Color.CYAN)
    gm.add(Circle(4.5, (32,32)))
    gm.add(toRemove)

    gm.remove(Square(4.5, (32,32), Color.BLACK))
    assert(gm.list.size == 1)
    assert(!gm.list.contains(toRemove))
  }

  it should "only removes the first matching shape" in {
    val gm = new GeometricManager(baseCanvasMock)

    val toRemove = Square(4.5, (32,32), Color.CYAN)
    gm.add(Circle(4.5, (32,32)))
    gm.add(toRemove)
    gm.add(Square(4.5, (32,32), Color.RED))

    gm.remove(Square(4.5, (32,32), Color.BLACK))
    assert(gm.list.size == 2)
    assertResult(Color.CYAN){
      gm.list.find(_.equals(toRemove)) match {
        case None => fail("remove removed all matches")
        case Some(sq: Square) => sq.strokeColor
        case _ => fail("error: square equal to non square")
      }
    }
  }

  "clear" should "remove allo shapes from manager" in {
    val gm = new GeometricManager(baseCanvasMock)
    gm.add(Circle(4.5, (32,32)))
    gm.add(Square(4.5, (32,32)))

    gm.clear()
    assertResult(Nil)(gm.list)
  }

  "scaleAll" should "(without optional area) resize all managed shapes" in {
    val gm = new GeometricManager(baseCanvasMock)
    gm.add(Rectangle(60, 52, (34.5, 12.4)))
    gm.add(Ellipse(60, 52, (34.5, 12.4)))
    gm.add(Circle(60, (34.5, 12.4)))

    val frac = MyFraction(2, 8)

    gm.scaleAll(frac, None)
    assertResult(
      List(Circle(15, (34.5, 12.4)), Ellipse(15, 13, (34.5, 12.4)), Rectangle(15, 13, (34.5, 12.4)))
    )(gm.list)
  }

  it should "only resize those larger than minArea if present" in {
    val gm = new GeometricManager(baseCanvasMock)
    gm.add(Rectangle(60, 52, (34.5, 12.4)))
    gm.add(Ellipse(30, 26, (34.5, 12.4)))
    gm.add(Circle(60, (34.5, 12.4)))

    val frac = MyFraction(3, 6)

    gm.scaleAll(frac, Some(3000))
    assertResult(
      List(Circle(30, (34.5, 12.4)), Ellipse(30, 26, (34.5, 12.4)), Rectangle(30, 26, (34.5, 12.4)))
    )(gm.list)
  }

  "drawAll" should "(without optional area) draw all managed shapes" in {
    val canvasMock = mock[FigureCanvas]
    val gm = new GeometricManager(canvasMock)
    gm.add(Rectangle(60, 52, (34.5, 12.4), Color.BLUE))
    gm.add(Ellipse(30, 26, (34.5, 12.4), Color.RED, Color.CYAN))
    gm.add(Circle(60, (34.5, 12.4), Color.GRAY))

    inSequence {
      //Circle
      (canvasMock.setDrawingColor _).expects(Color.GRAY)
      (canvasMock.outlineEllipse _).expects(34.5, 12.4, 60, 60)

      //Ellipse
      (canvasMock.setDrawingColor _).expects(Color.CYAN)
      (canvasMock.fillEllipse _).expects(34.5, 12.4, 30, 26)
      (canvasMock.setDrawingColor _).expects(Color.RED)
      (canvasMock.outlineEllipse _).expects(34.5, 12.4, 30, 26)

      //Rectangle
      (canvasMock.setDrawingColor _).expects(Color.BLUE)
      (canvasMock.outlineRectangle _).expects(34.5, 12.4, 60, 52)
    }
    gm.drawAll(None)
  }

  it should "only draw those larger than minArea if present" in {
    val canvasMock = mock[FigureCanvas]
    val gm = new GeometricManager(canvasMock)
    gm.add(Rectangle(60, 52, (34.5, 12.4), Color.BLUE))
    gm.add(Ellipse(30, 26, (34.5, 12.4), Color.RED, Color.CYAN))
    gm.add(Circle(60, (34.5, 12.4), Color.GRAY))

    inSequence {
      //Circle
      (canvasMock.setDrawingColor _).expects(Color.GRAY)
      (canvasMock.outlineEllipse _).expects(34.5, 12.4, 60, 60)

      //Rectangle
      (canvasMock.setDrawingColor _).expects(Color.BLUE)
      (canvasMock.outlineRectangle _).expects(34.5, 12.4, 60, 52)
    }
    gm.drawAll(Some(3000))
  }

  "totalArea" should "sum all areas of shapes managed" in {
    val gm = new GeometricManager(baseCanvasMock)
    val circ = Circle(4.5, (32,32))
    val sq = Square(4.5, (32,32))
    val rec = Rectangle(3.4, 5.6, (23, 56))
    gm.add(circ)
    gm.add(sq)
    gm.add(rec)

    assertResult(circ.area + sq.area + rec.area)(gm.totalArea())
  }

  "totalPerimeter" should "sum all perimeters of shapes managed" in {
    val gm = new GeometricManager(baseCanvasMock)
    val circ = Circle(4.5, (32,32))
    val sq = Square(4.5, (32,32))
    val rec = Rectangle(3.4, 5.6, (23, 56))
    gm.add(circ)
    gm.add(sq)
    gm.add(rec)

    assertResult(circ.perimeter + sq.perimeter + rec.perimeter)(gm.totalPerimeter())
  }

}
