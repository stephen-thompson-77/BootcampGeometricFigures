package shapes

trait Drawable {

  val origin: (Double, Double)
  def draw(canvas: FigureCanvas): Unit = {}

}

trait DrawableDecorator extends Drawable {
  override def draw(canvas: FigureCanvas): Unit = super.draw(canvas)
  def bounds(): ((Double, Double), Double, Double)

}