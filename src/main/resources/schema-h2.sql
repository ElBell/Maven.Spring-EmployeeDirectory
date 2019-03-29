# DROP TABLE PERSON;
#
# CREATE TABLE PERSON (
#   Number NUMBER(10,0) NOT NULL AUTO_INCREMENT,
#   firstName VARCHAR2(255) NOT NULL DEFAULT '',
#   lastName VARCHAR2(255) NOT NULL DEFAULT '',
#   title VARCHAR2(255) NOT NULL DEFAULT '',
#   phoneNumber VARCHAR2(20) NOT NULL DEFAULT '',
#   email VARCHAR2(255) NOT NULL DEFAULT '',
#   hireDate DATE DEFAULT NULL,
#   manager_id NUMBER(10,0),
#   department_id
#   PRIMARY KEY (ID));
#
# DROP TABLE DEPARTMENT;
#
# CREATE TABLE DEPARTMENT (
#   number NUMBER(10,0) NOT NULL AUTO_INCREMENT,
#   name VARCHAR2(255) not null default '',
#   manager_id NUMBER(10,0) NOT NULL DEFAULT '',
#   PRIMARY KEY (ID)
# );
#
#
# DROP SEQUENCE hibernate_sequence;
#
# CREATE SEQUENCE hibernate_sequence;