package ivi.dp.service.impl

import ivi.dp.models.{ Salary}
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class SalaryTableDef(tag: Tag) extends Table[Salary](tag, "salary") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def employeeId = column[Long]("employee_id")
  def salary = column[Int]("salary")

  override def * = (id, employeeId,salary) <> (Salary.tupled, Salary.unapply)
}
class IVISalaryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider
                            )(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  val salaryList = TableQuery[SalaryTableDef]


  def save(salary: Salary) :Future[String]={
    dbConfig.db.run(
      salaryList+=salary
    ).map(response => s"Salary successfully added")
      .recover {
        case ex: Exception => {
          println(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def update(salary: Salary)={
    dbConfig.db.run(
      salaryList.filter(_.id === salary.id)
        .map(x => ( x.employeeId,x.salary))
        .update(salary.employeeId,salary.salary))
  }

  def getAll: Future[Seq[Salary]] = {
    dbConfig.db.run(salaryList.result)
  }

  def getById(id:Long):Future[Option[Salary]]={
    dbConfig.db.run(salaryList.filter(_.id === id).result.headOption)
  }

  def delete(id:Long) :Future[Int]={
    dbConfig.db.run(salaryList.filter(_.id === id).delete)
  }

}
