create table wc_cases
(
    CASE_ID          int auto_increment
        primary key,
    CASE_TITLE       varchar(100)                           not null,
    CASE_DESC        varchar(500)                           not null,
    INCIDENT_DATE    date                                   not null,
    INTAKE_DATE      timestamp    default CURRENT_TIMESTAMP not null,
    CUSTOMER_ID      int                                    null,
    CONTACT_ID       int                                    null,
    OPPOSING_COMPANY varchar(100) default 'Unknown Co.'     not null,
    constraint CONTACT_WC_CASE_FK
        foreign key (CONTACT_ID) references contacts (Contact_ID),
    constraint CUSTOMER_WC_CASE_FK
        foreign key (CUSTOMER_ID) references customers (Customer_ID)
);

create index contact_id
    on wc_cases (CONTACT_ID);

create index customer_id
    on wc_cases (CUSTOMER_ID);