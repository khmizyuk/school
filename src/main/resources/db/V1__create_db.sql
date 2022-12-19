create table place(
    id varchar(255) not null,
    claster varchar(255) not null,
    row_letter varchar(1) not null,
    number int not null,
    constraint place_pk primary key (id)
);

create table project(
    id varchar(255) not null,
    description text not null,
    subject text not null,
    check_list text  not null,
    experience_points int not null,
    weight int not null,
    constraint project_pk primary key (id)
);

create table campus(
    id varchar(255) not null,
    name varchar(255) not null,
    city varchar(255) not null,
    country varchar(255) not null,
    constraint campus_pk primary key (id)
);

create table coalition(
    id varchar(255) not null,
    name varchar(255) not null,
    score int not null,
    constraint coalition_pk primary key (id)
);

create table making_projects(
    student_id varchar(255) not null,
    project_id varchar(255) not null,
    primary key (student_id, project_id)
);

create table completed_projects
(
    student_id varchar(255) not null,
    project_id varchar(255) not null,
    primary key (student_id, project_id)
);

create table students_titles(
    student_id varchar(255) not null,
    title_id varchar(255) not null,
    primary key (student_id, title_id)
);

create table title(
    id varchar(255) not null,
    name varchar(255) not null,
    description text not null,
    constraint title_pk primary key (id)
);

create table student(
    id               varchar(255) not null,
    first_name       varchar(255) not null,
    last_name        varchar(255) not null,
    email            varchar(255) not null,
    password         varchar(255) not null,
    experience_point integer      not null default 0,
    allowed_project  integer      not null default 1,
    place_id         varchar(255)   references place (id) ON UPDATE CASCADE ON DELETE CASCADE,
    coalition_id     varchar(255) not null references coalition (id) ON UPDATE CASCADE ON DELETE CASCADE,
    campus_id        varchar(255) not null references campus (id) ON UPDATE CASCADE ON DELETE CASCADE,
    constraint student_pk primary key (id)
);

create table staff(
    id               varchar(255) not null,
    first_name       varchar(255) not null,
    last_name        varchar(255) not null,
    email            varchar(255) not null,
    password         varchar(255) not null,
    coalition_id     varchar(255) not null references coalition (id) ON UPDATE CASCADE ON DELETE CASCADE,
    campus_id        varchar(255) not null references campus (id) ON UPDATE CASCADE ON DELETE CASCADE,
    constraint student_pk primary key (id)
);

-- @GeneratedValue(strategy = GenerationType.AUTO)