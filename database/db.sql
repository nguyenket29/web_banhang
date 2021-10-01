-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project_website
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (49,'tiendungit24@gmail.com','2021-09-27 10:40:54','tiendungit24@gmail.com','2021-09-27 10:40:54',4,31,1);
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,NULL,NULL,NULL,NULL,'Accessories','Accessories'),(2,NULL,NULL,NULL,NULL,'Designer Boots','Designer Boots'),(3,NULL,NULL,NULL,NULL,'Footwear','Footwear'),(5,NULL,NULL,NULL,NULL,'Shirts','Shirts'),(6,NULL,NULL,NULL,NULL,'Shorts','Shorts');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (30,'ketmax121@gmail.com','2021-09-27 09:51:47','ketmax121@gmail.com','2021-09-27 09:51:47','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Blue Forest Red Dress',50,5,'image1632711106772.jpg',1),(31,'ketmax121@gmail.com','2021-09-27 09:55:35','ketmax121@gmail.com','2021-09-27 16:38:15','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam','Black Denim Designer Outfit',150,4,NULL,1),(32,'ketmax121@gmail.com','2021-09-27 10:10:08','ketmax121@gmail.com','2021-09-27 10:10:08','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Satin French Dress',150,6,'image1632712207803.jpg',1),(33,'ketmax121@gmail.com','2021-09-27 10:10:58','ketmax121@gmail.com','2021-09-27 10:10:58','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Elegant White Night Shirt',450,6,'image1632712257623.jpg',1),(35,'ketmax121@gmail.com','2021-09-27 10:13:47','ketmax121@gmail.com','2021-09-27 10:13:47','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Velvet Drape Blue Skirt',180,3,'image1632712426509.jpg',2),(36,'ketmax121@gmail.com','2021-09-27 10:14:33','ketmax121@gmail.com','2021-09-27 10:14:33','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Evening Black Night Skirt',550,3,'image1632712472509.jpg',2),(37,'ketmax121@gmail.com','2021-09-27 10:15:34','ketmax121@gmail.com','2021-09-27 10:15:34','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Long Sleeve Red Night Dress',250,5,'image1632712533945.jpg',2),(38,'ketmax121@gmail.com','2021-09-27 10:16:27','ketmax121@gmail.com','2021-09-27 10:16:27','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Elegant White Night Skirt',450,5,'image1632712587382.jpg',2),(39,'ketmax121@gmail.com','2021-09-27 10:17:08','ketmax121@gmail.com','2021-09-27 10:17:08','Take your shoe style to new heights with this alluring peep toe court shoe. Features a slim high heel and metallic detailing along the platform. Team with a high waisted pencil skirt and midi top for after dark glam.','Black Denim Designer Suit',500,5,'image1632712627683.jpg',2),(40,'ketmax121@gmail.com','2021-09-27 10:21:57','ketmax121@gmail.com','2021-09-27 10:21:57','Lightweight stretch construction improves mobility for full range of motion Anti-microbial technology keeps your gear smelling fresher, longer.','Tech Tee',39.99,10,'image1632712916542.jpg',5),(41,'ketmax121@gmail.com','2021-09-27 10:22:31','ketmax121@gmail.com','2021-09-27 10:22:31','Lightweight stretch construction improves mobility for full range of motion Anti-microbial technology keeps your gear smelling fresher, longer.','Performance polo',54.99,8,'image1632712950954.jpg',5),(42,'ketmax121@gmail.com','2021-09-27 10:23:01','ketmax121@gmail.com','2021-09-27 10:23:01','Lightweight stretch construction improves mobility for full range of motion Anti-microbial technology keeps your gear smelling fresher, longer.','Football Tee',36.99,8,'image1632712980925.jpg',5),(43,'ketmax121@gmail.com','2021-09-27 10:23:43','ketmax121@gmail.com','2021-09-27 10:23:43','Lightweight stretch construction improves mobility for full range of motion Anti-microbial technology keeps your gear smelling fresher, longer.','Training Shorts',44,4,'image1632713022539.jpg',6),(44,'ketmax121@gmail.com','2021-09-27 10:24:09','ketmax121@gmail.com','2021-09-27 10:24:09','Lightweight stretch construction improves mobility for full range of motion Anti-microbial technology keeps your gear smelling fresher, longer.','Chino Shorts',69.5,4,'image1632713049342.jpg',6),(45,'ketmax121@gmail.com','2021-09-27 10:27:39','ketmax121@gmail.com','2021-09-27 10:27:39','Timberland PRO A1KJ8 Women\'s Direct Attach 6\'\'','Steel Toe Boots',700,3,'image1632713258741.jpg',3),(46,'ketmax121@gmail.com','2021-09-27 10:28:27','ketmax121@gmail.com','2021-09-27 10:28:27','Women 3 inch Heel Ladies Boots- LB-03-1 BLACK','Colour',100,3,'image1632713307366.jpg',3),(47,'ketmax121@gmail.com','2021-09-27 10:29:35','ketmax121@gmail.com','2021-09-27 10:29:35','Women 3 inch Heel Ladies Boots- LB-03-1 BLACK',' Comfort Slipper Colour',44.91,3,'image1632713374817.jpg',3);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'USER','USER'),(2,'ADMIN','ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles_users`
--

LOCK TABLES `roles_users` WRITE;
/*!40000 ALTER TABLE `roles_users` DISABLE KEYS */;
INSERT INTO `roles_users` VALUES (1,1),(3,1),(2,2);
/*!40000 ALTER TABLE `roles_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tiendungit24@gmail.com','Dũng','Phạm Tiến','$2a$10$yFcX3kXMd4XkQub4HoWzYujaxeJbn5tqtoGDQlQmjzl9OB9xBxUTy'),(2,'ketmax121@gmail.com','Kết','Nguyễn','$2a$10$z/8.isnUFpUt7NSvgmrNKOpLgYAdEu/MwNxspGmiGGOxWPq2K6WqS'),(3,'huylonghau@gmail.com','Huy Long','Nguyễn','$2a$10$LCXLddXMaWcJ2KkfFFipieCQvfO.XtFlDYYQV05wJgmPv/jct66xK');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-01  9:03:47
