
DROP TABLE [Ponuda]
go

DROP TABLE [Voznja]
go

DROP TABLE [Paket]
go

DROP TABLE [Opstina]
go

DROP TABLE [Grad]
go

DROP TABLE [Kurir]
go

DROP TABLE [Zahtev]
go

DROP TABLE [Vozilo]
go

DROP TABLE [Administrator]
go

DROP TABLE [Korisnik]
go

CREATE TABLE [Administrator]
( 
	[KorIme]             varchar(100)  NOT NULL 
)
go

ALTER TABLE [Administrator]
	ADD CONSTRAINT [XPKAdministrator] PRIMARY KEY  CLUSTERED ([KorIme] ASC)
go

CREATE TABLE [Grad]
( 
	[IdGrad]             integer  NOT NULL  IDENTITY ,
	[Naziv]              varchar(100)  NOT NULL ,
	[PostanskiBr]        varchar(100)  NOT NULL 
)
go

ALTER TABLE [Grad]
	ADD CONSTRAINT [XPKGrad] PRIMARY KEY  CLUSTERED ([IdGrad] ASC)
go

ALTER TABLE [Grad]
	ADD CONSTRAINT [XAK1Grad] UNIQUE ([PostanskiBr]  ASC)
go

ALTER TABLE [Grad]
	ADD CONSTRAINT [XAK2Grad] UNIQUE ([Naziv]  ASC)
go

CREATE TABLE [Korisnik]
( 
	[Ime]                varchar(100)  NULL ,
	[Prezime]            varchar(100)  NULL ,
	[KorIme]             varchar(100)  NOT NULL ,
	[Sifra]              varchar(100)  NULL ,
	[BrPaketa]           integer  NULL 
	CONSTRAINT [DefaultBrPaketa_1493283779]
		 DEFAULT  0
)
go

ALTER TABLE [Korisnik]
	ADD CONSTRAINT [XPKKorisnik] PRIMARY KEY  CLUSTERED ([KorIme] ASC)
go

ALTER TABLE [Korisnik]
	ADD CONSTRAINT [XAK1Korisnik] UNIQUE ([KorIme]  ASC)
go

CREATE TABLE [Kurir]
( 
	[BrIsporucenihPaketa] char(18)  NULL ,
	[Profit]             char(18)  NULL ,
	[Status]             char(18)  NULL 
	CONSTRAINT [StatusKurira_109272485]
		CHECK  ( [Status]=0 OR [Status]=1 ),
	[KorIme]             varchar(100)  NOT NULL ,
	[RegBr]              varchar(100)  NULL 
)
go

ALTER TABLE [Kurir]
	ADD CONSTRAINT [XPKKurir] PRIMARY KEY  CLUSTERED ([KorIme] ASC)
go

CREATE TABLE [Opstina]
( 
	[IdOpstina]          integer  NOT NULL  IDENTITY ,
	[Naziv]              varchar(100)  NULL ,
	[X]                  integer  NULL ,
	[Y]                  integer  NULL ,
	[IdGrad]             integer  NULL 
)
go

ALTER TABLE [Opstina]
	ADD CONSTRAINT [XPKOpstina] PRIMARY KEY  CLUSTERED ([IdOpstina] ASC)
go

CREATE TABLE [Paket]
( 
	[IdPaket]            integer  NOT NULL ,
	[PolaznaOpstina]     integer  NOT NULL ,
	[OdredisnaOpstina]   integer  NOT NULL ,
	[TipPaketa]          integer  NULL 
	CONSTRAINT [TipPaketa_1787248994]
		CHECK  ( [TipPaketa]=0 OR [TipPaketa]=1 OR [TipPaketa]=2 ),
	[Tezina]             decimal(10,3)  NULL ,
	[Status]             integer  NULL 
	CONSTRAINT [StatusPaketa_75083639]
		CHECK  ( [Status]=0 OR [Status]=1 OR [Status]=2 OR [Status]=3 ),
	[Cena]               decimal(10,3)  NULL ,
	[VremePrihvatanja]   datetime  NULL ,
	[KorImeZahtevaoc]    varchar(100)  NULL ,
	[KorImeKurira]       varchar(100)  NULL 
)
go

ALTER TABLE [Paket]
	ADD CONSTRAINT [XPKPrevoz] PRIMARY KEY  CLUSTERED ([IdPaket] ASC)
go

