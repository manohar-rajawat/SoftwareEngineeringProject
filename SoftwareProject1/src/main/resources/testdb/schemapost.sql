drop table T_POST if exists;

create table T_POST (ID bigint identity primary key, SUBJECT varchar(20),
                        BODY varchar(50));