-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dicegamedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dicegamedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dicegamedb` DEFAULT CHARACTER SET utf8 ;
USE `dicegamedb` ;

-- -----------------------------------------------------
-- Table `dicegamedb`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dicegamedb`.`player` (
  `id_player` INT NOT NULL AUTO_INCREMENT,
  `player_name` VARCHAR(45) NOT NULL,
  `registration_date` DATE NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_player`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
