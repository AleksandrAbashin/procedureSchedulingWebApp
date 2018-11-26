create sequence hibernate_sequence start 1 increment 1;

    create table clients (
        id varchar(255) not null,
        email varchar(255),
        gender varchar(255),
        lastVisit timestamp,
        locale varchar(255),
        name varchar(255),
        userpic varchar(255),
        primary key (id)
    );


    create table doctors (
        doctorId int8 not null,
        doctor_name varchar(255),
        primary key (doctorId)
    );


    create table PatientInfo (
        id int8 not null,
        disease varchar(255),
        fileName varchar(255),
        phone int4 not null,
        primary key (id)
    );


    create table patients (
        id  bigserial not null,
        day_of_birth date,
        patient_name varchar(10) not null,
        patient_sex varchar(255),
        patientinfo_id int8,
        primary key (id)
    );


    create table rooms (
        roomId int8 not null,
        room_name varchar(255),
        primary key (roomId)
    );


    create table studies (
        id  bigserial not null,
        description varchar(255),
        estimated_end_time date,
        planned_start_time date not null,
        study_status varchar(255) not null,
        patient_id int8,
        primary key (id)
    );


    create table users (
        id  bigserial not null,
        name varchar(255),
        password varchar(255),
        primary key (id)
    );


    alter table patients
       add constraint patients_patientinfo_fk
       foreign key (patientinfo_id)
       references PatientInfo;


    alter table studies
       add constraint studies_patient_fk
       foreign key (patient_id)
       references patients;