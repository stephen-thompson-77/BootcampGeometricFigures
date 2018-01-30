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

  def scaleAll(myFraction: MyFraction, minArea: Option[Double]) = {
    list = list map { gf: GeometricFigure => minArea match {
      case None => gf.resize(myFraction)
      case Some(min) if gf.area() > min => gf.resize(myFraction)
      case _ => gf
    }}
  }

  def drawAll(minArea: Option[Double]): Unit = {
    list withFilter { gf => minArea match {
      case None => true
      case Some(area) => gf.area() > area
    }} foreach { gf =>
      gf.draw(canvas)
    }
  }

  def totalArea(): Double = {
    list.foldLeft(0d)((acc, shape) => acc + shape.area())
  }

  def totalPerimeter(): Double = {
    list.foldLeft(0d)((acc, shape) => acc + shape.perimeter())
  }

}
