drop table if exists accounts CASCADE;
drop table if exists consumers CASCADE;
create sequence accounts_id_seq increment 3 start 1;
create sequence consumers_id_seq increment 3 start 1;
create table consumers
(
    id                      serial,
    created_at              timestamp NOT NULL,
    created_by              varchar(255),
    deleted_at              timestamp,
    updated_at              timestamp,
    last_modified_by        varchar(255),
    country_code            varchar(255) NOT NULL,
    ext_id                  varchar(255) unique,
    ext_group_id            varchar(255) NOT NULL,
    status                  varchar(32) NOT NULL,
    high_risk               bool NOT NULL DEFAULT true,
    metadata                text,
    primary key (id)
);
create table accounts
(
    id                  serial,
    created_at          timestamp NOT NULL,
    created_by          varchar(255),
    deleted_at          timestamp,
    updated_at          timestamp,
    last_modified_by    varchar(255),
    account_type        varchar(255) NOT NULL,
    ext_consumer_id     varchar(255) NOT NULL,
    currency_iso        varchar(255) NOT NULL,
    ext_id              varchar(255) unique,
    primary_account     bool NULL,
    primary key (id),
    CONSTRAINT fk_consumer
        FOREIGN KEY(ext_consumer_id)
            REFERENCES consumers(ext_id)
);