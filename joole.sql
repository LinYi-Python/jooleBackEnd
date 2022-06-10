/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : joole

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 09/06/2022 11:10:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
BEGIN;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (44);
COMMIT;

-- ----------------------------
-- Table structure for manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `manufacturer`;
CREATE TABLE `manufacturer` (
  `id` int NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `web_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of manufacturer
-- ----------------------------
BEGIN;
INSERT INTO `manufacturer` (`id`, `create_time`, `department`, `email`, `phone`, `update_time`, `web_url`) VALUES (1, '2022-05-27 01:32:04.076763', 'department1', ' manu1@gmail.com', ' 666', '2022-05-27 01:32:04.076763', 'manu1.com');
INSERT INTO `manufacturer` (`id`, `create_time`, `department`, `email`, `phone`, `update_time`, `web_url`) VALUES (21, '2022-05-27 01:33:39.181306', 'department1', ' manu1@gmail.com', ' 666', '2022-05-27 01:33:39.181306', 'manu1.com');
INSERT INTO `manufacturer` (`id`, `create_time`, `department`, `email`, `phone`, `update_time`, `web_url`) VALUES (26, '2022-05-27 01:54:22.831618', 'department1', ' manu1@gmail.com', ' 666', '2022-05-27 01:54:22.831618', 'manu1.com');
INSERT INTO `manufacturer` (`id`, `create_time`, `department`, `email`, `phone`, `update_time`, `web_url`) VALUES (31, '2022-05-27 02:02:59.258892', 'department1', ' manu1@gmail.com', ' 666', '2022-05-27 02:02:59.258892', 'manu1.com');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `model_year` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `manufacturer_detail_id_id` int DEFAULT NULL,
  `product_type_id` int DEFAULT NULL,
  `sale_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdbi0m2n9407gp6p4omwlyxtk5` (`manufacturer_detail_id_id`),
  KEY `FKlabq3c2e90ybbxk58rc48byqo` (`product_type_id`),
  KEY `FKtgpfnkn7etmfumakc3iq75e95` (`sale_id`),
  CONSTRAINT `FKdbi0m2n9407gp6p4omwlyxtk5` FOREIGN KEY (`manufacturer_detail_id_id`) REFERENCES `manufacturer` (`id`),
  CONSTRAINT `FKlabq3c2e90ybbxk58rc48byqo` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`),
  CONSTRAINT `FKtgpfnkn7etmfumakc3iq75e95` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` (`id`, `brand`, `create_time`, `model_year`, `name`, `update_time`, `manufacturer_detail_id_id`, `product_type_id`, `sale_id`) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `product` (`id`, `brand`, `create_time`, `model_year`, `name`, `update_time`, `manufacturer_detail_id_id`, `product_type_id`, `sale_id`) VALUES (2, 'Meta', '2022-05-27 01:31:03.390503', 2022, 'Test1', '2022-05-27 01:31:03.390503', NULL, NULL, NULL);
INSERT INTO `product` (`id`, `brand`, `create_time`, `model_year`, `name`, `update_time`, `manufacturer_detail_id_id`, `product_type_id`, `sale_id`) VALUES (14, 'Meta', '2022-05-27 01:32:04.053439', 2022, 'Test1', '2022-05-27 01:32:04.053439', NULL, NULL, NULL);
INSERT INTO `product` (`id`, `brand`, `create_time`, `model_year`, `name`, `update_time`, `manufacturer_detail_id_id`, `product_type_id`, `sale_id`) VALUES (19, 'Meta', '2022-05-27 01:33:39.163458', 2022, 'Test1', '2022-05-27 01:33:39.216807', NULL, 20, NULL);
INSERT INTO `product` (`id`, `brand`, `create_time`, `model_year`, `name`, `update_time`, `manufacturer_detail_id_id`, `product_type_id`, `sale_id`) VALUES (24, 'Meta', '2022-05-27 01:54:22.809011', 2022, 'Test1', '2022-05-27 01:54:22.878004', 26, 25, 27);
INSERT INTO `product` (`id`, `brand`, `create_time`, `model_year`, `name`, `update_time`, `manufacturer_detail_id_id`, `product_type_id`, `sale_id`) VALUES (29, 'Meta', '2022-05-27 02:02:59.237350', 2022, 'Test1', '2022-05-27 02:02:59.304089', 31, 30, 32);
INSERT INTO `product` (`id`, `brand`, `create_time`, `model_year`, `name`, `update_time`, `manufacturer_detail_id_id`, `product_type_id`, `sale_id`) VALUES (37, 'ios', '2022-05-27 02:41:04.978477', 2077, 'testPost', '2022-05-27 02:41:05.004879', 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` int NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `product_type_detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of product_type
-- ----------------------------
BEGIN;
INSERT INTO `product_type` (`id`, `create_time`, `product_type_detail`) VALUES (1, '2022-05-27 01:32:04.068943', 'TypeTest1');
INSERT INTO `product_type` (`id`, `create_time`, `product_type_detail`) VALUES (20, '2022-05-27 01:33:39.175313', 'TypeTest1');
INSERT INTO `product_type` (`id`, `create_time`, `product_type_detail`) VALUES (25, '2022-05-27 01:54:22.822315', 'TypeTest1');
INSERT INTO `product_type` (`id`, `create_time`, `product_type_detail`) VALUES (30, '2022-05-27 02:02:59.251210', 'TypeTest1');
COMMIT;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`),
  CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` (`id`, `create_time`, `project_name`, `update_time`, `user_id`) VALUES (1, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for project_product
-- ----------------------------
DROP TABLE IF EXISTS `project_product`;
CREATE TABLE `project_product` (
  `id` int NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `project_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9coy5prmdj0e5r3sn9kl2vxrt` (`product_id`),
  KEY `FKhvi1cuh89n04xttom724fdgvr` (`project_id`),
  CONSTRAINT `FK9coy5prmdj0e5r3sn9kl2vxrt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKhvi1cuh89n04xttom724fdgvr` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of project_product
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `id` int NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `web_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sale
-- ----------------------------
BEGIN;
INSERT INTO `sale` (`id`, `create_time`, `email`, `name`, `phone`, `update_time`, `web_url`) VALUES (1, '2022-05-27 01:32:04.083661', 'saletest1.com', 'saletest1', '111', '2022-05-27 01:32:04.083661', ' testsale@gmail.com');
INSERT INTO `sale` (`id`, `create_time`, `email`, `name`, `phone`, `update_time`, `web_url`) VALUES (22, '2022-05-27 01:33:39.186991', 'saletest1.com', 'saletest1', '111', '2022-05-27 01:33:39.186991', ' testsale@gmail.com');
INSERT INTO `sale` (`id`, `create_time`, `email`, `name`, `phone`, `update_time`, `web_url`) VALUES (27, '2022-05-27 01:54:22.840319', 'saletest1.com', 'saletest1', '111', '2022-05-27 01:54:22.840319', ' testsale@gmail.com');
INSERT INTO `sale` (`id`, `create_time`, `email`, `name`, `phone`, `update_time`, `web_url`) VALUES (32, '2022-05-27 02:02:59.265333', 'saletest1.com', 'saletest1', '111', '2022-05-27 02:02:59.265333', ' testsale@gmail.com');
COMMIT;

