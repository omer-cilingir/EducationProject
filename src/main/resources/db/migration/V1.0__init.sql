-- comments: table
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `file_url` varchar(50) DEFAULT NULL,
  `source_user_id` bigint(20) DEFAULT NULL,
  `target_question_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6tthablduq1nn6dvlntel5nf` (`source_user_id`),
  KEY `FKnf8xd94xlfjxpuhj3sy2gwh5f` (`target_question_id`)
);

-- document: table
CREATE TABLE `document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `is_paid_document` bit(1) NOT NULL,
  `price` int(11) NOT NULL,
  `source_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3jua1o4uqdytqlb2v56s5omt8` (`source_user_id`)
);

-- follow: table
CREATE TABLE `follow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_confirmation` bit(1) NOT NULL,
  `source_user_id` bigint(20) DEFAULT NULL,
  `target_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2o1d708r3xevi905oow4ccums` (`source_user_id`),
  KEY `FKhpprnfdxo7qs05rl9rwjentby` (`target_user_id`)
);

-- preparation_exam: table
CREATE TABLE `preparation_exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- purchased document: table
CREATE TABLE `puchased_document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `source_document_id` bigint(20) DEFAULT NULL,
  `source_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgtmto5l1vh3dqkl4o44hbeu5a` (`source_document_id`),
  KEY `FKah4re152anwwpocanjfc0dttq` (`source_user_id`)
);


-- questions: table
CREATE TABLE `questions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `file_url` varchar(50) DEFAULT NULL,
  `source_user_id` bigint(20) DEFAULT NULL,
  `target_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3s14ykluyypcabnutuke1kgnj` (`source_user_id`),
  KEY `FK3ffre5ksuqpeow0gg0e3hi3oq` (`target_user_id`)
);

-- role: table
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- subject: table
CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `preparation_exam_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ltex04oeoi8l163ubj5fru74` (`preparation_exam_id`)
);

-- user: table
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `activation_key` varchar(255) DEFAULT NULL,
  `credit` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- user_role: table
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
);
