ALTER TABLE zoo ADD COLUMN species VARCHAR(100) NOT NULL DEFAULT 'default value';

UPDATE zoo SET species = 'Pantjera leo' WHERE id = 1;
UPDATE zoo SET species = 'loxodonta africana' WHERE id = 2;
UPDATE zoo SET species = 'Giraffa camelopardalis' WHERE id = 3;
UPDATE zoo SET species = 'Cebus capucinus' WHERE id = 4;

UPDATE zoo SET height = height*2.54;

SELECT * FROM zoo;