-- ----------------------------
-- Table structure for technical_detail
-- ----------------------------
DROP TABLE IF EXISTS `technical_detail`;
CREATE TABLE `technical_detail` (
  `id` int NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `technical_detail_name` varchar(255) DEFAULT NULL,
  `technical_detail_number` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `product_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeoi4c36ctq6x86139t9oitbsl` (`product_id`),
  KEY `FKcknnnh6fq6iko58xqmtoqlx3b` (`product_type_id`),
  CONSTRAINT `FKcknnnh6fq6iko58xqmtoqlx3b` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`),
  CONSTRAINT `FKeoi4c36ctq6x86139t9oitbsl` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of technical_detail
-- ----------------------------
BEGIN;
INSERT INTO `technical_detail` (`id`, `create_time`, `technical_detail_name`, `technical_detail_number`, `update_time`, `product_id`, `product_type_id`) VALUES (1, '2022-05-27 01:31:03.351172', 'TestAirflow', 6000, '2022-05-27 02:41:04.999714', 37, 1);
INSERT INTO `technical_detail` (`id`, `create_time`, `technical_detail_name`, `technical_detail_number`, `update_time`, `product_id`, `product_type_id`) VALUES (13, '2022-05-27 01:32:04.012519', 'TestAirflow', 6000, '2022-05-27 01:32:04.012519', NULL, NULL);
INSERT INTO `technical_detail` (`id`, `create_time`, `technical_detail_name`, `technical_detail_number`, `update_time`, `product_id`, `product_type_id`) VALUES (18, '2022-05-27 01:33:39.131446', 'TestAirflow', 6000, '2022-05-27 01:33:39.208476', 19, 20);
INSERT INTO `technical_detail` (`id`, `create_time`, `technical_detail_name`, `technical_detail_number`, `update_time`, `product_id`, `product_type_id`) VALUES (23, '2022-05-27 01:54:22.775614', 'TestAirflow', 6000, '2022-05-27 01:54:22.865725', 24, 25);
INSERT INTO `technical_detail` (`id`, `create_time`, `technical_detail_name`, `technical_detail_number`, `update_time`, `product_id`, `product_type_id`) VALUES (28, '2022-05-27 02:02:59.186860', 'TestAirflow', 6000, '2022-05-27 02:02:59.286368', 29, 30);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture_url` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `create_time`, `name`, `password`, `profile_picture_url`, `role`, `update_time`, `username`) VALUES (1, '2022-05-26 15:32:20.389468', NULL, '$2a$10$3nGEU//qmrGmRnuXqSnc3u920gR/UHRvYnuJOvCpMqa3np5TH1pdu', NULL, 'ROLE_USER', '2022-05-26 15:32:20.389468', 'test1');
INSERT INTO `user` (`id`, `create_time`, `name`, `password`, `profile_picture_url`, `role`, `update_time`, `username`) VALUES (33, '2022-05-27 02:16:59.432938', NULL, '$2a$10$qt0fpgdErNuXajg8M8wSeO66iJfeUIphQLxf.gQ7s0lz4a38/qQ6m', NULL, 'ROLE_USER', '2022-05-27 02:16:59.432938', 'test2');
INSERT INTO `user` (`id`, `create_time`, `name`, `password`, `profile_picture_url`, `role`, `update_time`, `username`) VALUES (34, '2022-05-27 02:17:12.187822', NULL, '$2a$10$pcH/dG.Ii3HMP/l4zYFKquIxsxmPaLnD342W.PUCYwHKe6BkAeeFu', NULL, 'ROLE_USER', '2022-05-27 02:17:12.187822', 'test3');
INSERT INTO `user` (`id`, `create_time`, `name`, `password`, `profile_picture_url`, `role`, `update_time`, `username`) VALUES (35, '2022-05-27 02:35:10.683269', NULL, '$2a$10$8BTwDQQz8z00v7zncI5tJu3dZOZ/Oa0Mgb3aQKsnt.piUkSkmUvAi', NULL, 'ROLE_MANUFACTURER', '2022-05-27 02:35:10.683269', 'test5');
INSERT INTO `user` (`id`, `create_time`, `name`, `password`, `profile_picture_url`, `role`, `update_time`, `username`) VALUES (36, '2022-05-27 02:36:52.946104', NULL, '$2a$10$ClPrBqxvkMw5AHHyLzv4Bun/s4.iO.rI78LGgc8PePVFHlZAjnp/i', NULL, 'ROLE_ADMIN', '2022-05-27 02:36:52.946104', 'test4');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
