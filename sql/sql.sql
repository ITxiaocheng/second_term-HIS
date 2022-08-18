
-- His-Whole信息管理系统

#创建数据库
CREATE DATABASE His_Whole;

#打开数据库
USER His_Whole;

#删除表
DROP TABLE His_login;

#创建登录&注册表
CREATE TABLE His_login(
	`id` INT PRIMARY KEY AUTO_INCREMENT, #序号
	`name` VARCHAR(20), #姓名
	`idNumber` VARCHAR(18), #身份证号
	`phoneNumber` VARCHAR(1000), #手机号码
	`password` VARCHAR(100), #密码
	`permission` INT #权限 0为用户 1为医生 2为管理员
);

#插入数据
INSERT INTO His_login(`name`,`idNumber`,`phoneNumber`,`password`, `permission`)VALUES
('江永芳', '362337111120031112', 19808006488, '123456', 0),
('憨小成', '372987178120031112', 15946811522, '123', 1),
('怡成', '382987178120031112', 16825954852, 'admin', 2);

#插入数据
INSERT INTO His_login(`name`,`idNumber`,`phoneNumber`,`password`, `permission`)VALUES
('欧伯泰克', '392337111120031112', 16486486489, '123456', 3),
('扁鹊', '402337111120031112', 13540404040, '123456', 2);

#查询所有数据
SELECT * FROM His_login;



-- 

-- 创建菜单表
CREATE TABLE menu(
 menu_id INT PRIMARY KEY AUTO_INCREMENT COMMENT "菜单id",
 menu_name VARCHAR(50) COMMENT "菜单名称",
 menu_show INT COMMENT "对应的权限才可以访问",
 menu_fatherId INT COMMENT "父级菜单id",
 menu_icon VARCHAR(50) COMMENT "菜单图标",
 menu_path VARCHAR(50) COMMENT "菜单路径"
);       
       
-- 添加菜单信息
INSERT INTO menu(menu_id,menu_name,menu_show,menu_fatherId,menu_icon,menu_path)
VALUES (1,"预约挂号",1,0,"document",""),
       (2,"普通挂号",1,1,"document","/aa"),
       
       (3,"就诊",    2,0,"PieChart",""),   
       (4,"开始就诊",2,3,"PieChart","/bb"),   
       (5,"电子病历",2,0,"User",""), 
       (6,"病历",    2,5,"User","/cc"), 
       (7,"电子处方",2,0,"PieChart",""),
       (8,"处方", 2,7,"PieChart", "/dd"),
       
       (9,"药房管理",3,0,"CollectionTag",""), 
       (10,"药房",   3,9,"CollectionTag","/ee"),     
          
       (11,"权限管理",0,0,"Edit",""),  
      (12,"医生管理",0,11,"Edit","/qq");

DROP TABLE menu;
       
       
SELECT * FROM menu;

SELECT * FROM his_login;

SELECT * FROM reg;

SELECT * FROM reg WHERE regDoctor = '扁鹊';
       
SELECT * FROM reg WHERE regCard = 220802012;



-- 创建病历表
CREATE TABLE caseHistory(
    `caseId` INT PRIMARY KEY AUTO_INCREMENT,
    `caseCard` INT, #就诊卡号
    `caseName` VARCHAR(100), #就诊人姓名
    `caseGender` VARCHAR(2), #就诊人性别
    `caseAge` INT,           #就诊人年龄
    `caseDate` VARCHAR(100), #候诊日期
    `caseIdNumber` VARCHAR(100), #就诊人身份证号 
    `caseMoney` VARCHAR(1000), #费别-----------------
    `caseDepartments` VARCHAR(100), #科室
    `caseDoctor` VARCHAR(50), #预约医生
    `caseComplaint` VARCHAR(1000), #主诉
    `casePHI` VARCHAR(1000), #现病史
    `casePreviousHistory` VARCHAR(1000), #既往史
    `caseHealthCheckup` VARCHAR(1000),  #体格检查
    `caseAuxiliaryExamination` VARCHAR(1000), #辅助检查
    `caseDiagnose` VARCHAR(1000), #诊断
    `caseOpinion` VARCHAR(1000), #处理意见
    `caseWriteDate` VARCHAR(1000) #书写日期 
)

#删除表
DROP TABLE caseHistory;

#删除结果
TRUNCATE TABLE caseHistory;

INSERT INTO caseHistory(`caseCard`,`caseName`,`caseGender`,`caseAge`,`caseDate`,`caseIdNumber`,`caseMoney`,`caseDepartments`,`caseDoctor`,`caseComplaint`, `casePHI`,`casePreviousHistory`,`caseHealthCheckup`, `caseAuxiliaryExamination`,`caseDiagnose`,`caseOpinion`,`caseWriteDate`)VALUES
(220802001, '憨小成', '男', 19, '2022-08-09', '365595988647772611','28元','普通外科门诊','扁鹊','主诉','现病史','既往史','体格检查','辅助检查','诊断','处理意见',NOW());

