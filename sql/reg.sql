his_wholehis_whole
-- 挂号信息表

#打开数据库
USE his_whole;

#创建表
CREATE TABLE reg(
  `regId` INT PRIMARY KEY AUTO_INCREMENT,
  `regName` VARCHAR(100),
  `regPhoneNumber` VARCHAR(100),
  `regDate` VARCHAR(100),
  `regClinicalDepartment` VARCHAR(100),
  `regDoctor` VARCHAR(100),
  `regMoney` VARCHAR(100)
)

#插入数据
INSERT INTO reg(`regName`,`regPhoneNumber`,`regDate`,`regClinicalDepartment`,`regDoctor`,`regMoney`)
VALUES('潇潇', '18665478962', '2022-09-10 08:00-9:00', '急诊内科门诊', '小成', '66元'),
('丽丽', '18665478962', '2022-10-10 13:00-14:00', '普通内科门诊', '小怡', '38元');

INSERT INTO reg(`regName`,`regPhoneNumber`,`regDate`,`regClinicalDepartment`,`regDoctor`,`regMoney`)
VALUES('王五', '18665478962', '2022-09-10 08:00-9:00', '急诊内科门诊', '小成', '66元'),
('老六', '18665478962', '2022-10-10 13:00-14:00', '普通内科门诊', '小怡', '38元');

#删除表
DROP TABLE reg;

#查看表结构
DESC reg;


CREATE TABLE reg(
  `regId` INT PRIMARY KEY AUTO_INCREMENT,
  `regCard` INT, #就诊卡号
  `regName` VARCHAR(100), #就诊人姓名
  `regGender` VARCHAR(2), #就诊人性别
  `regAge` INT,           #就诊人年龄
  `regDate` VARCHAR(100), #候诊日期
  `regIdNumber` VARCHAR(100), #就诊人身份证号
  `regSite` VARCHAR(200), #就诊人家庭地址
  `regDepartments` VARCHAR(100), #科室
  `regDoctor` VARCHAR(50), #预约医生
  `regState` VARCHAR(20) DEFAULT '未就诊' #就诊状态
);

INSERT INTO reg(`regCard`,`regName`,`regGender`,`regAge`,`regDate`,`regIdNumber`,`regSite`,`regDepartments`,`regDoctor`)
VALUES
(220802001,'张三','男',45, '2022-07-29', '361101200501119822', '望城区普瑞西路139号','外科A区03室', '扁鹊' ),
(220802002,'李四','男',35, '2022-07-30', '351101200501119822', '天心区239号','内科A区01室', '小李' ),
(220802003,'王五','女',25, '2022-07-31', '331101200501119822', '岳麓区339号','外科C区06室', '扁鹊' );

INSERT INTO reg(`regName`,`regGender`,`regAge`,`regDate`,`regIdNumber`,`regSite`,`regDepartments`,`regDoctor`)
VALUES
('李四','男',35, '2022-07-30', '351101200501119822', '天心区239号','内科A区01室', '小李'),
('王五','女',25, '2022-07-31', '331101200501119822', '岳麓区339号','外科C区06室', '扁鹊');

#模糊查询
SELECT * FROM reg WHERE regName LIKE '%三%';

SELECT * FROM reg;


INSERT INTO his_login(`name`,`idNumber`,`phoneNumber`,`password`)
VALUES('怡成', '3911292200108152711','19625412624', 'cjy123');

UPDATE his_login SET NAME='obtk',PASSWORD='obtk321' WHERE id = 8;

SELECT * FROM his_login;

-- 

#查询最后一条数据的id
SELECT regId FROM reg ORDER BY regId DESC LIMIT 1;

#根据id查询更新就诊状态
UPDATE reg SET regState='已就诊' WHERE regId = 15;


SELECT * FROM reg;


