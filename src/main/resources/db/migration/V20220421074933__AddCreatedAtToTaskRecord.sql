ALTER TABLE delivery_task_record
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE parcel
DROP
CONSTRAINT fk_parcel_on_currentparcelmachinelocker;

ALTER TABLE parcel
DROP
COLUMN current_parcel_machine_locker_id;