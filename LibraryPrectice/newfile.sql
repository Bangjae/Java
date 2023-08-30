
/* Drop Tables */

DROP TABLE rental CASCADE CONSTRAINTS;
DROP TABLE BOOKDB CASCADE CONSTRAINTS;
DROP TABLE memberDB CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE BOOKDB
(
	book_id number NOT NULL,
	book_name varchar2(50) NOT NULL,
	book_count number,
	PRIMARY KEY (book_id)
);


CREATE TABLE memberDB
(
	memeber_no number NOT NULL,
	id varchar2(50),
	name varchar2(50),
	PRIMARY KEY (memeber_no)
);


CREATE TABLE rental
(
	rental_num number NOT NULL,
	borrowdate date,
	return_date date,
	memeber_no number NOT NULL,
	book_id number NOT NULL,
	PRIMARY KEY (rental_num)
);



/* Create Foreign Keys */

ALTER TABLE rental
	ADD FOREIGN KEY (book_id)
	REFERENCES BOOKDB (book_id)
;


ALTER TABLE rental
	ADD FOREIGN KEY (memeber_no)
	REFERENCES memberDB (memeber_no)
;



