alter table bikes drop constraint fkk611h7nerl9nmcychdp2wf9j2;

drop table user_granted_authorities_list cascade;

alter table technical_passports drop COLUMN bike_name;

alter table technical_passports drop COLUMN client_name;

alter table technical_passports drop COLUMN replacement_parts;

alter table technical_passports drop COLUMN service_men_comments;

alter table technical_passports drop COLUMN service_work;

alter table technical_passports drop COLUMN servicemen_name;

alter table technical_passports drop COLUMN technical_status;

alter table technical_passports drop COLUMN work_prise;

alter table technical_passports drop COLUMN work_shop;

alter table technical_passports drop COLUMN warranty_work;

alter table bikes drop COLUMN technical_passport_id;

alter table technical_passports alter COLUMN  bike_id SET NOT NULL;

alter table technical_passports add warranty_work VARCHAR(255) NOT NULL;

alter table technical_passports alter COLUMN  warranty_work SET NOT NULL;