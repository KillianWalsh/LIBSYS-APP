CREATE DEFINER=`root`@`localhost` PROCEDURE `addBookToWishList`(inTitle VARCHAR(255), inAuthor VARCHAR(255), ISBN VARCHAR(255))
BEGIN

IF((SELECT COUNT(*) FROM wish_list) > 0) THEN
SELECT @currentId := MAX(wish_id)+1 FROM wish_list;
ELSE
SELECT @currentId := 1;
END IF;

IF EXISTS(SELECT * FROM wish_list WHERE isbn = ISBN AND author = inAuthor AND title = inTitle) THEN
	SELECT @NumberOfWishes := number_of_wishes FROM wish_list WHERE isbn = ISBN AND author = inAuthor AND title = inTitle;
    UPDATE wish_list SET number_of_wishes = (@NumberOfWishes + 1) WHERE author = inAuthor AND title = inTitle;
ELSE
	INSERT INTO wish_list VALUES (@currentId, ISBN, inAuthor, 1, inTitle);
END IF;

END