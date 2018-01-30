package shapes

import java.awt.Color

trait BorderedDrawable extends DrawableDecorator {

  override def draw(canvas: FigureCanvas): Unit = {
    val bds = bounds()
    canvas.setDrawingColor(Color.BLACK)
    canvas.outlineRectangle(bds._1._1, bds._1._2, bds._2, bds._3)
    super.draw(canvas)
  }

}
