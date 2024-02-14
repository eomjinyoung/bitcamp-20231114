-- DDL(Data Definition Language)

drop table if exists boards restrict;
drop table if exists board_files restrict;
drop table if exists assignments restrict;
drop table if exists members restrict;

create table boards(
  board_no int not null,
  title varchar(255) not null,
  content text not null,
  writer varchar(30) not null,
  category int not null,
  created_date datetime null default now()
);

alter table boards
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;

create table board_files(
  file_no int not null,
  file_path varchar(255) not null,
  board_no int not null
);

alter table board_files
  add constraint primary key (file_no),
  modify column file_no int not null auto_increment,
  add constraint board_files_fk foreign key (board_no) references boards(board_no);

create table assignments(
  assignment_no int not null,
  title varchar(255) not null,
  content text not null,
  deadline date not null
);

alter table assignments
  add constraint primary key (assignment_no),
  modify column assignment_no int not null auto_increment;

create table members(
  member_no int not null,
  email varchar(255) not null,
  name varchar(255) not null,
  password varchar(100) not null,
  created_date datetime null default now()
);

alter table members
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;

