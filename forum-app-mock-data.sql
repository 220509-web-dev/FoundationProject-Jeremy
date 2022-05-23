set search_path to forum_app;

-- Create some base user roles
insert into user_roles (role_name) values ('BASIC_USER'), ('VERIFIED_USER'), ('ADMIN'), ('APP_OWNER'); 

--create a new Forum user
insert into app_users (first_name, last_name, email, username, password, profile_pic, role_id)
values 
    ('Dummy', 'Bot', 'Dummybot@gmail.com', 'DummyUser', 'DummyPassfew', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896084/ForumApp/download_d5mnuu.jpg', 3),
    ('Jeremy', 'Bushay', 'jbushay@gmail.com', 'Sosathegod', 'sosa1242', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896153/ForumApp/download_livdk3.jpg', 4),
    ('Hedwig', 'Maldin', 'hmaldin0@parallels.com', 'hmaldin0', 'FynEu4702MSfewc', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896048/ForumApp/images_qgcwsr.jpg', 2),
    ('Burk', 'Godmer', 'bgodmer1@eventbrite.com', 'bgodmer1', 'g9fnoBefwefgwgw', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896043/ForumApp/images_nbeejd.jpg', 1),
    ('Blakelee', 'Touhig', 'btouhig2@taobao.com', 'btouhig2', 'uIt8iQfewfewRR', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896039/ForumApp/download_ltn8yu.jpg', 1),
    ('Salomi', 'Strangward', 'sstrangward3@nature.com', 'sstrangward3', 'tmyhU2UHfwejEN', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896025/ForumApp/download_oaxwh4.jpg', 2),
    ('Mischa', 'Bowling', 'mbowling4@buzzfeed.com', 'mbowling4', 'CILhLmwfew3Dc', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896022/ForumApp/download_ydvto5.jpg', 4),
    ('See', 'Haymes', 'shaymes5@tinypic.com', 'shaymes5', 'sBkCt8tfewfews', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652896001/ForumApp/download_bbewwa.jpg', 4);

--create some categories for the Forum
insert into categories (category_name) 
values ('Film & Animation'), ('Autos & Vehicles'), ('Music'), ('Pets & Animals'), ('Sports'), ('Travel & Events'), ('Gaming'), ('People & Blogs'), ('News & Politics'), ('Entertainment'), ('Howto & Style'), ('Education'), ('Science & Technology'), ('Nonprofits & Activism');


insert into app_posts (title, descripton, thumbnail_url, video_url, likes, dislikes, owner_id, category_id)
values ('Dummy Post', 'Dummy Post', 'https://res.cloudinary.com/drrkccbb4/image/upload/v1652890771/ForumApp/df2srkc-b2e2aa6b-1d68-4ae9-9743-c8559e50c8b1_dufgso.png', 
'https://res.cloudinary.com/drrkccbb4/video/upload/v1652891689/ForumApp/videoplayback_1_ytdpfb.mp4', 0, 0, 1, 10);

insert into favorites (user_id, post_id)
values (1, 2);