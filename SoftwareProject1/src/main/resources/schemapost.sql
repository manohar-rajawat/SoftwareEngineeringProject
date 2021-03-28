drop table T_POST if exists;

create table T_POST (ID int identity primary key, SUBJECT varchar(20),
                        BODY varchar(50), NUMBER varchar(9), FOREIGN KEY (NUBMER) REFERENCES T_ACCOUNT(NUMBER) );