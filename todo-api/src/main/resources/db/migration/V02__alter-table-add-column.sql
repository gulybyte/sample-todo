ALTER TABLE todo ADD context_todo varchar(20);

UPDATE todo SET context_todo = 'none' WHERE context_todo IS NULL;