CREATE TABLE [Ponuda]
( 
	[IdPonuda]           integer  NOT NULL ,
	[Procenat]           decimal(10,3)  NULL ,
	[IdPaket]            integer  NULL ,
	[KorIme]             varchar(100)  NULL ,
	[Status]             char(18)  NOT NULL 
	CONSTRAINT [StatusPonude_1302482342]
		CHECK  ( [Status]=0 OR [Status]=1 )
)
go

ALTER TABLE [Ponuda]
	ADD CONSTRAINT [XPKPonuda] PRIMARY KEY  CLUSTERED ([IdPonuda] ASC)
go

CREATE TABLE [Vozilo]
( 
	[RegBr]              varchar(100)  NOT NULL ,
	[TipGoriva]          integer  NULL 
	CONSTRAINT [TipGoriva_2021023082]
		CHECK  ( [TipGoriva]=0 OR [TipGoriva]=1 OR [TipGoriva]=2 ),
	[Potrosnja]          decimal(10,3)  NULL 
)
go

ALTER TABLE [Vozilo]
	ADD CONSTRAINT [XPKVozilo] PRIMARY KEY  CLUSTERED ([RegBr] ASC)
go

ALTER TABLE [Vozilo]
	ADD CONSTRAINT [XAK1Vozilo] UNIQUE ([RegBr]  ASC)
go

CREATE TABLE [Voznja]
( 
	[Profit]             char(18)  NULL ,
	[KorIme]             varchar(100)  NOT NULL 
)
go

ALTER TABLE [Voznja]
	ADD CONSTRAINT [XPKVoznja] PRIMARY KEY  CLUSTERED ([KorIme] ASC)
go

CREATE TABLE [Zahtev]
( 
	[KorIme]             varchar(100)  NOT NULL ,
	[RegBr]              varchar(100)  NULL 
)
go

ALTER TABLE [Zahtev]
	ADD CONSTRAINT [XPKZahtev] PRIMARY KEY  CLUSTERED ([KorIme] ASC)
go


ALTER TABLE [Administrator]
	ADD CONSTRAINT [R_2] FOREIGN KEY ([KorIme]) REFERENCES [Korisnik]([KorIme])
		ON DELETE CASCADE
		ON UPDATE CASCADE
go


ALTER TABLE [Kurir]
	ADD CONSTRAINT [R_4] FOREIGN KEY ([RegBr]) REFERENCES [Vozilo]([RegBr])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Kurir]
	ADD CONSTRAINT [R_3] FOREIGN KEY ([KorIme]) REFERENCES [Korisnik]([KorIme])
		ON DELETE CASCADE
		ON UPDATE CASCADE
go


ALTER TABLE [Opstina]
	ADD CONSTRAINT [R_1] FOREIGN KEY ([IdGrad]) REFERENCES [Grad]([IdGrad])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Paket]
	ADD CONSTRAINT [R_10] FOREIGN KEY ([PolaznaOpstina]) REFERENCES [Opstina]([IdOpstina])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Paket]
	ADD CONSTRAINT [R_11] FOREIGN KEY ([OdredisnaOpstina]) REFERENCES [Opstina]([IdOpstina])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Paket]
	ADD CONSTRAINT [R_17] FOREIGN KEY ([KorImeZahtevaoc]) REFERENCES [Korisnik]([KorIme])
		ON DELETE NO ACTION
		ON UPDATE CASCADE
go

ALTER TABLE [Paket]
	ADD CONSTRAINT [R_22] FOREIGN KEY ([KorImeKurira]) REFERENCES [Kurir]([KorIme])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Ponuda]
	ADD CONSTRAINT [R_13] FOREIGN KEY ([IdPaket]) REFERENCES [Paket]([IdPaket])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Ponuda]
	ADD CONSTRAINT [R_12] FOREIGN KEY ([KorIme]) REFERENCES [Kurir]([KorIme])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Ponuda]
	ADD CONSTRAINT [R_23] FOREIGN KEY ([KorIme]) REFERENCES [Voznja]([KorIme])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Voznja]
	ADD CONSTRAINT [R_18] FOREIGN KEY ([KorIme]) REFERENCES [Kurir]([KorIme])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Zahtev]
	ADD CONSTRAINT [R_6] FOREIGN KEY ([RegBr]) REFERENCES [Vozilo]([RegBr])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Zahtev]
	ADD CONSTRAINT [R_5] FOREIGN KEY ([KorIme]) REFERENCES [Korisnik]([KorIme])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go
