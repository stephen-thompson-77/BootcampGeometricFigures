package shapes

import java.awt.Color

trait RedBackgroundedDrawable extends DrawableDecorator {

  override def draw(canvas: FigureCanvas): Unit = {
    val bds = bounds()
    canvas.setDrawingColor(Color.RED)
    canvas.fillRectangle(bds._1._1, bds._1._2, bds._2, bds._3)
    super.draw(canvas)
  }

}
