package shapes

trait ThreeDDrawable extends DrawableDecorator {

  override def draw(canvas: FigureCanvas): Unit = {
    println("Made it 3D!")
    super.draw(canvas)
  }

}
