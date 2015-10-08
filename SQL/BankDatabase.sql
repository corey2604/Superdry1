
create database BankDB;
use BankDB

create table BankAccount(BankAccountNo int primary key auto_increment, Forename varchar(20), Surname varchar(20), AccountTypeID int, Savings decimal(8,2));

create table AccountType(AccountTypeID int primary key auto_increment, AccountType varchar(20), OverdraftLimit decimal(8,2), DeductionCharge decimal(4,2));

alter table BankAccount add constraint fk_TypeID foreign key(AccountTypeID) references AccountType(AccountTypeID); 
