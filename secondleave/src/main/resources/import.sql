

INSERT INTO `secondleave`.`role` (`role_id`,`description`, `name`) VALUES (1,'Admin', 'Administrator');
INSERT INTO `secondleave`.`role` (`role_id`,`description`, `name`) VALUES (2,'Manage', 'Manager');
INSERT INTO `secondleave`.`role` (`role_id`,`description`, `name`) VALUES (3,'Work', 'Employee');


INSERT INTO `employee` (`employee_id`,`name`, `username`, `password`) VALUES (1,'alex', 'alex', 'alex');
INSERT INTO `employee` (`employee_id`,`name`, `username`, `password`, `manager_id`) VALUES (2,'bill', 'bill', 'bill',1);
INSERT INTO `employee` (`employee_id`,`name`, `username`, `password`, `manager_id`) VALUES (3,'cat', 'cat', 'cat',1);
INSERT INTO `employee` (`employee_id`,`name`, `username`, `password`) VALUES (4,'david', 'david', 'david');

INSERT INTO `emp_role` (`employee_id`,`role_id`) VALUES (1, 1);
INSERT INTO `emp_role` (`employee_id`,`role_id`) VALUES (1, 2);
INSERT INTO `emp_role` (`employee_id`,`role_id`) VALUES (1, 3);
INSERT INTO `emp_role` (`employee_id`,`role_id`) VALUES (2, 3);

INSERT INTO `leave_application` (`leave_app_id`,`fromdate`,`status`,`todate`,`type`,`employee_employee_id`,`reason`) VALUES (1, '2021-12-28','APPLIED','2021-12-29','ANNUAL',2,'holiday');
INSERT INTO `leave_application` (`leave_app_id`,`fromdate`,`status`,`todate`,`type`,`employee_employee_id`,`reason`) VALUES (2, '2021-12-30','APPLIED','2021-12-30','ANNUAL',2,'holiday');

INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (1,'ANNUAL',18);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (1,'MEDICAL',60);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (1,'ANNUAL',0);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (2,'COMPENSATION',18);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (2,'MEDICAL',50);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (2,'COMPENSATION',0);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (3,'ANNUAL',14);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (3,'MEDICAL',30);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (3,'COMPENSATION',0);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (4,'ANNUAL',18);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (4,'MEDICAL',5);
INSERT INTO leave_entitlement (employee_employee_id,type,entitlement) VALUES (4,'COMPENSATION',20);

INSERT INTO holiday_list (date,name,year) VALUES ('2021-12-25','Christmas',2021);
INSERT INTO holiday_list (date,name,year) VALUES ('2021-01-01','New Year',2021);
INSERT INTO holiday_list (date,name,year) VALUES ('2021-08-09','National Day',2021);
INSERT INTO holiday_list (date,name,year) VALUES ('2021-11-04','Deepavali',2021);
INSERT INTO holiday_list (date,name,year) VALUES ('2021-05-26','Hari Raya Haji',2021);
INSERT INTO holiday_list (date,name,year) VALUES ('2022-12-25','Christmas',2022);
INSERT INTO holiday_list (date,name,year) VALUES ('2022-01-01','New Year',2022);
INSERT INTO holiday_list (date,name,year) VALUES ('2022-08-09','National Day',2022);
INSERT INTO holiday_list (date,name,year) VALUES ('2022-11-04','Deepavali',2022);
INSERT INTO holiday_list (date,name,year) VALUES ('2022-05-26','Hari Raya Haji',2022);
