create table favorite_posts
(
    account_id uuid,
    post_id    uuid
);

alter table favorite_posts
    add constraint favoriteposts_accountid_primarykey_constraint primary key (account_id);
alter table favorite_posts
    add constraint favoriteposts_postid_foreignkey_constraint foreign key (post_id) REFERENCES posts;
