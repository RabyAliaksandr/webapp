-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.15 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных trainings_center
CREATE DATABASE IF NOT EXISTS `trainings_center` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `trainings_center`;

-- Дамп структуры для таблица trainings_center.consultations
CREATE TABLE IF NOT EXISTS `consultations` (
  `consultation_id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  `mentor_mark` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`consultation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.consultations_task
CREATE TABLE IF NOT EXISTS `consultations_task` (
  `consultation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  KEY `FK_consultations_task_consultations` (`consultation_id`),
  KEY `FK_consultations_task_users` (`user_id`),
  KEY `FK_consultations_task_tasks` (`task_id`),
  CONSTRAINT `FK_consultations_task_consultations` FOREIGN KEY (`consultation_id`) REFERENCES `consultations` (`consultation_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_consultations_task_tasks` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_consultations_task_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.consultations_topics
CREATE TABLE IF NOT EXISTS `consultations_topics` (
  `consultation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  KEY `FK_consultations_topics_consultations` (`consultation_id`),
  KEY `FK_consultations_topics_users` (`user_id`),
  KEY `FK_consultations_topics_topics_for_study` (`topic_id`),
  CONSTRAINT `FK_consultations_topics_consultations` FOREIGN KEY (`consultation_id`) REFERENCES `consultations` (`consultation_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_consultations_topics_topics_for_study` FOREIGN KEY (`topic_id`) REFERENCES `topics_for_study` (`topic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_consultations_topics_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.payment_cards
CREATE TABLE IF NOT EXISTS `payment_cards` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT,
  `card_number` mediumtext NOT NULL,
  `card_score` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.reviews
CREATE TABLE IF NOT EXISTS `reviews` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `review_text` varchar(1000) NOT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.student_task
CREATE TABLE IF NOT EXISTS `student_task` (
  `user_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  `mark` int(11) NOT NULL DEFAULT '0',
  KEY `FK_student_task_users` (`user_id`),
  KEY `FK_student_task_tasks` (`task_id`),
  CONSTRAINT `FK_student_task_tasks` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_student_task_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.student_topic
CREATE TABLE IF NOT EXISTS `student_topic` (
  `user_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `topic_status` tinyint(4) NOT NULL,
  KEY `FK_student_topic_users` (`user_id`),
  KEY `htgfd_idx` (`topic_id`),
  CONSTRAINT `FK_student_topic_topics_for_study` FOREIGN KEY (`topic_id`) REFERENCES `topics_for_study` (`topic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_student_topic_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `task_name` varchar(70) NOT NULL,
  `task` varchar(1000) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FK_tasks_trainings` (`training_id`),
  CONSTRAINT `FK_tasks_trainings` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`training_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.topics_for_study
CREATE TABLE IF NOT EXISTS `topics_for_study` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `name_topic` varchar(70) NOT NULL,
  `topic` varchar(1000) NOT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `FK_topics_for_study_trainings` (`training_id`),
  CONSTRAINT `FK_topics_for_study_trainings` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`training_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.trainings
CREATE TABLE IF NOT EXISTS `trainings` (
  `training_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `information` varchar(1000) NOT NULL,
  `mentor_id` int(11) NOT NULL,
  `training_status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`training_id`),
  KEY `FK_trainings_users` (`mentor_id`),
  CONSTRAINT `FK_trainings_users` FOREIGN KEY (`mentor_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.trainings_center_score
CREATE TABLE IF NOT EXISTS `trainings_center_score` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `consultation_id` int(11) NOT NULL,
  `sum` decimal(10,2) NOT NULL,
  `user_id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL,
  `payment_date` date NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK_trainings_center_score_consultations` (`consultation_id`),
  KEY `FK_trainings_center_score_users` (`user_id`),
  KEY `FK_trainings_center_score_payment_cards` (`card_id`),
  CONSTRAINT `FK_trainings_center_score_consultations` FOREIGN KEY (`consultation_id`) REFERENCES `consultations` (`consultation_id`),
  CONSTRAINT `FK_trainings_center_score_payment_cards` FOREIGN KEY (`card_id`) REFERENCES `payment_cards` (`card_id`),
  CONSTRAINT `FK_trainings_center_score_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.training_by_students
CREATE TABLE IF NOT EXISTS `training_by_students` (
  `user_id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  `grade_for_training` int(11) NOT NULL DEFAULT '0',
  KEY `FK_training_by_students_users` (`user_id`),
  KEY `FK_training_by_students_trainings` (`training_id`),
  CONSTRAINT `FK_training_by_students_trainings` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`training_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_training_by_students_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'guest',
  `user_status` varchar(45) NOT NULL DEFAULT 'unblocked',
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица trainings_center.users_payment_card
CREATE TABLE IF NOT EXISTS `users_payment_card` (
  `user_id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL,
  KEY `FK_users_payment_card_users` (`user_id`),
  KEY `FK_users_payment_card_payment_cards` (`card_id`),
  CONSTRAINT `FK_users_payment_card_payment_cards` FOREIGN KEY (`card_id`) REFERENCES `payment_cards` (`card_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_users_payment_card_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.

-- Дамп структуры для триггер trainings_center.update_student_task
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `update_student_task` AFTER INSERT ON `tasks` FOR EACH ROW BEGIN
    insert into student_task (user_id, task_id)
    SELECT   users.user_id, tasks.task_id
    FROM training_by_students  inner join tasks  using(training_id) left  join  student_task using (user_id, task_id) left join users  using (user_id)
    where student_task.user_id is  null;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Дамп структуры для триггер trainings_center.update_student_task_after_registered_student_at_training
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `update_student_task_after_registered_student_at_training` AFTER INSERT ON `training_by_students` FOR EACH ROW BEGIN
    insert into student_task (user_id, task_id)
    SELECT   users.user_id, tasks.task_id
    FROM training_by_students  inner join tasks  using(training_id) left  join  student_task using (user_id, task_id) left join users  using (user_id)
    where student_task.user_id is  null;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
