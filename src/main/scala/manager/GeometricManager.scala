package manager

import bootcamp_fraction.MyFraction
import shapes.{FigureCanvas, GeometricFigure}

class GeometricManager(canvas: FigureCanvas) {

  private[manager] var list: List[GeometricFigure]= Nil

  def add(geometricFigure: GeometricFigure) = {
    list =  geometricFigure :: list
  }

  def remove(geometricFigure: GeometricFigure) = {
    val indexToRemove: Option[Int] = list.zipWithIndex.find {
      case (gs, _) => gs.equals(geometricFigure)
    } map {
      case (_, i) => i
    }

    indexToRemove foreach { i =>
      list = i match {
        case 0 => list.tail
        case _ => list.splitAt(i-1) match {
          case (left, right) => left ++ right.tail
        }
      }
    }
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
