CREATE TABLE `ifarm`.`farm` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`_id`));

INSERT INTO `ifarm`.`farm` (`name`, `address`)
VALUES
  ("Cherry Blossom Estate", "Ap #553-3731 Sit Rd."),
  ("Horseshoe Grange", "972-7058 Pellentesque Rd."),
  ("Two Pines Estate", "P.O. Box 146, 9149 Hymenaeos. Ave"),
  ("Moonshadow Farms", "Ap #196-5848 Quisque Rd."),
  ("Mossy Oak Vineyard", "Ap #341-4012 Proin Road"),
  ("Meadowgrove Gardens", "P.O. Box 109, 1752 Tempus Street"),
  ("White Oak Acres", "821 Dolor Ave"),
  ("Bittersweet Lands", "Ap #153-1787 Mollis. Ave"),
  ("Windy Oaks Fields", "764-1552 In, Road"),
  ("Rattlesnake Ranch", "P.O. Box 279, 8717 Id Road");
  
  
CREATE TABLE `ifarm`.`unit_type` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `unitType` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`_id`));


INSERT INTO unit_type (unitType)  VALUES ('mass');
INSERT INTO unit_type (unitType)  VALUES ('pack');
INSERT INTO unit_type (unitType)  VALUES ('volume');

CREATE TABLE `ifarm`.`plant` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `unitType` INT NULL,
  PRIMARY KEY (`_id`),
  INDEX `plant_unitType_idx` (`unitType` ASC) VISIBLE,
  CONSTRAINT `plant_unitType`
    FOREIGN KEY (`unitType`)
    REFERENCES `ifarm`.`unit_type` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `ifarm`.`fertiliser` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `unitType` INT NOT NULL,
  PRIMARY KEY (`_id`),
  INDEX `fertiliser_unitType_idx` (`unitType` ASC) VISIBLE,
  CONSTRAINT `fertiliser_unitType`
    FOREIGN KEY (`unitType`)
    REFERENCES `ifarm`.`unit_type` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `ifarm`.`pesticide` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `unitType` INT NOT NULL,
  PRIMARY KEY (`_id`),
  INDEX `pesticide_unitType_idx` (`unitType` ASC) VISIBLE,
  CONSTRAINT `pesticide_unitType`
    FOREIGN KEY (`unitType`)
    REFERENCES `ifarm`.`unit_type` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO fertiliser (name,unitType)  VALUES ("Ammonium Polyphosphate (POLY11)", 3);
