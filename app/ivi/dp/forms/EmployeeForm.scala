package ivi.dp.forms

import ivi.dp.models.EmployeeFormData
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._

object EmployeeForm {
    val form = Form(
      mapping(
        "name" -> nonEmptyText,
        "title" -> nonEmptyText,
        "employeeId" -> nonEmptyText,
        "department" -> longNumber
      )(EmployeeFormData.apply)(EmployeeFormData.unapply)
    )
}
