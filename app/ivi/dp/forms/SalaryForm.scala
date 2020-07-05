package ivi.dp.forms

import ivi.dp.models.SalaryFormData
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._

object SalaryForm {
  val form = Form(
    mapping(
      "employeeId" -> longNumber,
      "salary" -> number
    )(SalaryFormData.apply)(SalaryFormData.unapply)
  )
}
