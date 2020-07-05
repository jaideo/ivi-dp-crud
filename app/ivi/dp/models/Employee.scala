package ivi.dp.models

case class Employee(id:Long,name:String,title:String,employeeId:String,department:Long)

case class EmployeeFormData(name: String, title:String,employeeId:String,department:Long)


case class EmployeeWithDepartment(employeeId:String,name:String,title:String,department:String)

case class EmployeeWithSalary(employeeId:String,name:String,title:String,salary:Int)