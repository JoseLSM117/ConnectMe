import { Exception } from "@src/shared/domain/exceptions/Exception";

export class SendEmailException extends Exception {
  constructor() {
    super("An error occurred while sending the email");
  }
}
export class EmailRejectedException extends Exception {
  constructor() {
    super("An error occurred while sending the email");
  }
}