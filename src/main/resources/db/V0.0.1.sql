CREATE TABLE `account` (
   `id` int NOT NULL AUTO_INCREMENT,
   `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
   `age` int NOT NULL DEFAULT '0' COMMENT '年龄',
   `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
   `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
   `disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用0否1是',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `login` (
   `id` int NOT NULL AUTO_INCREMENT,
   `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
   `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
   `login_type` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录方式USER_NAME：用户名；PHONE:手机号',
   `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `good` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `sn` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '编码\n',
                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
                        `alias` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '别名',
                        `picture` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片\n',
                        `stock` int NOT NULL DEFAULT '0' COMMENT '库存',
                        `cost_price` decimal(10,2) NOT NULL COMMENT '进货价\n',
                        `price` decimal(10,2) NOT NULL COMMENT '售价',
                        `status` tinyint NOT NULL DEFAULT '1' COMMENT '上下架0上架；1下架',
                        `operate` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作人',
                        `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;