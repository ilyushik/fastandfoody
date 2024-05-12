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
    ('Флет уайт', 20, 'lksjhlkjfhsaldkjfhakjhsdlkfjhakjhsdlfkhalkf', 7, 'Hot_drinks', 6);

INSERT INTO Item(item_name, price, description, prep_time, category, image)
VALUES
    ('Гамбургер зі смаженою куркою', 120.00, 'Смачний гамбургер зі смаженою куркою, салатом і соусом', 10, 'Fish_and_chicken', 7),
    ('Картопля фрі', 70.00, 'Хрустка картопля фрі з сіллю', 8, 'Fries_and_sauces', 8),
    ('Чікен нагетс', 100.00, 'Соковиті курячі нагетси з паніровкою', 12, 'Fish_and_chicken', 9),
    ('Чізбургер', 130.00, 'Гамбургер з сиром, салатом і соусом', 10, 'Fish_and_chicken', 10),
    ('Піца пепероні', 180.00, 'Піца з пепероні та сиром', 15, 'Fries_and_sauces', 11),
    ('Кока-кола 0,5л', 50.00, 'Газований напій Кока-кола', 2, 'Cold_drinks', 12),
    ('Чай з лимоном', 40.00, 'Ароматний чай з додаванням лимону', 3, 'Hot_drinks', 13),
    ('Фреш апельсиновий 0,3л', 90.00, 'Свіжий фреш з апельсинів', 5, 'Cold_drinks', 14),
    ('Хот-дог', 110.00, 'Хрусткий хлібець зі смаженою сосискою', 7, 'Fries_and_sauces', 15),
    ('Салат Цезар з куркою', 150.00, 'Салат з куркою, сухариками і соусом Цезар', 10, 'Breakfasts', 16);

INSERT INTO Order_Item(count, prep_time, price, item_id)
VALUES (1, 45, 60, 3),
       (2, 45, 40, 5),
       (3, 23, 150, 4),
       (4, 20, 240, 3),
       (5, 23, 150, 2);

INSERT INTO person(name, surname, phone, email, username, person_password, person_role, image)
VALUES ('Ілля', 'Камаралі', '+380972224444', 'kamaraliilya@gmail.com', 'IlliaKamarali', '12345678', 'ROLE_OWNER', 1),
       ('Ілля', 'Кисельов', '+380972342344', 'illia.kamarali.work@gmail.com', '1902Illia', '14141414', 'ROLE_ADMIN', 1),
       ('Богдан', 'Хохлов', '+380923234234', 'khokhlov@gmail.com', 'Bodya0301', '01010101', 'ROLE_ADMIN', 1),
       ('Андрій', 'Мацієвский', '+380972435678', 'matsiev2006@gmail.com', 'SushiMaster', '11111111', 'ROLE_ADMIN', 1),
       ('Денис', 'Кухарик', '+380978765432', 'kykharykden11223@gmail.com', 'Kukhar_ua', '11112222', 'ROLE_ADMIN', 1);

INSERT INTO person(name, surname, phone, email, username, person_password, person_role, image) VALUES
    ('Іван', 'Петров', '+380123456789', 'ivan.petrov@example.com', 'ivan_petrov', '12345678', 'ROLE_ADMIN', 1),
    ('Марія', 'Іванова', '+380987754321', 'maria.ivanova@example.com', 'maria_ivanova', '23456754', 'ROLE_ADMIN', 1),
    ('Олександр', 'Сидоров', '+380111233445', 'oleksandr.sidorov@example.com', 'oleksandr_sidorov', '34567854', 'ROLE_ADMIN', 1),
    ('Наталія', 'Васильєва', '+380556677889', 'natalia.vasylieva@example.com', 'natalia_vasylieva', '45678945', 'ROLE_ADMIN', 1),
    ('Петро', 'Коваленко', '+380998877665', 'petro.kovalenko@example.com', 'petro_kovalenko', '56789045', 'ROLE_ADMIN', 1),
    ('Олена', 'Павлова', '+380443322110', 'olena.pavlova@example.com', 'olena_pavlova', '67890143', 'ROLE_ADMIN', 1),
    ('Ігор', 'Білов', '+380667788990', 'igor.bilov@example.com', 'igor_bilov', '78901243', 'ROLE_ADMIN', 1),
    ('Тетяна', 'Лисенко', '+380112233445', 'tetiana.lisenko@example.com', 'tetiana_lisenko', '89012365', 'ROLE_ADMIN', 1),
    ('Сергій', 'Григоренко', '+380334455667', 'serhii.hryhorenko@example.com', 'serhii_hryhorenko', '90123487', 'ROLE_ADMIN', 1),
    ('Анна', 'Михайлова', '+380556677998', 'anna.mikhaylova@example.com', 'anna_mikhaylova', '012345546', 'ROLE_ADMIN', 1),
    ('Віктор', 'Зайцев', '+380111111111', 'viktor.zaitsev@example.com', 'viktor_zaitsev', '12312345', 'ROLE_ADMIN', 1),
    ('Євгенія', 'Романова', '+380222222222', 'yevgeniya.romanova@example.com', 'yevgeniya_romanova', '23423487', 'ROLE_ADMIN', 1),
    ('Олег', 'Ковалев', '+380333333333', 'oleg.kovalev@example.com', 'oleg_kovalev', '34534598', 'ROLE_ADMIN', 1),
    ('Катерина', 'Ковальчук', '+380444444444', 'katerina.kovalchuk@example.com', 'katerina_kovalchuk', '45645656', 'ROLE_ADMIN', 1),
    ('Максим', 'Волков', '+380555555555', 'maxim.volkov@example.com', 'maxim_volkov', '56756723', 'ROLE_ADMIN', 1),
    ('Ольга', 'Морозова', '+380666666666', 'olga.morozova@example.com', 'olga_morozova', '67867800', 'ROLE_ADMIN', 1),
    ('Артем', 'Козлов', '+380777777777', 'artem.kozlov@example.com', 'artem_kozlov', '78978987', 'ROLE_ADMIN', 1),
    ('Діана', 'Павлів', '+380888888888', 'diana.pavliv@example.com', 'diana_pavliv', '89089074', 'ROLE_ADMIN', 1),
    ('Олександра', 'Кравченко', '+380999999999', 'oleksandra.kravchenko@example.com', 'oleksandra_kravchenko', '90190114', 'ROLE_ADMIN', 1),
    ('Володимир', 'Лисенко', '+380000000000', 'volodymyr.lisenko@example.com', 'volodymyr_lisenko', '01010185', 'ROLE_ADMIN', 1),
