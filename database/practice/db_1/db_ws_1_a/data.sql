CREATE TABLE songs (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  title VARCHAR(100) NOT NULL,
  artists VARCHAR(100) NOT NULL,
  album VARCHAR(100) NOT NULL,
  genre VARCHAR(100) NOT NULL,
  duration INTEGER NOT NULL
);

INSERT INTO songs(title, artists, album, genre, duration)
VALUES
  ('New Title', 'Artist 1', 'Album 1', 'Pop', 200),
  ('Song 2', 'Artist 2', 'Album 2', 'Rock', 300),
  ('Song 3', 'Artist 3', 'Album 3', 'Hip Hop', 250),
  ('Song 4', 'Artist 4', 'Album 4', 'Electronic', 180),
  ('Song 5', 'Artist 5', 'Album 5', 'R&B', 320);

UPDATE songs
SET title = 'Song 1'
WHERE id = 1;