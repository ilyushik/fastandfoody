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

INSERT INTO Item(item_name, price, description, prep_time, category)
VALUES ('Бургер зі свининою', 35, 'asdfa;slkjhfljahsdlfkjhalkjshdflas', 10, 'Свинина'),
       ('Чізбургер з рибою', 30, 'asdfalksjdlhflkjahsldkfjhlakjhsdflf', 10, 'Риба та курка'),
       ('Картопля по-запорізьки', 60, 'sadflgkja;skdjflkajsldkjfhlakf', 15, 'Картопля-фрі та соуси'),
       ('Австрійский сніданок', 50, 'saldkfg;lakjsdlkfjlaskjdf;lkjsdj', 20, 'Сніданки'),
       ('Флет уайт', 20, 'lksjhlkjfhsaldkjfhakjhsdlkfjhakjhsdlfkhalkf', 7, 'Холодні напої');

-- INSERT INTO Item(item_name, price, description, prep_time, category)
-- VALUES
--     ('Бургер зі свининою', 35, 'asdfa;slkjhfljahsdlfkjhalkjshdflas', 10, 'Pork'),
--     ('Чізбургер з рибою', 30, 'asdfalksjdlhflkjahsldkfjhlakjhsdflf', 10, 'Fish_and_chicken'),
--     ('Картопля по-запорізьки', 60, 'sadflgkja;skdjflkajsldkjfhlakf', 15, 'Fries_and_sauces'),
--     ('Австрійский сніданок', 50, 'saldkfg;lakjsdlkfjlaskjdf;lkjsdj', 20, 'Breakfasts'),
--     ('Флет уайт', 20, 'lksjhlkjfhsaldkjfhakjhsdlkfjhakjhsdlfkhalkf', 7, 'Cold_drinks');


INSERT INTO Order_Item(count, prep_time, item_id)
VALUES (1, 45, 3),
       (2, 45, 5),
       (3, 23, 4),
       (4, 20, 3),
       (5, 23, 2);

INSERT INTO system_user(system_user_name, surname, phone, email, system_username, system_user_password, system_user_role)
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
    (12, 'asdasdflkasdafas', 2, 'Безготівковий розрахунок', 'asdf243fsc', 'Готується', 'Самовивіз', 2, 2),
    (42, 'asdasdflkadsfghds', 3, 'Готівковий розрахунок', 'lkjm5kn345', 'Доставлено', 'Доставка ку''єром', 3, 3),
    (45, 'asdasdflksdfgsdsfgs', 4, 'Безготівковий розрахунок', 'mbnzxcv234', 'В дорозі', 'Самовивіз', 4, 4),
    (24, 'asdasdflkassdfgsdf', 5, 'Готівковий розрахунок', 'asjkdhf234', 'Готується', 'Самовивіз', 5, 5);
