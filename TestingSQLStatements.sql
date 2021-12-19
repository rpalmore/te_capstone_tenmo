SELECT username, user_id FROM users;

SELECT account_id, user_id, balance FROM accounts WHERE user_id = ?;

 
        
 INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) 
        VALUES(2, 2, (SELECT account_id FROM accounts WHERE user_id = ?), (SELECT account_id FROM accounts WHERE user_id = ?), ?);
        
SELECT account_id FROM accounts WHERE user_id = ?;

SELECT transfer_id, account_from AS from, account_to AS To, amount, username 
        FROM transfers
        JOIN accounts ON transfers.account_from = accounts.account_id
        JOIN users ON accounts.user_id = users.user_id
        WHERE account_from = (SELECT account_id FROM accounts WHERE user_id = 1001) 
        OR account_to = (SELECT account_id FROM accounts WHERE user_id = 1001);
        
-- Can this replace our double SQL statements?        
SELECT transfer_id, amount, users.username AS from, us.username AS to
        FROM transfers
        JOIN accounts ON transfers.account_from = accounts.account_id
        JOIN accounts ac ON transfers.account_to = ac.account_id
        JOIN users ON ac.user_id = users.user_id
        JOIN users us ON accounts.user_id = us.user_id
        WHERE users.user_id = 1002 OR us.user_id = 1002;
        
-- Yes! Here is a one-line version
SELECT transfer_id, amount, users.username AS from, us.username AS to FROM transfers JOIN accounts ON transfers.account_from = accounts.account_id JOIN accounts ac ON transfers.account_to = ac.account_id JOIN users ON ac.user_id = users.user_id JOIN users us ON accounts.user_id = us.user_id WHERE users.user_id = 1002 OR us.user_id = 1002;
        
--THIS DISPLAYS TWO USERNAMES!!!!!        
SELECT transfer_id, amount, users.username AS from, us.username AS to
        FROM transfers
        JOIN accounts ON transfers.account_from = accounts.account_id
        JOIN accounts ac ON transfers.account_to = ac.account_id
        JOIN users ON ac.user_id = users.user_id
        JOIN users us ON accounts.user_id = us.user_id
        WHERE transfer_id = 3038;
        
--Same as above on one line:
SELECT transfer_id, amount, users.username AS from, us.username AS to FROM transfers JOIN accounts ON transfers.account_from = accounts.account_id JOIN accounts ac ON transfers.account_to = ac.account_id JOIN users ON ac.user_id = users.user_id JOIN users us ON accounts.user_id = us.user_id WHERE transfer_id = 3003;
        
-- transfer 3003 is FROM bob TO Mary.
SELECT transfer_id, transfer_type_id, transfer_status_id, username AS to, account_from, amount 
        FROM transfers 
        JOIN accounts ON transfers.account_to = accounts.account_id
        JOIN users ON accounts.user_id = users.user_id
        WHERE transfer_id = 3003;
        
        -- trying to add if statement but not yet working ...
        if account_from = 2003 then
        account_from := 'bob'
        end if;

--transfer 3020 is FROM Mary to bob.
SELECT transfer_id, transfer_type_id, transfer_status_id, username AS from, account_to, amount 
        FROM transfers 
        JOIN accounts ON transfers.account_from = accounts.account_id
        JOIN users ON accounts.user_id = users.user_id
        WHERE transfer_id = 3020;
        
--transfer 3020 is FROM Mary to bob.    
--we can put the alias in the toString() method in the client.    
SELECT transfer_id, username AS from, users.user_id AS to, transfer_type_id, transfer_status_id, amount
                FROM transfers 
                JOIN accounts ON accounts.account_id = transfers.account_from
                JOIN users ON accounts.user_id = users.user_id 
                WHERE transfer_id = 3020;
                
-- transfer 3003 is FROM bob TO Mary.                
SELECT transfer_id, username AS from, account_to AS to, transfer_type_id, transfer_status_id, amount
                FROM transfers 
                JOIN accounts ON accounts.account_id = transfers.account_from
                JOIN users ON accounts.user_id = users.user_id 
                WHERE transfer_id = 3003;
                
-- testing transfer 3003 but with TWO usernames
-- I can add accounts twice, but cannot add users twice.
SELECT transfer_id, users.username AS from, ac.user_id AS to
                FROM transfers 
                JOIN accounts ON accounts.account_id = transfers.account_from
                JOIN users ON accounts.user_id = users.user_id 
                JOIN accounts ac ON ac.account_id = transfers.account_to
                WHERE transfer_id = 3003;

-- starting from users table                
SELECT tss.transfer_id, users.username AS from, users.username AS to
                FROM users
                JOIN accounts ON accounts.user_id = users.user_id
                JOIN transfers ON account_from = accounts.account_id
                JOIN transfers tss ON tss.account_to = accounts.account_id
                
                --JOIN transfers ts ON ts.account_to = accounts.account_id
                --JOIN accounts ac ON ac.account_id = transfers.account_to
                WHERE tss.transfer_id = 3003;
                
                
-- testing transfer 3003 but with TWO usernames
SELECT transfer_id, username AS from, username AS to, account_to AS account_to
                FROM users
                JOIN accounts ON accounts.user_id = users.user_id
                JOIN transfers ON transfers.account_from = accounts.account_id
                WHERE transfer_id = 3003;
                if account_from = 2003
                then
                JOIN transfers ON transfers.account_from = accounts.account_id
                else
                JOIN transers ON transfers.account_to = accounts.account_id;
                
SELECT transfer_id, account_from, account_to
        FROM transfers
        WHERE transfer_id = 3003;
                
                
SELECT transfer_id, username, amount 
                FROM users
                JOIN accounts ON accounts.user_id = users.user_id 
                JOIN transfers ON transfers.account_to = accounts.account_id 
                WHERE account_from =
                (SELECT account_id FROM accounts WHERE user_id = 1003);              
                
-- returns a big list of ints                
SELECT transfer_id
        FROM transfers
        UNION
SELECT user_id
        FROM users
        GROUP BY user_id
        ORDER BY transfer_id;