INSERT INTO fertiliser (Ammonium Polyphosphate (POLY11),3)  VALUES ("Calcium Nitrate", 3);
INSERT INTO fertiliser (Calcium Nitrate,3)  VALUES ("Eggshells", 3);
INSERT INTO fertiliser (Eggshells,3)  VALUES ("Ammonium Nitrate", 3);
INSERT INTO fertiliser (Ammonium Nitrate,3)  VALUES ("Urea", 3);
INSERT INTO fertiliser (Urea,3)  VALUES ("Ammonium Sulfate", 3);
INSERT INTO fertiliser (Ammonium Sulfate,3)  VALUES ("Diammonium Phosphate", 3);
INSERT INTO fertiliser (Diammonium Phosphate,3)  VALUES ("Monoammonium phosphate", 3);
INSERT INTO fertiliser (Monoammonium phosphate,3)  VALUES ("Triple Super Phosphate", 3);
INSERT INTO fertiliser (Triple Super Phosphate,3)  VALUES ("Potassium Nitrate", 3);
INSERT INTO fertiliser (Potassium Nitrate,3)  VALUES ("Potassium Chloride", 3);
INSERT INTO fertiliser (Potassium Chloride,3)  VALUES ("Anhydrous Ammonia", 3);
INSERT INTO fertiliser (Anhydrous Ammonia,3)  VALUES ("Aqua Ammonia", 3);
INSERT INTO fertiliser (Aqua Ammonia,3)  VALUES ("Ammonium Nitrate", 3);
INSERT INTO fertiliser (Ammonium Nitrate,3)  VALUES ("Ammonium Nitrate Solution", 3);
INSERT INTO fertiliser (Ammonium Nitrate Solution,3)  VALUES ("Amm Nitrate-Limestone Mixtures", 3);
INSERT INTO fertiliser (Amm Nitrate-Limestone Mixtures,3)  VALUES ("Ammonium Nitrate - Sulfate", 3);
INSERT INTO fertiliser (Ammonium Nitrate - Sulfate,3)  VALUES ("Ammonium Polysulfide", 3);
INSERT INTO fertiliser (Ammonium Polysulfide,3)  VALUES ("Ammonium Thiosulfate", 3);
INSERT INTO fertiliser (Ammonium Thiosulfate,3)  VALUES ("Calcium Ammonium Nitrate 1", 3);
INSERT INTO fertiliser (Calcium Ammonium Nitrate 1,3)  VALUES ("Calcium Cyanamide", 3);
INSERT INTO fertiliser (Calcium Cyanamide,3)  VALUES ("Calcium Nitrate", 3);
INSERT INTO fertiliser (Calcium Nitrate,3)  VALUES ("Calcium Nitrate - Urea", 3);
INSERT INTO fertiliser (Calcium Nitrate - Urea,3)  VALUES ("Ferrous Ammonium Sulfate", 3);
INSERT INTO fertiliser (Ferrous Ammonium Sulfate,3)  VALUES ("Magnesium Nitrate", 3);
INSERT INTO fertiliser (Magnesium Nitrate,3)  VALUES ("Nitric Acid", 3);
INSERT INTO fertiliser (Nitric Acid,3)  VALUES ("Sodium Nitrate", 3);
INSERT INTO fertiliser (Sodium Nitrate,3)  VALUES ("Sulfur Coated Urea", 3);
INSERT INTO fertiliser (Sulfur Coated Urea,3)  VALUES ("Urea", 3);
INSERT INTO fertiliser (Urea,3)  VALUES ("cwqDimethoa", 3);
INSERT INTO fertiliser (cwqDimethoa,3)  VALUES ("Zinc Ammonium Sulfate Solution", 3);
INSERT INTO fertiliser (Zinc Ammonium Sulfate Solution,3)  VALUES ("Zinc Manganese Amm Sulfate", 3);
INSERT INTO fertiliser (Zinc Manganese Amm Sulfate,3)  VALUES ("Nitrogen Product", 3);
INSERT INTO fertiliser (Nitrogen Product,3)  VALUES ("Nitrogen", 3);
INSERT INTO fertiliser (Nitrogen,3)  VALUES ("SevinQian", 3);
INSERT INTO fertiliser (SevinQian,3)  VALUES ("Kelthweiane", 3);
INSERT INTO fertiliser (Kelthweiane,3)  VALUES ("Ammonium Phosphate", 3);
INSERT INTO fertiliser (Ammonium Phosphate,3)  VALUES ("GuthionChongi 4", 3);
INSERT INTO fertiliser (GuthionChongi 4,3)  VALUES ("Diammonium Phosphate", 3);
INSERT INTO fertiliser (Diammonium Phosphate,3)  VALUES ("Basic Lime Phosphate", 3);
INSERT INTO fertiliser (Basic Lime Phosphate,3)  VALUES ("Ammonium Phosphate Nitrate", 3);
INSERT INTO fertiliser (Ammonium Phosphate Nitrate,3)  VALUES ("Ammonium Phosphate Sulfate", 3);
INSERT INTO fertiliser (Ammonium Phosphate Sulfate,3)  VALUES ("Basic Slag", 3);
INSERT INTO fertiliser (Basic Slag,3)  VALUES ("Monoammonium Phosphate", 3);
INSERT INTO fertiliser (Monoammonium Phosphate,3)  VALUES ("Warrior Chong", 3);
INSERT INTO fertiliser (Warrior Chong,3)  VALUES ("Bone Meal, Steamed", 3);
INSERT INTO fertiliser (Bone Meal, Steamed,3)  VALUES ("Calcium Metaphosphate", 3);
INSERT INTO fertiliser (Calcium Metaphosphate,3)  VALUES ("Bone, Precipitated", 3);
INSERT INTO fertiliser (Bone, Precipitated,3)  VALUES ("Limestone, Phosphatic", 3);
INSERT INTO fertiliser (Limestone, Phosphatic,3)  VALUES ("Magnesium Phosphate", 3);
INSERT INTO fertiliser (Magnesium Phosphate,3)  VALUES ("Nitric Phosphate", 3);
INSERT INTO fertiliser (Nitric Phosphate,3)  VALUES ("Nufos 4Ecwq", 3);
INSERT INTO fertiliser (Nufos 4Ecwq,3)  VALUES ("Phosphoric Acid", 3);
INSERT INTO fertiliser (Phosphoric Acid,3)  VALUES ("Liquid Amm Polyphosphate", 3);
INSERT INTO fertiliser (Liquid Amm Polyphosphate,3)  VALUES ("Precipitated Phosphate", 3);
INSERT INTO fertiliser (Precipitated Phosphate,3)  VALUES ("Cwqgon 400", 3);
INSERT INTO fertiliser (Cwqgon 400,3)  VALUES ("Superphosphate, Enriched", 3);
INSERT INTO fertiliser (Superphosphate, Enriched,3)  VALUES ("Manure Salts", 3);
INSERT INTO fertiliser (Manure Salts,3)  VALUES ("Pymetrodun", 3);
INSERT INTO fertiliser (Pymetrodun,3)  VALUES ("Potassium Nitrate", 3);
INSERT INTO fertiliser (Potassium Nitrate,3)  VALUES ("Potassium Carbonate", 3);
INSERT INTO fertiliser (Potassium Carbonate,3)  VALUES ("Guano", 3);
INSERT INTO fertiliser (Guano,3)  VALUES ("Hexakis 33", 3);
INSERT INTO fertiliser (Hexakis 33,3)  VALUES ("Aluminum Sulfate", 3);
INSERT INTO fertiliser (Aluminum Sulfate,3)  VALUES ("Cobalt Sulfate", 3);
INSERT INTO fertiliser (Cobalt Sulfate,3)  VALUES ("Dipel 2X", 3);
INSERT INTO fertiliser (Dipel 2X,3)  VALUES ("Ferric Sulfate", 3);
INSERT INTO fertiliser (Ferric Sulfate,3)  VALUES ("Manganese Slag", 3);
INSERT INTO fertiliser (Manganese Slag,3)  VALUES ("Manganese Oxide", 3);
INSERT INTO fertiliser (Manganese Oxide,3)  VALUES ("MSR", 3);
INSERT INTO fertiliser (MSR,3)  VALUES ("Manganese Sulfate", 3);
INSERT INTO fertiliser (Manganese Sulfate,3)  VALUES ("Soil Amendment", 3);
INSERT INTO fertiliser (Soil Amendment,3)  VALUES ("Zinc Oxysulfate", 3);
INSERT INTO fertiliser (Zinc Oxysulfate,3)  VALUES ("Keogn Orien", 3);
INSERT INTO fertiliser (Keogn Orien,3)  VALUES ("Thiodan", 3);
INSERT INTO fertiliser (Thiodan,3)  VALUES ("Zjies Shaet", 3);
INSERT INTO fertiliser (Zjies Shaet,3)  VALUES ("QianZeal", 3);
INSERT INTO fertiliser (QianZeal,3)  VALUES ("ImidanSeal", 3);
INSERT INTO fertiliser (ImidanSeal,3)  VALUES ("LannateL", 3);
INSERT INTO fertiliser (LannateL,3)  VALUES ("Norm 188", 3);
INSERT INTO fertiliser (Norm 188,3)  VALUES ("MorningFlower", 3);
INSERT INTO fertiliser (MorningFlower,3)  VALUES ("Rikkuan 21", 3);
INSERT INTO fertiliser (Rikkuan 21,3)  VALUES ("Sunpic 3", 3);
INSERT INTO fertiliser (Sunpic 3,3)  VALUES ("Luwac 991", 3);
INSERT INTO fertiliser (Luwac 991,3)  VALUES ("Pinocialy56", 3);
INSERT INTO fertiliser (Pinocialy56,3)  VALUES ("Yubako_B", 3);
INSERT INTO fertiliser (Yubako_B,3)  VALUES ("Zinc Manganese Amm Sulfate", 3);
INSERT INTO fertiliser (Zinc Manganese Amm Sulfate,3)  VALUES ("Bone Meal, Raw", 3);
INSERT INTO fertiliser (Bone Meal, Raw,3)  VALUES ("Calcium Metaphosphate", 3);
INSERT INTO fertiliser (Calcium Metaphosphate,3)  VALUES ("Petronas21", 3);
INSERT INTO fertiliser (Petronas21,3)  VALUES ("Zinc Petrusia", 3);
INSERT INTO fertiliser (Zinc Petrusia,3)  VALUES ("Zjies Shaet", 3);
INSERT INTO fertiliser (Zjies Shaet,3)  VALUES ("DragonW31", 3);
INSERT INTO fertiliser (DragonW31,3)  VALUES ("Basic Lime Phosphate", 3);
INSERT INTO fertiliser (Basic Lime Phosphate,3)  VALUES ("Incrusi Ferti Inc. Pe 349", 3);
INSERT INTO fertiliser (Incrusi Ferti Inc. Pe 349,3)  VALUES ("Chong Fa23", 3);
INSERT INTO fertiliser (Chong Fa23,3)  VALUES ("Soybean Meal", 3);
INSERT INTO fertiliser (Soybean Meal,3)  VALUES ("RunBigkals 09-124", 3);
INSERT INTO fertiliser (RunBigkals 09-124,3)  VALUES ("KullMein 31", 3);
INSERT INTO fertiliser (KullMein 31,3)  VALUES ("Dup 1.2-1", 3);

