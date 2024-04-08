CREATE TABLE orders (
  order_id INTEGER PRIMARY KEY AUTOINCREMENT,
  order_date DATE NOT NULL,
  total_amount REAL NOT NULL
);


INSERT INTO orders (order_date, total_amount)
VALUES
  ('2023-07-15', 50.99),
  ('2023-07-16', 75.5),
  ('2023-07-17', 30.25);


CREATE TABLE customers (
  customer_id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(10) NOT NULL,
  email VARCHAR(30) NOT NULL,
  phone VARCHAR(13) NOT NULL
);


INSERT INTO customers (name, email, phone)
VALUES
  ('허균', 'hong.gildong@example.com', '010-1234-5678'),
  ('김영희', 'kim.younghee@example.com', '010-9876-5432'),
  ('이철수', 'lee.cheolsu@example.com', '010-5555-4444');


DELETE FROM orders WHERE order_id IN (SELECT order_id FROM orders LIMIT 1 OFFSET 2);


UPDATE customers SET name = '홍길동' WHERE customer_id IN (SELECT customer_id FROM customers LIMIT 1);


SELECT * FROM orders;
SELECT * from customers;