#     ('Вікторія', 'Гончаренко', '+380123123123', 'viktoria.honcharenko@example.com', 'viktoria_honcharenko', '12121225', 'ROLE_ADMIN', 1),
#     ('Артур', 'Мельник', '+380234234234', 'artur.melnyk@example.com', 'artur_melnyk', '23232369', 'ROLE_ADMIN', 1),
#     ('Аліна', 'Коваленко', '+380345345345', 'alina.kovalenko@example.com', 'alina_kovalenko', '34343436', 'ROLE_ADMIN', 1),
#     ('Віра', 'Панасенко', '+380456456456', 'vira.panasenko@example.com', 'vira_panasenko', '45454510', 'ROLE_ADMIN', 1),
#     ('Михайло', 'Гриценко', '+380567567567', 'mikhailo.hrytsenko@example.com', 'mikhailo_hrytsenko', '56565620', 'ROLE_ADMIN', 1),
    ('Олексій', 'Семененко', '+380987654321', 'oleksiy.semenenko@example.com', 'oleksiy_semenenko', '11111130', 'ROLE_CLIENT', 1),
    ('Юлія', 'Полякова', '+380876543210', 'yuliya.polyakova@example.com', 'yuliya_polyakova', '22222257', 'ROLE_CLIENT', 1),
    ('Максим', 'Коваленко', '+380765432109', 'maxim.kovalenko@example.com', 'maxim_kovalenko', '33333368', 'ROLE_CLIENT', 1),
    ('Оксана', 'Біліч', '+380654321098', 'oksana.bilich@example.com', 'oksana_bilich', '44444424', 'ROLE_CLIENT', 1),
    ('Андрій', 'Петров', '+380543210987', 'andriy.petrov@example.com', 'andriy_petrov', '55555535', 'ROLE_CLIENT', 1);

INSERT INTO city(name, longitude, latitude) VALUES
    ('Маріуполь', 47.104500, 37.543761),
    ('Чернігів', 51.503600, 31.286362),
    ('Запоріжжя', 47.830958, 35.167202),
    ('Київ', 50.442673, 30.490800),
    ('Дніпро', 48.473107, 35.002404);

INSERT INTO city(name, longitude, latitude) VALUES
    ('Львів', 49.839683, 24.029717),
    ('Одеса', 46.482526, 30.723309),
    ('Харків', 49.9935, 36.2304),
    ('Тернопіль', 49.5535, 25.5948),
    ('Івано-Франківськ', 48.9226, 24.7111),
    ('Черкаси', 49.4444, 32.0595),
    ('Житомир', 50.2633, 28.6587),
    ('Ужгород', 48.6208, 22.2879),
    ('Рівне', 50.6199, 26.2516),
    ('Суми', 50.9077, 34.7981);

