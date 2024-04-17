CREATE TABLE zoo (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50),
  eat VARCHAR(100),
  weight INTEGER,
  height INTEGER,
  age INTEGER
);

INSERT INTO zoo (name, eat, weight, height, age)
VALUES
('Lion', 'Meat', 200, 120, 5),
('Elephant', 'Plants', 5000, 300, 15),
('Graffe', 'Leaves', 1500, 550, 10),
('Monkey', 'Fruits', 50, 60, 8);

SELECT * FROM zoo;