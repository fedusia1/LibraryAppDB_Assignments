
-- US01
-- actual
select count(id) from users;

-- expected
SELECT count(distinct ID) FROM users;

-- US02
select count(*) from book_borrow
where is_returned=0;

-- US03
select name from book_categories;

-- US04
select * from books
where name ='Liudas book';

-- get name of category for category_id
select name
from book_categories
where id=(select book_category_id from books
          where id='14042');

-- US05
select bc.name,count(*) from book_borrow bb
                                 inner join books b on bb.book_id = b.id
                                 inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;


-- US05 SECOND METHOD
-- i have most popular books_id
select book_id, count(*) from book_borrow
group by book_id
order by count(*) desc limit 1;

-- in order get the category name i need find it from most popular books_id
select book_category_id from books
where id=(select book_id from book_borrow
group by book_id
order by count(*) desc limit 1);

-- get name of category
select name
from book_categories
where id=(select book_category_id from books
where id=(select book_id from book_borrow
          group by book_id
          order by count(*) desc limit 1));


-- US05
select bc.name, count(*) from book_borrow bb
                                   inner join books b on bb.book_id = b.id
                                   inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;




-- us06
select id,name,author from books
where name = 'Clean Code' and author='Robert C.Martin'
order by id desc;

-- us06
select name from books
where name = 'Head First Java from Liuda' and author='Kathy Sierra'
order by id desc;

-- us06
select name from books
where name = 'The Scrum Field Guide from Liuda'
order by id desc;
--
select * from books
where author = 'Liudmyla Fedorenko';
select * from book_borrow;
select * from users;


-- US07
select full_name,b.name,bb.borrowed_date, is_returned from users u
inner join book_borrow bb on u.id = bb.user_id
inner join books b on bb.book_id = b.id
where full_name='Test Student 5' and name='LIUDAS_PRACTICE'
order by 3 desc;

-- US08
select count(*) from users
where users.status='ACTIVE' and user_group_id=1;


-- how many INACTIVE and ACTIVE users we have
select status,count(*) from users
group by status;





