-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema zhadiyem
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema zhadiyem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `zhadiyem` DEFAULT CHARACTER SET utf8 ;
USE `zhadiyem` ;

-- -----------------------------------------------------
-- Table `zhadiyem`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zhadiyem`.`Account` (
  `userId` INT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `dateOfBirth` DATE NOT NULL,
  `sex` VARCHAR(45) NOT NULL,
  `phoneNumber` VARCHAR(45) NOT NULL,
  `adres` VARCHAR(45) NOT NULL,
  `creationDate` DATETIME NULL,
  `picturePath` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `userId_UNIQUE` (`userId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zhadiyem`.`LikeList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zhadiyem`.`LikeList` (
  `idLikeList` INT NOT NULL,
  `likedPerson` VARCHAR(45) NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`idLikeList`, `userId`),
  INDEX `fk_verzinzelf_idx` (`userId` ASC),
  CONSTRAINT `fk_verzinzelf`
    FOREIGN KEY (`userId`)
    REFERENCES `zhadiyem`.`Account` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
