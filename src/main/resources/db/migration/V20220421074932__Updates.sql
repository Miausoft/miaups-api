ALTER TABLE delivery_task
    ADD order_in_plan INTEGER;

ALTER TABLE parcel_machine_locker
    ADD reserved BOOLEAN;

ALTER TABLE delivery_task
DROP
COLUMN delivery_method;

UPDATE parcel_machine_locker
SET reserved = FALSE;

INSERT INTO public.warehouse(address)
VALUES ('LT,11111,Vilnius,Sandelio,19'),
       ('LT,23456,Panevezys,Plaktuku,22'),
       ('LT,22235,Kryzkalnis,Kryziaus,1');
