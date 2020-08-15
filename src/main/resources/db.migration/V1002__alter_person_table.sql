DROP TABLE `persons`;
DROP TABLE `galleries`;
DROP TABLE `places`;
DROP TABLE `reviews`;


CREATE TABLE `persons` (
                           id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                           `name` VARCHAR(64) NOT NULL,
                           `email` VARCHAR(64) NOT NULL,
                           `password` VARCHAR(64) NOT NULL,
                           `enabled` boolean NOT NULL default true,
                           `role` varchar(16) NOT NULL default 'USER',
     PRIMARY KEY (`id`),
     UNIQUE INDEX `email_unique` (`email` ASC)
);

CREATE TABLE `galleries` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`photo` varchar(64) NOT NULL,
`name` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `places` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `gallery_id` bigint NOT NULL,
  `person_id` bigint NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_gallery_id` (`gallery_id`),
  KEY `IDX_person_id` (`person_id`),
  CONSTRAINT `FK_person` FOREIGN KEY (`person_id`) REFERENCES `persons` (`id`),
  CONSTRAINT `FK_gallery` FOREIGN KEY (`gallery_id`) REFERENCES `galleries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



CREATE TABLE `reviews` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rating` int NOT NULL,
  `text` varchar(255) NOT NULL,
  `place_id` bigint NOT NULL,
  `person_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_place_id` (`place_id`),
  KEY `IDX_person_id` (`person_id`),
  CONSTRAINT `FK_person` FOREIGN KEY (`person_id`) REFERENCES `persons` (`id`),
  CONSTRAINT `FK_place` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci