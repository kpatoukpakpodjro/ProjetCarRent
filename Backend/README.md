# springbootLoginAndRegistrationWithMySQL

1. Création de la base de données et des tables

CREATE DATABASE webproject;
USE webproject;

Puis, allez le projet pour générer les tables (vides) ainsi que les relations entre elles

2. Insertion des voitures :
   INSERT INTO cars (plate_number, price, rented) VALUES
   ('ABC1234', 50, false),
   ('XYZ5678', 60, false),
   ('DEF2468', 70, false),
   ('GHI1357', 55, false),
   ('JKL456', 60, false),
   ('MNO789', 80, false),
   ('PQR123', 100, false),
   ('STU987', 75, false),
   ('VWX654', 90, false),
   ('YZA321', 110, false),
   ('BCD852', 95, false),
   ('EFG741', 70, false),
   ('HIJ258', 85, false),
   ('KLM369', 95, false),
   ('NOP852', 65, false),
   ('QRS987', 120, false),
   ('TUV654', 130, true),
   ('WXY321', 140, false),
   ('ZAB159', 100, false),
   ('CDE357', 110, false);
3. Insertions de quelques users :
INSERT INTO users (id,username, email, role) VALUES
   (12,'kkpodjro', 'alice@example.com', 'USER'),
   (1,'wpaty', 'bob@example.com', 'USER'),
   (52,'admin', 'admin@example.com', 'ADMIN');
