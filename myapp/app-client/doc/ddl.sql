-- DDL(Data Definition Language)

drop table boards;

create table boards(
  board_no int primary key auto_increment,
  title varchar(255) not null,
  content text not null,
  writer varchar(30) not null,
  created_date datetime null default now()
);

insert into boards(board_no,title,content,writer) 
  values(1, '제목1','내용1','홍길동');
insert into boards(board_no,title,content,writer) 
  values(2, '제목2','내용2','임꺽정');
insert into boards(board_no,title,content,writer) 
  values(3, '제목3','내용3','유관순');
insert into boards(board_no,title,content,writer) 
  values(4, '제목4','내용4','안중근');
insert into boards(board_no,title,content,writer) 
  values(5, '제목5','내용5','윤봉길');

select * 
from boards;

select * 
from boards 
where board_no = 3;

update boards set 
  title='okok', 
  content='nono', 
  writer='hoho' 
where board_no = 3;

delete from boards where board_no=3;


drop table assignments;

create table assignments(
  assignment_no int primary key auto_increment,
  title varchar(255) not null,
  content text not null,
  deadline date not null
);

insert into assignments(assignment_no,title,content,deadline) 
  values(1,'과제1','내용1','2024-1-1');
insert into assignments(assignment_no,title,content,deadline) 
  values(2,'과제2','내용2','2024-2-2');
insert into assignments(assignment_no,title,content,deadline) 
  values(3,'과제3','내용3','2024-3-3');
insert into assignments(assignment_no,title,content,deadline) 
  values(4,'과제4','내용4','2024-4-4');
insert into assignments(assignment_no,title,content,deadline) 
  values(5,'과제5','내용5','2024-5-5');