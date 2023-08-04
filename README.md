# demo-audited-account-service

### basic audit table structure

##### actual table

create table public.accounts
(
    id               bigint default nextval('accounts_id_seq1'::regclass) not null primary key,
    created_at       timestamp(6)                                         not null,
    created_by       varchar(255),
    deleted_at       timestamp(6),
    updated_at       timestamp(6),
    last_modified_by varchar(255),
    account_type     varchar(255)                                         not null,
    ext_consumer_id  varchar(255)                                         not null,
    currency_iso     varchar(255)                                         not null,
    ext_id           varchar(255)
        unique,
    primary_account  boolean
);

##### audit table

create table public.accounts_aud
(
    id               bigint  not null,
    rev              integer not null
        constraint fkd4olimrdvgjr27d6gcjbqljic
            references public.revinfo,
    revtype          smallint,
    created_by       varchar(255),
    created_at       timestamp(6),
    deleted_at       timestamp(6),
    last_modified_by varchar(255),
    updated_at       timestamp(6),
    account_type     varchar(255),
    ext_consumer_id  varchar(255),
    currency_iso     varchar(255),
    ext_id           varchar(255),
    primary_account  boolean,
    primary key (rev, id)
);

