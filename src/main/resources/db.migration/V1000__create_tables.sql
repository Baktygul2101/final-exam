use `cafe_rating`;

CREATE TABLE `persons` (
                           id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                           `name` VARCHAR(64) NOT NULL,
                           `email` VARCHAR(64) NOT NULL,
                           `password` VARCHAR(64) NOT NULL
);


CREATE TABLE `galleries` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`photo` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `places` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(64) NOT NULL,
`gallery_id` bigint(20) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `FK_gallery` FOREIGN KEY (`gallery_id`) REFERENCES `galleries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `reviews` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`text` varchar(64) NOT NULL,
`place_id` bigint(20) NOT NULL,
`rating` int NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `FK_place` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;