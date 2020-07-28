
CREATE TABLE PRODUCT (
    PRODUCT_ID VARCHAR2(20) PRIMARY KEY,
    P_NAME VARCHAR2(30) NOT NULL,
    PRICE NUMBER NOT NULL,
    DESCRIPTION VARCHAR(30),
    STOCK NUMBER NOT NULL
);

INSERT INTO PRODUCT
VALUES ('nb_ss7','삼성노트북',1570000,'시리즈7',10);
INSERT INTO PRODUCT
VALUES ('nb_ama4','맥북에어',1200000,'xcode4',20);
insert into PRODUCT
values ('pc_ibm','ibmPC',750000,'window8',5);

commit;
