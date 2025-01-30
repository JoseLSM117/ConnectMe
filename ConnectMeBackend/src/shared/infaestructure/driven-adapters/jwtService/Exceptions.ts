import { Exception } from "@src/shared/domain/exceptions/Exception";

export class CreateAccessTokenException extends Exception {
  constructor(error: string) {
    super(`Error creating access token ${error}`);
  }
}
export class CreateRefreshTokenException extends Exception {
  constructor(error: string) {
    super(`Error creating access token ${error}`);
  }
}
export class CreateTokenException extends Exception {
  constructor(error: string) {
    super(`Error creating access token ${error}`);
  }
}