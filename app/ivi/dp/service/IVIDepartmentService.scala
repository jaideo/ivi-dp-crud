package ivi.dp.service

import ivi.dp.models.Department
import ivi.dp.service.impl.IVIDepartmentImpl
import javax.inject.Inject

import scala.concurrent.Future

class IVIDepartmentService @Inject()(dept:IVIDepartmentImpl){

  def add(employee:Department):Future[String] ={
    dept.save(employee)
  }

  def update(employee: Department)={
    dept.update(employee)
  }

  def getAllDepartments: Future[Seq[Department]] = {
    dept.getAll
  }

  def getById(id:Long):Future[Option[Department]] = {
    dept.getById(id)
  }

  def delete(id:Long):Future[Int] ={
    dept.delete(id)
  }
}
