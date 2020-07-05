package controllers

import ivi.dp.forms.{ SalaryForm}
import ivi.dp.models.{ Salary}
import ivi.dp.service.IVISalaryService
import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class IVISalaryController  @Inject()(cc: ControllerComponents, salaryService:IVISalaryService) extends AbstractController(cc)  {

  implicit val todoFormat = Json.format[Salary]

  private val logger = Logger(getClass)

  def createSalary :Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    SalaryForm.form.bindFromRequest.fold(
      // validate
      error => {
        error.errors.foreach(println)
        Future.successful(BadRequest("Form add Salary validations Error!"))
      },
      value => {
        val newEmployee = Salary(0, value.employeeId,value.salary)
        salaryService.add(newEmployee).map( _ => Redirect(routes.IVIEmployeeController.getAll))
      })
  }

  def update(id:Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    SalaryForm.form.bindFromRequest.fold(
      // validate
      error => {
        error.errors.foreach(println)
        Future.successful(BadRequest("Form update Salary validations Error!"))
      },
      value => {
        val todoItem = Salary(id, value.employeeId,value.salary)
        salaryService.update(todoItem).map( _ => Redirect(routes.IVIEmployeeController.getAll))
      })
  }


  def getAll:Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    salaryService.getAllSalaries.map { salaries =>
      Ok(Json.toJson(salaries))
    }
  }

  def getById(id: Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    salaryService.getById(id) map { sal =>
      Ok(Json.toJson(sal))
    }
  }

  def delete(id: Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    salaryService.delete(id) map { sal =>
      Redirect(routes.IVIEmployeeController.getAll)
    }
  }
}
