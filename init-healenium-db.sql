CREATE SCHEMA IF NOT EXISTS healenium;

GRANT ALL ON SCHEMA healenium TO healenium_user;

ALTER ROLE healenium_user SET search_path TO healenium;