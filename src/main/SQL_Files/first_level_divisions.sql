create table first_level_divisions
(
    Division        varchar(100)                       not null,
    Division_ID     int auto_increment
        primary key,
    Create_Date     datetime default CURRENT_TIMESTAMP null,
    Created_By      varchar(100)                       null,
    Last_Update     datetime default CURRENT_TIMESTAMP null,
    Last_Updated_By varchar(100)                       null,
    Country_ID      int                                not null,
    constraint first_level_divisions_ibfk_1
        foreign key (Country_ID) references countries (Country_ID)
);