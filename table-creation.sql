create schema forum_app;

set search_path to forum_app;

CREATE TABLE user_roles (
    id int generated always as identity primary key,
    role_name varchar not null unique
);

CREATE TABLE app_users (
  id int generated always as identity primary key,
  first_name varchar NOT NULL,
  last_name varchar NOT NULL,
  email varchar unique NOT NULL,
  username varchar unique NOT NULL check(length(username) >= 4),
  password varchar NOT NULL check(length(password) >= 7),
  profile_pic varchar,
  role_id int default 1,
  
  constraint app_users_fk
  foreign key (role_id)
  references user_roles(id)
);

CREATE TABLE categories (
    id int generated always as identity primary key,
    category_name varchar not null unique
);

CREATE TABLE app_posts (
    id int generated always as identity primary key,
    title varchar not null,
    description text not null,
    thumbnail_url varchar,
    video_url varchar,
    likes int default 0,
    dislikes int default 0,
    owner_id int,
    category_id int,
    created_at timestamp default current_timestamp,
    
    constraint appPosts_owner_fk
    foreign key (owner_id)
    references app_users(id),

    constraint appPosts_category_fk
    foreign key (category_id)
    references categories(id)
);