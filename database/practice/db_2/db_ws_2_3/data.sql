SELECT * FROM hotels;

UPDATE hotels SET grade = UPPER(grade);

SELECT grade FROM hotels;

CREATE TABLE customers (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50),
  email VARCHAR(50)
);

CREATE TABLE reservations (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  customer_id INTEGER REFERENCES customers(id),
  room_num INTEGER REFERENCES hotels(room_num),
  check_in VARCHAR(30),
  check_out VARCHAR(30)
);

INSERT INTO customers (name, email)
VALUES
  ('홍길동', 'john@example.com'),
  ('박지영', 'jane@example.com'),
  ('김미영', 'alice@example.com'),
  ('이철수', 'bob@example.com');

INSERT INTO reservations (customer_id, room_num, check_in, check_out)
VALUES
  (1, 101, '2024-03-20', '2024-03-25'),
  (2, 202, '2024-03-21', '2024-03-24'),
  (3, 303, '2024-03-22', '2024-03-26'),
  (4, 404, '2024-03-23', '2024-03-27');