INSERT INTO pesticide (name, unitType)  VALUES ("Baba Mr Ganick Organic Pesticide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Duojunling Plants Fungicide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Armada 1.8EC", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Zagro Fighter 505", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Shamrock Pesticide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("ECPA", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Pirit 1PCS", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Imidacolprad", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Nutri Garlic Oil Plus", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Esfenvalerate (pyrethroid)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Abamectin", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Bacillus thuringiensis (BT—Biologicals),", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Diazinon (organophosphate),", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("PPPs", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Thiodan 35", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Asataf 75", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Superban 20", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Rogor 30", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Metasystox 25", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Curaron 50", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Flash 25", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Tata Alpha 10", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Bulldock 2.5", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Cymbush 25", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Decis 2.8", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Punkaso 10", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Meothrin 30", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Fenitrothion", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Actara 25", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Permethrin (pyrethroid)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Fudan 3G", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Basf Kodetsu", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Fastac BASF", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Confidor 17.8", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Neemazl-F", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Malathion (organophosphate),", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("pyrethrin (botanical),", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Carbaryl (N-methyl carbamate),", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("EcoPest", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Confidor 17.8", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Endosulfan (organochlorine)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Vertimac", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Slyngenta", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Skupantaf 49", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("BayerDecus", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("HectarLavette 455", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("BAYEreagen", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Acmpolita", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Artemisia argyi Wormwood", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("TKL Organic Pesticide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Pennywort Hydrocotyle Verticillata", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Asana XL (esfenvalerate)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Baythroid 2 (cyfluthrin)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("CWQ Plant Pesticide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Cruiser 5FS (thiamethoxam)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Dimethoate 4E (organophosphate)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Gaucho 480 (imidacloprid)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Life Botanic Natural Pesticide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Lorsban 4E (chlorpyrifos)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Mustang Max (pyrethroid)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Nufos 4E (chlorpyrifos)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Warrior (organophosphate)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Acramite (bifenazate)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Baythroid (cyfluthrin)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Dimilin (diflubenzuron)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Fulfill (pymetrozine)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("YUMYUM Pesticide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("MSR (oxydemeton-methyl)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Cygon 400 (dimethoate)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Cythion 57% (malathion)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Diazinon AG500 (organophosphate)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Sevin (carbaryl)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Imidan (phosmet)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Kelthane (dicofol)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Guthion (azinphos methyl)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Vendex (hexakis fenbutatin-oxide)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Lanate (methomyl)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Methoxychlor (methoxychlor)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Provado (imidacloprid)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Thiodan (endosulfan)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Neemix", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Malathion", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Pyrethrins", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Dibrom 8E", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Dipel 2X", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Temik (aldicarb)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("MSR (oxydemeton-methyl)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Venom (dinotefuran)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Zeal (etoxazole)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Imidan 50 WP", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Triadimefon Rust Smut Fungicide", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Methidathion (organophosphate)", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Lannate L", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Lorsban 15 G", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Parathion 4E", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Zolone 3EC", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Metasystox-R", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Acrolein", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Fagdue 45", 2);
INSERT INTO pesticide (name, unitType)  VALUES ("Unfel 67", 2);

INSERT INTO plant (name, unitType)  VALUES ("Eggleaf Silktassel", 1);
INSERT INTO plant (name, unitType)  VALUES ("Lacy Spleenwort", 1);
INSERT INTO plant (name, unitType)  VALUES ("Circumpolar Starwort", 1);
INSERT INTO plant (name, unitType)  VALUES ("Amerorchis", 1);
INSERT INTO plant (name, unitType)  VALUES ("Lesser Canadian St. Johnswort", 1);
INSERT INTO plant (name, unitType)  VALUES ("Bushy Goldentop", 1);
INSERT INTO plant (name, unitType)  VALUES ("Moenkopi Milkvetch", 1);
INSERT INTO plant (name, unitType)  VALUES ("Thorn-mint", 1);
INSERT INTO plant (name, unitType)  VALUES ("Russian Rocket", 1);
INSERT INTO plant (name, unitType)  VALUES ("Quinine", 1);
INSERT INTO plant (name, unitType)  VALUES ("Bluntleaf Waterleaf", 1);
INSERT INTO plant (name, unitType)  VALUES ("Needletip Trumpets", 1);
INSERT INTO plant (name, unitType)  VALUES ("Atlantic Cup Lichen", 1);
INSERT INTO plant (name, unitType)  VALUES ("Longleaf False Goldeneye", 1);
INSERT INTO plant (name, unitType)  VALUES ("Oatgrass", 1);
INSERT INTO plant (name, unitType)  VALUES ("Guara Blanca", 1);
INSERT INTO plant (name, unitType)  VALUES ("San Bernardino Spineflower", 1);
INSERT INTO plant (name, unitType)  VALUES ("Indian Teasel", 1);
INSERT INTO plant (name, unitType)  VALUES ("Diablo Hareleaf", 1);
INSERT INTO plant (name, unitType)  VALUES ("Yunnan Bauhinia", 1);
INSERT INTO plant (name, unitType)  VALUES ("Hybrid Oak", 1);
INSERT INTO plant (name, unitType)  VALUES ("German Iris", 1);
INSERT INTO plant (name, unitType)  VALUES ("Vail Lake Ceanothus", 1);
INSERT INTO plant (name, unitType)  VALUES ("Whiskerbush", 1);
INSERT INTO plant (name, unitType)  VALUES ("Staghorn Fern", 1);
INSERT INTO plant (name, unitType)  VALUES ("Royal Tonguefern", 1);
INSERT INTO plant (name, unitType)  VALUES ("Giant Flatsedge", 1);
INSERT INTO plant (name, unitType)  VALUES ("Summer Farewell", 1);
INSERT INTO plant (name, unitType)  VALUES ("Big Bend Woodyaster", 1);
INSERT INTO plant (name, unitType)  VALUES ("Upright Chickweed", 1);
INSERT INTO plant (name, unitType)  VALUES ("Streambank Springbeauty", 1);
INSERT INTO plant (name, unitType)  VALUES ("Brickellbush", 1);
INSERT INTO plant (name, unitType)  VALUES ("Clay Sand Verbena", 1);
INSERT INTO plant (name, unitType)  VALUES ("Small Onion", 1);
INSERT INTO plant (name, unitType)  VALUES ("Wild Chives", 1);
INSERT INTO plant (name, unitType)  VALUES ("Shield Lichen", 1);
INSERT INTO plant (name, unitType)  VALUES ("Rock Mousetail", 1);
INSERT INTO plant (name, unitType)  VALUES ("Night Scented Orchid", 1);
INSERT INTO plant (name, unitType)  VALUES ("Idaho Blue-eyed Grass", 1);
INSERT INTO plant (name, unitType)  VALUES ("Ballhead Waterleaf", 1);
INSERT INTO plant (name, unitType)  VALUES ("Brown Beaksedge", 1);
INSERT INTO plant (name, unitType)  VALUES ("Oxypetalum", 1);
INSERT INTO plant (name, unitType)  VALUES ("Bill's Neoparrya", 1);
INSERT INTO plant (name, unitType)  VALUES ("Bryoid Fissidens Moss", 1);
INSERT INTO plant (name, unitType)  VALUES ("Pleodendron", 1);
INSERT INTO plant (name, unitType)  VALUES ("Black Gram", 1);
INSERT INTO plant (name, unitType)  VALUES ("Green Medusa Orchid", 1);
INSERT INTO plant (name, unitType)  VALUES ("Alkali Milkvetch", 1);
INSERT INTO plant (name, unitType)  VALUES ("Cottam's Buckwheat", 1);
INSERT INTO plant (name, unitType)  VALUES ("Japanese Elm", 1);
INSERT INTO plant (name, unitType)  VALUES ("Rough Century Plant", 1);
INSERT INTO plant (name, unitType)  VALUES ("Southern Monardella", 1);
INSERT INTO plant (name, unitType)  VALUES ("Woolly Prairie Clover", 1);
INSERT INTO plant (name, unitType)  VALUES ("Maui Melicope", 1);
INSERT INTO plant (name, unitType)  VALUES ("Dot Lichen", 1);
INSERT INTO plant (name, unitType)  VALUES ("Puckhout", 1);
INSERT INTO plant (name, unitType)  VALUES ("Twocleft Stenogyne", 1);
INSERT INTO plant (name, unitType)  VALUES ("Silky Willow", 1);
INSERT INTO plant (name, unitType)  VALUES ("St. Augustine Grass", 1);
INSERT INTO plant (name, unitType)  VALUES ("Vallesia", 1);
INSERT INTO plant (name, unitType)  VALUES ("Greenleaf Five Eyes", 1);
INSERT INTO plant (name, unitType)  VALUES ("Liverleaf Wintergreen", 1);
INSERT INTO plant (name, unitType)  VALUES ("Proliferous Pink", 1);
INSERT INTO plant (name, unitType)  VALUES ("Purplestem Aster", 1);
INSERT INTO plant (name, unitType)  VALUES ("Prickly Bog Sedge", 1);
INSERT INTO plant (name, unitType)  VALUES ("Mate", 1);
INSERT INTO plant (name, unitType)  VALUES ("Dimocarpus", 1);
INSERT INTO plant (name, unitType)  VALUES ("Drummond's Bruchia Moss", 1);
INSERT INTO plant (name, unitType)  VALUES ("Redbird Flower", 1);
INSERT INTO plant (name, unitType)  VALUES ("Toothed Pogonatum Moss", 1);
INSERT INTO plant (name, unitType)  VALUES ("Ring Lichen", 1);
INSERT INTO plant (name, unitType)  VALUES ("Rhexophyllum Moss", 1);
INSERT INTO plant (name, unitType)  VALUES ("Shining Alkaligrass", 1);
INSERT INTO plant (name, unitType)  VALUES ("Swamp Peperomia", 1);
INSERT INTO plant (name, unitType)  VALUES ("Durango Tumblemustard", 1);
INSERT INTO plant (name, unitType)  VALUES ("Jointfir", 1);
INSERT INTO plant (name, unitType)  VALUES ("Clustered Goldenweed", 1);
INSERT INTO plant (name, unitType)  VALUES ("Maidencane", 1);
INSERT INTO plant (name, unitType)  VALUES ("Appalachian Valley Rose", 1);
INSERT INTO plant (name, unitType)  VALUES ("Blueflower Eryngo", 1);
INSERT INTO plant (name, unitType)  VALUES ("Cnicus", 1);
INSERT INTO plant (name, unitType)  VALUES ("Peach Springs Canyon Cholla", 1);
INSERT INTO plant (name, unitType)  VALUES ("Oso Manzanita", 1);
INSERT INTO plant (name, unitType)  VALUES ("Chilean Wormseed", 1);
INSERT INTO plant (name, unitType)  VALUES ("Hairy Jointweed", 1);
INSERT INTO plant (name, unitType)  VALUES ("Pinaleno Mountain Rubberweed", 1);
INSERT INTO plant (name, unitType)  VALUES ("West Indian Porterweed", 1);
INSERT INTO plant (name, unitType)  VALUES ("Fewleaf Sunflower", 1);
INSERT INTO plant (name, unitType)  VALUES ("Kern Mallow", 1);
INSERT INTO plant (name, unitType)  VALUES ("Calabar Bean", 1);
INSERT INTO plant (name, unitType)  VALUES ("Chisos Prairie Acacia", 1);
INSERT INTO plant (name, unitType)  VALUES ("Kentucky Lady's Slipper", 1);
INSERT INTO plant (name, unitType)  VALUES ("Orcutt's Threeawn", 1);
INSERT INTO plant (name, unitType)  VALUES ("San Clemente Island Woodland-star", 1);
INSERT INTO plant (name, unitType)  VALUES ("Engelmann's Hedgehog Cactus", 1);
INSERT INTO plant (name, unitType)  VALUES ("Pink Tickseed", 1);
INSERT INTO plant (name, unitType)  VALUES ("Desert Starvine", 1);
INSERT INTO plant (name, unitType)  VALUES ("Hairy White Oldfield Aster", 1);
INSERT INTO plant (name, unitType)  VALUES ("English Walnut", 1);
INSERT INTO plant (name, unitType)  VALUES ("Cliff Beardtongue", 1);
