CREATE TABLE IF NOT EXISTS 'book'(
	'id' bigint(20) NOT NULL AUTO_INCREMENT,
    'name' varchar(256) NOT NULL, 
    'autor' varchar(128) NOT NULL,
    'volume' int(8) NOT NULL, 
    'avaliable' boolean NOT NULL,
	'borrowing_date' DATE,
    'return_date' DATE,
    'reserved' boolean NOT NULL,
    'user_with_the_book' int REFERENCES user);