DROP TABLE IF EXISTS `jwt_client_details`;
CREATE TABLE `jwt_client_details` (
  `client_id`               varchar(30) NOT NULL,
  `resource_ids`            varchar(256)  DEFAULT NULL,
  `client_secret`           varchar(256)  DEFAULT NULL,
  `scope`                   varchar(256)  DEFAULT NULL,
  `authorized_grant_types`  varchar(256)  DEFAULT NULL,
  `web_server_redirect_uri` varchar(256)  DEFAULT NULL,
  `authorities`             varchar(256)  DEFAULT NULL,
  `access_token_validity`   int(11)       DEFAULT NULL,
  `refresh_token_validity`  int(11)       DEFAULT NULL,
  `additional_information`  varchar(4096) DEFAULT NULL,
  `autoapprove`             varchar(256)  DEFAULT NULL,
  PRIMARY KEY (`client_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `jwt_authority`;
CREATE TABLE `jwt_authority` (
  `id`                 bigint(20)  NOT NULL AUTO_INCREMENT,
  `parent_id`          bigint(20)  NOT NULL,
  `created_by`         varchar(50) NOT NULL,
  `created_date`       datetime    NOT NULL,
  `last_modified_by`   varchar(50)          DEFAULT NULL,
  `last_modified_date` datetime             DEFAULT NULL,
  `name`               varchar(255)         DEFAULT NULL,
  `value`              varchar(255)         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `jwt_role`;
CREATE TABLE `jwt_role` (
  `id`                 bigint(20)  NOT NULL AUTO_INCREMENT,
  `created_by`         varchar(50) NOT NULL,
  `created_date`       datetime    NOT NULL,
  `last_modified_by`   varchar(50)          DEFAULT NULL,
  `last_modified_date` datetime             DEFAULT NULL,
  `name`               varchar(255)         DEFAULT NULL,
  `value`              varchar(255)         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `jwt_role_authorities`;
CREATE TABLE `jwt_role_authorities` (
  `jwt_role_id`  bigint(20) NOT NULL,
  `authorities_id` bigint(20) NOT NULL,
  PRIMARY KEY (`jwt_role_id`, `authorities_id`),
  KEY `FK_authorities_id` (`authorities_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `jwt_user`;
CREATE TABLE `jwt_user` (
  `id`                      bigint(20)  NOT NULL AUTO_INCREMENT,
  `created_by`              varchar(50) NOT NULL,
  `created_date`            datetime    NOT NULL,
  `last_modified_by`        varchar(50)          DEFAULT NULL,
  `last_modified_date`      datetime             DEFAULT NULL,
  `email`                   varchar(100)         DEFAULT NULL,
  `first_name`              varchar(50)          DEFAULT NULL,
  `image_url`               varchar(256)         DEFAULT NULL,
  `last_name`               varchar(50)          DEFAULT NULL,
  `password`                varchar(60) NOT NULL,
  `username`                varchar(50) NOT NULL,
  `account_non_expired`     bit(1)               DEFAULT b'1',
  `account_non_locked`      bit(1)               DEFAULT b'1',
  `credentials_non_expired` bit(1)               DEFAULT b'1',
  `enabled`                 bit(1)               DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_username` (`username`),
  UNIQUE KEY `UK_email` (`email`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `jwt_user_roles`;
CREATE TABLE `jwt_user_roles` (
  `jwt_user_id` bigint(20) NOT NULL,
  `roles_id`      bigint(20) NOT NULL,
  PRIMARY KEY (`jwt_user_id`, `roles_id`),
  KEY `FK_roles_id` (`roles_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;