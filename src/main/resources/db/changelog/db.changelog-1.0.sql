--liquibase formatted sql

--changeset initial:1
CREATE TABLE Warehouse(
	Id int GENERATED ALWAYS AS IDENTITY,
	Address varchar(255) NOT NULL,
	CONSTRAINT PK_Warehouse PRIMARY KEY (Id)
);

CREATE TABLE Shipment(
	Id int GENERATED ALWAYS AS IDENTITY,
	Status varchar(50) NOT NULL,
	CourierId varchar(36),
	CONSTRAINT PK_Shipment PRIMARY KEY (Id)
);

CREATE TABLE ParcelMachine(
	Id int GENERATED ALWAYS AS IDENTITY,
	Address varchar(255) NOT NULL,
	LockersCount int NOT NULL,
	CONSTRAINT PK_ParcelMachine PRIMARY KEY (Id)
);

CREATE TABLE ParcelDimensions(
	Id int NOT NULL,
	Label varchar(3) NOT NULL,
	Height float NOT NULL,
	Width float NOT NULL,
	Length float NOT NULL,
	Price float NOT NULL,
	CONSTRAINT PK_ParcelDimensions PRIMARY KEY (Id)
);

CREATE TABLE ParcelMachineLocker(
	Id int NOT NULL,
	PlaceNr int NOT NULL,
	ParcelMachineId int NOT NULL,
	Empty boolean NOT NULL,
	CONSTRAINT PK_ParcelMachineLocker PRIMARY KEY (Id),
	CONSTRAINT FK_ParcelMachineLocker FOREIGN KEY (ParcelMachineId) REFERENCES ParcelMachine(Id),
	CONSTRAINT UQ_PlaceNr_PM UNIQUE(PlaceNr,ParcelMachineId)
);

CREATE TABLE Parcel(
	Id UUID DEFAULT gen_random_uuid(),
	Type varchar(50) NOT NULL,
	Price float NOT NULL,
	DimensionsId int NOT NULL,
	StartAddress varchar(255),
	DestinationAddress varchar(255),
	StartParcelMachineId int,
	DestinationParcelMachineId int,
	CurrentAddress varchar(255),
	CurrentParcelMachineLockerId int,
	CurrentWarehouseId int,
	CONSTRAINT PK_Parcel PRIMARY KEY (Id),
	CONSTRAINT FK_ParcelDimensions FOREIGN KEY (DimensionsId) REFERENCES ParcelDimensions(Id),
	CONSTRAINT FK_ParcelStartPM FOREIGN KEY (StartParcelMachineId) REFERENCES ParcelMachine(Id),
	CONSTRAINT FK_ParcelDestinationPM FOREIGN KEY (DestinationParcelMachineId) REFERENCES ParcelMachine(Id),
	CONSTRAINT FK_CurrentParcelLocker FOREIGN KEY (CurrentParcelMachineLockerId) REFERENCES ParcelMachineLocker(Id),
	CONSTRAINT FK_CurrentWarehouse FOREIGN KEY (CurrentWarehouseId) REFERENCES Warehouse(Id)
);

CREATE TABLE DeliveryTask(
	Id int GENERATED ALWAYS AS IDENTITY,
	Type varchar(50) NOT NULL,
	Status varchar(50) NOT NULL,
	ParcelId UUID NOT NULL,
	CreatedBy varchar(36) NOT NULL,
	CreatedAt TIMESTAMP NOT NULL,
	ShipmentId int,
	CompletedAt TIMESTAMP,
	StartAddress varchar(255),
	DestinationAddress varchar(255),
	StartParcelMachineId int,
    DestinationParcelMachineId int,
	StartWarehouseId int,
	DestinationWarehouseId int,
	CONSTRAINT PK_DeliveryTask PRIMARY KEY (Id),
	CONSTRAINT FK_ParcelDelivery FOREIGN KEY (ParcelId) REFERENCES Parcel(Id),
	CONSTRAINT FK_TaskShipment FOREIGN KEY (ShipmentId) REFERENCES Shipment(Id),
	CONSTRAINT FK_DeliveryStartPM FOREIGN KEY (StartParcelMachineId) REFERENCES ParcelMachine(Id),
	CONSTRAINT FK_DeliveryDestinationPM FOREIGN KEY (DestinationParcelMachineId) REFERENCES ParcelMachine(Id),
	CONSTRAINT FK_DeliveryStartWarehouse FOREIGN KEY (StartWarehouseId) REFERENCES Warehouse(Id),
	CONSTRAINT FK_DeliveryDestinationWarehouse FOREIGN KEY (DestinationWarehouseId) REFERENCES Warehouse(Id)
);