INSERT INTO caseHistory(`caseName`)VALUES(SELECT caseName FROM caseHistory WHERE caseName = '憨小成')

#降序
SELECT * FROM caseHistory ORDER  BY caseId DESC;

SELECT * FROM reg WHERE regDoctor = '扁鹊' ORDER BY regId LIMIT 0,2;

SELECT * FROM his_login;

SELECT COUNT(1) FROM reg WHERE regDoctor = '扁鹊'

SELECT * FROM reg WHERE regDoctor =  '扁鹊' ORDER BY regId LIMIT 0,1

SELECT * FROM reg  WHERE regDoctor = '扁鹊' AND regName LIKE '%张%' ORDER BY regId LIMIT 0,2

SELECT * FROM caseHistory WHERE caseName LIKE '%小%'; 

SELECT * FROM caseHistory WHERE caseCard = 220809080;

#药
CREATE TABLE medicine(
  `medId` INT PRIMARY KEY AUTO_INCREMENT,
  `medName` VARCHAR(1000), #药物名字
  `medUsage` VARCHAR(100) #药物用法
);
DROP TABLE medicine
INSERT INTO medicine(`medName`,`medUsage`)VALUES
('0.4%左氧氟沙星注射液 100ml×1瓶', '静脉滴注 每日1次'),
('0.5%甲硝唑注射液 100ml×1瓶', '静脉滴注 每日1次'),
('阿莫西林胶囊 0.25g×50粒', '2粒 每日3次 口服'),
('阿莫西林胶囊[0.5] 0.5g×24粒', '0.5g 口服 每日3次'),
('阿莫西林颗粒 0.125g×12袋', '1袋  每日3次 口服'),
('阿莫西林克拉维酸钾分散片 156.25mg×18片', '2片 每日3次 口服'),
('阿莫西林克拉维酸钾胶囊 156.25mg×18粒', '2粒 每日3次 口服'),
('阿奇霉素冲剂 0.1g×6包', ''),
('阿奇霉素分散片 0.25g×12粒', ''),
('阿奇霉素胶囊 0.25g×6粒', ''),
('阿奇霉素颗粒 0.1g×6袋', ''),
('阿奇霉素片 0.25g×6片', ''),
('阿奇霉素注射液 0.25×2支', ''),
('阿昔洛韦片 0.1g×24片', ''),
('奥硝唑胶囊 0.25g×12粒', ''),
('奥硝唑氯化钠 100ml×1瓶', ''),
('吡哌酸片 0.25g×100片', ''),
('大观霉素注射剂 2g×1支', '')


SELECT * FROM medicine;

SELECT * FROM medicine WHERE medClassification LIKE '%抗生素%'

SELECT * FROM medicine WHERE medStock <= 500


INSERT INTO medicine(medName,medUsualDose,medUnit,medUsageAndDosage,medSpell,medShapeCode,medClassification,medPurchasingPrice,medSellingPrice,medStock)VALUES
('aa阿莫西林aa','100mlx1','瓶','','fdsf', 'null', '西城要', 3, 4, 100)

SELECT * FROM medicine WHERE medName = 'aa阿莫西林aa'

SELECT * FROM medicine WHERE medName = '这次成功！';




DROP TABLE doctor
-- 医生挂号信息表
CREATE TABLE doctor(
  docId INT PRIMARY KEY AUTO_INCREMENT,
  docName VARCHAR(50), #专家名称
  docVisitTime VARCHAR(100), #出诊时间
  docTotalNumber INT, #总号
  docRemainingNumber INT, #余号
  docIntroduction VARCHAR(10000) #简介
);

INSERT INTO doctor(docName,docVisitTime,docTotalNumber,docRemainingNumber,docIntroduction)VALUES
('扁鹊','2022-08-11', 100, 100, '呼吸系统常见病多发病（如：肺部感染、占位等疾病）诊治、鉴别及急危重病人的救治、肿瘤化疗，熟练掌握气管镜、呼吸机等的应用。 '),
('怡成','2022-08-11', 100, 100, '从事内科临床30多年，有丰富的内科疾病诊断和治疗经验。特别对心血管疾病、呼吸道疾病以及消化系统、内分泌系统、神经系统、血液等系统疾病有一定的诊疗技巧。对复杂、疑难的内科疾病有独特的诊治技能。 ');

SELECT * FROM doctor

UPDATE FROM doctor SET WHERE docRemainingNumber - 1

UPDATE doctor SET docRemainingNumber=docRemainingNumber-1 WHERE docName='扁鹊';
