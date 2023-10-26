create table posts
(
    id              uuid,
    account_id      uuid,
    title           varchar(30) not null,
    description     text        not null,
    status          varchar(20) not null default ('NOT STARTED'),
    publishing_time date        not null,
    price           int         not null default (0),
    paths_to_photos text[] not null
);

alter table posts
    add constraint posts_id_primarykey_constraint primary key (id);
alter table posts
    add constraint posts_accountid_foreignkey_constraint foreign key (account_id) REFERENCES accounts (id);
alter table posts
    add constraint posts_status_checkvalid_constraint check (status = 'EXPIRED' or status = 'NOT STARTED');
ALTER TABLE posts
    ADD CONSTRAINT posts_price_checkvalid_constraint CHECK (price >= 0);
