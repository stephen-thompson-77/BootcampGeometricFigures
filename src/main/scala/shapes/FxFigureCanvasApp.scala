package moodsdesign.cw.provd

import java.awt.Color

import shapes._

import scalafx.application.JFXApp

object FxFigureCanvasApp extends JFXApp {

  val canvas = new FxFigureCanvas(FxFigureCanvasApp)

  val ell: Ellipse = new Ellipse(103.4, 145.4, (0d,0d), Color.BLUE, Some(Color.CYAN))
    with RedBackgroundedDrawable
    with LightGrayBackgroundedDrawable
    with BorderedDrawable

  ell.draw(canvas)
}

