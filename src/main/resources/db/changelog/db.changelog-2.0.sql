--liquibase formatted sql

--changeset data:1
INSERT INTO ParcelDimensions(
	id, label, height, width, length, price)
	VALUES
	(1, 'S', 8,43, 61, 4.99),
	(2, 'M', 17,43, 61, 6.50),
	(3, 'L', 36,43, 61, 8.99);

INSERT INTO ParcelMachine
	VALUES
	(DEFAULT, 'Vilnius Ateities g. 1', 5),
	(DEFAULT, 'Kaunas Savanoriu pr. 2', 6),
	(DEFAULT, 'Vilnius Didlaukio g. 59', 4),
	(DEFAULT, 'Klaipeda Danes g. 99', 10);

INSERT INTO ParcelMachineLocker
	VALUES
	(DEFAULT, 1, 1, true),
	(DEFAULT, 2, 1, true),
	(DEFAULT, 3, 1, true),
	(DEFAULT, 4, 1, true),
	(DEFAULT, 5, 1, true),

	(DEFAULT, 1, 2, true),
	(DEFAULT, 2, 2, true),
	(DEFAULT, 3, 2, true),
	(DEFAULT, 4, 2, true),
	(DEFAULT, 5, 2, true),
	(DEFAULT, 6, 2, true),

	(DEFAULT, 1, 3, true),
	(DEFAULT, 2, 3, true),
	(DEFAULT, 3, 3, true),
	(DEFAULT, 4, 3, true),

	(DEFAULT, 1, 4, true),
	(DEFAULT, 2, 4, true),
	(DEFAULT, 3, 4, true),
	(DEFAULT, 4, 4, true),
	(DEFAULT, 5, 4, true),
	(DEFAULT, 6, 4, true),
	(DEFAULT, 7, 4, true),
	(DEFAULT, 8, 4, true),
	(DEFAULT, 9, 4, true),
	(DEFAULT, 10, 4, true);