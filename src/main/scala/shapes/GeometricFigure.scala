package shapes

abstract class GeometricFigure {
  def perimeter(): Double
  def area(): Double
}

class Ellipse(val hRadius: Double, val vRadius: Double) extends GeometricFigure {
  override def perimeter(): Double = ???
  override def area(): Double = ???
}

class Circle(val radius: Double) extends Ellipse(radius, radius) {



}

class Rectangle(val width: Double, val height: Double) extends GeometricFigure{
  override def perimeter(): Double = ???

  override def area(): Double = ???
}

class Square(val length: Double) extends Rectangle(length, length) {

}
