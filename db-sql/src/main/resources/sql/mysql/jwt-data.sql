INSERT INTO `auth`.`oauth_client_details` (`client_id`,
                                    `resource_ids`,
                                    `client_secret`,
                                    `scope`,
                                    `authorized_grant_types`,
                                    `web_server_redirect_uri`,
                                    `authorities`,
                                    `access_token_validity`,
                                    `refresh_token_validity`,
                                    `additional_information`,
                                    `autoapprove`)
VALUES ('client',
        NULL,
        '$2a$10$GIELfSWVuxnLqpymNkH/Guhc0OXB86Z7DdOA20wEFhRUO/M4VYHwy',
        'all',
        'password,authorization_code,refresh_token,implicit,client_credentials',
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        'true');

INSERT INTO `auth`.`oauth_authority` VALUES ('2','-1', 'xsf', now(), null, now(), '查看demo', 'query-demo');
INSERT INTO `auth`.`oauth_authority` VALUES ('3','-1', 'xsf', now(), null, now(), '查看user', 'query-user');

INSERT INTO `auth`.`oauth_role` VALUES ('3', 'xsf', now(), null, now(), '管理员', 'ROLE_ADMIN');
INSERT INTO `auth`.`oauth_role` VALUES ('4', 'xsf', now(), null, now(), '普通用户', 'ROLE_USER');

INSERT INTO `auth`.`oauth_role_authorities` VALUES ('3', '2');

INSERT INTO `auth`.`oauth_user` VALUES ('5', 'xsf', now(), null, now(), null, null, null, null, '$2a$10$XOVs4Z1YtPKqKwQVywG9j.xLAqXYRQLGZMGMrZDNbtl6pUC0Weteq', 'admin', b'1', b'1', b'1', b'1');
INSERT INTO `auth`.`oauth_user` VALUES ('6', 'xsf', now(), null, now(), null, null, null, null, '$2a$10$XOVs4Z1YtPKqKwQVywG9j.xLAqXYRQLGZMGMrZDNbtl6pUC0Weteq', 'xsf', b'1', b'1', b'1', b'1');

INSERT INTO `auth`.`oauth_user_roles` VALUES ('5', '3');
INSERT INTO `auth`.`oauth_user_roles` VALUES ('6', '4');