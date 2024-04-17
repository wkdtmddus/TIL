CREATE TABLE transactions (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  user_id INTEGER REFERENCES users(id),
  amount VARCHAR(30),
  transaction_date DATE
);

INSERT INTO transactions (user_id, amount, transaction_date)
VALUES
  (1, '500', '2024-03-15'),
  (2, '700', '2024-03-16'),
  (3, '1000', '2024-03-18'),
  (4, '200', '2024-03-17');

SELECT first_name, last_name, amount, transaction_date FROM users LEFT JOIN transactions ON users.id = transactions.user_id;

SELECT first_name, last_name, amount, transaction_date FROM users LEFT JOIN transactions ON users.id = transactions.user_id WHERE transaction_date > '2024-03-16';

SELECT first_name, last_name, SUM(amount) AS total_amount FROM users LEFT JOIN transactions ON users.id = transactions.user_id GROUP BY user_id;