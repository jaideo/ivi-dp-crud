package controllers

import ivi.dp.models.{Employee, EmployeeWithDepartment, EmployeeWithSalary}
import ivi.dp.forms.EmployeeForm
import ivi.dp.service.IVIEmployeeService
import javax.inject._
import play.api.Logger
import play.api.mvc._
import play.api.libs.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class IVIEmployeeController @Inject()(cc: ControllerComponents, empServise:IVIEmployeeService) extends AbstractController(cc)  {

  implicit val todoFormat = Json.format[Employee]
  implicit val format = Json.format[EmployeeWithDepartment]
  implicit val format1 = Json.format[EmployeeWithSalary]

  private val logger = Logger(getClass)


  def createEmployee :Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    EmployeeForm.form.bindFromRequest.fold(
      // validate
      error => {
        error.errors.foreach(println)
        Future.successful(BadRequest("Form add Employee validations Error!"))
      },
      value => {
        val newEmployee = Employee(0, value.name,value.title, value.employeeId,value.department)
        empServise.add(newEmployee).map( _ => Redirect(routes.IVIEmployeeController.getAll))
      })
  }

  def update(id:Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    EmployeeForm.form.bindFromRequest.fold(
      // validate
      error => {
        error.errors.foreach(println)
        Future.successful(BadRequest("Form update Employee validations Error!"))
      },
      value => {
        val todoItem = Employee(id, value.name,value.title, value.employeeId,value.department)
        empServise.update(todoItem).map( _ => Redirect(routes.IVIEmployeeController.getAll))
      })
  }


  def getAll:Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
      empServise.getAllEmployees.map { items =>
        Ok(Json.toJson(items))
      }
  }

  def getById(id: Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    empServise.getById(id) map { emp =>
      Ok(Json.toJson(emp))
    }
  }

  def delete(id: Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    empServise.delete(id) map { emp =>
      Redirect(routes.IVIEmployeeController.getAll)
    }
  }

  def employeesWithDepartment:Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    empServise.employeesWithDepartment.map { empwithDepartment =>
      Ok(Json.toJson(empwithDepartment))
    }
  }

  def employeesWithSalary:Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    empServise.getAllEmployeesWithDepartmentWithSalary.map { employeeWithSalary =>
      Ok(Json.toJson(employeeWithSalary))
    }
  }

}
