package shapes

import java.awt.Color

class TextFigure(val text: String, val origin: (Double, Double), val strokeColor: Color) extends Drawable {
  override def draw(canvas: FigureCanvas): Unit = {}
}
