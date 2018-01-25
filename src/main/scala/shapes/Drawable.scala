package shapes

trait Drawable {

  val origin: (Double, Double)
  def draw(canvas: FigureCanvas): Unit

}
