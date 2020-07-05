package ivi.dp.service

import ivi.dp.models.{Employee, EmployeeWithDepartment,  EmployeeWithSalary}
import ivi.dp.service.impl.IVIEmployeeImpl
import javax.inject.Inject

import scala.concurrent.Future

class IVIEmployeeService @Inject()(emp:IVIEmployeeImpl){

  def add(employee:Employee):Future[String] ={
    emp.save(employee)
  }

  def update(employee: Employee)={
    emp.update(employee)
  }

  def getAllEmployees: Future[Seq[Employee]] = {
    emp.getAll
  }

  def getById(id:Long):Future[Option[Employee]] = {
    emp.getById(id)
  }

  def delete(id:Long):Future[Int] ={
    emp.delete(id)
  }

  def employeesWithDepartment : Future[Seq[EmployeeWithDepartment]] = {
    emp.getAllEmployeesWithDepartment
  }

  def getAllEmployeesWithDepartmentWithSalary : Future[Seq[EmployeeWithSalary]] = {
    emp.getAllEmployeesWithSalary
  }
}
