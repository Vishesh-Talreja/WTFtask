CREATE TABLE WTFuser
(
LastName varchar(255),
FirstName varchar(255),
username varchar(255) NOT NULL,
email varchar(255),
password varchar(255),
CONSTRAINT primary_key_user PRIMARY KEY (username)
);

CREATE TABLE WTFtasks
(
TaskID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Taskname varchar(255),
Taskpoints varchar(255),
Duedate date,
CONSTRAINT primary_key_task PRIMARY KEY (TaskID)
);

CREATE TABLE WTFtaskallocation
(
TaskID INTEGER,
username varchar(255),
FOREIGN KEY(TaskID) REFERENCES WTFtasks(TaskID),
FOREIGN KEY(username) REFERENCES WTFuser(username)
);

CREATE TABLE WTFFriends
(
mainusername varchar(255),
friendname varchar(255),
FOREIGN KEY(mainusername) REFERENCES WTFuser(username),
CONSTRAINT primary_key_friends PRIMARY KEY (mainusername,friendname)
);

Alter table WTFtaskallocation
add column status varchar(255)


Alter table WTFtasks
drop column status

UPDATE WTFtaskallocation
SET STATUS='Pending'
WHERE USERNAME="vtalreja" and TASKID=21;

Alter table WTFuser
add column pointearned varchar(255)

Alter table WTFuser
add column pointpossible varchar(255)

Update WTFuser set PointEarned='90',PointPossible='100' where username='vtalreja';
Update WTFuser set PointEarned='70',PointPossible='100' where username='vinaraja';
Update WTFuser set PointEarned='60',PointPossible='100' where username='akanade';

Update WTFuser set PointEarned='64',PointPossible='100' where username='mkothari';


DELETE from WTFuser;
DELETE from WTFtasks;
DELETE from WTFtaskallocation;
DELETE from WTFFriends;

DELETE from WTFuser where USERNAME ='ad';
select * from WTFuser;
select * from WTFtasks;
select * from WTFtaskallocation;
select * FROM WTFFriends;

SELECT STATUS FROM WTFtaskallocation WHERE USERNAME = 'vtalreja' and TASKID = 22

ALTER TABLE WTFuser
DROP COLUMN USERID

ALTER TABLE WTFtasks ADD recur VARCHAR(20) DEFAULT 'none'

DROP TABLE WTFuser;
DROP TABLE WTFtasks;
DROP TABLE WTFtaskallocation;
DROP TABLE WTFFriends;
Delete from WTFtaskallocation


INSERT INTO WTFuser VALUES ('talreja', 'vishesh', 'vtalreja', 'vtalreja@indiana.edu', 'firewaterthunder');
INSERT INTO WTFuser VALUES ('rajagopalan', 'vinay', 'vinaraja', 'vinaraja@indiana.edu', 'firewaterthunder');
INSERT INTO WTFuser VALUES ('kanade', 'aashish', 'akanade', 'akanade@indiana.edu', 'firewaterthunder');

INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,owner) VALUES ('Clean', '50', '09/10/2014', 'vtalreja');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,owner) VALUES ('Cook', '50', '09/10/2014', 'vinaraja');

INSERT INTO WTFtaskallocation VALUES (22,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (23,'akanade','Pending');
INSERT INTO WTFtaskallocation VALUES (24,'vinaraja','Pending');
INSERT INTO WTFtaskallocation VALUES (25,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (26,'vinaraja','Pending');
INSERT INTO WTFtaskallocation VALUES (27,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (28,'akanade','Pending');
INSERT INTO WTFtaskallocation VALUES (29,'vinaraja','Pending');
INSERT INTO WTFtaskallocation VALUES (30,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (31,'akanade','Pending');
INSERT INTO WTFtaskallocation VALUES (33,'vinaraja','Pending');
INSERT INTO WTFFriends VALUES('akanade','vtalreja');
INSERT INTO WTFFriends VALUES('akanade','vinaraja');

