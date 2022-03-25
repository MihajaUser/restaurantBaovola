CREATE TABLE Produit (
	id serial NOT NULL,
	nom VARCHAR(255) NOT NULL,
	type varchar(255) NOT  NULL,
	prixNormal FLOAT NOT NULL,
	prixLongue FLOAT NOT NULL,
	PRIMARY KEY (id)
) ;

insert into Produit values(1,'Vary @anana','entre', 4500 , 6000);
insert into Produit values(2,'Riz contonais','entre', 7500 , 10000);
insert into Produit values(3,'Glace','dessert', 2000 , 5000);
insert into Produit values(4,'salade de fruit','dessert', 1500 , 5000);





CREATE TABLE composants (
	idComposants serial NOT NULL,
	nomComposants VARCHAR(255) NOT NULL,
	prixUnitaire float not null,
	 PRIMARY KEY (idComposants)
);

insert into composants values(5,'sira',300 );
insert into composants values(6,'anana',500 );
insert into composants values(7,'menaka',8000 );
insert into composants values(8,'vary',2400 );

insert into composants values(21,'ronono',3500);
insert into composants values(22,'creme',3000);
insert into composants values(23,'siramamy',2000);
insert into composants values(24,'rano',2000);



CREATE TABLE recette(
	idRecette int not null,
	quantite float not null,
	idProduit int not null,
	idComposants int not null,
	PRIMARY key(idRecette),
	 FOREIGN key(idProduit) REFERENCES Produit(id),
	 FOREIGN key(idComposants) REFERENCES composants(idComposants)
);

insert into recette values(9, 0.2, 1,5);
insert into recette values(10, 0.3, 1,6);
insert into recette values(11, 0.3, 1,8);
insert into recette values(12, 0.2, 1,7);

insert into recette values(13, 0.4, 3,22);
insert into recette values(14, 0.75, 3,24);
insert into recette values(15, 0.2, 3,23);



create VIEW  ViewRecette	 as  
SELECT
recette.idRecette as idRecette,
recette.quantite as quantite,
Produit.id as idProduit,
Produit.nom as nom_Produit,
Produit.type as type_produit,
Produit.prixNormal as prix_Normal_Produit,
Produit.prixLongue as prix_longue_produit,
composants.idComposants as idComposant,
composants.nomComposants as nom_Composants,
composants.prixUnitaire as prix_unitaire
from
Produit 
inner join recette on  Produit.id=recette.idProduit
INNER JOIN composants ON  recette.idComposants =  composants.idComposants;



create VIEW  view_recette as  
SELECT
recette.idRecette as idRecette,
recette.quantite as quantite,
recette.idComposants as idC,
composants.nom as nom,
composants.prixUnitaire as prix
from
recette 
INNER JOIN composants ON  recette.idComposants =  composants.idComposants;



CREATE TABLE Stocks (
	id serial NOT NULL,
	idComposant int NOT NULL,
	quantity FLOAT NOT NULL,
	date DATE NOT NULL,
	heure TIME not null,
	PRIMARY KEY (id)
);



CREATE TABLE Commandes (
	id serial NOT NULL,
	idTable int NOT NULL,
	sorteClient VARCHAR(255) NOT NULL,
	date DATE NOT NULL,
	heure TIME not null,
	CONSTRAINT Commandes_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Tables (
	id serial NOT NULL,
	disponible serial NOT NULL,
	type int NOT NULL,
	CONSTRAINT Tables_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE DetailsCommandes (
	id serial NOT NULL,
	idComande int NOT NULL,
	idProduit int NOT NULL,
	quantite int NOT NULL,
	CONSTRAINT DetailsCommandes_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);



CREATE TABLE DetailsProduits (
	id serial NOT NULL,
	idProduit int NOT NULL,
	idComposants int NOT NULL,
	quantite FLOAT NOT NULL,
	CONSTRAINT DetailsProduits_pk PRIMARY KEY (id)
) WITH (
  OIDS=FALSE
);






ALTER TABLE Stocks ADD FOREIGN KEY (idComposant) REFERENCES composants(id);

ALTER TABLE Commandes ADD FOREIGN KEY (idTable) REFERENCES Tables(id);


ALTER TABLE DetailsCommandes ADD  FOREIGN KEY (idComande) REFERENCES Commandes(id);
ALTER TABLE DetailsCommandes ADD  FOREIGN KEY (idProduit) REFERENCES Produits(id);

ALTER TABLE DetailsProduits ADD FOREIGN KEY (idProduit) REFERENCES Produits(id);
ALTER TABLE DetailsProduits ADD FOREIGN KEY (idComposants) REFERENCES composants(id);








