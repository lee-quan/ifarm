DROP SCHEMA IF EXISTS ifarm;
CREATE SCHEMA ifarm;

CREATE TABLE `ifarm`.`unit_type` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `unitType` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`_id`));


INSERT INTO `ifarm`.unit_type (unitType)  VALUES ('mass');
INSERT INTO `ifarm`.unit_type (unitType)  VALUES ('pack');
INSERT INTO `ifarm`.unit_type (unitType)  VALUES ('volume');

CREATE TABLE `ifarm`.`plant` (
  `_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `unitType` INT NOT NULL,
  PRIMARY KEY (`_id`),
  INDEX `plant_unitType_idx` (`unitType` ASC) VISIBLE,
  CONSTRAINT `plant_unitType`
    FOREIGN KEY (`unitType`)
    REFERENCES `ifarm`.`unit_type` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `ifarm`.`fertiliser` (
  `_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `unitType` INT NOT NULL,
  PRIMARY KEY (`_id`),
  INDEX `fertilizer_unitType_idx` (`unitType` ASC) VISIBLE,
  CONSTRAINT `fertilizer_unitType`
    FOREIGN KEY (`unitType`)
    REFERENCES `ifarm`.`unit_type` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `ifarm`.`pesticide` (
  `_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `unitType` INT NOT NULL,
  PRIMARY KEY (`_id`),
  INDEX `pesticide_unitType_idx` (`unitType` ASC) VISIBLE,
  CONSTRAINT `pesticide_unitType`
    FOREIGN KEY (`unitType`)
    REFERENCES `ifarm`.`unit_type` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
    INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("1","Eggleaf Silktassel", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("2","Lacy Spleenwort", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("3","Circumpolar Starwort", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("4","Amerorchis", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("5","Lesser Canadian St. Johnswort", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("6","Bushy Goldentop", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("7","Moenkopi Milkvetch", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("8","Thorn-mint", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("9","Russian Rocket", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("10","Quinine", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("11","Bluntleaf Waterleaf", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("12","Needletip Trumpets", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("13","Atlantic Cup Lichen", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("14","Longleaf False Goldeneye", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("15","Oatgrass", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("16","Guara Blanca", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("17","San Bernardino Spineflower", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("18","Indian Teasel", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("19","Diablo Hareleaf", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("20","Yunnan Bauhinia", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("21","Hybrid Oak", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("22","German Iris", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("23","Vail Lake Ceanothus", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("24","Whiskerbush", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("25","Staghorn Fern", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("26","Royal Tonguefern", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("27","Giant Flatsedge", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("28","Summer Farewell", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("29","Big Bend Woodyaster", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("30","Upright Chickweed", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("31","Streambank Springbeauty", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("32","Brickellbush", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("33","Clay Sand Verbena", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("34","Small Onion", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("35","Wild Chives", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("36","Shield Lichen", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("37","Rock Mousetail", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("38","Night Scented Orchid", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("39","Idaho Blue-eyed Grass", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("40","Ballhead Waterleaf", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("41","Brown Beaksedge", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("42","Oxypetalum", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("43","Bill's Neoparrya", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("44","Bryoid Fissidens Moss", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("45","Pleodendron", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("46","Black Gram", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("47","Green Medusa Orchid", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("48","Alkali Milkvetch", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("49","Cottam's Buckwheat", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("50","Japanese Elm", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("51","Rough Century Plant", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("52","Southern Monardella", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("53","Woolly Prairie Clover", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("54","Maui Melicope", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("55","Dot Lichen", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("56","Puckhout", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("57","Twocleft Stenogyne", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("58","Silky Willow", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("59","St. Augustine Grass", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("60","Vallesia", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("61","Greenleaf Five Eyes", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("62","Liverleaf Wintergreen", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("63","Proliferous Pink", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("64","Purplestem Aster", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("65","Prickly Bog Sedge", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("66","Mate", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("67","Dimocarpus", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("68","Drummond's Bruchia Moss", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("69","Redbird Flower", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("70","Toothed Pogonatum Moss", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("71","Ring Lichen", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("72","Rhexophyllum Moss", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("73","Shining Alkaligrass", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("74","Swamp Peperomia", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("75","Durango Tumblemustard", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("76","Jointfir", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("77","Clustered Goldenweed", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("78","Maidencane", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("79","Appalachian Valley Rose", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("80","Blueflower Eryngo", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("81","Cnicus", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("82","Peach Springs Canyon Cholla", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("83","Oso Manzanita", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("84","Chilean Wormseed", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("85","Hairy Jointweed", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("86","Pinaleno Mountain Rubberweed", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("87","West Indian Porterweed", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("88","Fewleaf Sunflower", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("89","Kern Mallow", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("90","Calabar Bean", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("91","Chisos Prairie Acacia", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("92","Kentucky Lady's Slipper", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("93","Orcutt's Threeawn", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("94","San Clemente Island Woodland-star", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("95","Engelmann's Hedgehog Cactus", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("96","Pink Tickseed", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("97","Desert Starvine", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("98","Hairy White Oldfield Aster", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("99","English Walnut", 1);
INSERT INTO `ifarm`.plant (_id, name, unitType)  VALUES ("100","Cliff Beardtongue", 1);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("1","Baba Mr Ganick Organic Pesticide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("2","Duojunling Plants Fungicide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("3","Armada 1.8EC", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("4","Zagro Fighter 505", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("5","Shamrock Pesticide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("6","ECPA", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("7","Pirit 1PCS", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("8","Imidacolprad", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("9","Nutri Garlic Oil Plus", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("10","Esfenvalerate (pyrethroid)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("11","Abamectin", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("12","Bacillus thuringiensis (BT—Biologicals),", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("13","Diazinon (organophosphate),", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("14","PPPs", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("15","Thiodan 35", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("16","Asataf 75", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("17","Superban 20", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("18","Rogor 30", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("19","Metasystox 25", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("20","Curaron 50", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("21","Flash 25", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("22","Tata Alpha 10", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("23","Bulldock 2.5", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("24","Cymbush 25", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("25","Decis 2.8", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("26","Punkaso 10", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("27","Meothrin 30", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("28","Fenitrothion", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("29","Actara 25", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("30","Permethrin (pyrethroid)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("31","Fudan 3G", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("32","Basf Kodetsu", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("33","Fastac BASF", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("34","Confidor 17.8", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("35","Neemazl-F", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("36","Malathion (organophosphate),", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("37","pyrethrin (botanical),", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("38","Carbaryl (N-methyl carbamate),", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("39","EcoPest", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("40","Confidor 17.8", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("41","Endosulfan (organochlorine)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("42","Vertimac", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("43","Slyngenta", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("44","Skupantaf 49", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("45","BayerDecus", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("46","HectarLavette 455", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("47","BAYEreagen", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("48","Acmpolita", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("49","Artemisia argyi Wormwood", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("50","TKL Organic Pesticide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("51","Pennywort Hydrocotyle Verticillata", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("52","Asana XL (esfenvalerate)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("53","Baythroid 2 (cyfluthrin)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("54","CWQ Plant Pesticide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("55","Cruiser 5FS (thiamethoxam)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("56","Dimethoate 4E (organophosphate)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("57","Gaucho 480 (imidacloprid)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("58","Life Botanic Natural Pesticide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("59","Lorsban 4E (chlorpyrifos)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("60","Mustang Max (pyrethroid)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("61","Nufos 4E (chlorpyrifos)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("62","Warrior (organophosphate)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("63","Acramite (bifenazate)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("64","Baythroid (cyfluthrin)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("65","Dimilin (diflubenzuron)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("66","Fulfill (pymetrozine)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("67","YUMYUM Pesticide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("68","MSR (oxydemeton-methyl)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("69","Cygon 400 (dimethoate)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("70","Cythion 57% (malathion)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("71","Diazinon AG500 (organophosphate)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("72","Sevin (carbaryl)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("73","Imidan (phosmet)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("74","Kelthane (dicofol)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("75","Guthion (azinphos methyl)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("76","Vendex (hexakis fenbutatin-oxide)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("77","Lanate (methomyl)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("78","Methoxychlor (methoxychlor)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("79","Provado (imidacloprid)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("80","Thiodan (endosulfan)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("81","Neemix", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("82","Malathion", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("83","Pyrethrins", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("84","Dibrom 8E", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("85","Dipel 2X", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("86","Temik (aldicarb)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("87","MSR (oxydemeton-methyl)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("88","Venom (dinotefuran)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("89","Zeal (etoxazole)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("90","Imidan 50 WP", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("91","Triadimefon Rust Smut Fungicide", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("92","Methidathion (organophosphate)", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("93","Lannate L", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("94","Lorsban 15 G", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("95","Parathion 4E", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("96","Zolone 3EC", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("97","Metasystox-R", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("98","Acrolein", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("99","Fagdue 45", 2);
INSERT INTO `ifarm`.pesticide (_id, name, unitType)  VALUES ("100","Unfel 67", 2);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('1','Ammonium Polyphosphate (POLY11)', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('2','Calcium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('3','Eggshells', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('4','Ammonium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('5','Urea', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('6','Ammonium Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('7','Diammonium Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('8','Monoammonium phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('9','Triple Super Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('10','Potassium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('11','Potassium Chloride', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('12','Anhydrous Ammonia', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('13','Aqua Ammonia', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('14','Ammonium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('15','Ammonium Nitrate Solution', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('16','Amm Nitrate-Limestone Mixtures', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('17','Ammonium Nitrate - Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('18','Ammonium Polysulfide', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('19','Ammonium Thiosulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('20','Calcium Ammonium Nitrate 1', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('21','Calcium Cyanamide', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('22','Calcium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('23','Calcium Nitrate - Urea', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('24','Ferrous Ammonium Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('25','Magnesium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('26','Nitric Acid', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('27','Sodium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('28','Sulfur Coated Urea', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('29','Urea', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('30','cwqDimethoa', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('31','Zinc Ammonium Sulfate Solution', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('32','Zinc Manganese Amm Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('33','Nitrogen Product', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('34','Nitrogen', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('35','SevinQian', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('36','Kelthweiane', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('37','Ammonium Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('38','GuthionChongi 4', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('39','Diammonium Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('40','Basic Lime Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('41','Ammonium Phosphate Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('42','Ammonium Phosphate Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('43','Basic Slag', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('44','Monoammonium Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('45','Warrior Chong', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('46','Bone Meal, Steamed', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('47','Calcium Metaphosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('48','Bone, Precipitated', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('49','Limestone, Phosphatic', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('50','Magnesium Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('51','Nitric Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('52','Nufos 4Ecwq', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('53','Phosphoric Acid', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('54','Liquid Amm Polyphosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('55','Precipitated Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('56','Cwqgon 400', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('57','Superphosphate, Enriched', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('58','Manure Salts', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('59','Pymetrodun', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('60','Potassium Nitrate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('61','Potassium Carbonate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('62','Guano', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('63','Hexakis 33', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('64','Aluminum Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('65','Cobalt Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('66','Dipel 2X', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('67','Ferric Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('68','Manganese Slag', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('69','Manganese Oxide', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('70','MSR', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('71','Manganese Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('72','Soil Amendment', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('73','Zinc Oxysulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('74','Keogn Orien', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('75','Thiodan', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('76','Zjies Shaet', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('77','QianZeal', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('78','ImidanSeal', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('79','LannateL', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('80','Norm 188', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('81','MorningFlower', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('82','Rikkuan 21', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('83','Sunpic 3', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('84','Luwac 991', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('85','Pinocialy56', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('86','Yubako_B', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('87','Zinc Manganese Amm Sulfate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('88','Bone Meal, Raw', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('89','Calcium Metaphosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('90','Petronas21', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('91','Zinc Petrusia', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('92','Zjies Shaet', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('93','DragonW31', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('94','Basic Lime Phosphate', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('95','Incrusi Ferti Inc. Pe 349', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('96','Chong Fa23', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('97','Soybean Meal', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('98','RunBigkals 09-124', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('99','KullMein 31', 3);
INSERT INTO `ifarm`.fertiliser (_id, name,unitType)  VALUES ('100','Dup 1.2-1', 3);

CREATE TABLE `ifarm`.`farm` (
  `_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `plants` VARCHAR(45) NULL,
  `fertilizers` VARCHAR(45) NULL,
  `pesticides` VARCHAR(45) NULL,
  PRIMARY KEY (`_id`));

INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("1", "Cherry Blossom Estate", "Ap #553-3731 Sit Rd");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("2", "Horseshoe Grang", "972-7058 Pellentesque Rd.");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("3", "Two Pines Estate", "P.O. Box 146, 9149 Hymenaeos. Ave");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("4", "Moonshadow Farms", "Ap #196-5848 Quisque Rd.");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("5", "Mossy Oak Vineyard", "Ap #341-4012 Proin Road");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("6", "Meadowgrove Gardens", "P.O. Box 109, 1752 Tempus Street");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("7", "White Oak Acres", "821 Dolor Ave");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("8", "Bittersweet Lands", "Ap #153-1787 Mollis. Ave");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("9", "Windy Oaks Fields", "764-1552 In, Road");
INSERT INTO `ifarm`.farm (_id, name, address)  VALUES ("10", "Rattlesnake Ranch", "P.O. Box 279, 8717 Id Road");

CREATE TABLE `ifarm`.`users` (
  `_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `farm` VARCHAR(45) NULL,
  PRIMARY KEY (`_id`));

INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("1","Marcille Berthomieu", "mberthomieu0@fc2.com","Pc97yP0","2447014408");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("2","Chrotoem Alfonzo", "calfonzo1@paypal.com","enkFFqY0cB","9001803118");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("3","Rafaelia Liddel", "rliddel2@cargocollective.com","MMwyT2x5p","3803680022");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("4","Avrit Norcliffe", "anorcliffe3@paypal.com","EK7FyqHN4k31","2243262065");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("5","Job Circuitt", "jcircuitt4@xinhuanet.com","rM2IVvWBb","3291791565");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("6","Rouvin Gollard", "rgollard5@salon.com","giIqSJfxnte","4178211489");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("7","Caria Flay", "cflay6@yale.edu","TpZrKOdVYl6e","5954450414");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("8","Carmen Frenzel;", "cfrenzel7@uol.com.br","7n2BPX2","5108764012");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("9","Courtnay Dugood", "cdugood8@mayoclinic.com","by9HKKOPs","9828379079");
INSERT INTO `ifarm`.users (_id, name,email,password,phoneNumber)  VALUES ("10","Amandie Stife", "astife9@hud.gov","laeLRim","6597619213");