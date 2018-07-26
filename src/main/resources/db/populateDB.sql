DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals(description, dateTime, calories,user_id) VALUES
  ('Lunch','25.07.2017',510,100001),
  ('lunchtime','25.07.2017',675,100001),
  ('dinner','25.07.2017',700,100001);
INSERT INTO meals(description, dateTime, calories,user_id) VALUES
  ('Завтрак','30.07.2018 10:00:00',500,100000),
  ('Обед','30.07.2018 13:00:00',1000,100000),
  ('Ужин','30.07.2018 20:00:00',500,100000),
  ('Завтрак','31.07.2018 10:00:00',500,100000),
  ('Обед','31.07.2018 13:00:00',1000,100000),
  ('Ужин','31.07.2018 20:00:00',510,100000);