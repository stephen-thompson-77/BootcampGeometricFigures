package manager

import bootcamp_fraction.MyFraction
import shapes.{FigureCanvas, GeometricFigure}

class GeometricManager(canvas: FigureCanvas) {

  private[GeometricManagerSpec] var list: List[GeometricFigure]= Nil

  def add(geometricFigure: GeometricFigure) = ???

  def remove(geometricFigure: GeometricFigure) = ???

  def clear() = ???

  def scaleAll(myFraction: MyFraction, maxArea: Option[Double]) = ???

  def drawAll(maxArea: Option[Double]) = ???

  def totalArea(): Double = ???

  def totalPerimeter(): Double = ???

}
