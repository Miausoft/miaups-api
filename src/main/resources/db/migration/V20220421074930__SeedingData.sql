CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

INSERT INTO parcel_dimensions (size, max_length, max_width, max_height, price)
VALUES
    ('XS', 4, 43, 61, 4.99),
    ('S',  8, 43, 61, 5.99),
    ('M', 12, 43, 61, 6.50),
    ('L', 16, 43, 61, 8.99),
    ('XL', 20, 43, 61, 10.0);

INSERT INTO parcel_machine (id, address, lockers_count, available_lockers_count)
VALUES
    (1, 'LT,11111,Vilnius,Ateities,1', 5 ,5),
    (2, 'LT,22222,Kaunas,Savanoriu,2', 6, 6),
    (3, 'LT,11111,Vilnius,Didlaukio,59', 4, 4),
    (4, 'LT,33333,Klaipeda,Danes,99', 10, 10);

INSERT INTO parcel_machine_locker (locker_id, parcel_machine_id)
VALUES
    (1, 1),(2, 1),(3, 1),(4, 1),(5, 1),
    (1, 2),(2, 2),(3, 2),(4, 2),(5, 2),(6, 2),
    (1, 3),(2, 3),(3, 3),(4, 3),
    (1, 4),(2, 4),(3, 4),(4, 4),(5, 4),(6, 4),(7, 4),(8, 4),(9, 4),(10,4);