import * as env from "env-var";
export const envs = {
  API_URL: env.get("API_URL").required().asString(),
  AWS_REGION: env.get("AWS_REGION").required().asString(),
  AWS_ACCESS_KEY_ID: env.get("AWS_ACCESS_KEY_ID").required().asString(),
  AWS_SECRET_ACCESS_KEY: env.get("AWS_SECRET_ACCESS_KEY").required().asString(),
  AWS_BUCKET_NAME: env.get("AWS_BUCKET_NAME").asString(),
  MAILER_SECRET_KEY: env.get("MAILER_SECRET_KEY").required().asString(),
  MAILER_HOST: env.get("MAILER_HOST").required().asString(),
  MAILER_SECURITY: env.get("MAILER_SECURITY").required().asString(),
  MAILER_EMAIL: env.get("MAILER_EMAIL").required().asString(),
  MAILER_PASSWORD: env.get("MAILER_PASSWORD").required().asString(),
  MAILER_PORT: env.get("MAILER_PORT").required().asString(),
};