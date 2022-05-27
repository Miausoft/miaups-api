ALTER TABLE parcel
    ADD version int not null default '0';

update parcel set version=0 where true;