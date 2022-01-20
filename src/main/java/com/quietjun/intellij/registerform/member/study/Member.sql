CREATE TABLE boardMember(
    Member_id VARCHAR2(15) PRIMARY KEY NOT NULL,
    Member_pw VARCHAR2(15),
    Member_name VARCHAR2(15),
    Member_tel VARCHAR2(11),
    Member_email VARCHAR2(30),
    Member_gender VARCHAR2(5),
    Member_interest VARCHAR2(30),
    Member_postCode NUMBER,
    Member_address VARCHAR2(90),
    Member_detailAddress VARCHAR2(50),
    Member_reference VARCHAR2(50),
    Member_birth datetime,
);

SELECT * FROM boardMember;