package shapes

import java.awt.Color

abstract class GeometricFigure(val origin : (Double, Double) = (1.0, 1.0),
                               val strokeColor: Color = Color.BLACK,
                               val fillColor: Color = Color.BLACK,
                               val filled: Boolean = true) {
  def perimeter(): Double
  def area(): Double
}

class Ellipse(val hRadius: Double, val vRadius: Double,
              origin: (Double, Double),
              strokeColor: Color, fillColor: Color,
              filled: Boolean) extends GeometricFigure(origin, strokeColor, fillColor, filled) {
  if(hRadius < 0 || vRadius < 0){
    throw new IllegalArgumentException()
  }

  override val perimeter: Double = (2 * Math.PI) * Math.sqrt((hRadius * hRadius + vRadius * vRadius) / 2)
  override val area: Double = Math.PI * (hRadius * vRadius)
}

class Circle(val radius: Double,
             origin: (Double, Double),
             strokeColor: Color, fillColor: Color,
             filled: Boolean) extends Ellipse(radius, radius, origin, strokeColor, fillColor, filled) {
}

class Rectangle(val width: Double, val height: Double,
                origin: (Double, Double),
                strokeColor: Color, fillColor: Color,
                filled: Boolean) extends GeometricFigure(origin, strokeColor, fillColor, filled){
  if(width < 0 || height < 0){
    throw new IllegalArgumentException()
  }

  override val perimeter: Double = (width + height) * 2
  override val area: Double = width * height
}

class Square(val length: Double, origin: (Double, Double),
             strokeColor: Color, fillColor: Color,
             filled: Boolean) extends Rectangle(length, length, origin, strokeColor, fillColor, filled) {
}
