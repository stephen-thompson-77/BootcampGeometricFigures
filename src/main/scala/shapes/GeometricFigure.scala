package shapes

import java.awt.Color

abstract class GeometricFigure(val origin : (Double, Double),
                               val strokeColor: Color,
                               val fillColor: Color,
                               val filled: Boolean) {
  if (origin._1 < 0.0 || origin._2 < 0.0) throw new IllegalArgumentException

  def perimeter(): Double
  def area(): Double

  def draw(canvas: FigureCanvas): Unit = {
    if (filled) {
      canvas.setDrawingColor(fillColor)
      drawFill(canvas)
    }
    canvas.setDrawingColor(strokeColor)
    drawOutline(canvas)
  }

  protected def drawOutline(canvas: FigureCanvas): Unit
  protected def drawFill(canvas: FigureCanvas): Unit


  def canEqual(other: Any): Boolean = other.isInstanceOf[GeometricFigure]

  override def equals(other: Any): Boolean = other match {
    case that: GeometricFigure =>
      (that canEqual this) &&
        origin == that.origin
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(origin)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

class Ellipse(val hRadius: Double, val vRadius: Double,
              origin: (Double, Double) = (1.0, 1.0),
              strokeColor: Color = Color.BLACK, fillColor: Color = Color.BLUE,
              filled: Boolean = false) extends GeometricFigure(origin, strokeColor, fillColor, filled) {
  if(hRadius < 0 || vRadius < 0){
    throw new IllegalArgumentException()
  }

  override val perimeter: Double = (2 * Math.PI) * Math.sqrt((hRadius * hRadius + vRadius * vRadius) / 2)
  override val area: Double = Math.PI * (hRadius * vRadius)

  override protected def drawOutline(canvas: FigureCanvas): Unit = {
    canvas.outlineEclipse(origin._1, origin._2, hRadius, vRadius)
  }

  override protected def drawFill(canvas: FigureCanvas): Unit = {
    canvas.fillEclipse(origin._1, origin._2, hRadius, vRadius)
  }


  override def canEqual(other: Any): Boolean = other.isInstanceOf[Ellipse]

  override def equals(other: Any): Boolean = other match {
    case that: Ellipse =>
      super.equals(that) &&
        (that canEqual this) &&
        hRadius == that.hRadius &&
        vRadius == that.vRadius
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(super.hashCode(), hRadius, vRadius)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

class Circle(val radius: Double,
             origin: (Double, Double) = (1.0, 1.0),
             strokeColor: Color = Color.BLACK, fillColor: Color = Color.BLUE,
             filled: Boolean = false) extends Ellipse(radius, radius, origin, strokeColor, fillColor, filled) {
}

class Rectangle(val width: Double, val height: Double,
                origin: (Double, Double) = (1.0, 1.0),
                strokeColor: Color = Color.BLACK, fillColor: Color = Color.BLUE,
                filled: Boolean = false) extends GeometricFigure(origin, strokeColor, fillColor, filled){
  if(width < 0 || height < 0){
    throw new IllegalArgumentException()
  }

  override val perimeter: Double = (width + height) * 2
  override val area: Double = width * height

  override protected def drawOutline(canvas: FigureCanvas): Unit = {
    canvas.outlineRectangle(origin._1, origin._2, width, height)
  }

  override protected def drawFill(canvas: FigureCanvas): Unit = {
    canvas.fillRectangle(origin._1, origin._2, width, height)
  }

  override def canEqual(other: Any): Boolean = other.isInstanceOf[Rectangle]

  override def equals(other: Any): Boolean = other match {
    case that: Rectangle =>
      super.equals(that) &&
        (that canEqual this) &&
        width == that.width &&
        height == that.height
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(super.hashCode(), width, height)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

class Square(val length: Double, origin: (Double, Double) = (1.0, 1.0),
             strokeColor: Color = Color.BLACK, fillColor: Color = Color.BLUE,
             filled: Boolean = false) extends Rectangle(length, length, origin, strokeColor, fillColor, filled) {
}
