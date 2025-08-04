create table topics(

    id bigint not null auto_increment,
    title varchar(150) not null unique,
    message varchar(350) not null,
    creation_date datetime not null ,
    status tinyint not null,
    user_id bigint not null,
    course_id bigint not null,
    primary key(id),
    constraint fk_topics_user_id foreign key(user_id) references users(id),
    constraint fk_topics_course_id foreign key(course_id) references courses(id)
);