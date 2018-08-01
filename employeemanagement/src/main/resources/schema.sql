DROP TABLE employee if EXISTS;
CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NULL,
  password VARCHAR(45) NULL,
  fullname VARCHAR(45) NULL,
  emailID VARCHAR(100) NULL,
  dateofbirth  VARCHAR(100) NULL,
  gender VARCHAR(45) NULL,
  securityquestion VARCHAR(100) NULL,
  securityanswer VARCHAR(100) NULL,
  PRIMARY KEY (id));