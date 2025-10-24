
DROP TABLE IF EXISTS public.user_app;
DROP SEQUENCE IF EXISTS public.user_seq;
CREATE SEQUENCE IF NOT EXISTS public.user_seq;

CREATE SEQUENCE IF NOT EXISTS USER_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE public.user_app (
    ID INT DEFAULT NEXT VALUE FOR USER_SEQ PRIMARY KEY NOT NULL,
    NAME VARCHAR(30) NOT NULL,
    EMAIL VARCHAR(30) NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


INSERT INTO public.user_app (name, email, created_at) VALUES ('John Doe', 'johndoe@example.com', '2023-05-27 04:34:12');
INSERT INTO public.user_app (name, email, created_at) VALUES ('Jane Smith', 'janesmith@example.com', '2023-05-27 04:34:12');
INSERT INTO public.user_app (name, email, created_at) VALUES ('Peter Jones', 'peterjones@example.com', '2023-09-08 00:47:38');
INSERT INTO public.user_app (name, email, created_at) VALUES ('Mary Brown', 'marybrown@example.com', '2023-06-10 23:37:22');
INSERT INTO public.user_app (name, email) VALUES ('Robert Thompson', 'robertthompson@example.com');

commit;

