CREATE TABLE chatroom (
    chatroomno number(3) primary key,
    chatroomname varchar(20) not null,
    chatroomowner varchar(50) not null,
    chatroommem varchar(50) not null
);

CREATE SEQUENCE chatroom_seq;

INSERT INTO chatroom(chatroomno, chatroomname, chatroomowner, chatroommem)
VALUES(chatroom_seq, ?, ?, ?);