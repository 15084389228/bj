CREATE TABLE `bu_clothes_order` (
                                    `id` int NOT NULL,
                                    `client_id` int DEFAULT NULL COMMENT '客户信息主键',
                                    `client_phone` varchar(255) DEFAULT NULL COMMENT '客户手机号',
                                    `client_name` varchar(255) DEFAULT NULL COMMENT '所洗衣服id',
                                    `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单总价格',
                                    `order_discount` int DEFAULT NULL COMMENT '所享折扣',
                                    `actual_price` decimal(10,2) DEFAULT NULL COMMENT '实付价格',
                                    `paper_num` varchar(255) DEFAULT NULL COMMENT '纸质订单号',
                                    `order_num` varchar(255) DEFAULT NULL COMMENT '订单号',
                                    `status` varchar(255) DEFAULT NULL COMMENT '订单状态(1:待处理  2:处理中  3:可取衣  4:已取)',
                                    `del_flag` varchar(255) DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
                                    `create_by` varchar(255) DEFAULT NULL,
                                    `create_time` datetime DEFAULT NULL,
                                    `update_by` varchar(255) DEFAULT NULL,
                                    `updateTime` datetime DEFAULT NULL,
                                    `remark` varchar(255) DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `bu_clothes_price` (
                                    `id` int NOT NULL,
                                    `clothes_type` int DEFAULT NULL COMMENT '衣服类型',
                                    `clothes_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '衣服名称',
                                    `clothes_price` decimal(10,2) DEFAULT NULL COMMENT '衣服价格',
                                    `clothes_group` int DEFAULT NULL COMMENT '衣服分组',
                                    `order_num` int DEFAULT NULL COMMENT '显示顺序',
                                    `status` int DEFAULT NULL COMMENT '状态（0正常 1停用）',
                                    `del_flag` varchar(255) DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
                                    `create_by` varchar(255) DEFAULT NULL,
                                    `create_time` datetime DEFAULT NULL,
                                    `update_by` varchar(255) DEFAULT NULL,
                                    `updateTime` datetime DEFAULT NULL,
                                    `remark` varchar(255) DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bu_member_info` (
                                  `id` int NOT NULL,
                                  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '会员名称',
                                  `member_phone` varchar(255) DEFAULT NULL COMMENT '会员手机号',
                                  `member_price` decimal(10,2) DEFAULT NULL COMMENT '会员余额',
                                  `member_type_id` int DEFAULT NULL COMMENT '会员卡id',
                                  `member_discount` int DEFAULT NULL COMMENT '会员折扣(专属折扣,覆盖会员卡折扣)',
                                  `status` int DEFAULT NULL COMMENT '会员状态',
                                  `del_flag` varchar(255) DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
                                  `create_by` varchar(255) DEFAULT NULL,
                                  `create_time` datetime DEFAULT NULL,
                                  `update_by` varchar(255) DEFAULT NULL,
                                  `updateTime` datetime DEFAULT NULL,
                                  `remark` varchar(255) DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `bu_member_type` (
                                  `id` int NOT NULL,
                                  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '会员名称',
                                  `member_phone` varchar(255) DEFAULT NULL COMMENT '会员手机号',
                                  `member_price` decimal(10,2) DEFAULT NULL COMMENT '会员余额',
                                  `member_type_id` int DEFAULT NULL COMMENT '会员卡id',
                                  `member_discount` int DEFAULT NULL COMMENT '会员折扣(专属折扣,覆盖会员卡折扣)',
                                  `status` int DEFAULT NULL COMMENT '会员状态',
                                  `del_flag` varchar(255) DEFAULT NULL COMMENT '删除标志（0代表存在 2代表删除）',
                                  `create_by` varchar(255) DEFAULT NULL,
                                  `create_time` datetime DEFAULT NULL,
                                  `update_by` varchar(255) DEFAULT NULL,
                                  `updateTime` datetime DEFAULT NULL,
                                  `remark` varchar(255) DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;