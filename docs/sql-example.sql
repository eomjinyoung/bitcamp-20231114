drop table if exists x_member;
drop table if exists x_study;
drop table if exists x_study_member;
drop table if exists x_curriculum;

create table x_member (
  mno int primary key,
  name varchar(50) not null
);

create table x_study (
  sno int primary key,
  title varchar(50) not null
);

create table x_study_member (
  mno int not null,
  sno int not null
);

alter table x_study_member
  add constraint primary key (mno, sno),
  add constraint x_study_member_mno_fk foreign key (mno) references x_member(mno),
  add constraint x_study_member_sno_fk foreign key (sno) references x_study(sno);

insert into x_member values (1, '홍길동');
insert into x_member values (2, '임꺽정');
insert into x_member values (3, '유관순');
insert into x_member values (4, '안중근');

insert into x_study values (101, '자바');
insert into x_study values (102, 'HTML');
insert into x_study values (103, '파이썬');

insert into x_study_member values(1, 101);
insert into x_study_member values(2, 101);
insert into x_study_member values(3, 101);
insert into x_study_member values(1, 102);
insert into x_study_member values(2, 102);
insert into x_study_member values(3, 102);
insert into x_study_member values(4, 102);

create table x_curriculum (
  cno int primary key,
  title varchar(255) not null,
  sno int,
  mno int
);

alter table x_curriculum 
  add constraint x_curriculum_sno_fk foreign key (sno) references x_study (sno),
  add constraint x_curriculum_mno_fk foreign key (sno, mno) references x_study_member (sno, mno);
  

insert into x_curriculum(cno, title, sno) values (11, '변수', 101);
insert into x_curriculum(cno, title, sno) values (12, '조건문', 101);
insert into x_curriculum(cno, title, sno) values (13, '반복문', 101);
insert into x_curriculum(cno, title, sno) values (14, '연산자', 101);

update x_curriculum set
  mno = 2
where cno = 14;