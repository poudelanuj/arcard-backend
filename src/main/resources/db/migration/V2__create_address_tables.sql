create table address
(
  id        bigint auto_increment
    primary key,
  city              varchar(255) null,
  street_address    varchar(255) null,
  state             varchar(255) null,
  country           varchar(255) null,
  user_id_user      bigint       null,
    foreign key (user_id_user) references users (id_user)
      on delete cascade
);