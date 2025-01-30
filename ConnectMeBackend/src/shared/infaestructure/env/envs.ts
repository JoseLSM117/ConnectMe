import * as dotenv from 'dotenv';
import * as env from "env-var";
dotenv.config({ path: '/ConnectMeBackend/.env' });
export const envs = {
  API_URL: env.get("BACKEND_API_URL").required().asString(),
  AWS_REGION: env.get("BACKEND_AWS_REGION").required().asString(),
  AWS_ACCESS_KEY_ID: env.get("BACKEND_AWS_ACCESS_KEY_ID").required().asString(),
  AWS_SECRET_ACCESS_KEY: env.get("BACKEND_AWS_SECRET_ACCESS_KEY").required().asString(),
  AWS_BUCKET_NAME: env.get("BACKEND_AWS_BUCKET_NAME").asString(),
  MAILER_SECRET_KEY: env.get("BACKEND_MAILER_SECRET_KEY").required().asString(),
  MAILER_HOST: env.get("BACKEND_MAILER_HOST").required().asString(),
  MAILER_SECURITY: env.get("BACKEND_MAILER_SECURITY").required().asString(),
  MAILER_EMAIL: env.get("BACKEND_MAILER_EMAIL").required().asString(),
  MAILER_PASSWORD: env.get("BACKEND_MAILER_PASSWORD").required().asString(),
  MAILER_PORT: env.get("BACKEND_MAILER_PORT").required().asString(),
  JWT_ACCESS_SECRET_KEY: env.get("JWT_ACCESS_SECRET_KEY").required().asString(),
  JWT_REFRESH_SECRET_KEY: env.get("JWT_REFRESH_SECRET_KEY").required().asString(),
  ACCESS_TOKEN_EXPIRES_IN: env.get("ACCESS_TOKEN_EXPIRES_IN").required().asString(),
  REFRESH_TOKEN_EXPIRES_IN: env.get("REFRESH_TOKEN_EXPIRES_IN").required().asString()
};