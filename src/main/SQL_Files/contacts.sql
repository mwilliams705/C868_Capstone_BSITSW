create table contacts
(
    Contact_ID   int auto_increment
        primary key,
    Contact_Name varchar(50) not null,
    Email        varchar(50) not null
);

create index contact_id
    on contacts (Contact_ID);