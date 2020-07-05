package ivi.dp.forms

import ivi.dp.models.{DepartmentFormData, EmployeeFormData}
import play.api.data.Form
import play.api.data.Forms.{mapping, _}

object DepartmentForm {
    val form = Form(
      mapping(
        "name" -> nonEmptyText,
        "location" -> nonEmptyText
      )(DepartmentFormData.apply)(DepartmentFormData.unapply)
    )
}
