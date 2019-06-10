--order type
insert into order_type
values (11101, 'Shirt');
insert into order_type
values (11102, 'Pants');
insert into order_type
values (11103, 'Hat');
insert into order_type
values (11104, 'Shoes');
--user detail
insert into user_detail
values (1001, 'mwidyr@gmail.com', 'M Widy Ramadhani', 6282220056885, 'sakti123', 'mwidyr');
insert into user_detail
values (1002, 'lalala@gmail.com', 'Lalala', 6282220056885, 'sakti123', 'lalala');
--user login
insert into user_login
values (1001, 'sakti123', 'simple-market', 'mwidyr',1001);
--order table
-- insert into order_table
-- values (1001, 5, 140000, 1001);
--order detail
insert into order_detail
values (1001, 140000, 5, 1001);
--object detail
insert into object_detail
values ( 1001, 'Just a normal Shirt', 'Normal Shirt', 25000, 'Shirt'
       , 'https://i.pinimg.com/originals/1f/f0/74/1ff07470cb1da4ac0dd66720aabdcb7e.jpg',1001);
insert into object_detail
values ( 1002, 'Just a normal Girl Shirt', 'Girl Shirt', 35000, 'Shirt'
       , 'https://www.dhresource.com/0x0s/f2-albu-g7-M00-06-B5-rBVaSlso2-GAaoh8AAJppvo1xTs791.jpg/8026-woolen-maternity-coats-dress-2017-autumn.jpg'
       ,1001);
insert into object_detail
values ( 1003, 'Just a normal Pants', 'Normal Pants', 45000, 'Pants'
       , 'https://www.veromoda.com/dw/image/v2/ABBT_PRD/on/demandware.static/-/Sites-pim-catalog/default/dw2eab096b/pim-static/large/10214120_DarkGreyMelange_003_ProductLarge.jpg?sw=685'
       ,1001);
insert into object_detail
values ( 1004, 'Just a normal Hat', 'Normal Hat', 15000, 'Hat'
       , 'https://cdn.shopify.com/s/files/1/0749/3241/products/LeatherPatchTrucker1_900x.jpg?v=1557936705',1001);
insert into object_detail
values ( 1005, 'Just a normal Shoes', 'Normal Shoes', 20000, 'Shoes'
       , 'https://in.pricenacdn.com/files/images/products/original/162/Converse-All-Star-150765CCTOX-Normal-Sneakers-Black-Casual-Shoes_17342784_914e21ed040f6ce2264970e578bf084b_t.jpg'
       ,1001);
