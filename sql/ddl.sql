CREATE TABLE  Credential (
  id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "key" VARCHAR(100) NOT NULL UNIQUE,
  user_name VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  info LONG VARCHAR,
  created TIMESTAMP DEFAULT CURRENT TIMESTAMP
);

CREATE TABLE Tag (
  id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE,
  info LONG VARCHAR,
  created TIMESTAMP DEFAULT CURRENT TIMESTAMP
);

CREATE TABLE Credential_Tag (
  id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  credential_id BIGINT NOT NULL REFERENCES Credential(id),
  tag_id BIGINT NOT NULL REFERENCES Tag(id),
  created TIMESTAMP DEFAULT CURRENT TIMESTAMP,
  UNIQUE (credential_id, tag_id)
);