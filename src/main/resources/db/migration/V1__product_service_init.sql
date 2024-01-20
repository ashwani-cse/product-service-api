CREATE TABLE IF NOT EXISTS category
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    create_timestamp VARCHAR(255) NULL,
    update_timestamp VARCHAR(255) NULL,
    is_deleted       BIT(1) NULL,
    name             VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    create_timestamp VARCHAR(255) NULL,
    update_timestamp VARCHAR(255) NULL,
    is_deleted       BIT(1) NULL,
    title            VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    category_id      BIGINT NULL,
    `description`    VARCHAR(255) NULL,
    image_url        VARCHAR(255) NULL,
    product_uuid     VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);