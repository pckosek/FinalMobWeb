DROP TABLE IF EXISTS smart_led;
CREATE TABLE smart_led (id INT, stamp DATETIME, op VARCHAR(5), PRIMARY KEY(id));
INSERT INTO smart_led (id, stamp, op) VALUES (0, NOW(), "ON"); # proof of concept

DROP PROCEDURE IF EXISTS add_entry; 
 
DELIMITER $$ 

CREATE PROCEDURE add_entry(IN new_status CHAR(5), IN u_name VARCHAR(100)) # name the procedure; this has TWO arguments

BEGIN
    DECLARE queried_value INT;
    SELECT MAX(id) INTO queried_value
    FROM smart_led;

    INSERT INTO smart_led(id, stamp, op, user_name) VALUE (queried_value+1, NOW(), new_status, u_name);
END$$ 

DELIMITER ;  # change the delimiter back to normal