create table pi_cases
(
    CASE_ID        int auto_increment
        primary key,
    CASE_TITLE     varchar(100)                           not null,
    CASE_DESC      varchar(500)                           not null,
    INCIDENT_DATE  date                                   not null,
    INTAKE_DATE    timestamp    default CURRENT_TIMESTAMP not null,
    CUSTOMER_ID    int                                    null,
    CONTACT_ID     int                                    null,
    OPPOSING_PARTY varchar(100) default 'John Doe'        not null,
    constraint CONTACT_PI_CASE_FK
        foreign key (CONTACT_ID) references contacts (Contact_ID),
    constraint CUSTOMER_PI_CASE_FK
        foreign key (CUSTOMER_ID) references customers (Customer_ID)
);

create index contact_id
    on pi_cases (CONTACT_ID);

create index customer_id
    on pi_cases (CUSTOMER_ID);