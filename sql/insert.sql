INSERT INTO Category(category_name)
VALUES ('Cold_drinks'), ('Hot_drinks'), ('Beef'), ('Pork'), ('Fish_and_chicken'), ('Desserts'), ('Breakfasts'), ('Fries_and_sauces');

INSERT INTO delivery_way(way)
VALUES ('Delivery'), ('PickUp');

INSERT INTO Status(status_name)
VALUES ('In_progress'), ('Delivered'), ('On_way');

INSERT INTO promo_code(code)
VALUES ('asjkdhf234'), ('lkjm5kn345'), ('mbnzxcv234'), ('asdf243fsc'), ('3245mml322');

INSERT INTO payment_way(way)
VALUES ('Card'), ('Cash');

INSERT INTO user_role(user_role)
VALUES ('ROLE_CLIENT'), ('ROLE_ADMIN'), ('ROLE_OWNER');

-- INSERT INTO Item(item_name, price, description, prep_time, category)
-- VALUES ('Бургер зі свининою', 35, 'asdfa;slkjhfljahsdlfkjhalkjshdflas', 10, 'Свинина'),
--        ('Чізбургер з рибою', 30, 'asdfalksjdlhflkjahsldkfjhlakjhsdflf', 10, 'Риба та курка'),
--        ('Картопля по-запорізьки', 60, 'sadflgkja;skdjflkajsldkjfhlakf', 15, 'Картопля-фрі та соуси'),
--        ('Австрійский сніданок', 50, 'saldkfg;lakjsdlkfjlaskjdf;lkjsdj', 20, 'Сніданки'),
--        ('Флет уайт', 20, 'lksjhlkjfhsaldkjfhakjhsdlkfjhakjhsdlfkhalkf', 7, 'Холодні напої');

INSERT INTO Item(item_name, price, description, prep_time, category, item_img)
VALUES
    ('Бургер зі свининою', 35, 'asdfa;slkjhfljahsdlfkjhalkjshdflas', 10, 'Pork', 'PorkBurger.jpeg'),
    ('Чізбургер з рибою', 30, 'asdfalksjdlhflkjahsldkfjhlakjhsdflf', 10, 'Fish_and_chicken', 'FishCheeseburger.jpeg'),
    ('Картопля по-запорізьки', 60, 'sadflgkja;skdjflkajsldkjfhlakf', 15, 'Fries_and_sauces', 'ZaporPotato.jpeg'),
    ('Австрійский сніданок', 50, 'saldkfg;lakjsdlkfjlaskjdf;lkjsdj', 20, 'Breakfasts', 'austrianBreakfast.jpeg'),
    ('Флет уайт', 20, 'lksjhlkjfhsaldkjfhakjhsdlkfjhakjhsdlfkhalkf', 7, 'Cold_drinks', 'flatWhite.jpeg');


INSERT INTO Order_Item(count, prep_time, item_id)
VALUES (1, 45, 3),
       (2, 45, 5),
       (3, 23, 4),
       (4, 20, 3),
       (5, 23, 2);

INSERT INTO systemuser(system_user_name, surname, phone, email, system_username, system_user_password, system_user_role)
VALUES ('Ілля', 'Камаралі', '+380972224444', 'illia@gmail.com', 'IlliaKamarali', '12345678', 'ROLE_ADMIN'),
       ('Ілля', 'Кисельов', '+380972342344', 'kyselov@gmail.com', '1902Illia', '14141414', 'ROLE_ADMIN'),
       ('Богдан', 'Хохлов', '+380923234234', 'khokhlov@gmail.com', 'Bodya0301', '01010101', 'ROLE_ADMIN'),
       ('Андрій', 'Мацієвский', '+380972435678', 'matsiev2006@gmail.com', 'SushiMaster', '11111111', 'ROLE_ADMIN'),
       ('Денис', 'Кухарик', '+380978765432', 'kykharykden11223@gmail.com', 'Kukhar_ua', '11112222', 'ROLE_ADMIN');

INSERT INTO restaurant(admin_id, address, longitude, latitude)
VALUES (5, 'вул. Вишгородська 3', -98.123, 123.123),
       (4, 'вул. Портова 6', 12.123, -12.234),
       (3, 'бул. Шевченка 12', 58.234, 46.465),
       (2, 'просп. Миру 154', 20.234, 34.23),
       (1, 'просп. Соборний 198', 123.213, 34.124);

INSERT INTO purchase(prep_time, wish, restaurant_id, payment_way, promo_code, status, delivery_way, order_item_id, system_user_id)
VALUES
    (12, 'asdasdflkasdafas', 2, 'Card', 1, 'In_progress', 'PickUp', 2, 2),
    (42, 'asdasdflkadsfghds', 3, 'Cash', 2, 'Delivered', 'Delivery', 3, 3),
    (45, 'asdasdflksdfgsdsfgs', 4, 'Card',3, 'On_way', 'PickUp', 4, 4),
    (24, 'asdasdflkassdfgsdf', 5, 'Cash', 4, 'In_progress', 'PickUp', 5, 5);

UPDATE order_item SET purchase_id = 1 WHERE order_item.id = 1;
UPDATE order_item SET purchase_id = 4 WHERE order_item.id = 2;
UPDATE order_item SET purchase_id = 3 WHERE order_item.id = 3;
UPDATE order_item SET purchase_id = 2 WHERE order_item.id = 4;
UPDATE order_item SET purchase_id = 1 WHERE order_item.id = 5;