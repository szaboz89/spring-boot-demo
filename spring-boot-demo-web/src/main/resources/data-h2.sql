-- noinspection SqlResolveForFile
INSERT INTO Developer (id, firstName, lastName, email, createdDate, modifiedDate, developerLevel) VALUES
  (1, 'Aaa', 'Aaa', 'test@test.com', NOW(), NOW(), 'JUNIOR'),
  (2, 'AAA', 'Aaa', 'test@test.com', NOW(), NOW(), 'INTERMEDIATE'),
  (3, 'Bbb', 'Aaa', 'test@test.com', NOW(), NOW(), 'SENIOR'),
  (4, 'Bbb', 'Bbb', 'test@test.com', NOW(), NOW(), 'JUNIOR'),
  (5, 'Ccc', 'Bbb', 'test@test.com', NOW(), NOW(), 'INTERMEDIATE');
