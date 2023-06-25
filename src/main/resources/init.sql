CREATE TABLE department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE salary (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_salary DOUBLE
);

CREATE TABLE employee (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  salary_id BIGINT,
  department_id BIGINT,
  FOREIGN KEY (salary_id) REFERENCES salary(id),
  FOREIGN KEY (department_id) REFERENCES department(id)
);

CREATE TABLE salary_component (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  factor DOUBLE
);


CREATE TABLE salary_salary_components(
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     salary_id BIGINT,
     salary_components_id BIGINT,
     FOREIGN KEY (salary_id) REFERENCES salary(id),
     FOREIGN KEY (salary_components_id) REFERENCES salary_component(id)
);

