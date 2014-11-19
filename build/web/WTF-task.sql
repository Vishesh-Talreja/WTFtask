CREATE TABLE WTFuser
(
LastName varchar(255),
FirstName varchar(255),
username varchar(255) NOT NULL,
email varchar(255),
password varchar(255),
pointearned varchar(255),
pointpossible varchar(255),
CONSTRAINT primary_key_user PRIMARY KEY (username)
);

CREATE TABLE WTFtasks
(
TaskID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Taskname varchar(255),
Taskpoints varchar(255),
AllotedTaskpoints varchar (255),
Duedate date,
recur VARCHAR(20) DEFAULT 'none',
owner VARCHAR(100),
CONSTRAINT primary_key_task PRIMARY KEY (TaskID)
);

CREATE TABLE WTFtaskallocation
(
TaskID INTEGER,
username varchar(255) DEFAULT NULL,
status varchar(255),
FOREIGN KEY(TaskID) REFERENCES WTFtasks(TaskID)

);

CREATE TABLE WTFFriends
(
mainusername varchar(255),
friendname varchar(255),
FOREIGN KEY(mainusername) REFERENCES WTFuser(username),
CONSTRAINT primary_key_friends PRIMARY KEY (mainusername,friendname)
);



select * from WTFuser;
select * from WTFtasks;
select * from WTFtaskallocation;
select FRIENDNAME from WTFFriends where Mainusername='akanade';

UPDATE WTFtaskallocation SET status = 'Pending',username='vatlreja'where TaskId=7;

INSERT INTO WTFtasks(Taskname,Taskpoints,AllotedTaskpoints,Duedate,recur,owner) VALUES ('Cook Lunch', '50','0','09/10/2014','none', 'vinaraja');
INSERT INTO WTFtasks(Taskname,Taskpoints,AllotedTaskpoints,Duedate,recur,owner) VALUES ('Lunch', '250','0', '11/12/2014','none', 'vtalreja');
INSERT INTO WTFtasks(Taskname,Taskpoints,AllotedTaskpoints,Duedate,recur,owner) VALUES ('Bathroom Cleaning', '150','0', '12/11/2014','none', 'akanade');
INSERT INTO WTFtasks(Taskname,Taskpoints,AllotedTaskpoints,Duedate,recur,owner) VALUES ('Garbage Disposal', '450','0', '11/12/2014','none', 'akanade');
INSERT INTO WTFtasks(Taskname,Taskpoints,AllotedTaskpoints,Duedate,recur,owner) VALUES ('Cook dinner', '350','0', '11/11/2014','none', 'vinaraja');
INSERT INTO WTFtasks(Taskname,Taskpoints,AllotedTaskpoints,Duedate,recur,owner) VALUES ('Clean Hall', '30','0', '12/2/2014','none', 'akanade');
INSERT INTO WTFtasks(Taskname,Taskpoints,AllotedTaskpoints,Duedate,recur,owner) VALUES ('Cook BreakFast', '70','0', '11/10/2014','none', 'akanade');

DROP TABLE WTFuser;
DROP TABLE WTFtasks;
DROP TABLE WTFtaskallocation;
DROP TABLE WTFFriends;

UPDATE WTFtaskallocation
SET username='null';

UPDATE WTFtaskallocation
SET status = 'Pending' where username='null';

UPDATE WTFuser
SET POINTPOSSIBLE='0';


UPDATE WTFuser
SET POINTEARNED='0';    


DELETE from WTFuser;
DELETE from WTFtasks;
DELETE from WTFtaskallocation;
DELETE from WTFFriends;

select * from WTFuser;
select * from WTFtasks;
select * from WTFtaskallocation;
select * FROM WTFFriends;

DROP TABLE WTFuser;
DROP TABLE WTFtasks;
DROP TABLE WTFtaskallocation;
DROP TABLE WTFFriends;

SELECT STATUS FROM WTFtaskallocation WHERE USERNAME = 'vtalreja' and TASKID = 22

Delete from WTFtaskallocation;
Delete from WTFuser
Delete from WTFtasks
Delete from WTFFriends


INSERT INTO WTFuser VALUES ('talreja', 'vishesh', 'vtalreja', 'vtalreja@indiana.edu', 'firewaterthunder','0','0');
INSERT INTO WTFuser VALUES ('rajagopalan', 'vinay', 'vinaraja', 'vinaraja@indiana.edu', 'firewaterthunder','0','0');
INSERT INTO WTFuser VALUES ('kanade', 'aashish', 'akanade', 'akanade@indiana.edu', 'firewaterthunder','0','0');

INSERT INTO WTFFriends VALUES('akanade','vtalreja');
INSERT INTO WTFFriends VALUES('akanade','vinaraja');
INSERT INTO WTFFriends VALUES('vtalreja','akanade');
INSERT INTO WTFFriends VALUES('vtalreja','vinaraja');
INSERT INTO WTFFriends VALUES('vinaraja','akanade');
INSERT INTO WTFFriends VALUES('vinaraja','vtalreja');

INSERT INTO WTFtaskallocation VALUES (1,'null','Pending');
INSERT INTO WTFtaskallocation VALUES (2,'null','Pending');
INSERT INTO WTFtaskallocation VALUES (3,'null','Pending');
INSERT INTO WTFtaskallocation VALUES (4,'null','Pending');
INSERT INTO WTFtaskallocation VALUES (5,'null','Pending');
INSERT INTO WTFtaskallocation VALUES (6,'null','Pending');
INSERT INTO WTFtaskallocation VALUES (7,'null','Pending');

SELECT COUNT(*) FROM WTFTASKALLOCATION WHERE USERNAME = 'akanade'
