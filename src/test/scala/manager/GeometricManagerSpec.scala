package manager

import java.awt.Color

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
