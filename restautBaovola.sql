
CREATE TABLE Produit (
	id serial NOT NULL,
	nom VARCHAR(255) NOT NULL,
	type varchar(255) NOT  NULL,
	prixNormal FLOAT NOT NULL,
	prixLongue FLOAT NOT NULL,
	PRIMARY KEY (id)
);

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


CREATE TABLE Stocks (
	id serial NOT NULL,
	idComposant int NOT NULL,
	quantity FLOAT NOT NULL,
	date DATE NOT NULL,
	heure TIME not null,
	PRIMARY KEY (id)
);



CREATE TABLE Tables (
	idTables int NOT NULL,
	disponible boolean not null,
	PRIMARY KEY (idTables))
;

insert into tables values(31,true);
insert into tables values(30,true);



CREATE TABLE DetailsProduits (
	id serial NOT NULL,
	idProduit int NOT NULL,
	idComposants int NOT NULL,
	quantite FLOAT NOT NULL,
	PRIMARY KEY (id)
);



create table serveur(
	idServeur int not null,
	nomServeur VARCHAR(50) not null,
	pourboir float not null,
	primary key(idServeur)
);

insert into serveur values(40,'Micha',0);
insert into serveur values(41,'Nico',0);



CREATE TABLE Commandes (
	idCommandes serial NOT NULL,
	idTable int NOT NULL,
	sorteClient VARCHAR(255) NOT NULL,
	date DATE NOT NULL,
	heure TIME not null,
	idServeur int not null,
	PRIMARY KEY (idCommandes),
	foreign key(idServeur) REFERENCES serveur(idServeur)
);


insert into commandes values(51,30,'normal','2022-03-30','15:00:00',41);
insert into commandes values(52,30,'normal','2022-03-30','15:00:00',41);


CREATE TABLE DetailsCommandes (
	idDetailsCommandes serial NOT NULL,
	idCommandes int NOT NULL,
	idProduit int NOT NULL,
	quantite int NOT NULL,
	PRIMARY KEY (idDetailsCommandes)
);

insert into DetailsCommandes values(60,50,1,1);
insert into DetailsCommandes values(62,51,2,1);
insert into DetailsCommandes values(63,50,4,1);
insert into DetailsCommandes values(64,52,4,1);
insert into DetailsCommandes values(65,52,4,1);



CREATE TABLE CommandesNonValider (
	idCommandeNV serial NOT NULL,
	nomProduitNV varchar(100) NOT NULL,
	typeProduitNV VARCHAR(255) NOT NULL,
	prixNormalNV float not null,
	prixLoungeNV float not null,
	quantite int not null
);



ALTER TABLE Stocks ADD FOREIGN KEY (idComposant) REFERENCES composants(idComposants);

ALTER TABLE Commandes ADD FOREIGN KEY (idTable) REFERENCES Tables(idTables);

ALTER TABLE DetailsCommandes ADD  FOREIGN KEY (idCommandes) REFERENCES Commandes(idCommandes);
ALTER TABLE DetailsCommandes ADD  FOREIGN KEY (idProduit) REFERENCES Produit(id);

ALTER TABLE DetailsProduits ADD FOREIGN KEY (idProduit) REFERENCES Produit(id);
ALTER TABLE DetailsProduits ADD FOREIGN KEY (idComposants) REFERENCES composants(idComposants);



create view  view_Serveurs
as SELECT
Commandes.idCommandes,
Commandes.idTable,
Commandes.sorteClient,
Commandes.date,
Commandes.heure,
serveur.idServeur,
serveur.nomServeur,
serveur.pourboir,
DetailsCommandes.idDetailsCommandes,
DetailsCommandes.idProduit,
DetailsCommandes.quantite,
produit.nom,
produit.prixNormal
from Commandes
inner join  serveur on serveur.idServeur = Commandes.idServeur
inner join DetailsCommandes on DetailsCommandes.idCommandes = commandes.idCommandes
inner join produit on DetailsCommandes.idProduit = produit.id;

create view calcul as select date,nomServeur ,sum(prixNormal * quantite) * 0.02 as total from view_Serveurs GROUP BY nomServeur,date ;


create view view_Commandes
as SELECT 
DetailsCommandes.idDetailsCommandes,
Commandes.idTable,
Commandes.date,
Commandes.heure,
Commandes.sorteClient,
serveur.nomserveur,
DetailsCommandes.quantite,
produit.nom,
produit.prixNormal,
produit.prixLongue
from Commandes
inner join  serveur on serveur.idServeur = Commandes.idServeur
inner join DetailsCommandes on DetailsCommandes.idCommandes = commandes.idCommandes
inner join produit on DetailsCommandes.idProduit = produit.id
inner join tables on tables.idtables=commandes.idtable;