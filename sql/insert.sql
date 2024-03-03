INSERT INTO Category(category_name)
VALUES ('Холодні напої'), ('Гарячі напої'), ('Телятина'), ('Свинина'), ('Риба та курка'), ('Десерти'), ('Сніданки'), ('Картопля-фрі та соуси');

INSERT INTO delivery_way(way)
VALUES ('Доставка ку''єром'), ('Самовивіз');

INSERT INTO Status(status_name)
VALUES ('Готується'), ('Доставлено'), ('В дорозі');

INSERT INTO promo_code(code)
VALUES ('asjkdhf234'), ('lkjm5kn345'), ('mbnzxcv234'), ('asdf243fsc'), ('3245mml322');

INSERT INTO payment_way(way)
VALUES ('Безготівковий розрахунок'), ('Готівковий розрахунок');

INSERT INTO user_role(user_role)
VALUES ('ROLE_CLIENT'), ('ROLE_ADMIN'), ('ROLE_OWNER');

INSERT INTO Item(item_name, price, description, prep_time, category_id)
VALUES ('Бургер зі свининою', 35, 'asdfa;slkjhfljahsdlfkjhalkjshdflas', 10, 4),
       ('Чізбургер з рибою', 30, 'asdfalksjdlhflkjahsldkfjhlakjhsdflf', 10, 5),
       ('Картопля по-запорізьки', 60, 'sadflgkja;skdjflkajsldkjfhlakf', 15, 8),
       ('Австрійский сніданок', 50, 'saldkfg;lakjsdlkfjlaskjdf;lkjsdj', 20, 7),
       ('Флет уайт', 20, 'lksjhlkjfhsaldkjfhakjhsdlkfjhakjhsdlfkhalkf', 7, 1);

INSERT INTO Order_Item(count, prep_time, item_id)
VALUES (1, 45, 3),
       (2, 45, 5),
       (3, 23, 4),
       (4, 20, 3),
       (5, 23, 2);

INSERT INTO "user"(user_name, surname, phone, email, username, user_password, user_role_id)
VALUES ('Ілля', 'Камаралі', '+380972224444', 'illia@gmail.com', 'IlliaKamarali', '12345678', 2),
       ('Ілля', 'Кисельов', '+380972342344', 'kyselov@gmail.com', '1902Illia', '14141414', 2),
       ('Богдан', 'Хохлов', '+380923234234', 'khokhlov@gmail.com', 'Bodya0301', '01010101', 2),
       ('Андрій', 'Мацієвский', '+380972435678', 'matsiev2006@gmail.com', 'SushiMaster', '11111111', 2),
       ('Денис', 'Кухарик', '+380978765432', 'kykharykden11223@gmail.com', 'Kukhar_ua', '11112222', 2);

INSERT INTO restaurant(admin_id, address, longitude, latitude)
VALUES (5, 'вул. Вишгородська 3', -98.123, 123.123),
       (4, 'вул. Портова 6', 12.123, -12.234),
       (3, 'бул. Шевченка 12', 58.234, 46.465),
       (2, 'просп. Миру 154', 20.234, 34.23),
       (1, 'просп. Соборний 198', 123.213, 34.124);

INSERT INTO "order"(prep_time, wish, restaurant_id, payment_way_id, promo_code_id, status_id, delivery_way_id, order_item_id)
VALUES (50, 'asdasdflkas', 1, 2, 4, 2, 1, 1),
       (12, 'asdasdflkasdafas', 2, 1, 1, 1, 2, 2),
       (42, 'asdasdflkadsfghds', 3, 2, 2, 3, 1, 3),
       (45, 'asdasdflksdfgsdsfgs', 4, 1, 3, 3, 2, 4),
       (24, 'asdasdflkassdfgsdf', 5, 2, 5, 1, 2, 5);