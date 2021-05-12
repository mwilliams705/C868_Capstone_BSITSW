create table cases(
                      CASE_ID int(10) primary key auto_increment not null ,
                      CASE_TITLE VARCHAR(100) NOT NULL,
                      CASE_DESC VARCHAR(100) NOT NULL ,
                      INCIDENT_DATE DATE NOT NULL ,
                      INTAKE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      CUSTOMER_ID INT(10),
                      CONTACT_ID INT (10),
                      OPPOSING_PARTY VARCHAR(100) not null default 'N/A',
                      OPPOSING_COMPANY VARCHAR(100) not null default 'N/A',
                      CONSTRAINT CUSTOMER_FK FOREIGN KEY (CUSTOMER_ID) REFERENCES customers(Customer_ID),
                      CONSTRAINT CONTACT_FK FOREIGN KEY (CONTACT_ID) REFERENCES contacts(Contact_ID)
)
    CHARSET = utf8mb4;

create index customer_id on cases (CUSTOMER_ID);
create index contact_id on cases (Contact_ID);
