DELETE FROM user_roles;
DELETE FROM users;
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