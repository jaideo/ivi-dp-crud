package ivi.dp.service

import ivi.dp.models.Salary
import ivi.dp.service.impl.IVISalaryImpl
import javax.inject.Inject

import scala.concurrent.Future

class IVISalaryService @Inject()(salaryImpl:IVISalaryImpl){

  def add(salary:Salary):Future[String] ={
    salaryImpl.save(salary)
  }

  def update(salary: Salary)={
    salaryImpl.update(salary)
  }

  def getAllSalaries: Future[Seq[Salary]] = {
    salaryImpl.getAll
  }

  def getById(id:Long):Future[Option[Salary]] = {
    salaryImpl.getById(id)
  }

  def delete(id:Long):Future[Int] ={
    salaryImpl.delete(id)
  }
}
