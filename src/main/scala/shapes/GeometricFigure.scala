package shapes

import java.awt.Color

abstract class GeometricFigure(val origin : (Double, Double),
                               val strokeColor: Color,
                               val fillColor: Color,
                               val filled: Boolean) extends Drawable {
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
    canvas.outlineEllipse(origin._1, origin._2, hRadius, vRadius)
  }

  override protected def drawFill(canvas: FigureCanvas): Unit = {
    canvas.fillEllipse(origin._1, origin._2, hRadius, vRadius)
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

object Ellipse {
  def apply(hRadius: Double, vRadius: Double, origin: (Double, Double)): Ellipse = {
    if (hRadius == vRadius) new Circle(hRadius, origin)
    else new Ellipse(hRadius, vRadius, origin)
  }

  def apply(hRadius: Double, vRadius: Double, origin: (Double, Double), strokeColour: Color): Ellipse = {
    if (hRadius == vRadius) new Circle(hRadius, origin, strokeColour, filled = false)
    else new Ellipse(hRadius, vRadius, origin, strokeColour, filled = false)
  }

  def apply(hRadius: Double, vRadius: Double, origin: (Double, Double), strokeColour: Color, fillColor: Color): Ellipse = {
    if (hRadius == vRadius) new Circle(hRadius, origin, strokeColour, fillColor, filled = true)
    else new Ellipse(hRadius, vRadius, origin, strokeColour, fillColor, filled = true)
  }
}

class Circle(val radius: Double,
             origin: (Double, Double) = (1.0, 1.0),
             strokeColor: Color = Color.BLACK, fillColor: Color = Color.BLUE,
             filled: Boolean = false) extends Ellipse(radius, radius, origin, strokeColor, fillColor, filled) {
}

object Circle {
  def apply(radius: Double, origin: (Double, Double)): Circle = new Circle(radius, origin)

  def apply(radius: Double, origin: (Double, Double), strokeColour: Color): Circle =
    new Circle(radius, origin, strokeColour, filled = false)

  def apply(radius: Double, origin: (Double, Double), strokeColour: Color, fillColor: Color): Circle =
    new Circle(radius, origin, strokeColour, fillColor, filled = true)
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

object Rectangle {
  def apply(width: Double, height: Double, origin: (Double, Double)): Rectangle = {
    if (width == height) new Square(width, origin)
    else new Rectangle(width, height, origin)
  }

  def apply(width: Double, height: Double, origin: (Double, Double), strokeColour: Color): Rectangle = {
    if (width == height) new Square(width, origin, strokeColour, filled = false)
    else new Rectangle(width, height, origin, strokeColour, filled = false)
  }

  def apply(width: Double, height: Double, origin: (Double, Double), strokeColour: Color, fillColor: Color): Rectangle = {
    if (width == height) new Square(width, origin, strokeColour, fillColor, filled = true)
    else new Rectangle(width, height, origin, strokeColour, fillColor, filled = true)
  }
}

class Square(val length: Double, origin: (Double, Double) = (1.0, 1.0),
             strokeColor: Color = Color.BLACK, fillColor: Color = Color.BLUE,
             filled: Boolean = false) extends Rectangle(length, length, origin, strokeColor, fillColor, filled) {
}

object Square {
  def apply(length: Double, origin: (Double, Double)): Square = new Square(length, origin)

  def apply(length: Double, origin: (Double, Double), strokeColour: Color): Square =
    new Square(length, origin, strokeColour, filled = false)

  def apply(length: Double, origin: (Double, Double), strokeColour: Color, fillColor: Color): Square =
    new Square(length, origin, strokeColour, fillColor, filled = true)
}
