package ivi.dp.service.impl

import ivi.dp.models.Department
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.{TableQuery, Tag}

import scala.concurrent.{ExecutionContext, Future}

class DepartmentTableDef(tag: Tag) extends Table[Department](tag, "department") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def location = column[String]("location")

  override def * = (id, name,location) <> (Department.tupled, Department.unapply)
}

class IVIDepartmentImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider
                          )(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

      val deptList = TableQuery[DepartmentTableDef]

      def save(dept: Department) :Future[String]={
        dbConfig.db.run(
          deptList+=dept
        ).map(response => s"Department added successfully. ")
          .recover {
            case ex: Exception => {
              println(ex.getMessage())
              ex.getMessage
            }
          }
      }

      def update(dept: Department)={
        dbConfig.db.run(
          deptList.filter(_.id === dept.id)
            .map(x => (x.name, x.location))
            .update(dept.name, dept.location))
      }

      def getAll: Future[Seq[Department]] = {
          dbConfig.db.run(deptList.result)
      }

      def getById(id:Long):Future[Option[Department]]={
        dbConfig.db.run(deptList.filter(_.id === id).result.headOption)
      }

      def delete(id:Long) :Future[Int]={
        dbConfig.db.run(deptList.filter(_.id === id).delete)
      }

  }
