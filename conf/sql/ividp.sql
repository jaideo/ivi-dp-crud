create database ividp;

use ividp;


# --- !department

CREATE TABLE IF NOT EXISTS `ividp`.`department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `location` varchar(45) NOT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8


 
# --- !employee
CREATE TABLE IF NOT EXISTS `ividp`.`employee` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `job_title` VARCHAR(45) NOT NULL DEFAULT 'emp',
  `employee_id` varchar(45) NOT NULL,
  `department_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_department
    FOREIGN KEY (department_id) 
        REFERENCES department(id))
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8


# --- !salary

CREATE TABLE IF NOT EXISTS `ividp`.`salary` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `employee_id` INT(11) NOT NULL,
  `salary` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_employee
    FOREIGN KEY (employee_id) 
        REFERENCES employee(id))
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8



insert into department (id, `name`, location)
values(10, 'ACCOUNTING', 'NEW YORK');


insert into department  
values(20, 'RESEARCH', 'DALLAS');

insert into department  
values(30, 'SALES', 'CHICAGO');


insert into department  
values(40, 'OPERATIONS', 'BOSTON');



insert into employee  
values( null, 
 'KING', 'PRESIDENT', 001, 
 10
);


insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,'BLAKE', 'MANAGER', 7839,  
 20
);

insert into employee(id,`name`,job_title,employee_id,department_id)
values(null,  'CLARK', 'MANAGER', 7839, 
 30 
);

insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,  'JONES', 'MANAGER', 7839,    
 40  
);
insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,  
 'SCOTT', 'ANALYST', 7566,  
  10
);

insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,  
 'FORD', 'ANALYST', 7566,  
 20
);
insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,  
 'SMITH', 'CLERK', 7902, 
 30
);
insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,  'ALLEN', 'SALESMAN', 7698,  
 40
);

insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,  'WARD', 'SALESMAN', 7698, 
 10  
);

insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,   'MARTIN', 'SALESMAN', 7698,   
 10
);
insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,   'TURNER', 'SALESMAN', 7698,
 10 
);
insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null, 'ADAMS', 'CLERK', 7788, 
 20
);
insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null, 'JAMES', 'CLERK', 7698,  
 30  
);
insert into employee(id,`name`,job_title,employee_id,department_id)  
values(null,  'MILLER', 'CLERK', 7782,   
 40
);



insert into salary (id, employee_id, salary)
values(null,3 , 300000);


insert into salary (id, employee_id, salary)
values(null,4, 300000);

insert into salary (id, employee_id, salary)
values(null, 5, 300000);

insert into salary (id, employee_id, salary)
values(null, 6, 300000);

insert into salary (id, employee_id, salary)
values(null, 7, 300000);

insert into salary (id, employee_id, salary)
values(null, 8, 300000);

insert into salary (id, employee_id, salary)
values(null, 9, 300000);

insert into salary (id, employee_id, salary)
values(null,10 , 300000);
 
 
 



