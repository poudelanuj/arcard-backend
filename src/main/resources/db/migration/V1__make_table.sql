create table address
(
  id             bigint auto_increment
    primary key,
  city           varchar(255) null,
  country        varchar(255) null,
  pin_code       varchar(255) null,
  state          varchar(255) null,
  street_address varchar(255) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table roles
(
  id_role   bigint auto_increment
    primary key,
  role_name varchar(60) null,
  constraint UK_epk9im9l9q67xmwi4hbed25do
    unique (role_name),
  constraint UK_nb4h0p6txrmfc0xbrd1kglp9t
    unique (role_name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table user_description
(
  id                   bigint auto_increment
    primary key,
  hobby                varchar(255) null,
  professional_details varchar(255) null,
  skills               varchar(255) null,
  summary              varchar(255) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;;

create table users
(
  id_user             binary(16)   not null
    primary key,
  email               varchar(255) not null,
  password            varchar(255) null,
  facebook_link       varchar(255) null,
  first_name          varchar(255) null,
  instagram_link      varchar(255) null,
  last_name           varchar(255) null,
  linkedin_link       varchar(255) null,
  phone               varchar(255) null,
  twitter_link        varchar(255) null,
  address_id          bigint       null,
  user_description_id bigint       null,
  constraint UK2jg5d0b9dx236rtll2uylnv7a
    unique (email),
  constraint FK6516o2ejwmjihid32aheccneq
    foreign key (user_description_id) references user_description (id),
  constraint FKditu6lr4ek16tkxtdsne0gxib
    foreign key (address_id) references address (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table user_roles
(
  id_user binary(16) not null,
  id_role bigint not null,
  primary key (id_user, id_role),
  constraint FKt3ccjnypjbg03cawlifd4pod7
    foreign key (id_user) references users (id_user)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

