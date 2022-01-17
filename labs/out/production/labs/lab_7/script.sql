USE lab_7;

CREATE TABLE `clinic`
(
`ID` INT NOT NULL AUTO_INCREMENT,
`lastName` VARCHAR(20),
`firstName` VARCHAR(15),
`patronymic` VARCHAR(25),
`birthDate` DATE,
`post` VARCHAR(20),
`spec` VARCHAR(30),
PRIMARY KEY(`ID`)
);

INSERT INTO `clinic` (`lastName`, `firstName`, `patronymic`, `birthDate`, `post`, `spec`)
VALUES
('Папич', 'Владимир', 'Потапович', '1990.03.20', 'Врач', 'Нарколог'),
('Терентьев', 'Михаил', 'Палыч', '2010.04.15', 'Главный врач', 'Психотерапевт');