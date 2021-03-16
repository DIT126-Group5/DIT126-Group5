INSERT INTO app.AUTHOR (NAME) VALUES
	('Michael Chavez'),
	('William Shelton'),
	('Betty Griffin'),
	('Joseph Holland'),
	('Mark Mitchell'),
	('Deanna Walker'),
	('Alex Gardner'),
	('Richard Cameron'),
	('Darren King'),
	('Jerry Washington');

INSERT INTO app.BOOK (ISBN, TITLE, PUBLICATIONYEAR, IMAGEURL) VALUES
	('978-1-4514-5357-7', 'Task have rise unit pretty.', 1975, ''),
	('978-0-7886-9310-6', 'Staff imagine provide political several prepare population.', 2013, ''),
	('978-1-128-77986-3', 'Most guy ever southern never effect pattern care.', 1991, ''),
	('978-1-55765-583-7', 'Address near customer entire industry woman our stay.', 1975, ''),
	('978-1-924219-70-9', 'Wrong enter administration move without check health be.', 1979, ''),
	('978-1-86699-843-4', 'Million response hotel rock main everyone rest.', 2008, ''),
	('978-1-66351-991-7', 'It necessary although cold.', 2003, ''),
	('978-1-71837-943-5', 'Life my western peace.', 1970, ''),
	('978-0-251-93547-4', 'Quickly must rate memory worker different end.', 1987, ''),
	('978-0-7098-0386-7', 'Much great music hundred sound thought their.', 1979, '');

INSERT INTO app.ACCOUNT (USERNAME, STREET, POSTALCODE, CITY, BALANCE, FIRSTNAME, LASTNAME, PASSWORD, EMAIL, PHONENUMBER) VALUES
	('xlandry', '190 Victor Court', '59320', 'Anthonyhaven', 454, 'Darryl', 'Smith', '_Va%kUOt7w', 'haileybooker@gmail.com', '673-036-8807x6922'),
	('bli', '57760 Meghan Parkway', '41231', 'Port Matthew', 808603091, 'Mitchell', 'Wilson', '*T3kKHz(2Z', 'irivas@hotmail.com', '164.266.0109x54461'),
	('lirobert', '556 Lori Mountains Suite 595', '48249', 'East John', 9, 'Shannon', 'Herrera', 'tgi6lX^W#8', 'jerry95@estrada-patel.com', '600.457.0883x31821'),
	('dawn26', '596 Hoover Expressway', '01642', 'Meaganside', 8, 'Patrick', 'Vaughan', '0fMyrAxg%q', 'amanda34@yahoo.com', '001-994-509-5147'),
	('meagan31', '92739 Carter Cliffs', '62087', 'North Erintown', 1163562, 'Juan', 'Chase', 'VC$LR4Nusi', 'nicole92@fisher-petersen.com', '720.595.8267'),
	('mmullen', '44579 Shannon Cliffs Apt. 886', '21431', 'Kathrynchester', 24819902, 'Richard', 'Ellis', '#1zWb&NtMi', 'briannaproctor@smith.com', '(633)389-2612x0488'),
	('timothy85', '7321 Emily Flat', '96799', 'South Michele', 5899890, 'Kelsey', 'Ross', 'l5UII5Vo#N', 'pattersonmicheal@gmail.com', '001-393-687-5311x6171'),
	('hendersonalisha', '294 Parker Fields', '90808', 'North Jeffrey', 83460, 'Harry', 'Jones', 'tD9G^JSA&8', 'ewhite@hotmail.com', '001-190-672-9171x890'),
	('kreyes', '857 Harrington Hills Apt. 865', '81062', 'Coleport', 8, 'Christopher', 'Roman', '!3LZy^&mcc', 'andersonraymond@hotmail.com', '001-964-610-0163x27592'),
	('hford', '577 Alyssa Extension', '38991', 'Brianshire', 201571147, 'Jennifer', 'Smith', '_*WD8PHdE+', 'millerethan@chavez-mercer.com', '323-802-0190x864');

INSERT INTO app.USERREVIEW (REVIEWER, REVIEWEE, COMMENT, RATING) VALUES
	('hendersonalisha', 'mmullen', 'One risk economy remember stand stage. History impact learn name. Also memory Mr.', 8),
	('hendersonalisha', 'hford', 'Answer eat eat four. Lead girl second of light above.', 9),
	('mmullen', 'bli', 'Away area away seem. Lay power behind.', 7),
	('timothy85', 'xlandry', 'Go question whether them son option American class. Yourself law life hope. People at cup movement with front.', 3),
	('dawn26', 'hendersonalisha', 'Next threat back we nature science. Voice involve clearly manager.', 1),
	('meagan31', 'hendersonalisha', 'Feel her box into. Military evening let six PM into pass.', 4),
	('dawn26', 'dawn26', 'Moment write close budget soon admit can sister. Young card glass oil.', 0),
	('timothy85', 'hford', 'First own agree power right she series senior. Yeah production it model say morning create.', 8),
	('timothy85', 'timothy85', 'Message social four. Understand teacher outside son discover late not.', 1),
	('mmullen', 'mmullen', 'There set manage indicate. Already win within tax air.', 2);

INSERT INTO app.CONDITION (NAME) VALUES
	('Nyskick'),
	('Bra skick'),
        ('Gott skick'),
        ('Ok skick'),
	('Begagnad');

INSERT INTO app.CATEGORY (NAME) VALUES
	('entire'),
	('painting'),
	('fill'),
	('policy'),
	('southern'),
	('human'),
	('state'),
	('think'),
	('statement'),
	('cup');

INSERT INTO app.book_author (author, book) VALUES
	('Deanna Walker', '978-1-66351-991-7'),
	('William Shelton', '978-1-86699-843-4'),
	('Deanna Walker', '978-0-251-93547-4'),
	('Richard Cameron', '978-1-71837-943-5'),
	('Joseph Holland', '978-1-86699-843-4'),
	('Jerry Washington', '978-0-251-93547-4'),
	('Darren King', '978-0-7098-0386-7'),
	('William Shelton', '978-1-55765-583-7'),
	('Jerry Washington', '978-1-86699-843-4');

INSERT INTO app.book_category (book, category) VALUES
	('978-1-66351-991-7', 'think'),
	('978-0-251-93547-4', 'human'),
	('978-1-4514-5357-7', 'think'),
	('978-1-71837-943-5', 'fill'),
	('978-1-924219-70-9', 'policy'),
	('978-1-924219-70-9', 'painting'),
	('978-1-4514-5357-7', 'statement'),
	('978-1-55765-583-7', 'policy');
