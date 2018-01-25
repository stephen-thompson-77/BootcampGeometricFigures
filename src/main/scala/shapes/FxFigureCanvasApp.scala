package moodsdesign.cw.provd

import java.awt.Color

import shapes._

import scalafx.application.JFXApp

object FxFigureCanvasApp extends JFXApp {

  val canvas = new FxFigureCanvas(FxFigureCanvasApp)
  Ellipse(103.4, 145.4, (270d,200d), Color.BLUE, Color.GRAY).draw(canvas)
  Ellipse(103.4, 145.4, (540d,200d), Color.ORANGE, Color.GRAY).draw(canvas)
  Circle(60.4, (405d,450d), Color.BLACK, Color.GRAY).draw(canvas)

}

