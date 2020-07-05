package controllers

import ivi.dp.forms.DepartmentForm
import ivi.dp.models.Department
import ivi.dp.service.IVIDepartmentService
import javax.inject._
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class IVIDepartmentController @Inject()(cc: ControllerComponents, deptServise:IVIDepartmentService) extends AbstractController(cc)  {

  implicit val todoFormat = Json.format[Department]
  private val logger = Logger(getClass)

  def createDepartment :Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    DepartmentForm.form.bindFromRequest.fold(
      // validate
      error => {
        error.errors.foreach(println)
        Future.successful(BadRequest("Form add Department validations Error!"))
      },
      value => {
        val newDept = Department(0, value.name,value.location)
        deptServise.add(newDept).map( _ => Redirect(routes.IVIDepartmentController.getAll))
      })
  }

  def update(id:Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    DepartmentForm.form.bindFromRequest.fold(
      // validate
      error => {
        error.errors.foreach(println)
        Future.successful(BadRequest("Form update Department validations Error!"))
      },
      value => {
        val todoItem = Department(id, value.name,value.location)
        deptServise.update(todoItem).map( _ => Redirect(routes.IVIDepartmentController.getAll))
      })
  }


  def getAll:Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    deptServise.getAllDepartments.map { depts =>
        Ok(Json.toJson(depts))
      }
  }

  def getById(id: Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    deptServise.getById(id) map { dept =>
      Ok(Json.toJson(dept))
    }
  }

  def delete(id: Long):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    deptServise.delete(id) map { dept =>
      Redirect(routes.IVIDepartmentController.getAll)
    }
  }

}
