create table roles
(
    id_role   bigint auto_increment
        primary key,
    role_name varchar(60) null,
    constraint UK_nb4h0p6txrmfc0xbrd1kglp9t
        unique (role_name)
);

create table users
(
    id_user        bigint auto_increment
        primary key,
    about_me       varchar(255) null,
    activities     varchar(255) null,
    email          varchar(255) null,
    facebook_link  varchar(255) null,
    first_name     varchar(255) null,
    image_path     varchar(255) null,
    instagram_link varchar(255) null,
    last_name      varchar(255) null,
    linkedin_link  varchar(255) null,
    middle_name    varchar(255) null,
    password       varchar(255) null,
    phone          varchar(255) null,
    projects       varchar(255) null,
    portfolio_link varchar(255) null,
    skills         varchar(255) null,
    twitter_link   varchar(255) null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email)
);

create table address
(
    id             bigint auto_increment
        primary key,
    city           varchar(255) null,
    country        varchar(255) null,
    state          varchar(255) null,
    street_address varchar(255) null,
    user_id_user   bigint       null,
    constraint FKllru83q5c06h1nbo9oe3xhue3
        foreign key (user_id_user) references users (id_user)
);

create table education
(
    id           bigint auto_increment
        primary key,
    degree       varchar(255) null,
    end_time     varchar(255) null,
    institution  varchar(255) null,
    location     varchar(255) null,
    start_time   varchar(255) null,
    user_id_user bigint       null,
    constraint FKd2p6w8q65rs6eo9ax76r9vxbl
        foreign key (user_id_user) references users (id_user)
            on delete cascade
);

create table experience
(
    id           bigint auto_increment
        primary key,
    end_time     varchar(255) null,
    institution  varchar(255) null,
    job_title    varchar(255) null,
    location     varchar(255) null,
    start_time   varchar(255) null,
    user_id_user bigint       null,
    constraint FKrgq1g1nq6l8s3jjrh7joi96ha
        foreign key (user_id_user) references users (id_user)
            on delete cascade
);

create table user_roles
(
    id_user bigint not null,
    id_role bigint not null,
    primary key (id_user, id_role),
    constraint FK1v995xldvmr6w96c5feofx1gf
        foreign key (id_role) references roles (id_role),
    constraint FK9ihrn1kwsu0a99doxpm7jbkdb
        foreign key (id_user) references users (id_user)
);

