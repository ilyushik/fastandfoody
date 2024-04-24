INSERT INTO Category(category_name)
VALUES ('Cold_drinks'), ('Hot_drinks'), ('Beef'), ('Pork'), ('Fish_and_chicken'), ('Desserts'), ('Breakfasts'), ('Fries_and_sauces');

INSERT INTO delivery_way(way)
VALUES ('Delivery'), ('PickUp');

INSERT INTO Status(status_name)
VALUES ('In_progress'), ('Delivered'), ('On_way'), ('Canceled');

INSERT INTO promo_code(code)
VALUES ('asjkdhf234'), ('lkjm5kn345'), ('mbnzxcv234'), ('asdf243fsc'), ('3245mml322');

INSERT INTO payment_way(way)
VALUES ('Card'), ('Cash');

INSERT INTO user_role(user_role)
VALUES ('ROLE_CLIENT'), ('ROLE_ADMIN'), ('ROLE_OWNER');

INSERT INTO Item(item_name, price, description, prep_time, category, image)
VALUES
    ('Бургер зі свининою', 35, 'asdfa;slkjhfljahsdlfkjhalkjshdflas', 10, 'Pork', 3),
    ('Чізбургер з рибою', 30, 'asdfalksjdlhflkjahsldkfjhlakjhsdflf', 10, 'Fish_and_chicken', 2),
    ('Картопля по-запорізьки', 60, 'sadflgkja;skdjflkajsldkjfhlakf', 15, 'Fries_and_sauces', 5),
    ('Австрійский сніданок', 50, 'saldkfg;lakjsdlkfjlaskjdf;lkjsdj', 20, 'Breakfasts', 4),
    ('Флет уайт', 20, 'lksjhlkjfhsaldkjfhakjhsdlkfjhakjhsdlfkhalkf', 7, 'Cold_drinks', 6);

INSERT INTO Order_Item(count, prep_time, item_id)
VALUES (1, 45, 3),
       (2, 45, 5),
       (3, 23, 4),
       (4, 20, 3),
       (5, 23, 2);

INSERT INTO person(name, surname, phone, email, username, person_password, person_role, image)
VALUES ('Ілля', 'Камаралі', '+380972224444', 'kamaraliilya@gmail.com', 'IlliaKamarali', '12345678', 'ROLE_OWNER', 1),
       ('Ілля', 'Кисельов', '+380972342344', 'illia.kamarali.work@gmail.com', '1902Illia', '14141414', 'ROLE_ADMIN', 1),
       ('Богдан', 'Хохлов', '+380923234234', 'khokhlov@gmail.com', 'Bodya0301', '01010101', 'ROLE_ADMIN', 1),
       ('Андрій', 'Мацієвский', '+380972435678', 'matsiev2006@gmail.com', 'SushiMaster', '11111111', 'ROLE_ADMIN', 1),
       ('Денис', 'Кухарик', '+380978765432', 'kykharykden11223@gmail.com', 'Kukhar_ua', '11112222', 'ROLE_ADMIN', 1);

INSERT INTO city(name, longitude, latitude) VALUES
    ('Маріуполь', 47.104500, 37.543761),
    ('Чернігів', 51.503600, 31.286362),
    ('Запоріжжя', 47.830958, 35.167202),
    ('Київ', 50.442673, 30.490800),
    ('Дніпро', 48.473107, 35.002404);

INSERT INTO restaurant(admin_id, address, longitude, latitude, city_id, phone, email)
VALUES (5, 'вул. Вишгородська 3', 48.459832, 35.031861, 5, '+380685573589', 'fastandfoodycorp@gmail.com'),
       (4, 'вул. Портова 6', 50.432090, 30.519048, 4, '+380984575567', 'fastandfoodycorp@gmail.com'),
       (3, 'бул. Шевченка 12', 47.862232, 35.080849, 3, '+380977842869', 'fastandfoodycorp@gmail.com'),
       (2, 'просп. Миру 154', 51.505058, 31.272312, 2, '+380687412589', 'fastandfoodycorp@gmail.com'),
       (1, 'просп. Соборний 198', 47.097155, 37.544608, 1, '+380671234675', 'fastandfoodycorp@gmail.com');

INSERT INTO purchase(prep_time, wish, restaurant_id, payment_way, promo_code, status, delivery_way, order_item_id, person_id, address, date)
VALUES
    (12, 'asdasdflkasdafas', 2, 'Card', 1, 'In_progress', 'PickUp', 2, 2, null, '2024-03-11 12:00:00'),
    (42, 'asdasdflkadsfghds', 3, 'Cash', 2, 'Delivered', 'Delivery', 3, 3, 'Харківське шосе, 148', '2023-09-20 12:00:00'),
    (45, 'asdasdflksdfgsdsfgs', 4, 'Card',3, 'On_way', 'PickUp', 4, 4, null,  '2024-02-20 12:00:00'),
    (24, 'asdasdflkassdfgsdf', 5, 'Cash', 4, 'In_progress', 'PickUp', 5, 5, null, '2023-10-20 12:00:00');

UPDATE order_item SET purchase_id = 1 WHERE order_item.id = 1;
UPDATE order_item SET purchase_id = 4 WHERE order_item.id = 2;
UPDATE order_item SET purchase_id = 3 WHERE order_item.id = 3;
UPDATE order_item SET purchase_id = 2 WHERE order_item.id = 4;
UPDATE order_item SET purchase_id = 1 WHERE order_item.id = 5;

UPDATE person set restaurant_id = 5 WHERE id = 1;
UPDATE person set restaurant_id = 4 WHERE id = 2;
UPDATE person set restaurant_id = 3 WHERE id = 3;
UPDATE person set restaurant_id = 2 WHERE id = 4;
UPDATE person set restaurant_id = 1 WHERE id = 5;