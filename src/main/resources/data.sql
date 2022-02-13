/**멤버**/
INSERT INTO "MEMBER"(MEMBER_ID, EMAIL, NICKNAME, PASSWORD) VALUES(1, 'gyub1@naver.com', 'GYub1', '$2a$10$V1jxabg3G4/N239zKGONZ.fyROwELG1gtCzBH5x2P0aiiiELiodxy');
INSERT INTO "MEMBER"(MEMBER_ID, EMAIL, NICKNAME, PASSWORD) VALUES(2, 'gyub2@naver.com', 'GYub2', '$2a$10$7ANZZmMY8rDTr6F81GNBquHhF4KwbALdttcUdKWbno.30rp/VW1He');
INSERT INTO "MEMBER"(MEMBER_ID, EMAIL, NICKNAME, PASSWORD) VALUES(3, 'gyub3@kakao.com', 'GYub3', '$2a$10$7ANZZmMY8rDTr6F81GNBquHhF4KwbALdttcUdKWbno.30rp/VW1He');

/**가계부**/
INSERT INTO ACCOUNT(ACCOUNT_ID, CREATE_DATE, DELETE_YN, MODIFY_DATE, ACCOUNT_NAME) VALUES(1, '2022-02-09 16:47:22.578', 'N', '2022-02-09 16:47:22.578', '테스트 가계부2');
INSERT INTO ACCOUNT(ACCOUNT_ID, CREATE_DATE, DELETE_YN, MODIFY_DATE, ACCOUNT_NAME) VALUES(2, '2022-02-09 17:38:09.752', 'N', '2022-02-09 17:38:09.752', '나만의 가계뿌');

/**가계부 권한**/
INSERT INTO AUTHORITY(AUTHORITY_ID, "ROLE", ACCOUNT_ID, MEMBER_ID) VALUES(1, 'OWNER', 1, 1);
INSERT INTO AUTHORITY(AUTHORITY_ID, "ROLE", ACCOUNT_ID, MEMBER_ID) VALUES(3, 'OWNER', 2, 2);

/**유저 권한**/
INSERT INTO MEMBER_ROLE(AUTHORITY_NAME) VALUES('ROLE_USER');
INSERT INTO MEMBER_ROLE(AUTHORITY_NAME) VALUES('ROLE_ADMIN');

INSERT INTO MEMBER_AUTHORITY(MEMBER_ID, AUTHORITY_NAME) VALUES(1, 'ROLE_USER');


