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

Alter table WTFtasks
add column owner varchar(255)

Alter table WTFtasks
add column status varchar(255)

UPDATE WTFtasks
SET STATUS='Pending'
WHERE TASKID=28;

DELETE from WTFuser;
DELETE from WTFtasks;
DELETE from WTFtaskallocation;
DELETE from WTFFriends;

select * from WTFuser;
select * from WTFtasks;
select * from WTFtaskallocation;
select * FROM WTFFriends;

ALTER TABLE WTFuser
DROP COLUMN USERID

DROP TABLE WTFuser
DROP TABLE WTFtasks
DROP TABLE WTFtaskallocation;
DROP TABLE WTFFriends;


INSERT INTO WTFuser VALUES ('talreja', 'vishesh', 'vtalreja', 'vtalreja@indiana.edu', 'firewaterthunder');
INSERT INTO WTFuser VALUES ('rajagopalan', 'vinay', 'vinaraja', 'vinaraja@indiana.edu', 'firewaterthunder');
INSERT INTO WTFuser VALUES ('kanade', 'aashish', 'akanade', 'akanade@indiana.edu', 'firewaterthunder');

INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,owner) VALUES ('Clean', '50', '09/10/2014', 'vtalreja');
INSERT INTO WTFtasks(Taskname,Taskpoints,Duedate,owner) VALUES ('Cook', '50', '09/10/2014', 'vinaraja');

INSERT INTO WTFtaskallocation VALUES (18,'vtalreja');
INSERT INTO WTFtaskallocation VALUES (18,'akanade');
INSERT INTO WTFtaskallocation VALUES (18,'vinaraja');
INSERT INTO WTFtaskallocation VALUES (19,'vtalreja');
INSERT INTO WTFtaskallocation VALUES (19,'vinaraja');

INSERT INTO WTFFriends VALUES('akanade','vtalreja');
INSERT INTO WTFFriends VALUES('akanade','vinaraja');

