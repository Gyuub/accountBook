-- GN.`member` definition

CREATE TABLE `member` (
`member_id` bigint(20) NOT NULL,
`email` varchar(255) DEFAULT NULL,
`nickname` varchar(255) DEFAULT NULL,
`password` varchar(255) DEFAULT NULL,
`delete_yn` char(1) DEFAULT NULL,
`create_id` varchar(40) DEFAULT NULL,
`create_date` datetime(6) DEFAULT NULL,
`modify_id` varchar(40) DEFAULT NULL,
`modify_date` datetime(6) DEFAULT NULL,
PRIMARY KEY (`member_id`),
UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- GN.member_role definition

CREATE TABLE `member_role` (
`authority_name` varchar(20) NOT NULL,
PRIMARY KEY (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- GN.member_authority definition
CREATE TABLE `member_authority` (
`member_authority_id` bigint(20) NOT NULL,
`authority_name` varchar(20) DEFAULT NULL,
PRIMARY KEY (`member_authority_id`),
KEY `FKksnn3qnjlokghmai5p60e9asf` (`authority_name`),
CONSTRAINT `FKksnn3qnjlokghmai5p60e9asf` FOREIGN KEY (`authority_name`) REFERENCES `member_role` (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- GN.category definition

CREATE TABLE `category` (
`categoty_id` bigint(20) NOT NULL,
`name` varchar(30) DEFAULT NULL,
PRIMARY KEY (`categoty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- GN.account definition

CREATE TABLE `account` (
`account_id` bigint(20) NOT NULL,
`account_name` varchar(50) NOT NULL,
`delete_yn` char(1) DEFAULT NULL,
`create_id` varchar(40) DEFAULT NULL,
`create_date` datetime(6) DEFAULT NULL,
`modify_id` varchar(40) DEFAULT NULL,
`modify_date` datetime(6) DEFAULT NULL,
PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- GN.account_detail definition

CREATE TABLE `account_detail` (
`account_detail_id` bigint(20) NOT NULL,
`detail_cd` varchar(10) DEFAULT NULL,
`title` varchar(50) DEFAULT NULL,
`contents` varchar(255) DEFAULT NULL,
`amount` int(11) DEFAULT NULL,
`write_date` datetime(6) DEFAULT NULL,
`member_id` bigint(20) DEFAULT NULL,
`account_id` bigint(20) DEFAULT NULL,
`category_id` bigint(20) DEFAULT NULL,
`delete_yn` char(1) DEFAULT NULL,
`create_id` varchar(40) DEFAULT NULL,
`create_date` datetime(6) DEFAULT NULL,
`modify_id` varchar(40) DEFAULT NULL,
`modify_date` datetime(6) DEFAULT NULL,
PRIMARY KEY (`account_detail_id`),
KEY `FKgmcrvfpbrxux7hy3svk9oy0cx` (`account_id`),
KEY `FK1j69isutl09gh9s84bb0hvdeb` (`category_id`),
KEY `FK5dkrnmrqp8lly2dt0jxqnlw84` (`member_id`),
CONSTRAINT `FK1j69isutl09gh9s84bb0hvdeb` FOREIGN KEY (`category_id`) REFERENCES `category` (`categoty_id`),
CONSTRAINT `FK5dkrnmrqp8lly2dt0jxqnlw84` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
CONSTRAINT `FKgmcrvfpbrxux7hy3svk9oy0cx` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- GN.authority definition

CREATE TABLE `authority` (
`authority_id` bigint(20) NOT NULL,
`role` varchar(10) DEFAULT NULL,
`account_id` bigint(20) DEFAULT NULL,
`member_id` bigint(20) DEFAULT NULL,
PRIMARY KEY (`authority_id`),
KEY `FKjt3k36kq6i87qn2doo3haenry` (`account_id`),
KEY `FKn0jr9c8garf53koe63e2ipa8q` (`member_id`),
CONSTRAINT `FKjt3k36kq6i87qn2doo3haenry` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
CONSTRAINT `FKn0jr9c8garf53koe63e2ipa8q` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





-- GN.sharing definition

CREATE TABLE `sharing` (
`sharing_id` bigint(20) NOT NULL,
`sharing_state` varchar(10) DEFAULT NULL,
`account_id` bigint(20) DEFAULT NULL,
`from_member_id` bigint(20) DEFAULT NULL,
`to_member_id` bigint(20) DEFAULT NULL,
`delete_yn` char(1) DEFAULT NULL,
`create_id` varchar(40) DEFAULT NULL,
`create_date` datetime(6) DEFAULT NULL,
`modify_id` varchar(40) DEFAULT NULL,
`modify_date` datetime(6) DEFAULT NULL,
PRIMARY KEY (`sharing_id`),
KEY `FKgwovn0fjk8nb898nn22q8dvbt` (`account_id`),
KEY `FKfyf3bpt4fwmy2afa8wsiaoec2` (`from_member_id`),
KEY `FKrj8hm805s3agexv9ueadq4gai` (`to_member_id`),
CONSTRAINT `FKfyf3bpt4fwmy2afa8wsiaoec2` FOREIGN KEY (`from_member_id`) REFERENCES `member` (`member_id`),
CONSTRAINT `FKgwovn0fjk8nb898nn22q8dvbt` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
CONSTRAINT `FKrj8hm805s3agexv9ueadq4gai` FOREIGN KEY (`to_member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;