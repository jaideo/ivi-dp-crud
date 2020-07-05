package ivi.dp.service.impl

import ivi.dp.models.{Employee, EmployeeWithDepartment,  EmployeeWithSalary}
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.{TableQuery, Tag}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class EmployeeTableDef(tag: Tag) extends Table[Employee](tag, "employee") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def employeeId = column[String]("employee_id")
  def title = column[String]("job_title")
  def department = column[Long]("department_id")

  override def * = (id, name,title, employeeId,department) <> (Employee.tupled, Employee.unapply)
}


class IVIEmployeeImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,dept: IVIDepartmentImpl,salary: IVISalaryImpl
                          )(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  val employeeList = TableQuery[EmployeeTableDef]

  def save(employee: Employee): Future[String] = {
    dbConfig.db.run(
      employeeList += employee
    ).map(response => s"Employee successfully added")
      .recover {
        case ex: Exception => {
          println(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def update(employee: Employee) = {
    dbConfig.db.run(
      employeeList.filter(_.id === employee.id)
        .map(x => (x.name, x.employeeId, x.department))
        .update(employee.name, employee.employeeId, employee.department))
  }

  def getAll: Future[Seq[Employee]] = {
    dbConfig.db.run(employeeList.result)
  }

  def getById(id: Long): Future[Option[Employee]] = {
    dbConfig.db.run(employeeList.filter(_.id === id).result.headOption)
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(employeeList.filter(_.id === id).delete)
  }

  def getAllEmployeesWithDepartment: Future[Seq[EmployeeWithDepartment]] = {
    val query = for {
      (e, d) <- employeeList join dept.deptList on (_.department === _.id)
    } yield (e.employeeId, e.name, e.title, d.name)
    db.run(query.result).map(_.map(EmployeeWithDepartment.tupled))
  }

  def getAllEmployeesWithSalary: Future[Seq[EmployeeWithSalary]] = {
    val query = for {
      (e, s) <- employeeList join salary.salaryList on (_.id === _.employeeId)
    } yield (e.employeeId, e.name, e.title, s.salary)

    db.run(query.result).map(_.map(EmployeeWithSalary.tupled))
  }
}