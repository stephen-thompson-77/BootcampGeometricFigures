package manager

import bootcamp_fraction.MyFraction
import shapes.{FigureCanvas, GeometricFigure}

class GeometricManager(canvas: FigureCanvas) {

  private[GeometricManagerSpec] var list: List[GeometricFigure]= Nil

  def add(geometricFigure: GeometricFigure) = {
    list =  geometricFigure :: list
  }

  def remove(geometricFigure: GeometricFigure) = {
    list = list.filter(_.equals(geometricFigure))
  }

  def clear() = {
    list = Nil
  }

  def scaleAll(myFraction: MyFraction, maxArea: Option[Double]) = ???

  def drawAll(maxArea: Option[Double]) = ???

  def totalArea(): Double = {
    list.foldLeft(0d)((acc, shape) => acc + shape.area())
  }

  def totalPerimeter(): Double = {
    list.foldLeft(0d)((acc, shape) => acc + shape.perimeter())
  }

}