INSERT INTO restaurant(admin_id, address, longitude, latitude, city_id, phone, email)
VALUES (5, 'вул. Вишгородська 3', 48.459832, 35.031861, 5, '+380685573589', 'fastandfoodycorp@gmail.com'),
       (4, 'вул. Портова 6', 50.432090, 30.519048, 4, '+380984575567', 'fastandfoodycorp@gmail.com'),
       (3, 'бул. Шевченка 12', 47.862232, 35.080849, 3, '+380977842869', 'fastandfoodycorp@gmail.com'),
       (2, 'просп. Миру 154', 51.505058, 31.272312, 2, '+380687412589', 'fastandfoodycorp@gmail.com'),
       (1, 'просп. Соборний 198', 47.097155, 37.544608, 1, '+380671234675', 'fastandfoodycorp@gmail.com');

INSERT INTO restaurant(admin_id, address, longitude, latitude, city_id, phone, email)
VALUES
    (6, 'вул. Вірменська, 10', 49.839683, 24.029717, 6, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (7, 'вул. Шевченка, 20', 49.849683, 24.039717, 6, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (8, 'вул. Дерибасівська, 15', 46.462526, 30.733309, 7, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (9, 'вул. Приморська, 25', 46.482526, 30.723309, 7, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (10, 'вул. Сумська, 5', 49.9035, 36.2504, 8, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (11, 'вул. Пушкінська, 12', 49.9435, 36.2304, 8, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (12, 'вул. Степана Бандери, 30', 49.5735, 25.6048, 9, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (13, 'вул. Руська, 8', 49.5535, 25.5948, 9, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (14, 'вул. Грушевського, 40', 48.9426, 24.7311, 10, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (15, 'вул. Вічева, 5', 48.9226, 24.7111, 10, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (16, 'вул. Шевченка, 7', 49.4744, 32.0795, 11, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (17, 'вул. Гоголя, 18', 49.4444, 32.0595, 11, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (18, 'вул. Шевченка, 55', 50.2833, 28.6387, 12, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (19, 'вул. Київська, 12', 50.2633, 28.6587, 12, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (20, 'вул. Корятовича, 3', 48.6008, 22.3079, 13, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (21, 'вул. Кошицька, 15', 48.6208, 22.2879, 13, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (22, 'вул. Соборна, 20', 50.6599, 26.2816, 14, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (23, 'вул. Мазепи, 5', 50.6199, 26.2516, 14, '+380987654321', 'fastandfoodycorp@gmail.com'),
    (24, 'вул. Шевченка, 35', 50.9477, 34.8081, 15, '+380123456789', 'fastandfoodycorp@gmail.com'),
    (25, 'вул. Коцюбинського, 14', 50.9077, 34.7981, 15, '+380987654321', 'fastandfoodycorp@gmail.com');

INSERT INTO purchase(prep_time, wish, restaurant_id, payment_way, promo_code, status, delivery_way, order_item_id, person_id, address, date, sum)
VALUES
    (12, 'asdasdflkasdafas', 2, 'Card', 1, 'In_progress', 'PickUp', 2, 2, null, '2024-03-11 12:00:00', 210),
    (42, 'asdasdflkadsfghds', 3, 'Cash', 2, 'Delivered', 'Delivery', 3, 3, 'Харківське шосе, 148', '2023-09-20 12:00:00', 240),
    (45, 'asdasdflksdfgsdsfgs', 4, 'Card',3, 'On_way', 'PickUp', 4, 4, null,  '2024-02-20 12:00:00', 150),
    (24, 'asdasdflkassdfgsdf', 5, 'Cash', 4, 'In_progress', 'PickUp', 5, 5, null, '2023-10-20 12:00:00', 40);

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
UPDATE person set restaurant_id = 6 WHERE id = 6;
UPDATE person set restaurant_id = 7 WHERE id = 7;
UPDATE person SET restaurant_id = 7 WHERE id = 7;
UPDATE person SET restaurant_id = 8 WHERE id = 8;
UPDATE person SET restaurant_id = 9 WHERE id = 9;
UPDATE person SET restaurant_id = 10 WHERE id = 10;
UPDATE person SET restaurant_id = 11 WHERE id = 11;
UPDATE person SET restaurant_id = 12 WHERE id = 12;
UPDATE person SET restaurant_id = 13 WHERE id = 13;
UPDATE person SET restaurant_id = 14 WHERE id = 14;
UPDATE person SET restaurant_id = 15 WHERE id = 15;
UPDATE person SET restaurant_id = 16 WHERE id = 16;
UPDATE person SET restaurant_id = 17 WHERE id = 17;
UPDATE person SET restaurant_id = 18 WHERE id = 18;
UPDATE person SET restaurant_id = 19 WHERE id = 19;
UPDATE person SET restaurant_id = 20 WHERE id = 20;
UPDATE person SET restaurant_id = 21 WHERE id = 21;
UPDATE person SET restaurant_id = 22 WHERE id = 22;
UPDATE person SET restaurant_id = 23 WHERE id = 23;
UPDATE person SET restaurant_id = 24 WHERE id = 24;
UPDATE person SET restaurant_id = 25 WHERE id = 25;