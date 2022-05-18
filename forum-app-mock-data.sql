set search_path to forum_app;

-- Create some base user roles
insert into user_roles (name) values ('ADMIN'), ('BASIC_USER'), ('VERIFIED_USER'), ('APP_OWNER'); 

--create a new Forum user
insert into app_users (first_name, last_name, email, username, password, profile_pic, role_id)
values ('Dummy', 'Bot', 'Dummybot@gmail.com', 'DummyUser', 'DummyPass', 
'https://res.cloudinary.com/drrkccbb4/image/upload/v1652890771/ForumApp/df2srkc-b2e2aa6b-1d68-4ae9-9743-c8559e50c8b1_dufgso.png', 4);

--create some categories for the Forum
insert into categories (category_name) 
values ('Film & Animation'), ('Autos & Vehicles'), ('Music'), ('Pets & Animals'), ('Sports'), ('Travel & Events'), ('Gaming'), ('People & Blogs'), ('News & Politics'), ('Entertainment'), ('Howto & Style'), ('Education'), ('Science & Technology'), ('Nonprofits & Activism');


insert into app_posts (title, descripton, thumbnail_url, video_url, likes, dislikes, owner_id, category_id)
values ('Dummy Post', 'Dummy Post', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652890771/ForumApp/df2srkc-b2e2aa6b-1d68-4ae9-9743-c8559e50c8b1_dufgso.png', 
'https://res.cloudinary.com/drrkccbb4/video/upload/v1652891689/ForumApp/videoplayback_1_ytdpfb.mp4', 0, 0, 1, 10);

insert into favorites (user_id, post_id)
values (1, 2);