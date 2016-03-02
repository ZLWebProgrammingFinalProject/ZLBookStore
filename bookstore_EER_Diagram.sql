-- MySQL Script generated by MySQL Workbench
-- Tue Mar  1 16:31:25 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Customer` (
  `username` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `idCustomer_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Books` (
  `idProduct` INT NULL,
  `price` DECIMAL(5,2) NULL,
  `category` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  `published year` YEAR NULL,
  `amountInventory` INT NULL,
  PRIMARY KEY (`idProduct`),
  UNIQUE INDEX `idProduct_UNIQUE` (`idProduct` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Transactions` (
  `idTransactions` INT NOT NULL,
  `date` DATE NULL,
  `price` DECIMAL(5,2) NULL,
  `quantity` INT NULL,
  `Customer_username` VARCHAR(45) NOT NULL,
  `Books_idProduct` INT NOT NULL,
  PRIMARY KEY (`idTransactions`, `Customer_username`, `Books_idProduct`),
  INDEX `fk_Transactions_Customer1_idx` (`Customer_username` ASC),
  INDEX `fk_Transactions_Books1_idx` (`Books_idProduct` ASC),
  CONSTRAINT `fk_Transactions_Customer1`
    FOREIGN KEY (`Customer_username`)
    REFERENCES `mydb`.`Customer` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactions_Books1`
    FOREIGN KEY (`Books_idProduct`)
    REFERENCES `mydb`.`Books` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Rating` (
  `idRating` INT NULL,
  `date` TIMESTAMP(0) NULL,
  `Rating` INT NULL,
  `Customer_username` VARCHAR(45) NOT NULL,
  `Books_idProduct` INT NOT NULL,
  PRIMARY KEY (`idRating`, `Customer_username`, `Books_idProduct`),
  INDEX `fk_Customer_to_Book_Rating_Customer_idx` (`Customer_username` ASC),
  INDEX `fk_Customer_to_Book_Rating_Books1_idx` (`Books_idProduct` ASC),
  CONSTRAINT `fk_Customer_to_Book_Rating_Customer`
    FOREIGN KEY (`Customer_username`)
    REFERENCES `mydb`.`Customer` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_to_Book_Rating_Books1`
    FOREIGN KEY (`Books_idProduct`)
    REFERENCES `mydb`.`Books` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;