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
Duedate date,
recur VARCHAR(20) DEFAULT 'none',
owner VARCHAR(100),
CONSTRAINT primary_key_task PRIMARY KEY (TaskID)
);

CREATE TABLE WTFtaskallocation
(
TaskID INTEGER,
username varchar(255),
status varchar(255),
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



select * from WTFuser;
select * from WTFtasks;
select * from WTFtaskallocation;
select FRIENDNAME from WTFFriends where Mainusername='akanade';

INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Clean', '50', '09/10/2014','none', 'vtalreja');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Cook', '50', '09/10/2014','none', 'vinaraja');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Lunch', '250', '11/12/2014','none', 'vtalreja');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Bathroom Cleaning', '150', '12/11/2014','none', 'akanade');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Garbage Disposal', '450', '11/12/2014','none', 'akanade');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Cook dinner', '350', '11/11/2014','none', 'vinaraja');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Clean hall', '30', '12/2/2014','none', 'akanade');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,recur,owner) VALUES ('Cook BreakFast', '70', '11/10/2014','none', 'akanade');

DROP TABLE WTFuser;
DROP TABLE WTFtasks;
DROP TABLE WTFtaskallocation;
DROP TABLE WTFFriends;

UPDATE WTFtaskallocation
SET STATUS='Pending'
WHERE USERNAME="vtalreja" and TASKID=21;

UPDATE WTFuser
SET POINTEARNED='0'
WHERE USERNAME='';

UPDATE WTFuser
SET POINTPOSSIBLE='680'
WHERE username = 'vtalreja';


UPDATE WTFuser
SET POINTEARNED='0';    


DELETE from WTFuser;
DELETE from WTFtasks;
DELETE from WTFtaskallocation where TASKID=9;
DELETE from WTFFriends;

DELETE from WTFuser where USERNAME ='ad';
select * from WTFuser;
select * from WTFtasks;
select * from WTFtaskallocation;
select * FROM WTFFriends;

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

INSERT INTO WTFtaskallocation VALUES (1,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (2,'akanade','Pending');
INSERT INTO WTFtaskallocation VALUES (18,'vinaraja','Pending');
INSERT INTO WTFtaskallocation VALUES (4,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (5,'vinaraja','Pending');
INSERT INTO WTFtaskallocation VALUES (5,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (6,'akanade','Pending');
INSERT INTO WTFtaskallocation VALUES (7,'vinaraja','Pending');
INSERT INTO WTFtaskallocation VALUES (7,'vtalreja','Pending');
INSERT INTO WTFtaskallocation VALUES (8,'akanade','Pending');
INSERT INTO WTFtaskallocation VALUES (8,'vinaraja','Pending');


SELECT COUNT(*) FROM WTFTASKALLOCATION WHERE USERNAME = 'akanade'
